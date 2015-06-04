package com.StudentCourseSystem.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_STUDENT")
public class TStudent {

	private long id;
	private String num;
	private String name;
	private String sex;
	private int age;
	private String tel;
	private TClass class1 = new TClass();
	private Set<TCourse> scores = new HashSet<TCourse>();
	private String password;

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "class1_id")
	public TClass getClass1() {
		return class1;
	}

	public void setClass1(TClass class1) {
		this.class1 = class1;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "student_id")
	public Set<TCourse> getScores() {
		return scores;
	}

	public void setScores(Set<TCourse> scores) {
		this.scores = scores;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
