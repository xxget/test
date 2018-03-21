package com.lding.wiqs.modular.common.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.lding.wiqs.app.login.domain.NetAppUser;
import com.lding.wiqs.app.login.domain.StateCode;
import com.lding.wiqs.general.business.controller.BaseController;
import com.lding.wiqs.general.business.exception.BaseWebException;
import com.lding.wiqs.general.web.validate.AbstractValidateCode;
import com.lding.wiqs.general.web.validate.GifValidateCode;
import com.lding.wiqs.general.web.vo.TreeVo;
import com.lding.wiqs.general.web.vo.UserVo;
import com.lding.wiqs.modular.dict.domain.Dict;
import com.lding.wiqs.modular.dict.service.DictService;
import com.lding.wiqs.modular.imggroup.service.ImgGroupService;
import com.lding.wiqs.modular.org.domain.Org;
import com.lding.wiqs.modular.org.service.OrgService;
import com.lding.wiqs.modular.permission.domain.Permission;
import com.lding.wiqs.modular.permission.service.PermissionService;
import com.lding.wiqs.modular.pointinfo.domain.PointInfo;
import com.lding.wiqs.modular.pointinfo.service.PointInfoService;
import com.lding.wiqs.modular.userinfo.domain.UserInfo;
import com.lding.wiqs.modular.userinfo.service.UserInfoService;
import com.lding.wiqs.utils.Base64Util;
import com.lding.wiqs.utils.DataUtils;
import com.lding.wiqs.utils.MultipartFileUploadUtil;
import com.lding.wiqs.utils.NetOperation;
import com.lding.wiqs.utils.SessionUtils;

/**
 * 处理登陆页面的相关
 * 
 * @author xxg
 *
 */
@Controller
public class IndexController extends BaseController {

	DataUtils dataUtils = new DataUtils();

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private OrgService orgService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private DictService dictService;

	@Autowired
	private ImgGroupService imgGroupService;

	@Autowired
	private PointInfoService pointInfoService;

	private String mainpage = "mainpage";

	@RequestMapping(value = { "/index", "/", "/index.html", "/defualt.html" })
	public String index() {
		return "redirect:/mainpage.do";
	}

	/**
	 * 进入首页
	 * 
	 * @return
	 */
	@RequestMapping("/mainpage")
	public String mainpage() {
		Subject subject = SecurityUtils.getSubject();
		UserVo user = (UserVo) subject.getPrincipal();
		req.getSession().setAttribute(SessionUtils.USER, user);
		return mainpage;
	}

