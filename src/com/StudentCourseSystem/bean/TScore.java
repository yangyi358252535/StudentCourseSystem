package com.StudentCourseSystem.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 学生成绩信息
 */
@Entity
@Table(name="T_SCORE")
public class TScore {

	private long id;
	//学科类型
	private TCourse type=new TCourse();
	//录入时间
	private String createDate;
	//学生姓名
	private String sudentName;
	//学生主键
	private long sudentId;
	//分值
	private float score;
	//年份
	private String year_str;
	//学期
	private TMaster term=new TMaster();
	//删除标记
	private int deleteflag;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="type_id")
	public TCourse getType() {
		return type;
	}
	public void setType(TCourse type) {
		this.type = type;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getDeleteflag() {
		return deleteflag;
	}
	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}
	public String getYear_str() {
		return year_str;
	}
	public void setYear_str(String year_str) {
		this.year_str = year_str;
	}
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="term_id")
	public TMaster getTerm() {
		return term;
	}
	public void setTerm(TMaster term) {
		this.term = term;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getSudentName() {
		return sudentName;
	}
	public void setSudentName(String sudentName) {
		this.sudentName = sudentName;
	}
	public long getSudentId() {
		return sudentId;
	}
	public void setSudentId(long sudentId) {
		this.sudentId = sudentId;
	}
}
