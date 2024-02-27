package task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Logger;

import constants.ExceptionMessage;
import exceptions.CustomException;
import mysql.Employee;
import mysql.EmployeeRelations;
import runner.SQLRunner;
import utility.SchemaUtility;
import utility.Utility;

public class SQLTask {

	private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String URL_PREFIX = "jdbc:mysql://";

	public static final int MAX_RECORD_LIMIT = 100;

	private Connection serverConnection = null;
	private Logger logger = SQLRunner.logger;
	public String selectedDB = null;

	public void establishConnection(String url, String username, String password) throws CustomException {
		try {
			Class.forName(DRIVER_CLASS_NAME);
			serverConnection = DriverManager.getConnection(URL_PREFIX.concat(url), username, password);
			logger.info("Connection Established : " + url);
		} catch (ClassNotFoundException | SQLException e) {
			throw new CustomException(e.getMessage());
		}
	}

	public void ensureConnectionAvailable() throws CustomException {
		Utility.validateObject(serverConnection, ExceptionMessage.CONNECTION_NOT_ESTABLISHED);
	}

	public void closeConnection() throws CustomException {
		if (!Utility.isNull(serverConnection)) {
			try {
				serverConnection.close();
				serverConnection = null;
			} catch (SQLException e) {
				throw new CustomException(e.getMessage());
			}
		}
	}

