package com.sanss.log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;

public class TestLog {

	private static Logger log = Logger.getLogger(TestLog.class);
	public static void main(String[] args) {
		/*log.info("11111111111111");
		log.info("11111111111111");
		LogLog.error("fdasfsd");
		
		Calendar c = Calendar.getInstance();
		String strMini = String.valueOf(c.get(Calendar.MINUTE));
		log.info("Here is info message This is webserver 1111111111111111111111111111111111111111111111111111, hahah 2015:"
				+ strMini + ":02:29:10082:foo");*/
		
		String s [] = new String[]{"session", "event", "activity"};
		Random r = new Random();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		log.info("22222222222222222, 10000:"
				+ s[r.nextInt(s.length)] + ":" + sdf.format(date));
	}
}