	@RequestMapping(value = "/login")
	public String loginAction(String username, String password) throws BaseWebException {
		// 取得 session 中的 code
		String exceptionClassName = (String) req.getAttribute(SessionUtils.SHIRO_LOGIN_FAILURE);
		String error = null;
		if (exceptionClassName != null) {
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				error = "用户名/密码错误";
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				error = "用户名/密码错误";
			} else if ("ValidateCodeExcption".equals(exceptionClassName)) {
				error = "验证码错误";
			} else if ("ExcessiveAttemptsException".equals(exceptionClassName)) {
				error = "登录失败多次，账户锁定";
			} else if (exceptionClassName != null) {
				error = "其他错误：" + exceptionClassName;
			}
			// logger.debug(exceptionClassName);
			// throw new BaseWebException(error);
		}
		logger.debug("进入登录页面...");
		return "/login";
	}

	/**
	 * 导航菜单
	 * 
	 * @return
	 */
	@RequestMapping("/readMenuTree")
	@ResponseBody
	public List<TreeVo> queryMenu() {
		Subject subject = SecurityUtils.getSubject();
		UserVo user = (UserVo) subject.getPrincipal();
		return user.getMenus();
	}

	/**
	 * 获取显示验证码的方法
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/validatecode", method = RequestMethod.GET)
	public void validateCode() throws IOException {
		try {
			// 设置响应头信息，告诉浏览器不要缓存此内容
			resp.setHeader("Pragma", "No-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
			// 设置响应类型为图片
			resp.setContentType("image/gif");

			AbstractValidateCode validateCode = new GifValidateCode(146, 33, 4);
			validateCode.out(resp.getOutputStream());
			req.getSession().setAttribute("validateCode", validateCode.getText().toLowerCase());
		} catch (Exception e) {
			logger.warn("验证码异常：" + e.getMessage());
		}
	}

	/**
	 * <p>
	 * Title: loginAction
	 * </p>
	 * <p>
	 * Description: app登录
	 * </p>
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws BaseWebException
	 */
	@RequestMapping("/applogin")
	@ResponseBody
	public NetAppUser loginAPP(String username, String password) throws BaseWebException {
		NetAppUser netAppUser = new NetAppUser();
		// 判断是否为空
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {// 参数不合法
			netAppUser.setStateCode(StateCode.INVALID_PARAMETERS);
			return netAppUser;
		}
		netAppUser = findUserByUserName(username);
		// 判断用户名是否错误
		if (netAppUser.getUserInfo() == null) {// 用户名不存在提示用户名或密码错误
			netAppUser.setStateCode(StateCode.USERNAME_AND_PASSWORD_NOT_EXIST);
			return netAppUser;
		}
		// 判断密码是否错误
		String pwd = new SimpleHash("md5", password, ByteSource.Util.bytes(netAppUser.getUserInfo().getSalt()), 2)
				.toHex();
		if (!netAppUser.getUserInfo().getPassword().equals(pwd)) {// 密码错误
			netAppUser.setStateCode(StateCode.USERNAME_AND_PASSWORD_NOT_EXIST);
			return netAppUser;
		}

		netAppUser.setStateCode(StateCode.OK);
		logger.debug("APP login..." + JSON.toJSONString(netAppUser));

		return netAppUser;
	}

	/**
	 * 根据登录名查询APP用户的详细信息及其相关联信息
	 */
	@RequestMapping("/findUserByUserName")
	public NetAppUser findUserByUserName(String username) {
		NetAppUser netAppUser = new NetAppUser();

		UserInfo userInfo = new UserInfo();
		userInfo = userInfoService.findUserByUsername(username);
		netAppUser.setUserInfo(userInfo);

		Org org = new Org();
		org = orgService.selectByKey(userInfo.getOrgId());
		netAppUser.setOrg(org);

		List<Permission> permissionList = permissionService.findPermissionListByUserId(userInfo.getUserId());
		netAppUser.setPermissionList(permissionList);

		return netAppUser;
	}

	/**
	 * <p>
	 * Title: telListInfo
	 * </p>
	 * <p>
	 * Description: 查询所有用户信息生成APP通讯录
	 * </p>
	 * 
	 * @return
	 */
	// @RequestMapping(value = "/telapplist", produces = "text/html;charset=UTF-8")
	@RequestMapping("/telapplist")
	@ResponseBody
	public String telListInfo() {
		logger.info("232");
		List<UserInfo> userInfoList = userInfoService.readAllRows();
		logger.info("234-userInfoList" + JSON.toJSONString(userInfoList));
		List<UserInfo> userList = new ArrayList();
		logger.info("236");
		UserInfo userInfo = new UserInfo();
		logger.info("238-userList" + JSON.toJSONString(userList));
		for (UserInfo u : userInfoList) {
			userInfo.setUserId(u.getUserId());
			userInfo.setIcon(u.getIcon());
			userInfo.setPhone(u.getPhone());
			userInfo.setRealName(u.getRealName());
			userList.add(userInfo);
		}
		logger.info("246 - " + JSON.toJSONString(userList));
		String userJson = JSON.toJSONString(userList);
		logger.info("APP login..." + userJson);
		return userJson;
	}

	/**
	 * <p>
	 * Title: appDict
	 * </p>
	 * <p>
	 * Description: 根据字典类型查询数据字典信息
	 * </p>
	 * 
	 * @param dictType
	 * @return
	 */
	// @RequestMapping(value = "/appdictlist", produces = "text/html;charset=UTF-8")
	@RequestMapping("/appdictlist")
	@ResponseBody
	public String appDict(String dictType) {
		if (StringUtils.isEmpty(dictType)) {
			return StateCode.INVALID_PARAMETERS;
		}
		String dictJson = JSON.toJSONString(dictService.readByType(dictType));
		return dictJson;
	}

	/**
	 * <p>
	 * Title: inputAppPointInfo
	 * </p>
	 * <p>
	 * Description: 接受APP提交的设施坐标
	 * </p>
	 * 
	 * @param pointType
	 *            设施点类型
	 * @param pointInfo
	 *            设置点信息
	 * @param lng
	 *            经度
	 * @param lat
	 *            纬度
	 * @param unitName
	 *            设施归属单位
	 * @param headName
	 *            附属单位负责人
	 * @param headPhone
	 *            负责人电话
	 * @param imgBase64
	 *            图片base64信息
	 * @param userId
	 *            用户编号
	 * @return
	 */
	// @RequestMapping(value = "/inputapppointinfo", produces =
	// "text/html;charset=UTF-8")
	@RequestMapping("/inputapppointinfo")
	//@RequestMapping(value = "/inputapppointinfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String inputAppPointInfo(String pointType, String pointName, String pointInfo, String lng, String lat,
			String unitName, String headName, String headPhone, String note, String userId, Integer hiddenImgSign,
			HttpServletRequest request) {
		logger.info("进入接受APP提交的设施坐标方法312");
		logger.info("###############################进来了" + hiddenImgSign);
		String hiddenImgUrls = "";
		if (hiddenImgSign > 0) {
			logger.info("进入循环319");
			// 转型为MultipartHttpRequest：
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			// 获取上传的文件列表
			Iterator fns = mRequest.getFileNames();
			logger.info("324获取上传的文件列表" + fns);
			while (fns.hasNext()) {
				String s = (String) fns.next();
				logger.info("327=========================" + s);
				String saveRelUrl = MultipartFileUploadUtil.saveImg(mRequest.getFile(s), true);
				if (hiddenImgUrls.equals("")) {
					hiddenImgUrls = saveRelUrl;
				} else {
					hiddenImgUrls += "あ" + saveRelUrl;
					// }
				}
			}
		}

		// 获取数据
		PointInfo p = new PointInfo();
		logger.info("进入315: " + pointType);
		p.setPointType(pointType);
		logger.info("进入317: " + pointInfo);
		p.setPointInfo(pointInfo);
		logger.info("进入319:  " + lng);
		// p.setLng(new BigDecimal(lng));
		logger.info("进入321: " + lat);
		// p.setLat(new BigDecimal(lat));
		logger.info("进入323: " + lng + "" + Base64Util.getBase64(lng));
		p.setLngs(Base64Util.getBase64(lng));
		logger.info("进入325: " + lat + "" + Base64Util.getBase64(lat));
		p.setLats(Base64Util.getBase64(lat));
		logger.info("进入327: " + unitName);
		p.setUnitName(unitName);
		logger.info("进入329: " + headName);
		p.setHeadName(headName);
		logger.info("进入331: " + headPhone);
		p.setHeadPhone(headPhone);
		logger.info("进入335: " + pointName);
		p.setPointName(pointName);
		logger.info("进入337: " + note);
		p.setNote(note);
		logger.info("进入339");
		p.setImgBase64(hiddenImgUrls);
		// 获取主键uuid
		p.setPointId(SessionUtils.getUUID());
		logger.info("进入342:  " + userId);
		p.setUserId(userId);
		p.setSaveTime(new Date().getTime() / 1000);
		logger.info("344初始化对象p获取对象的值 : " + JSON.toJSONString(p));

		int ret = 0;
		String msg = "";
		NetOperation netOperation = new NetOperation();
		try {
			ret = pointInfoService.insert(p);
			msg = StateCode.OK;
		} catch (Exception e) {
			// TODO: handle exception
			msg = StateCode.INTERNAL_SERVER_ERROR;
		}
		return msg;
	}

	/**
	 * <p>
	 * Title: getPointInfo
	 * </p>
	 * <p>
	 * Description: 根据设施点类型查询对应的设施点数据
	 * </p>
	 * 
	 * @param pointType
	 *            资源点类型（1:违建；2:凝水缸；3:阀门井；4:调压站；）
	 * @return
	 */
	// @RequestMapping(value = "/getpointinfo", produces =
	// "text/html;charset=UTF-8")
	@RequestMapping("/getpointinfo")
	@ResponseBody
	public String getPointInfo(String pointType) {

		Dict dict = new Dict();
		dict.setDictType("pointType");
		List<Dict> dictList = new ArrayList();
		dictList = dictService.readByEqualNotNull(dict);

		PointInfo pointInfo = new PointInfo();
		List<PointInfo> pointInfoList = new ArrayList();
		List<PointInfo> pList = new ArrayList();
		// 获取传入的设施点类型
		pointInfo.setPointType(pointType);

		pointInfoList = pointInfoService.readByEqualNotNull(pointInfo);
		for (PointInfo p : pointInfoList) {
			p.setLats(Base64Util.getFromBase64(p.getLats()));
			p.setLngs(Base64Util.getFromBase64(p.getLngs()));
			for (Dict d : dictList) {
				if (p.getPointType().equals(d.getDictDkey())) {
					p.setPointType(d.getDictValue());
				}
			}
			pList.add(p);
		}
		String pointJSON = JSON.toJSONString(pList);
		logger.info(pointJSON);
		return pointJSON;
	}
	
	/**
	 * <p>Title: appQueryPointInfo</p>  
	 * <p>Description: 根据设施编号查询设施信息</p>  
	 * @param pointType 设施编号
	 * @return
	 */
	@RequestMapping("/appquerypointinfo")
	@ResponseBody
	public String appQueryPointInfo(String pointId) {
		//获取类型为设施类型的数据字典list
		Dict dict = new Dict();
		dict.setDictType("pointType");
		List<Dict> dictList = new ArrayList();
		dictList = dictService.readByEqualNotNull(dict);
		
		//根据设施编号查询出设施信息
		PointInfo pointInfo = new PointInfo();
		pointInfo = pointInfoService.selectByKey(pointId);
		
		//用数据字典的名称替换掉设施信息中的key值
		for(Dict d : dictList) {
			if(pointInfo.getPointType().equals(d.getDictDkey())) {
				pointInfo.setPointType(d.getDictValue());
			}
		}
		
		//将查询结果转换为json返回给app
		String pointJSON = JSON.toJSONString(pointInfo);
		logger.info("根据设施编号：" + pointId + "查询设施信息的结果为： " + pointJSON);
		return pointJSON;
	}
}