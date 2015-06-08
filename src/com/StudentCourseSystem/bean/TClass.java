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
@Table(name = "T_CLAZZ")
public class TClass {

	private long id;
	private String name;
	private TSpecialty specialty = new TSpecialty();
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

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "specialty_id")
	public TSpecialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(TSpecialty specialty) {
		this.specialty = specialty;
	}
	@Transient
	public String getShowName() {
		showName="";
		if(specialty!=null){
			showName=specialty.getInstitute().getName()+"--"+specialty.getName()+"--"+name;
		}
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}
	
}