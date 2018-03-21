package com.lding.wiqs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Title: DataUtils
 * </p>
 * <p>
 * Description: 日期处理公共类
 * </p>
 * 
 * @author xixuguang
 * @date 2018年3月8日 下午4:35:24
 *
 */
public class DataUtils {
	private static SimpleDateFormat dateFormat1 = null;
	private static SimpleDateFormat dateFormat2 = null;
	static {
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat2 = new SimpleDateFormat("yyyyMMdd");
		// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		dateFormat1.setLenient(false);
		dateFormat2.setLenient(false);
	}

	/**
	 * 校验日期合法性
	 * 
	 * @param dateString
	 *            支持 YYYY-MM-DD YYYY/MM/DD YYYYMMDD 格式
	 * @return
	 */
	public static boolean isValidDate(String dateString) {
		try {
			if (dateString.length() == 8)
				dateFormat2.parse(dateString);
			else if (dateString.length() == 10) {
				if (dateString.indexOf('/') > 0)
					dateString = dateString.replace('/', '-');
				dateFormat1.parse(dateString);
			} else
				return false;
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	/**
	 * 
	 * <p>Title: getTimeStamp</p>  
	 * <p>Description: 获取当前时间的时间戳并转换为string类型</p>  
	 * @return
	 */
	public String getTimeStampString() {
		String timeStamp = String.valueOf(new Date().getTime());
		return timeStamp;
	}
	
	/**
	 * <p>Title: getTimeStampLong</p>  
	 * <p>Description: 获取当前时间的时间戳并转换为long类型</p>  
	 * @return
	 */
	public long getTimeStampLong() {
		return new Date().getTime();
	}
	
	/**
	 * <p>Title: getDate</p>  
	 * <p>Description: 获取date类型的时间，不建议用【Thu Mar 08 16:44:56 CST 2018】</p>  
	 * @return
	 */
	public Date getDate() {
		return new Date();
	}

	public static void main(String[] args) {
		System.out.println(isValidDate("2016-12-32"));
		System.out.println(isValidDate("2016-12-31"));
		System.out.println(isValidDate("2016/12/30"));
		System.out.println(isValidDate("20160228"));
		DataUtils dataUtils = new DataUtils();
		System.out.println(dataUtils.getDate());
		System.out.println(dataUtils.getTimeStampString());
		System.out.println(dataUtils.getTimeStampLong());
	}
}
