package com.StudentCourseSystem.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * LeftMenu
 */
@Entity
@Table(name="T_AUTHANDSOURCEINFO")
public class TAuthAndSourceInfo implements Serializable{

	private static final long serialVersionUID = 5404553672096581363L;
	private long authandsourceinfoid;
	private int authid;
	//菜单栏父标题名
	private String menuTitle_Name;
	//菜单栏子标题名
	private String authName_Chinese;
	//标题的路径URL
	private String source_Url;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getAuthandsourceinfoid() {
		return authandsourceinfoid;
	}
	public void setAuthandsourceinfoid(long authandsourceinfoid) {
		this.authandsourceinfoid = authandsourceinfoid;
	}
	public String getAuthName_Chinese() {
		return authName_Chinese;
	}
	public void setAuthName_Chinese(String authName_Chinese) {
		this.authName_Chinese = authName_Chinese;
	}
	public String getSource_Url() {
		return source_Url;
	}
	public void setSource_Url(String source_Url) {
		this.source_Url = source_Url;
	}
	public int getAuthid() {
		return authid;
	}
	public void setAuthid(int authid) {
		this.authid = authid;
	}
	public String getMenuTitle_Name() {
		return menuTitle_Name;
	}
	public void setMenuTitle_Name(String menuTitle_Name) {
		this.menuTitle_Name = menuTitle_Name;
	}
}
