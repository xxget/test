package app;

import java.util.Calendar;
import java.util.Date;

import com.lding.wiqs.utils.DataUtils;

public class Time {
	public static void main(String[] args) {
		DataUtils dataUtils = new DataUtils();
		System.out.println(System.currentTimeMillis() / 1000);
        System.out.println(Calendar.getInstance().getTimeInMillis() / 1000);
        System.out.println(new Date().getTime() / 1000);
        System.out.println(dataUtils.getTimeStampLong());
	}
}