	public int queryWithoutResultSet(String query) throws CustomException {
		int responseInteger = -1;
		ensureConnectionAvailable();
		Utility.validateObject(query);

		try (Statement queryStatement = serverConnection.createStatement()) {
			responseInteger = queryStatement.executeUpdate(query);
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
		return responseInteger;
	}

	public void createAndSelectDatabase(String databaseName) throws CustomException {
		Utility.validateObject(databaseName);
		String showDatabasesQuery = "SHOW DATABASES;";
		boolean isDBPresent = false;
		try (Statement statement = serverConnection.createStatement();
				ResultSet resultSet = statement.executeQuery(showDatabasesQuery)) {
			while (resultSet.next()) {
				if (resultSet.getString(1).equals(databaseName)) {
					isDBPresent = true;
					logger.info(databaseName + " already exists");
					break;
				}
			}
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}

		// creating database if absent
		if (!isDBPresent) {
			String createDatabaseQuery = "CREATE DATABASE " + databaseName + ";";
			int response = queryWithoutResultSet(createDatabaseQuery);
			if (response >= 0) {
				logger.info(databaseName + " database created");
			}
		}

		// Using Database
		String useDatabaseQuery = "USE " + databaseName + ";";
		int responseInteger = queryWithoutResultSet(useDatabaseQuery);
		if (responseInteger >= 0) {
			selectedDB = databaseName;
			logger.info(databaseName + " database Selected");
		}
	}

	public void createTable(SchemaUtility schema) throws CustomException {
		String showTablesQuery = "SHOW TABLES;";
		boolean isTablePresent = false;
		try (Statement statement = serverConnection.createStatement();
				ResultSet resultSet = statement.executeQuery(showTablesQuery)) {
			while (resultSet.next()) {
				if (resultSet.getString(1).equals(schema.toString())) {
					logger.info(schema + " table already exists");
					break;
				}
			}
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}

		if (!isTablePresent) {
			String createEmployeeTableQuery = "CREATE TABLE " + schema + " ( " + schema.getQuerySnippet() + ");";
			int response = queryWithoutResultSet(createEmployeeTableQuery);
			if (response >= 0) {
				logger.info(schema + " table Created");
			}
		}
	}

	public void addEmployees(List<Employee> employees) throws CustomException {
		ensureConnectionAvailable();
		Utility.validateCollection(employees);

		for (Employee employee : employees)
			try {
				String addEmployeeQuery = "INSERT INTO Employee(NAME, MOBILE, EMAIL, DEPARTMENT) VALUE(?,?,?,?);";
				PreparedStatement prepareStatement = serverConnection.prepareStatement(addEmployeeQuery);
				prepareStatement.setString(1, employee.getName());
				prepareStatement.setLong(2, employee.getMobile());
				prepareStatement.setString(3, employee.getEmail());
				prepareStatement.setString(4, employee.getDepartment());
				int response = prepareStatement.executeUpdate();
				if (response > 0) {
					logger.info("Added Employee : " + employee);
				}
				prepareStatement.close();
			} catch (SQLException e) {
				throw new CustomException(e.getMessage());
			}
	}

	public boolean deleteEmployee(int employeeID) throws CustomException {
		if (isEmployeeIDExists(employeeID)) {
			String deleteEmployeeQuery = "DELETE FROM Employee WHERE EMPLOYEE_ID=" + employeeID + ";";
			try (Statement statement = serverConnection.createStatement()) {
				return statement.executeUpdate(deleteEmployeeQuery) == 1;
			} catch (SQLException e) {
				throw new CustomException(e.getMessage());
			}
		}
		return false;
	}

	public boolean isEmployeeIDExists(int employeeID) throws CustomException {
		ensureConnectionAvailable();
		Utility.validateRange(0, employeeID);
		String checkEmployeeExistsQuery = "SELECT EMPLOYEE_ID FROM Employee WHERE EMPLOYEE_ID =" + employeeID + ";";
		try (Statement statement = serverConnection.createStatement();
				ResultSet resultSet = statement.executeQuery(checkEmployeeExistsQuery)) {
			return resultSet.next();
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
	}

	public List<Object> getSortedRecordsFromTable(SchemaUtility schema, int limit, String sortingColumnName,
			boolean isDescendingOrder) throws CustomException {
		ensureConnectionAvailable();
		Utility.validateObject(schema);
		Utility.validateObject(sortingColumnName);
		if (limit < 1) {
			throw new CustomException(ExceptionMessage.REQUIRES_NATURAL_NUMBER);
		}
		String getMatchingEmployeesQuery = "SELECT * FROM " + schema + " ORDER BY " + sortingColumnName;
		if (isDescendingOrder) {
			getMatchingEmployeesQuery += "DESC ";
		}
		getMatchingEmployeesQuery += " LIMIT " + limit + ";";
		try (Statement statement = serverConnection.createStatement()) {
			List<Object> recordsList = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery(getMatchingEmployeesQuery);
			while (resultSet.next()) {
				recordsList.add(schema.convertResultSetToRecord(resultSet));
			}
			return recordsList;
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
	}

	public List<Object> getSortedRecordsFromTable(SchemaUtility schema, String sortingColumn, boolean isDescending)
			throws CustomException {
		return getSortedRecordsFromTable(schema, MAX_RECORD_LIMIT, sortingColumn, false);
	}

	public List<Object> getRecordsFromTable(SchemaUtility schema, int limit) throws CustomException {
		ensureConnectionAvailable();
		Utility.validateObject(schema);
		if (limit < 1) {
			throw new CustomException(ExceptionMessage.REQUIRES_NATURAL_NUMBER);
		}
		String getMatchingEmployeesQuery = "SELECT * FROM " + schema + ";";
		try (Statement statement = serverConnection.createStatement()) {
			List<Object> recordsList = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery(getMatchingEmployeesQuery);
			while (resultSet.next()) {
				recordsList.add(schema.convertResultSetToRecord(resultSet));
			}
			return recordsList;
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
	}

	public List<Object> getRecordsFromTable(SchemaUtility schema) throws CustomException {
		return getRecordsFromTable(schema, MAX_RECORD_LIMIT);
	}

	public List<Object> getMatchingRecordDetails(SchemaUtility schema, String columnNameToIdentify,
			Object valueToSearch) throws CustomException {
		ensureConnectionAvailable();
		Utility.validateObject(columnNameToIdentify);
		Utility.validateObject(valueToSearch);
		String getMatchingEmployeesQuery = "SELECT * FROM " + schema + " WHERE " + columnNameToIdentify + " = ?;";
		try (PreparedStatement statement = serverConnection.prepareStatement(getMatchingEmployeesQuery)) {
			statement.setObject(1, valueToSearch);
			List<Object> recordsList = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				recordsList.add(schema.convertResultSetToRecord(resultSet));
			}
			return recordsList;
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
	}

	public List<Object> getMatchingRecordDetailsAndSort(SchemaUtility schema, String columnNameToIdentify,
			Object valueToSearch, String sortingColumnName, boolean isDescending) throws CustomException {
		ensureConnectionAvailable();
		Utility.validateObject(columnNameToIdentify);
		Utility.validateObject(valueToSearch);
		Utility.validateObject(sortingColumnName);

		String getMatchingEmployeesQuery = "SELECT * FROM " + schema + " WHERE " + columnNameToIdentify + " = ? "
				+ "ORDER BY " + sortingColumnName;
		if (isDescending) {
			getMatchingEmployeesQuery += " DESC";
		}
		getMatchingEmployeesQuery += ";";
		try (PreparedStatement statement = serverConnection.prepareStatement(getMatchingEmployeesQuery)) {
			statement.setObject(1, valueToSearch);
			List<Object> recordsList = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				recordsList.add(schema.convertResultSetToRecord(resultSet));
			}
			return recordsList;
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
	}

	public int updateEmployeeDetails(String columnNameOfModifier, Object modifyingObject, String columnNameOfSearcher,
			Object searchingObject) throws CustomException {
		ensureConnectionAvailable();
		Utility.validateObject(columnNameOfModifier);
		Utility.validateObject(modifyingObject);
		Utility.validateObject(columnNameOfSearcher);
		Utility.validateObject(searchingObject);

		String updateEmployeeDetailsQuery = "UPDATE Employee SET " + columnNameOfModifier + " = ? WHERE "
				+ columnNameOfSearcher + " = ?;";
		try (PreparedStatement prepStatement = serverConnection.prepareStatement(updateEmployeeDetailsQuery)) {
			prepStatement.setObject(1, modifyingObject);
			prepStatement.setObject(2, searchingObject);
			return prepStatement.executeUpdate();
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
	}

	public boolean addEmployeeRelations(List<EmployeeRelations> relations) throws CustomException {
		ensureConnectionAvailable();
		Utility.validateCollection(relations);
		String addEmployeeRelationsQuery = "INSERT INTO " + SchemaUtility.EMPLOYEE_RELATIONS
				+ "(NAME, AGE, RELATIONSHIP, EMPLOYEE_ID) VALUE(?,?,?,?);";
		try (PreparedStatement preparedStatement = serverConnection.prepareStatement(addEmployeeRelationsQuery)) {
			for (EmployeeRelations relation : relations) {
				preparedStatement.setString(1, relation.getName());
				preparedStatement.setInt(2, relation.getAge());
				preparedStatement.setString(3, relation.getRelationship());
				preparedStatement.setInt(4, relation.getEmployeeID());
				preparedStatement.addBatch();
			}
			int[] results = preparedStatement.executeBatch();
			for (int result : results) {
				if (result != 1) {
					return false;
				}
			}
			return true;
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
	}

	public Map<Integer, List<EmployeeRelations>> getRelationRecordsOfNEmployees(int numberOfEmployees)
			throws CustomException {
		ensureConnectionAvailable();
		if (numberOfEmployees < 1) {
			throw new CustomException(ExceptionMessage.REQUIRES_NATURAL_NUMBER);
		}
		String getNEmployeeRelationRecordsQuery = "SELECT EMPLOYEE_ID FROM " + SchemaUtility.EMPLOYEE + " LIMIT "
				+ numberOfEmployees + ";";
		try (Statement statement = serverConnection.createStatement();
				ResultSet resultSet = statement.executeQuery(getNEmployeeRelationRecordsQuery)) {
			StringJoiner employeeIDs = new StringJoiner(",");
			while (resultSet.next()) {
				employeeIDs.add(resultSet.getInt(1) + "");
			}

			String getEmployeeRelationsQuery = "SELECT EmployeeRelations.*, Employee.NAME AS EmployeeName FROM EmployeeRelations "
					+ "LEFT JOIN Employee ON EmployeeRelations.EMPLOYEE_ID = Employee.EMPLOYEE_ID WHERE "
					+ "Employee.EMPLOYEE_ID IN (" + employeeIDs + ") ORDER BY EmployeeRelations.NAME;";
			ResultSet relations = statement.executeQuery(getEmployeeRelationsQuery);
			Map<Integer, List<EmployeeRelations>> relationRecords = new HashMap<Integer, List<EmployeeRelations>>();
			while (relations.next()) {
				EmployeeRelations relationRecord = (EmployeeRelations) SchemaUtility.EMPLOYEE_RELATIONS
						.convertResultSetToRecord(relations);
				if (!relationRecords.containsKey(relationRecord.getEmployeeID())) {
					relationRecords.put(relationRecord.getEmployeeID(), new ArrayList<>());
				}
				relationRecords.get(relationRecord.getEmployeeID()).add(relationRecord);
			}
			return relationRecords;
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
	}

	public List<EmployeeRelations> getEmployeeRelationsWithEmployeeName(String columnNameToSearch, Object valueToSearch)
			throws CustomException {
		ensureConnectionAvailable();
		Utility.validateObject(valueToSearch);
		Utility.validateObject(columnNameToSearch);

		String getEmployeeRelationsWithEmployeeName = "SELECT EmployeeRelations.*, Employee.NAME AS EmployeeName FROM EmployeeRelations "
				+ "LEFT JOIN Employee ON EmployeeRelations.EMPLOYEE_ID = Employee.EMPLOYEE_ID WHERE Employee."
				+ columnNameToSearch + " = ? ORDER BY EmployeeRelations.NAME";
		try (PreparedStatement statement = serverConnection.prepareStatement(getEmployeeRelationsWithEmployeeName)) {
			statement.setObject(1, valueToSearch);
			ResultSet resultSet = statement.executeQuery();
			List<EmployeeRelations> relationRecords = new ArrayList<>();
			while (resultSet.next()) {
				relationRecords
						.add((EmployeeRelations) SchemaUtility.EMPLOYEE_RELATIONS.convertResultSetToRecord(resultSet));
			}
			return relationRecords;
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
	}

}

//public Map<String, List<Object>> queryWithResultSet(String query) throws CustomException {
//	ensureConnectionAvailable();
//	Utility.validateObject(query);
//
//	HashMap<String, List<Object>> resultMap = new HashMap<>();
//
//	try (Statement queryStatement = serverConnection.createStatement();
//			ResultSet resultSet = queryStatement.executeQuery(query)) {
//		int columnCount = resultSet.getMetaData().getColumnCount();
//		while (resultSet.next()) {
//			for (int i = 1; i <= columnCount; i++) {
//				String columnLabel = resultSet.getMetaData().getColumnLabel(i);
//				if (!resultMap.containsKey(columnLabel)) {
//					resultMap.put(columnLabel, new ArrayList<Object>());
//				}
//				resultMap.get(columnLabel).add(resultSet.getObject(i));
//			}
//		}
//	} catch (SQLException e) {
//		throw new CustomException(e.getMessage());
//	}
//	return resultMap;
//}
