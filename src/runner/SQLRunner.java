package runner;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.CustomException;
import mysql.Employee;
import mysql.EmployeeRelations;
import task.RegexTask;
import task.SQLTask;
import utility.InputUtil;
import utility.LoggerUtility;
import utility.SchemaUtility;
import utility.Utility;

public class SQLRunner {

	public static Logger logger = null;
	static {
		try {
			logger = LoggerUtility.getNewLogger(SQLRunner.class.getName());
			logger.setLevel(Level.INFO);
			LoggerUtility.addFileHandler(logger, logger.getName(), LoggerUtility.DEFAULT_LOGGER_PATH);
		} catch (Exception e) {
			LoggerUtility.logSevere(LoggerUtility.DEFAULT_LOGGER, e);
		}
	}

	public static SQLTask sqlTask = new SQLTask();

	public static void main(String... args) {
		Connection serverConnection = null;

		boolean isProgramActive = true;
		int numberOfQuestions = 14;

		logger.info("SQL Runner Program");
		logger.info("The following are the list of operations : ");
		while (isProgramActive) {

			logger.info("***First process is mandatory to execute the rest***"
					+ "\n1 - Establish JDBC Connection. Create database incubationDB and an Employee Table"
					+ "\n2 - Get employee details from cmd and update it in the table programatically"
					+ "\n3 - Get employee name from user and fetch matching records in database"
					+ "\n4 - Modify mobile, email or department of an employee"
					+ "\n5 - Get the first N number of employees from the database"
					+ "\n6 - Get the first N number of employees by sorting a specific column in asending or descending order"
					+ "\n7 - Delete an employee by providing the employee id. Repeat step 5"
					+ "\n9 - Create another table with Name, Age and Relationship. Map it to Employee table using foreign key"
					+ "\n10 - Add rows to the employee relations table by getting information from the command line"
					+ "\n11 - List all the dependent details for a given employee by getting ID or the name"
					+ "\n12 - Get the employee relations of first N employees and sort them in the ascending order of the names"
					+ "\n13 - Get the Employee Table\n14 - Get the EmployeeRelations Table" + "\nTo exit, enter 0"
					+ "\n\n-----------------------------------------------------------------");

			int choice = -1;
			do {
				try {
					logger.info("Enter your choice (0 to " + numberOfQuestions + "): ");
					choice = InputUtil.getIntInput();
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
			} while (choice < 0 || choice > numberOfQuestions);

			try {
				switch (choice) {
				case 0:
					isProgramActive = false;
					logger.info("Thank you for using the program");
					InputUtil.closeScanner();
					sqlTask.closeConnection();
					break;

				case 1: {
					// Setting up JDBC Connection
					if (Utility.isNull(serverConnection)) {
						String url = "localhost:3306";
						String username = "sharan";
						String password = "123456";
						sqlTask.establishConnection(url, username, password);
					} else {
						logger.info("Connection Exists");
					}

					// Creating incubationDB Database
					sqlTask.createAndSelectDatabase("incubationDB");

					// Creating Employee table
					sqlTask.createTable(SchemaUtility.EMPLOYEE);
				}
					break;
				}
				if (choice > 1) {
					sqlTask.ensureConnectionAvailable();
					switch (choice) {
					case 1: {
						// Setting up JDBC Connection
						if (Utility.isNull(serverConnection)) {
							String url = "localhost:3306";
							String username = "sharan";
							String password = "123456";
							sqlTask.establishConnection(url, username, password);
						} else {
							logger.info("Connection Exists");
						}

						// Creating incubationDB Database
						sqlTask.createAndSelectDatabase("incubationDB");

						// Creating Employee table
						sqlTask.createTable(SchemaUtility.EMPLOYEE);
					}
						break;

					case 2: {
						int numberOfEmployees = 0;
						do {
							try {
								logger.info("Enter the number of Employees to add (Min - 1, Max - 10) : ");
								numberOfEmployees = InputUtil.getIntInput();
								if (numberOfEmployees > 10 || numberOfEmployees < 0) {
									logger.info("You can only add upto 10 employees at a time. Please try again");
								}
							} catch (Exception e) {
								logger.info(e.getMessage());
							}
						} while (numberOfEmployees < 1 || numberOfEmployees > 10);

						List<Employee> employees = new ArrayList<>();
						for (int i = 1; i <= numberOfEmployees; i++) {
							logger.info("Employee " + i + " details : ");
							logger.info("Enter Name : ");
							String name = InputUtil.getStringInput();
							long mobileNumber = getMobileNumber();
							String email = getEmailAddress();
							logger.info("Enter Department : ");
							String department = InputUtil.getStringInput();
							Employee newEmployee = new Employee();
							newEmployee.setName(name);
							newEmployee.setMobile(mobileNumber);
							newEmployee.setEmailAddress(email);
							newEmployee.setDepartment(department);
							employees.add(newEmployee);
						}
						logger.info("Adding Employees to Database");
						sqlTask.addEmployees(employees);

					}
						break;

					case 3: {
						logger.info("Enter Employee name to find the details : ");
						String employeeName = InputUtil.getStringInput();
						List<Object> matchingEmployees = sqlTask.getMatchingRecordDetails(SchemaUtility.EMPLOYEE,
								"NAME", employeeName);
						if (matchingEmployees.isEmpty()) {
							logger.info("No matching records were found");
						} else {
							printRecords(matchingEmployees);
						}
					}
						break;

					case 4: {
						int employeeID = getEmployeeID();

						logger.info("Enter any one to modify : 1 - Mobile, 2 - Email, 3 - Department");
						int selectModifyingEntity = 0;
						do {
							try {
								selectModifyingEntity = InputUtil.getIntInput();
								if (selectModifyingEntity < 0 || selectModifyingEntity > 3) {
									logger.info("Invalid Selection.");
								}
							} catch (Exception e) {
								logger.info(e.getMessage());
							}
						} while (selectModifyingEntity < 0 || selectModifyingEntity > 3);

						switch (selectModifyingEntity) {
						case 1:
							if (sqlTask.updateEmployeeDetails("MOBILE", getMobileNumber(), "EMPLOYEE_ID",
									employeeID) > 0) {
								logger.info("Update Successful!");
							}
							break;

						case 2:
							if (sqlTask.updateEmployeeDetails("EMAIL", getEmailAddress(), "EMPLOYEE_ID",
									employeeID) > 0) {
								logger.info("Update Successful!");
							}
							break;

						case 3:
							logger.info("Enter Department : ");
							if (sqlTask.updateEmployeeDetails("DEPARTMENT", InputUtil.getStringInput(),
									"EMPLOYEE_ID", employeeID) > 0) {
								logger.info("Update Successful!");
							}
							break;
						default:
							logger.info("Process Cancelled!");
							break;
						}
					}
						break;

					case 5: {
						logger.info("Enter the number of Employees to fetch : ");
						int numberOfEmployees = InputUtil.getIntInput();
						List<Object> employees = sqlTask.getRecordsFromTable(SchemaUtility.EMPLOYEE, numberOfEmployees);
						printRecords(employees);
					}
						break;

					case 6: {
						logger.info("Enter the number of Employees to fetch : ");
						int numberOfEmployees = InputUtil.getIntInput();
						logger.info("Enter the column to sort : ");
						String columnName = InputUtil.getStringInput();
						logger.info("Enter 1 to sort it in descending order or any other number to skip : ");
						List<Object> employees = sqlTask.getSortedRecordsFromTable(SchemaUtility.EMPLOYEE,
								numberOfEmployees, columnName, InputUtil.getIntInput() == 1);
						printRecords(employees);
					}
						break;

					case 7: {
						int employeeID = getEmployeeID();
						if (sqlTask.deleteEmployee(employeeID)) {
							logger.info("Employee details deleted");
						} else {
							logger.info("Failed to delete employee details");
						}
					}
						break;

					case 9: {
						sqlTask.createTable(SchemaUtility.EMPLOYEE_RELATIONS);
					}
						break;

					case 10: {
						logger.info("Enter the number of Relatives to add : ");
						int numberOfEmployeeRelations = InputUtil.getIntInput();
						if (numberOfEmployeeRelations < 1) {
							break;
						}
						List<EmployeeRelations> relations = new ArrayList<>(numberOfEmployeeRelations);
						for (int i = 0; i < numberOfEmployeeRelations; i++) {
							logger.info("Enter Employee ID to create Relation record : ");
							int employeeID = getEmployeeID();
							logger.info("Enter the relation's name : ");
							String name = InputUtil.getStringInput();
							int age = 0;
							do {
								logger.info("Enter the age of the relative : ");
								try {
									age = InputUtil.getIntInput();
									if (age <= 0) {
										logger.info("Age cannot be negative. Please try again");
									}
								} catch (CustomException e) {
									logger.info(e.getMessage());
								}
							} while (age <= 0);
							logger.info("Enter the relationship : ");
							String relationShip = InputUtil.getStringInput();

							EmployeeRelations relation = new EmployeeRelations();
							relation.setName(name);
							relation.setAge(age);
							relation.setRelationship(relationShip);
							relation.setEmployeeID(employeeID);
							relations.add(relation);
						}
						if (sqlTask.addEmployeeRelations(relations)) {
							logger.info("Employee Relatives added");
						} else {
							logger.info("Failed to add relatives");
						}
					}
						break;

					case 11: {
						int selectionInteger;
						do {
							logger.info(
									"To get employee relations details, enter 1 for Employee ID, 2 for Employee Name : ");
							selectionInteger = InputUtil.getIntInput();
						} while (selectionInteger != 1 && selectionInteger != 2);
						List<EmployeeRelations> relations = null;
						switch (selectionInteger) {
						case 1: {
							int employeeID = getEmployeeID();
							relations = sqlTask.getEmployeeRelationsWithEmployeeName("EMPLOYEE_ID", employeeID);
						}
							break;

						case 2: {
							logger.info("Enter employee name : ");
							String employeeName = InputUtil.getStringInput();
							relations = sqlTask.getEmployeeRelationsWithEmployeeName("NAME", employeeName);
						}
							break;
						}
						if (relations.isEmpty()) {
							logger.info("No matching records were found");
						} else {
							printRecords(relations);
						}

					}
						break;

					case 12: {
						logger.info("Enter the number of Employees to fetch relations records : ");
						int numberOfEmployees = InputUtil.getIntInput();
						Map<Integer, List<EmployeeRelations>> relations = sqlTask
								.getRelationRecordsOfNEmployees(numberOfEmployees);
//						relations.forEach((k, v) -> {
//							for (EmployeeRelations relation : v) {
//								logger.info(relation.toString());
//							}
//						});
						logger.info(relations.toString());
					}
						break;

					case 13: {
						List<Object> employees = sqlTask.getRecordsFromTable(SchemaUtility.EMPLOYEE);
						logger.info("Employee Table : ");
						printRecords(employees);
					}
						break;

					case 14: {
						List<Object> employeeRelations = sqlTask.getRecordsFromTable(SchemaUtility.EMPLOYEE_RELATIONS);
						logger.info("Employee Relations Table : ");
						printRecords(employeeRelations);
					}
						break;

					default:
						logger.info("The choice is invalid");
						break;
					}
				}
			} catch (Exception e) {
				LoggerUtility.logSevere(logger, e);
			}
			logger.info("=================================================================");
		}
		logger.info("=================================================================");

	}

	private static void printRecords(List<?> records) {
		for (Object record : records) {
			logger.info(record.toString());
		}
	}

	private static int getEmployeeID() throws CustomException {
		int employeeID = 0;
		boolean isEmployeeIDFound = false;
		do {
			logger.info("Enter Employee ID or -1 to exit : ");
			try {
				employeeID = InputUtil.getIntInput();
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			if (employeeID < 0) {
				throw new CustomException("Exited");
			}
			isEmployeeIDFound = sqlTask.isEmployeeIDExists(employeeID);
			if (!isEmployeeIDFound) {
				logger.info("No matching ID was found. Please enter valid Employee ID");
			}
		} while (!isEmployeeIDFound);
		return employeeID;
	}

	private static String getEmailAddress() throws CustomException {
		RegexTask regexTask = new RegexTask();
		String email = null;
		boolean isEmailValid = false;
		while (!isEmailValid) {
			logger.info("Enter email address : ");
			email = InputUtil.getStringInput();
			isEmailValid = regexTask.isValidEmail(email);
			if (!isEmailValid) {
				logger.info("Invalid Email Address");
			}
		}
		return email;
	}

	private static long getMobileNumber() {
		RegexTask regexTask = new RegexTask();
		long mobileNumber = 0;
		boolean isMobileNumberValid = false;
		do {
			try {
				logger.info("Enter mobile number : ");
				mobileNumber = InputUtil.getLongInput();
				isMobileNumberValid = regexTask.isValidMobileNumber(mobileNumber + "");
				if (!isMobileNumberValid) {
					logger.info("Invalid Mobile number. Should contain 10 digits starting with 7,8 or 9");
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
			}

		} while (!isMobileNumberValid);
		return mobileNumber;
	}
}