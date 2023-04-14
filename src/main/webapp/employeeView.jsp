<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
	<head>	
		<title>Servlet, JSP, JDBC and MVC Example</title>
		
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">		
		<link rel="stylesheet" href="css/employee.css"> 
		
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	     <script src="js/employee.js"></script>
		
		<script>
                var app = angular.module('employeeApp', []);

                app.controller('employeeCtrl', function($scope) {
                  $scope.getEmployeeDetails = function(employeeId) {
                    var employeeDetails = '';
                    $.ajax({
                      url : '/Employee-CRUD-project/get',
                      type : 'POST',
                      data : { "employeeId" : employeeId },
                      async : false,
                      success : function(data, textStatus, jqXHR) {
                        employeeDetails = data;
                        console.log('employeeDetails==>' +JSON.parse(employeeDetails));
                      },
                      error : function(jqXHR, textStatus, error) {
                        employeeDetails = '';
                        console.log('Error in getting employee details from server==>' + error);
                      }
                    });
              
                    $scope.employee = JSON.parse(employeeDetails);
                    console.log('employee Details==>' + $scope.employee);
              
                     return $scope.employee;
                     }
               });
                <!-- select all   jQuery function-->
                $(document).ready(function() {
                	// Select/Deselect checkboxes
                	var checkbox = $('table tbody input[type="checkbox"]');

                	$("#selectAll").click(function() {
                		if (this.checked) {
                			checkbox.each(function() {
                				this.checked = true;
                			});
                		} else {
                			checkbox.each(function() {
                				this.checked = false;
                			});
                		}
                	});
                	$('#deleteBtn').click(
                		function () {
                			var deleteEmployees = [];
                			
                			$("input:checkbox[class='employeeCheckBox']:checked").each(function(){
                				deleteEmployees.push($(this).val());
                			});
                			deleteEmployees = deleteEmployees.join(",")
                			var employeeIds = deleteEmployees.toString();
                			$.ajax(
                				{
                					url : '/Employee-CRUD-project/delete',
                					async : false,
                					type : "POST",
                					data : {"employeeIds" : employeeIds},
                					success : function(data, textStatus, jqXHR)
                					{
                						if (data !="") {
                							response = data;
                						}else{
                							response = '';
                						}
                						window.location.href = '/Employee-CRUD-project/';
                					},
                					error : function(jqXHR, textStatus, errorThrown)
                					{
                						console.log("something went wrong==>", errorThrown);
                						response = '';
                						alert('exception, errorThrown==>'+errorThrown);
                					}
                				}
                			);
                			
                		}
                	);
                });
             
              </script>
		
	</head>
	<body ng-app="employeeApp" ng-controller="employeeCtrl">
		<div class="container">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-sm-6">
							<h2>
								Manage <b>Employees</b>
							</h2>
						</div>
						<div class="col-sm-6">
							<a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal">
								<i class="material-icons">&#xE147;</i> 
								<span>Add New Employee</span>
								</a> 
							<a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal">
								<i class="material-icons">&#xE15C;</i> <span>Delete</span>
							</a>
						</div>
					</div>
				</div>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>
								<span class="custom-checkbox"> 
									<input type="checkbox" id="selectAll"> 
									<label for="selectAll"></label>
								</span>
							</th>
							<th>Name</th>
							<th>Email</th>
							<th>Address</th>
							<th>Phone</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${employees}">
							<tr>
								<td>
									<span class="custom-checkbox"> 
									<input 
										type="checkbox" 
										class="employeeCheckBox" 
										id="${employee.id}" 
										value="${employee.id}" />
										<label for="checkbox"></label>
									</span>
								</td>
								<td>${employee.name}</td>
								<td>${employee.email}</td>
								<td>${employee.address}</td>
								<td>${employee.phone}</td> 
								<td>
									<a href="#editEmployeeModal"  class="edit" data-toggle="modal">
										<i class="material-icons" ng-click="getEmployeeDetails(${employee.id})" data-toggle="tooltip" title="Edit">&#xE254;</i>
									</a> 
									<a href="#deleteEmployeeModal" class="delete" data-toggle="modal">
										<i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
     <!-- Add model HTML -->
     <jsp:include page="addEmployeeView.jsp"></jsp:include>
     <!-- edit model HTML -->
     <jsp:include page="updateEmployeeView.jsp"></jsp:include>
     <!-- delete model HTML -->	
     <jsp:include page="deleteEmployeeView.jsp"></jsp:include>	
		
	</body>
</html>