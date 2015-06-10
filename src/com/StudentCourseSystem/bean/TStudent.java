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
/*
 * 学生信息表
 */
@Entity
@Table(name="T_STUDENT")
public class TStudent {

	private long id;
	//学生编号
	private String num;
	//姓名
	private String name;
	//性别
	private String sex;
	//年龄
	private int age;
	//成绩记录
	private Set<TScore> scores=new HashSet<TScore>();
	//班级
	private TClass clasz=new TClass();
	//注册时间
	private String createDate;
	//联系电话
	private String tel;
	//密码
	private String password;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="clasz_id")
	public TClass getClasz() {
		return clasz;
	}
	public void setClasz(TClass clasz) {
		this.clasz = clasz;
	}
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="student_id")
	public Set<TScore> getScores() {
		return scores;
	}
	public void setScores(Set<TScore> scores) {
		this.scores = scores;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
