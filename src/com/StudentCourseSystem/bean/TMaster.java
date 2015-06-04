package com.StudentCourseSystem.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 权限信息
 */
@Entity
@Table(name="T_MASTER")
public class TMaster implements Serializable {
	private static final long serialVersionUID = 4772642745684676200L;
	private long masterid;
	private String code;
	private String mastername;
	public TMaster(){
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getMasterid() {
		return masterid;
	}
	public void setMasterid(long masterid) {
		this.masterid = masterid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	
}
