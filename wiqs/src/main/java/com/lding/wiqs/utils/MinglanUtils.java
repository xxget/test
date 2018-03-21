package com.lding.wiqs.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 通用工具系列
 * 1.汉字转换拼音
 * @author yangling
 *
 */
public class MinglanUtils {
	
	public static final String md5 = "MD5";
	public static final String md2 = "MD2";
	public static final String sha1 = "SHA-1";
	public static final String sha256 = "SHA-256";
	public static final String sha384 = "SHA-384";
	public static final String sha512 = "SHA-512";
	public static int[] weight={7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};    //十七位数字本体码权重
	public static char[] validate={ '1','0','X','9','8','7','6','5','4','3','2'};    //mod11,对应校验码字符值
	
	/**
	 * 汉字转换全拼，全小写
	 * @param src
	 * @return
	 */
    public static String getPinyin(String src) {
        char[] caSrc = src.toCharArray();
        HanyuPinyinOutputFormat pFormat = new HanyuPinyinOutputFormat();
        pFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        pFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        String outString = "";
        int srcLen = caSrc.length;
        try {
            for (int i = 0; i < srcLen; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(caSrc[i]).matches("[\\u4E00-\\u9FA5]+")) {
                	String[] singlePinyin = PinyinHelper.toHanyuPinyinStringArray(caSrc[i], pFormat);
                    outString += singlePinyin[0];
                } else {
                	outString += java.lang.Character.toString(caSrc[i]);
                }
            }
            return outString;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return outString;
    }

    /**
     * 汉字转换拼音首字母
     * @param str
     * @return 拼音首字母
     */
    public static String getPinyinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }     
        }
        convert=convert.toUpperCase();
        return convert;
    }
    
    /**
     * 汉字转换ASCII码
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }
    
    public static String hashEncrypte(String plainText,String algorithm) {
    	MessageDigest md = null;
    	try {
			md=MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
    	md.update(plainText.getBytes());
    	byte[] b = md.digest();
    	StringBuilder outString = new StringBuilder();
    	for(int i=0;i<b.length;i++) {
    		String temp = Integer.toHexString(b[i] & 0xff);
    		if(temp.length()<2) outString.append("0");
    		outString.append(temp);
    	}
    	return outString.toString();
    }
    
    public static String md5(String srcString) {
    	return hashEncrypte(srcString, md5);
    }

    public static void main(String[] args) {

        String cnStr = "窗前明月光，意识地上霜";
        System.out.println(getPinyin(cnStr));
        System.out.println(getPinyinHeadChar(cnStr));
        System.out.println(getCnASCII(cnStr));
        System.out.println(md5(cnStr));
        System.out.println(hashEncrypte(cnStr, md5));
        System.out.println(IDCardUtils.verify("142431197807300022"));
        System.out.println(IDCardUtils.verify("14273119780730002X"));
    }
}


