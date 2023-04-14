<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Edit model HTML -->
<div id="editEmployeeModal" class="modal fade" ng-app="employeeApp" ng-controller="employeeCtrl">
	<div class="modal-dialog">
		<div class="modal-content">			
			<form action="/Employee-CRUD-project/update" method="POST">
				<div class="modal-header">
					<h4 class="modal-title">Edit Employee</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Name</label>
						<input name="name" ng-model="employee.name" type="text" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Email</label> 
						<input name="email" ng-model="employee.email" type="email" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Address</label>
						<textarea name="address" ng-model="employee.address" class="form-control" required></textarea>
					</div>
					<div class="form-group">
						<label>Phone</label> 
						<input name="phone" ng-model="employee.phone" type="text" class="form-control" required>
					</div>
				</div>
				<div class="modal-footer">
					<input type="hidden" name="id" value="{{employee.id}}"/>
					<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"> 
					<input type="submit" class="btn btn-info" value="Update">
				</div>
			</form>
		</div>
	</div>
</div>