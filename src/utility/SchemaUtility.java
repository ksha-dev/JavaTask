package utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CustomException;
import mysql.Employee;
import mysql.EmployeeRelations;

public enum SchemaUtility {
	EMPLOYEE("Employee",
			"EMPLOYEE_ID int AUTO_INCREMENT, NAME VARCHAR(255), MOBILE long, EMAIL VARCHAR(255), "
					+ "DEPARTMENT VARCHAR(255), PRIMARY KEY (EMPLOYEE_ID)"),

	EMPLOYEE_RELATIONS("EmployeeRelations",
			"ER_ID int AUTO_INCREMENT, EMPLOYEE_ID int, NAME VARCHAR(255), AGE int, "
					+ "RELATIONSHIP VARCHAR(255), PRIMARY KEY (ER_ID), FOREIGN KEY (EMPLOYEE_ID) "
					+ "REFERENCEs Employee(EMPLOYEE_ID) ON DELETE CASCADE");

	private String columnQuerySnippet;
	private String tableName;

	private SchemaUtility(String tableName, String columnQuerySnippet) {
		this.tableName = tableName;
		this.columnQuerySnippet = columnQuerySnippet;
	}

	public String getQuerySnippet() {
		return this.columnQuerySnippet;
	}

	public String toString() {
		return this.tableName;
	}

	public Object convertResultSetToRecord(ResultSet resultSet) throws SQLException, CustomException {
		Utility.validateObject(resultSet);
		Object record = null;
		switch (this) {
		case EMPLOYEE: {
			Employee employee = new Employee(resultSet.getInt(1));
			employee.setName(resultSet.getString(2));
			employee.setMobile(resultSet.getLong(3));
			employee.setEmailAddress(resultSet.getString(4));
			employee.setDepartment(resultSet.getString(5));
			record = employee;
		}
			break;

		case EMPLOYEE_RELATIONS: {

			EmployeeRelations relation = new EmployeeRelations();
			relation.setID(resultSet.getInt(1));
			relation.setEmployeeID(resultSet.getInt(2));
			relation.setName(resultSet.getString(3));
			relation.setAge(resultSet.getInt(4));
			relation.setRelationship(resultSet.getString(5));
			record = relation;
		}
			break;
		}

		return record;
	}
}
