package com.StudentCourseSystem.tool;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class PrimaryGenerater {
 
    private static final String SERIAL_NUMBER = "XXXX"; // 流水号格式
    private static PrimaryGenerater primaryGenerater = null;
 
    private PrimaryGenerater() {
    }
 
    /**
     * 取得PrimaryGenerater的单例实现
     * 
     * @return
     */
    public static PrimaryGenerater getInstance() {
        if (primaryGenerater == null) {
            synchronized (PrimaryGenerater.class) {
                if (primaryGenerater == null) {
                    primaryGenerater = new PrimaryGenerater();
                }
            }
        }
        return primaryGenerater;
    }
 
    /**
     * 生成下一个编号
     */
    public synchronized String generaterNextNumber(Long maxid,boolean userDate,String mark) {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        if (maxid==null||maxid == 0) {
            id = formatter.format(date) + "0001";
        } else {
            int count = SERIAL_NUMBER.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append("0");
            }
            DecimalFormat df = new DecimalFormat(sb.toString());
            if(userDate){
            	 id = formatter.format(date)
                         + df.format(1 + maxid);
            }else{
            	id = df.format(1 + maxid);
            }
        }
        return mark+id;
    }
    public static void main(String[] args) {
    	//System.out.println( PrimaryGenerater.getInstance().generaterNextNumber(1, "b"));
	}
}