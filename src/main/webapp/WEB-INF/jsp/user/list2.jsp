<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>
  <jsp:include page="../fragments/bodyHeader.jsp" />
	
	<div class="container-fluid">
    <div class="container">
      <div class="row card">
  			<div class="col-sm-6">
			    <ol class="breadcrumb">
					  <li><a href="/users">Home</a></li>
					  <li>사용자 관리</li>
					</ol>
				</div>	
  			<div class="col-sm-6 text-sm-right">
      		<button type="button" class="btnCreate btn btn-primary-outline">create</button>
      	</div>	
      </div>
 
 			<div class="row">
	 			<div class="col-sm-12">
		      <table class="table table-striped">
		        <thead class="thead-inverse">
		        	<tr>
		            <th>userId</th>
		            <th>name</th>
		            <th>email</th>
		            <th>tel</th>
		            <th>생성일시</th>
		            <th>생성자</th>
		            <th></th>
		            <th></th>
		        	</tr>
		        </thead>
		        <tbody>
		        <c:forEach items="${users}" var="info">
		          <tr>
		            <td>${info.userId}</td>
		            <td>${info.name}</td>
		            <td>${info.email}</td>
		            <td>${info.tel}</td>
		            <td>${info.createDate}</td>
		            <td>${info.createUser.name}</td>
		            <td><button type="button" data-uid="${info.uid}" class="btnUpdate btn btn-success-outline btn-sm">edit</button></td>
		            <td><button type="button" data-uid="${info.uid}" class="btnDelete btn btn-danger-outline btn-sm">delete</button></td>
		          </tr>
		        </c:forEach>
		        </tbody>
		      </table>
	      </div>
      </div>
      <div class="row card">
  			<div class="col-sm-12 text-sm-right">
      		<button type="button" class="btnCreate btn btn-primary-outline">create</button>
      	</div>	
      </div>
    </div>
	</div> <!-- /container -->

  <jsp:include page="../fragments/footer.jsp" />

<form method="POST" id="form">
	<input type="hidden" id="_method" name="_method" value="DELETE"/> 
</form>

<script>
/////vsvsvsvsvsvsv333333333333vvvvv
$(document).ready(function() {
	$('.btnCreate').click(function() {
		var uid = 'new';
		var action = '/users/' + uid;
		formSubmit(action, 'GET');
	});
	$('.btnUpdate').click(function() {
		var uid = $(this).data('uid');
		var action = '/users/' + uid + '/edit';
		formSubmit(action, 'GET');
	});
	$('.btnDelete').click(function() {
		var uid = $(this).data('uid');
		var action = '/users/' + uid;
		formSubmit(action, 'DELETE');
	});

	function formSubmit(action, method) {
		$('#_method').val(method);
		$('#form').prop('action', action).submit();
	}
});

</script>
</body>
</html>
