package com.lding.wiqs.utils;

import java.util.HashMap;

public class IDCardUtils {
    private static String _codeError;  
    
    static final int[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};  
    static final int[] vi = {1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2};  
    private static int[] ai = new int[18];  
    private static String[] _areaCode={"11","12","13","14","15","21","22"  
        ,"23","31","32","33","34","35","36","37","41","42","43","44"  
        ,"45","46","50","51","52","53","54","61","62","63","64","65","71","81","82","91"};  
    private static HashMap<String,Integer> dateMap;  
    private static HashMap<String,String> areaCodeMap;  
    static{  
          dateMap=new HashMap<String,Integer>();  
          dateMap.put("01",31);  
          dateMap.put("02",null);  
          dateMap.put("03",31);  
          dateMap.put("04",30);  
          dateMap.put("05",31);  
          dateMap.put("06",30);  
          dateMap.put("07",31);  
          dateMap.put("08",31);  
          dateMap.put("09",30);  
          dateMap.put("10",31);  
          dateMap.put("11",30);  
          dateMap.put("12",31);  
          areaCodeMap=new HashMap<String,String>();  
          for(String code:_areaCode){  
                areaCodeMap.put(code,null);  
          }  
    }  

    //验证身份证位数,15位和18位身份证  
    public static boolean verifyLength(String code){  
          if(code.length()==18){  
                return true;  
          }else{  
                _codeError="错误：输入的身份证号不是18位的";  
                return false;  
          }  
    }  

    //判断地区码  
    public static boolean verifyAreaCode(String code){  
          String areaCode=code.substring(0,2);  
          if(areaCodeMap.containsKey(areaCode)){  
                return true;  
          }else{  
                _codeError="错误：输入的身份证号的地区码(1-2位)["+areaCode+"]不符合中国行政区划分代码规定(GB/T2260-1999)";  
                return false;  
          }  
    }  

    //生日
    public static boolean verifyBirthdayCode(String code){  
          String birthday=code.substring(6,14);  
          return DataUtils.isValidDate(birthday); 
    }  

    //验证身份除了最后位其他的是否包含字母  
    public static boolean containsAllNumber(String code) {  
          char[] ch = code.toCharArray();  
          for (int i = 0; i < 17; i++) {  
                if (! (ch[i] >= '0' && ch[i] <= '9')) {  
                      return false;  
                }  
          }  
          return true;  
    }  

    public String getCodeError(){  
          return _codeError;  
    }  

    //验证身份证  
    public static boolean verify(String idcard) {  
          if(!verifyLength(idcard)){  
              return false;  
          }  
          //验证身份除了最后位其他的是否包含字母  
          if(!containsAllNumber(idcard)){  
                return false;  
          }  

          //验证身份证的地区码  
          if(!verifyAreaCode(idcard)){  
                return false;  
          }  
          //判断月份和日期  
          if(!verifyBirthdayCode(idcard)){  
                return false;  
          }  
          //验证18位校验码,校验码采用ISO 7064：1983，MOD 11-2 校验码系统  
          if(!verifyMOD(idcard)){  
                return false;  
          }  
          return true;  
    }  

    //验证18位校验码,校验码采用ISO 7064：1983，MOD 11-2 校验码系统  
    public static boolean verifyMOD(String code){  
          String verify = code.substring(17, 18);  
          if("x".equals(verify)){  
                code=code.replaceAll("x","X");  
                verify="X";  
          }  
          String verifyIndex=getVerify(code);  
          if (verify.equals(verifyIndex)) {  
                return true;  
          }  
          return false;  
    }  

    //获得校验位  
	public static String getVerify(String eightcardid) {
		int remaining = 0;
		eightcardid = eightcardid.substring(0, 17);

		int sum = 0;
		for (int i = 0; i < 17; i++) {
			String k = eightcardid.substring(i, i + 1);
			ai[i] = Integer.parseInt(k);
		}

		for (int i = 0; i < 17; i++) {
			sum = sum + wi[i] * ai[i];
		}
		remaining = sum % 11;

		return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
	}
	
	public static String getCnSex(String idcard) {
		String sex = getSex(idcard);
		if(sex.equals("F")) return "男";
		else if(sex.equals("M")) return "女";
		else return sex;
	}
	
	/**
	 * 根据身份证获取性别
	 * @param idcard
	 * @return
	 */
	public static String getSex(String idcard) {
		if(!verify(idcard))
			return null;
		int sexValue = Integer.parseInt(idcard.substring(16,17));
		if(sexValue==1||sexValue==3||sexValue==5||sexValue==7||sexValue==9) return "F";
		else return "M";
	}

	// 15位转18位身份证
	public static String uptoeighteen(String fifteencardid) {
		if (fifteencardid.length() == 15) {
			String eightcardid = fifteencardid.substring(0, 6);
			eightcardid = eightcardid + "19";
			eightcardid = eightcardid + fifteencardid.substring(6, 15);
			eightcardid = eightcardid + getVerify(eightcardid);
			return eightcardid;
		} else
			return null;
	}
}
