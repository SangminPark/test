<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp" />
    <form role="form" action="${empty user.uid ? '/user/new' : '/user/${user.uid}/new' }" method="post">
    	<input type="hidden" name="uid" id="uid" value="${user.uid}">
        <div class="form-group">
            <label for="userId">userId</label>
            ${user.userId}
        </div>
        <div class="form-group">
            <label for="name">name</label>
            ${user.name}
        </div>
        <div class="form-group">
            <label for="email">email</label>
            ${user.email}
        </div>
        <div class="form-group">
            <label for="tel">tel</label>
            ${user.tel}
        </div>
    </form>
    <a href="/user/${user.uid}/edit">수정</a>
    <a href="/users">목록</a>
    <br/>
    <jsp:include page="../fragments/footer.jsp" />

</div> <!-- /container -->


</body>
</html>
