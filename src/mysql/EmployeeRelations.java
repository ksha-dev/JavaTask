package mysql;

import exceptions.CustomException;
import utility.Utility;

public class EmployeeRelations {
	private int id;
	private String name;
	private int age;
	private String relationship;
	private int employeeID;

	public EmployeeRelations() {
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setName(String name) throws CustomException {
		Utility.validateObject(name);
		this.name = name;
	}

	public void setAge(int age) throws CustomException {
		if (age <= 0) {
			throw new CustomException("Age cannot be negative");
		}
		this.age = age;
	}

	public void setRelationship(String relationship) throws CustomException {
		Utility.validateObject(relationship);
		this.relationship = relationship;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getRelationship() {
		return relationship;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public String toString() {
		String displayString = "";
		if (id > 0) {
			displayString += "ER_ID : " + id + ", ";
		}
		displayString += "Name : " + name + ", Age : " + age + ", Relationship : " + relationship + ", Employee ID : "
				+ employeeID;
		return displayString;
	}
}