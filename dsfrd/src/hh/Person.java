package hh;

import java.io.Serializable;

public abstract class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2335014102768138620L;
	
	protected String name;
	protected int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
