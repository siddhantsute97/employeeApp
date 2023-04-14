<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Add model HTML -->
<div id="addEmployeeModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="/Employee-CRUD-project/add" method="post">
				<div class="modal-header">
					<h4 class="modal-title">Add Employee</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Name</label> 
						<input name="name" type="text" class="form-control" required>
					</div> 
					<div class="form-group">
						<label>Email</label> 
						<input name="email" type="email" class="form-control" required>
					</div>
					<div class="form-group">
						<label>Address</label>
						<textarea name="address" class="form-control" required></textarea>
					</div>
					<div class="form-group">
						<label>Phone</label> 
						<input name="phone" type="text" class="form-control" required>
					</div>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"> 
					<input type="submit" class="btn btn-success" value="Add">
				</div>
			</form>
		</div>
	</div>
</div>
     
   
     <!-- Add model HTML -->
     <div id="editEmployeeModel" class="modal fade">></div>
     <!-- Add model HTML -->	
     <div id="deleteEmployeeModel" class="modal fade">></div>	
		
	</body>
</html>