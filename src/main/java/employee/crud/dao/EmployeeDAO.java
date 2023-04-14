package employee.crud.dao;

import java.util.List;

import employee.crud.beans.Employee;

public interface EmployeeDAO {

	//1.insert
	public boolean addEmployee(Employee employee);
	//2.Update
	 public boolean updateEmployee(Employee employee);
	//3.delete
	 public boolean deleteEmployee(int employeeId);
	//4.get all employee
	 public List<Employee>getAllEmployee();
	//5.get employee
	 public Employee getEmployee(int employeeId);
}
