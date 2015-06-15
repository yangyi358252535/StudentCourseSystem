package com.StudentCourseSystem.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_COURSE")
public class TCourse {

	private long id;
	private String name;
	private TSpecialty specialty = new TSpecialty();
	//学期
	private TMaster term=new TMaster();
	//学年
	private String year_str;
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
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="term_id")
	public TMaster getTerm() {
		return term;
	}
	public void setTerm(TMaster term) {
		this.term = term;
	}

	public String getYear_str() {
		return year_str;
	}

	public void setYear_str(String year_str) {
		this.year_str = year_str;
	}
}
