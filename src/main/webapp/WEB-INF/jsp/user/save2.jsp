<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

  <jsp:include page="../fragments/bodyHeader.jsp" />
  
	<div class="container">
    <div class="row card">
		  <div class="col-sm-6">
		    <ol class="breadcrumb">
				  <li><a href="/users">Home</a></li>
				  <li><a href="/users">사용자 관리</a></li>
					<li class="active">생성</li>
				</ol>
			</div>	
 			<div class="col-sm-6 text-sm-right">
		    <button type="button" class="btnSave btn btn-danger-outline">저장</button>
		    <button type="button" class="btnList btn btn-info-outline">목록</button>
			</div>
		</div>
    <div class="row">
 			<div class="col-sm-12">
		    <form:form modelAttribute="user" id="form" method="POST" autocomplete="off">
		    	<input type="hidden" id="_method" name="_method" value="POST"/> 
		      <form:hidden path="uid"/>
		    	<div class="form-group row">
		        <label for="userId" class="col-sm-2 form-control-label">userId</label>
		        <div class="col-sm-10">
		          <form:input class="form-control" placeholder="userId 입력하세요" path="userId"/>
		          <form:errors path="userId" cssClass="text-danger" />
		        </div>
		      </div>        
		      <div class="form-group row">
		        <label for="passwd" class="col-sm-2 form-control-label">passwd</label>
		          <!-- 크롬에서 필드에 대한 페이크 요령
		            password 필드의 앞쪽에 text 필드는 autocomplete가 자동으로  on 되고 색상도 노란색으로 지정되어 버림, 
		          	이를 막기 위해서 위의 역할을 해주는 가짜 password 의필드와  가짜 text 필드를 선언후 보여주지 않는다.
		          	그러면, 이상동작을 하는 패스워드와 텍스트필드는 화면에 보여지지않고, 화면에 보여지는 패스워드필드는 정상동작을 하게 된다.
		           -->
		        <input style="display:none" type="text" name="fakeautocompletepasswd"/>
		        <input style="display:none" type="password" name="fakeautocompletepasswd"/>
		        <div class="col-sm-10">
		          <input type="password" name="passwd" id="passwd" class="form-control" placeholder="패스워드 입력하세요"/>
		        </div>  
		     	</div>
		      <div class="form-group row">
		        <label for="passwdConfirm" class="col-sm-2 form-control-label">passwdConfirm</label>
		        <div class="col-sm-10">
		          <input type="password" name="passwdConfirm" id="passwdConfirm" class="form-control" placeholder="패스워드 입력하세요"/>
		        </div>  
		      </div>
		      <div class="form-group row">
		        <label for="name" class="col-sm-2 form-control-label">name</label>
		        <div class="col-sm-10">
		          <form:input class="form-control" placeholder="이름을 입력하세요" path="name"/>
		          <form:errors path="name" cssClass="text-danger" />
		        </div>  
		      </div>
		      <div class="form-group row">
		        <label for="email" class="col-sm-2 form-control-label">email</label>
		        <div class="col-sm-10">
		          <form:input class="form-control" placeholder="이메일 입력하세요" path="email"/>
		        </div>  
		      </div>
		      <div class="form-group row">
		        <label for="tel" class="col-sm-2 form-control-label">tel</label>
		        <div class="col-sm-10">
		          <form:input class="form-control" placeholder="전화번호입력하세요" path="tel"/>
		        </div>  
		      </div>
		    </form:form>
	    </div>
		</div>   
		<div class="row card">
 			<div class="col-sm-12 text-sm-right">
		    <button type="button" class="btnSave btn btn-danger-outline">저장</button>
		    <button type="button" class="btnList btn btn-info-outline">목록</button>
			</div>
		</div>
	</div> <!-- /container -->
11
  <jsp:include page="../fragments/footer.jsp" />


<script>
$(document).ready(function() {
	var uid = '${user.uid}';
	var saveMode = 'CREATE';
	if(uid !== '') {
		saveMode = 'UPDATE';
		$('#userId').prop('readonly', true);
		$('.breadcrumb .active').text('수정');
	}

	$('.btnSave').click(function() {
		switch(saveMode) {
		case 'CREATE': 
			var action = '/users/new';
			formSubmit(action, 'POST');
			break;
		case 'UPDATE':
			var action = '/users/' + uid;
			formSubmit(action, 'PUT');
			break;
		}
	});	
	$('.btnList').click(function() {
		var action = '/users';
		formSubmit(action, 'GET');
	});	
	
	function formSubmit(action, method) {
		$('#_method').val(method);
		$('#form').prop('action', action).submit();
	}	
});
</script>
</body>
</html>
