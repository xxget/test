package com.lding.wiqs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import sun.misc.BASE64Decoder;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;


public class MultipartFileUploadUtil {

	/**
	 * 保存文件,返回相对路径
	 * 
	 * @param multipartFile
	 * @return
	 */
	public static String saveFile(MultipartFile multipartFile) {
		String dateFolderName = getDateFolderName();
		String diskDirPath = getAvailableDiskDirectoryPath(dateFolderName);
		// 原始名称
		String originalFileName = multipartFile.getOriginalFilename();
		if (multipartFile == null || originalFileName == null || originalFileName.length() < 1) {
			return null;
		}
		String suffix = StringUtils.substringAfterLast(originalFileName, ".");
		String simpleOriFileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
		String fileMark = UUID.randomUUID().toString();
		// 新的图片名称
		// String newFileName = UUID.randomUUID() +
		// originalFileName.substring(originalFileName.lastIndexOf("."));
		String newFileName = simpleOriFileName + "-" + fileMark + "." + suffix;
		// 新文件对象
		File newFile = new File(diskDirPath, newFileName);
		// 将内存中的数据写入磁盘
		try {
			multipartFile.transferTo(newFile);
			return dateFolderName + "/" + newFileName;
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据当前日期获取日期文件夹名称
	 * 
	 * @return
	 */
	private static String getDateFolderName() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhh");
		return fmt.format(rightNow.getTime());
	}

	private static String getAvailableDiskDirectoryPath(String dateFolderName) {// D:\Medical\fileupload
		 String rootPath = getWebRootAbsPath();//
//		 System.getProperty("medical.webapp");
		 String diskDirPath = rootPath + "resources"+File.separator+"upload" +File.separator+dateFolderName;
//		 String diskDirPath = "D:\\apache-tomcat-8.0.43\\webapps\\aboutfile\\fileupload\\"+dateFolderName;
//		String diskDirPath = "D:\\Medical\\fileupload\\" + dateFolderName;
//		String diskDirPath = "C:\\file\\upload\\medical\\" + dateFolderName;
		File diskFolder = new File(diskDirPath);
		if (!diskFolder.exists()) {
			diskFolder.mkdirs();
		}
		return diskDirPath;
	}

	private static String getWebRootAbsPath() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = ClassLoader.getSystemClassLoader();
		}
		java.net.URL url = classLoader.getResource("");
		String ROOT_CLASS_PATH = url.getPath() + File.separator;
		// System.out.println(ROOT_CLASS_PATH);
		File rootFile = new File(ROOT_CLASS_PATH);
		String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + File.separator;
		// System.out.println(WEB_INFO_DIRECTORY_PATH);
		File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
		String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + File.separator;
		// System.out.println(SERVLET_CONTEXT_PATH);
		return SERVLET_CONTEXT_PATH;
	}

	/**
	 * 保存图片
	 * 
	 * @param multipartFile
	 * @return
	 */
	public static String saveImg(MultipartFile multipartFile) {
		return saveImg(multipartFile, false);
	}

	/**
	 * 保存图片
	 * 
	 * @param multipartFile
	 * @param isGenerateThumbnail
	 *            是否生成缩略图
	 * @return
	 */
	public static String saveImg(MultipartFile multipartFile, boolean isGenerateThumbnail) {
		String dateFolderName = getDateFolderName();
		String diskDirPath = getAvailableDiskDirectoryPath(dateFolderName);
		// 原始名称(带后缀)
		String originalFileName = multipartFile.getOriginalFilename();
		if (multipartFile == null || originalFileName == null || originalFileName.length() < 1) {
			return null;
		}
		String suffix = StringUtils.substringAfterLast(originalFileName, ".");
		String simpleOriFileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
		String fileMark = UUID.randomUUID().toString();
		// 新的图片名称
		// String newFileName = UUID.randomUUID() +
		// originalFileName.substring(originalFileName.lastIndexOf("."));
		String newFileName = simpleOriFileName + "-" + fileMark + "." + suffix;
		// 新图片
		File newFile = new File(diskDirPath, newFileName);
		// 将内存中的数据写入磁盘
		try {
			multipartFile.transferTo(newFile);
			// if (isGenerateThumbnail)
			// {
			// ImgHandleUtil.MakeThumbnail(filePath,dirPath+dateTime+"_Thumbnail.jpg",60,60,"HW");
			// }
			if (isGenerateThumbnail) {
				String thumbnailImgPath = diskDirPath + File.separator + simpleOriFileName + "-" + fileMark + "-thumbnail" + "."
						+ suffix;
				ImgHandleUtil.zoomImageScale(newFile, thumbnailImgPath, 80);
			}
			return dateFolderName + "/" + newFileName;
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对字节数组字符串进行Base64解码生成图片并保存图片
	 * 
	 * @param imgName
	 *            uuid图片名称
	 * @param suffixName
	 *            图片后缀名
	 * @param base64Str
	 *            base64串
	 * @param isGenerateThumbnail
	 *            是否生成缩略图
	 * @return
	 */
	public static String saveBase64GenerateImage(String base64Str, HttpServletRequest request) {
		
		// 获取文件后缀名
		String suffixName = StringUtils.substringBefore(base64Str, ";base64,");
		suffixName = StringUtils.substringAfterLast(suffixName, "data:image/");
		ArrayList<String> sList = new ArrayList<String>() {
			{
				add("bmp");
				add("gif");
				add("jpe");
				add("jpeg");
				add("png");
				add("jpg");
				add("ico");
			}
		};
		boolean isImg = true;
		for (String s : sList) {
			if (!suffixName.equals(s)) {
				isImg = false;
			}
		}
		if (isImg) {
			suffixName = "jpeg";
		}
		// 获取uuid生成的图片文件名称
		String imgName = UUID.randomUUID().toString();
		// 文件夹名称
		String dateFolderName = getDateFolderName();
		// 绝对路径名称
		String diskDirPath = getAvailableDiskDirectoryPath(dateFolderName);
		// 上传到服务器中的图片名称
		String newFileName = imgName + "." + suffixName;
		//String newFileName = imgName + ".png";
		BASE64Decoder decoder = new BASE64Decoder();
		//整理编译后的base64
		base64Str = base64Str.replace("data:image/jpeg;base64,", "");
		//三星手机剪裁后的图片格式为png
		base64Str = base64Str.replace("data:image/png;base64,", ""); 
		// 将Base64解码后的图片存入服务器
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(base64Str);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = diskDirPath + File.separator + newFileName;// 新生成的图片
			System.out.println("上传到服务器中的图片路径为 ： " + imgFilePath);
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return dateFolderName + File.separator + newFileName;
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
