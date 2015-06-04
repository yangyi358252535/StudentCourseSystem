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
	private TTeacher teacher = new TTeacher();
	private TSpecialty specialty = new TSpecialty();
	// zhou ci
	private TMaster zhouci = new TMaster();
	// jie shu
	private TMaster jieshu = new TMaster();
	// jiaoshi
	private TMaster weizhi = new TMaster();
	private int allStuedentCount;
	private int currentStuedentCount;

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
	@JoinColumn(name = "teacher_id")
	public TTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(TTeacher teacher) {
		this.teacher = teacher;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "specialty_id")
	public TSpecialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(TSpecialty specialty) {
		this.specialty = specialty;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "zhouci_id")
	public TMaster getZhouci() {
		return zhouci;
	}

	public void setZhouci(TMaster zhouci) {
		this.zhouci = zhouci;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "jieshu_id")
	public TMaster getJieshu() {
		return jieshu;
	}

	public void setJieshu(TMaster jieshu) {
		this.jieshu = jieshu;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "weizhi_id")
	public TMaster getWeizhi() {
		return weizhi;
	}

	public void setWeizhi(TMaster weizhi) {
		this.weizhi = weizhi;
	}

	public int getAllStuedentCount() {
		return allStuedentCount;
	}

	public void setAllStuedentCount(int allStuedentCount) {
		this.allStuedentCount = allStuedentCount;
	}

	public int getCurrentStuedentCount() {
		return currentStuedentCount;
	}

	public void setCurrentStuedentCount(int currentStuedentCount) {
		this.currentStuedentCount = currentStuedentCount;
	}
}
