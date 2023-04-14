package employee.crud.controller;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import employee.crud.beans.Employee;
import employee.crud.dao.EmployeeDAO;
import employee.crud.dao.EmployeeDAOImpl;
/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       EmployeeDAO employeeDAO = null;
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	
    	employeeDAO = new EmployeeDAOImpl();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("EmployeeController, dopost method started");
		//add
		//update
		//delete
		//select
		String action = request.getServletPath();
		System.out.println("doPost Action "+action);
		switch (action) {
		case "/add": {
			addNewEmployee(request,response);
			break;
		}
		case "/update": {
			updateEmployee(request,response);
			break;
		}
		case "/delete": {
			deleteEmployee(request,response);
			break;
		}
		case "/list": {
			getAllEmployee(request,response);
			break;
		}
		case "/get": {
			getEmployee(request,response);
			break;
		}
		default:
		{
			getAllEmployee(request,response);
			break;
		}
		}
		
	}

	private void getEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("start getemployee");
		
		int id = Integer.parseInt(request.getParameter("employeeId"));
		
		System.out.println("getEmployee Details = "+id);
		
		Employee employee=employeeDAO.getEmployee(id);
		System.out.println("getEmployee = "+employee);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			 String employeeStr = mapper.writeValueAsString(employee);
			
			 ServletOutputStream outputStream=response.getOutputStream();
			 outputStream.write(employeeStr.getBytes());
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	private void getAllEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("start getAllEmployee");
		
		
		
		List<Employee> employees=employeeDAO.getAllEmployee();
		System.out.println("getAllEmployee  = "+employees.size());
		
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
			
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
		
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("start delete employee");
		String employeeIds = request.getParameter("employeeIds");
		
		System.out.println("delete Employee, employee Id = "+employeeIds);
		
		StringTokenizer tokenizer = new StringTokenizer(employeeIds,",");
		while (tokenizer.hasMoreElements()) {
			int employeeId = Integer.parseInt(tokenizer.nextToken());
			boolean result=employeeDAO.deleteEmployee(employeeId);
			System.out.println("deleteEmployee = "+result);
			
		}
		
		List<Employee> employees=employeeDAO.getAllEmployee();
		System.out.println("getAllEmployee  = "+employees.size());
		
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
			
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("start update employee");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		long phone = Long.parseLong(request.getParameter("phone"));
		String address = request.getParameter("address");
		
		Employee  employee = new Employee(id,name,email,phone,address);
		System.out.println("update Employee Details = "+employee);
		
		boolean result=employeeDAO.updateEmployee(employee);
		System.out.println("updateEmployee = "+result);
		
		List<Employee> employees=employeeDAO.getAllEmployee();
		System.out.println("getAllEmployee  = "+employees.size());
		
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
			
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
		
	}

	private void addNewEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("start add employee");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		long phone = Long.parseLong(request.getParameter("phone"));
		String address = request.getParameter("address");
		
		Employee  employee = new Employee(name,email,phone,address);
		System.out.println("addEmployee, Employee Details = "+employee);
		
		boolean result=employeeDAO.addEmployee(employee);
		System.out.println("addNewEmployee = "+result);
		
		List<Employee> employees=employeeDAO.getAllEmployee();
		System.out.println("getAllEmployee  = "+employees.size());
		
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
			
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
