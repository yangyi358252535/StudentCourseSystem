package com.StudentCourseSystem.tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import javax.imageio.ImageIO;

public final class SystemUtil {
	public static Map<Integer, String> master = new HashMap<Integer, String>();

	/**
	 * @CreateDate 2012-12-26 下午10:39:53
	 * @Description 对密码进行MD5加密
	 * @param @param password 要加密的密码
	 * @param @return
	 * @return String 已加密的密码
	 */
//	public static String putMD5Security(String password) {
//		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//		// false 表示：生成32位的Hex版, 这也是encodeHashAsBase64的, Acegi 默认配置; true
//		// 表示：生成24位的Base64版
//		md5.setEncodeHashAsBase64(false);
//		String pwd = md5.encodePassword(password, null);
//		return pwd;
//	}

	/**
	 * @CreateDate 2013-1-26 下午10:47:11
	 * @Description 根据格式取得系统当前的时间
	 * @param @param formate 1.年月日 yyyy-MM-dd 2.年月日时分 yyyy-MM-dd HH:mm 3.年月日时分秒
	 *        yyyy-MM-dd HH:mm:ss
	 * @param @return
	 * @return String 系统时间的字符串形式
	 */
	public static String getSystemDateTime(String formate) {
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		Date date = new Date();
		return sdf.format(date);
	}

	/**
	 * 计算两个日期间的天数
	 * 
	 * @param fromDate
	 *            起始日期
	 * @param toDate
	 *            结束日期
	 * @return
	 * @throws ParseException
	 */
	public static int dateDiff(String fromDate, String toDate, String format)
			throws ParseException {
		int days = 0;
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date from = df.parse(fromDate);
		Date to = df.parse(toDate);
		days = (int) Math.abs((to.getTime() - from.getTime())
				/ (24 * 60 * 60 * 1000)) + 1;
		if (days < 0) {
			days = -days;
		}
		return days;
	}

	public static int compare_date(String DATE1, String DATE2, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static Calendar getDateOfLastMonth(Calendar date,int monthLater) {
		Calendar lastDate = (Calendar) date.clone();
		lastDate.add(Calendar.MONTH, monthLater);
		return lastDate;
	}

	public static String getDateOfLastMonth(String dateStr,String format,int monthLater) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(dateStr);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			Calendar d=getDateOfLastMonth(c,monthLater);
			String s = sdf.format(d.getTime());  
			return s;
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"Invalid date format("+format+"): " + dateStr);
		}
	}
	/**
	 * @CreateDate 2013-2-6 下午7:22:10
	 * @Description For Example 将 String idString="22,44,66,43,99"; 转换成
	 *              List<Integer> array={22,44,66,43,99};
	 * @param @param idString
	 * @param @return
	 * @return int[]
	 */
	public static List<Integer> caseStringToIntegerArray(String idString) {
		idString = clearFirstAndEndComma(idString);
		String[] ids = idString.split(",");
		List<Integer> temp = new ArrayList<Integer>();
		for (String id : ids) {
			temp.add(Integer.parseInt(id));
		}
		return temp;
	}
	/**
	 * @CreateDate 2013-2-6 下午7:22:10
	 * @Description For Example 将 String idString="22,44,66,43,99"; 转换成
	 *              List<Long> array={22,44,66,43,99};
	 * @param @param idString
	 * @param @return
	 * @return int[]
	 */
	public static List<Long> caseStringToLongArray(String idString) {
		idString = clearFirstAndEndComma(idString);
		String[] ids = idString.split(",");
		List<Long> temp = new ArrayList<Long>();
		for (String id : ids) {
			if(id==""||id=="0"){
				temp.add(0L);
			}else{
				temp.add(Long.parseLong(id));
			}
		}
		return temp;
	}
	/**
	 * @CreateDate 2013-2-11 下午5:59:49
	 * @Description 去掉ID字符串中首字符的“,” 和尾字符“,” for example
	 *              ,88,4,22,55,6,1,7,-》》88,4,22,55,6,1,7
	 * @param @param idString
	 * @param @return
	 * @return String
	 */
	public static String clearFirstAndEndComma(String idString) {
		int index = 0;
		if (idString == null|| "null".equals(idString) || "".equals(idString)) {
			return "";
		}
		if (idString.startsWith(",")) {
			index = idString.length();
			idString = idString.substring(1, index);
		}
		if (idString.endsWith(",")) {
			index = idString.length();
			idString = idString.substring(0, index - 1);
		}
		return idString;
	}

	public static String caseIntegerArrayToString(List<Integer> ids) {
		if (ids == null) {
			return null;
		}
		StringBuffer idSb = new StringBuffer(ids.toString());
		idSb.deleteCharAt(0);
		idSb.deleteCharAt(ids.toString().length() - 2);
		return idSb.toString();
	}

	public static String getUserNo(String mark) {
		Long time = System.currentTimeMillis();
		String t = mark + time.toString().substring(2, 12);
		return t;
	}

	public static String getMasterName(int id) {
		if (master.size() == 0) {
			master.put(7, "普通");
			master.put(8, "vip_Lv1");
			master.put(9, "vip_Lv2");
			master.put(10, "vip_Lv3");
		}
		return master.get(id);
	}
	public static String getId(){//静态方法，获得id
		Date d=new Date();
		//对日期进行格式化,四位年两位月两位天两位时两位分两位秒三位毫秒
		SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fd=f.format(d);
		return fd;
	}
	/***
	 * 功能 :调整图片大小 开发：wuyechun 2011-7-22
	 * 
	 * @param srcImgPath
	 *            原图片路径
	 * @param distImgPath
	 *            转换大小后图片路径
	 * @param width
	 *            转换后图片宽度
	 * @param height
	 *            转换后图片高度
	 */
	public static void resizeImage(String srcImgPath, String distImgPath,
			int width, int height) throws IOException {
		File srcFile = new File(srcImgPath);
		Image srcImg = ImageIO.read(srcFile);
		BufferedImage buffImg = null;
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		buffImg.getGraphics().drawImage(
				srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
				0, null);
		File distImg = new File(distImgPath);
		if (!distImg.getParentFile().exists()) {
			distImg.getParentFile().mkdirs();
		}
		ImageIO.write(buffImg, "JPG", distImg);

	}
}
