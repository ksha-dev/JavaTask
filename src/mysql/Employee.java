package mysql;

import exceptions.CustomException;
import utility.Utility;

public class Employee {
	private int id;
	private String name;
	private long mobile;
	private String email;
	private String department;

	public Employee() {
	}

	public Employee(int id) {
		this.id = id;
	}

	public void setName(String name) throws CustomException {
		Utility.validateObject(name);
		this.name = name;
	}

	public void setMobile(long mobile) throws CustomException {
		Utility.validateMobileNumber(mobile);
		this.mobile = mobile;
	}

	public void setEmailAddress(String emailAddress) throws CustomException {
		Utility.validateEmailAddress(emailAddress);
		this.email = emailAddress;
	}

	public void setDepartment(String department) throws CustomException {
		Utility.validateObject(department);
		this.department = department;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public long getMobile() {
		return this.mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public String getDepartment() {
		return this.department;
	}

	@Override
	public String toString() {
		return ((id > 0) ? "ID : " + id + ", " : "") + "Name : " + name + ", Mobile : " + mobile + ", Email : " + email
				+ ", Department : " + department;
	}
}
