<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp" />
    
    <form role="form" id="form" method="post" autocomplete="off">
    	<input type="hidden" name="uid" id="uid" value="${user.uid}">
        <div class="form-group">
            <label for="userId">userId</label>
            <input type="text" name="userId" class="form-control" id="userId" placeholder="userId 입력하세요" value="${user.userId}">
        </div>        
        <div class="form-group">
            <label for="passwd">passwd</label>
            <!-- 크롬에서 필드에 대한 페이크 요령
              password 필드의 앞쪽에 text 필드는 autocomplete가 자동으로  on 되고 색상도 노란색으로 지정되어 버림, 
            	이를 막기 위해서 위의 역할을 해주는 가짜 password 의필드와  가짜 text 필드를 선언후 보여주지 않는다.
            	그러면, 이상동작을 하는 패스워드와 텍스트필드는 화면에 보여지지않고, 화면에 보여지는 패스워드필드는 정상동작을 하게 된다.
             -->
            <input style="display:none" type="text" name="fakeautocompletepasswd"/>
            <input style="display:none" type="password" name="fakeautocompletepasswd"/>
            <input type="password" name="passwd" class="form-control" id="passwd" placeholder="패스워드 입력하세요">
       </div>
        <div class="form-group">
            <label for="passwdConfirm">passwdConfirm</label>
            <input type="password" name="passwdConfirm" class="form-control" id="passwdConfirm" placeholder="패스워드 입력하세요">
       </div>
       <div class="form-group">
            <label for="name">name</label>
            <input type="text" name="name" class="form-control" id="name" placeholder="이름을 입력하세요" value="${user.name}">
        </div>
       <div class="form-group">
            <label for="email">email</label>
            <input type="email" name="email" class="form-control" id="email" placeholder="이메일 입력하세요" value="${user.email}">
        </div>
        <div class="form-group">
            <label for="tel">tel</label>
            <input type="text" name="tel" class="form-control" id="tel" placeholder="전화번호입력하세요" value="${user.tel}">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <a href="/users">목록</a>
    <br/>
    <jsp:include page="../fragments/footer.jsp" />

</div> <!-- /container -->

<script>
$(document).ready(function() {
	var action = '/user/new';
	if('${user.uid}' != '') {
		action = '/user/${user.uid}/edit';
	}
	$('#form').prop('action', action);

});

</script>
</body>
</html>
