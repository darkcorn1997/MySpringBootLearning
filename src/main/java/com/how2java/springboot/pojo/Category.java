package com.how2java.springboot.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //表示这是个实体类
@Table(name = "category_") //表示这个类对应的表名是 category_
public class Category {

    @Id //表明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //表明自增长方式
    @Column(name = "id") //表明对应的数据库字段名
	private int id;
    
    @Column(name = "name")
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
}
