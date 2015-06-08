package com.StudentCourseSystem.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_SPECIALTY")
public class TSpecialty {

	private long id;
	private String name;
	private String createDate;
	private String comment;
	private TInstitute institute=new TInstitute();
	private int deleteflag;
	private String showName;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "institute_id")
	public TInstitute getInstitute() {
		return institute;
	}

	public void setInstitute(TInstitute institute) {
		this.institute = institute;
	}

	public int getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}
	@Transient
	public String getShowName() {
		showName="";
		if(institute!=null){
			showName=institute.getName()+"--"+name;
		}
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}
}
