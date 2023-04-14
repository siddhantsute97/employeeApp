package employee.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import employee.crud.beans.Employee;
import employee.crud.db.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO{
	private static Connection connection = DBConnection.connection;

	@Override
	public boolean addEmployee(Employee employee) {
		System.out.println("start add employee");
		try {
			String query = "insert into employee_db(name,email,phone,address) value(?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, employee.getName());
			pstmt.setString(2, employee.getEmail());
			pstmt.setLong(3, employee.getPhone());
			pstmt.setString(4, employee.getAddress());
			
			int result=pstmt.executeUpdate();
			return result == 1?true:false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		System.out.println("start update employee");
		try {
			String query = "update employee_db set name=?, email=?, phone=?, address=? where id=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, employee.getName());
			pstmt.setString(2, employee.getEmail());
			pstmt.setLong(3, employee.getPhone());
			pstmt.setString(4, employee.getAddress());
			pstmt.setInt(5, employee.getId());
			
			int result=pstmt.executeUpdate();
			return result == 1?true:false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		System.out.println("start delete employee");
		try {
			String query = "delete from employee_db where id=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, employeeId);
			 
			int result=pstmt.executeUpdate();
			return result == 1?true:false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		System.out.println("start select all employee");
		try {
			String query = "select * from employee_db";
			PreparedStatement pstmt = connection.prepareStatement(query);
			
			
		    ResultSet rs=pstmt.executeQuery();
		    List<Employee>employees = new ArrayList<Employee>();
		    while(rs.next()) {
		    	Employee employee = new Employee();
		    	employee.setId(rs.getInt("id"));
		    	employee.setName(rs.getString("name"));
		    	employee.setEmail(rs.getString("email"));
		    	employee.setPhone(rs.getLong("phone"));
		    	employee.setAddress(rs.getString("address"));
		    	
		    	employees.add(employee);
		    }
		    return employees;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee getEmployee(int employeeId) {
		System.out.println("start select all employee ");
		try {
			String query = "select * from employee_db where id=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, employeeId);
			
		    ResultSet rs=pstmt.executeQuery();
		    Employee employee = new Employee();
		    while(rs.next()) {
		    	
		    	employee.setId(rs.getInt("id"));
		    	employee.setName(rs.getString("name"));
		    	employee.setEmail(rs.getString("email"));
		    	employee.setPhone(rs.getLong("phone"));
		    	employee.setAddress(rs.getString("address"));
		    	
		    }
		    return employee;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		
		Employee employee = new Employee();
		//employee.setId(2);
		employee.setName("nicholas");
		employee.setEmail("nicholas34@gmail.com");
		employee.setPhone(555511155);
		employee.setAddress("LAS VEGAS");
		
		EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
		//System.out.println(employeeDAOImpl.addEmployee(employee));
		//System.out.println(employeeDAOImpl.updateEmployee(employee));
		//System.out.println(employeeDAOImpl.deleteEmployee(10));
		//System.out.println(employeeDAOImpl.getAllEmployee().size());
		//System.out.println(employeeDAOImpl.getAllEmployee().get(0));
		//System.out.println(employeeDAOImpl.getEmployee(6));
	}
	

}
