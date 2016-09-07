package com.rofine.gp.platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.rofine.gp.platform.exception.GpException;

public class DateUtil {

	private static Date sysDate = null;

	public static Date getSysDate() {
		if (sysDate == null) {
			sysDate = new Date();
		}
		return sysDate;
	}

	public static void setSysDate(Date sysDate1) {
		sysDate = sysDate1;
	}

	public static void clearSysDate() {
		sysDate = null;
	}

	public static int getSysYear() {
		Date sysDate = getSysDate();
		Calendar a = Calendar.getInstance();
		a.setTime(sysDate);
		return a.get(Calendar.YEAR);
	}

	public static Date createDate(String dateStr) throws GpException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new GpException(e);
		}
	}
}
