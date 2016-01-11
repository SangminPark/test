<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>
  <jsp:include page="../fragments/bodyHeader.jsp" />
	
	<div class="container-fluid">
    <div class="container">
      <table class="table table-striped">
        <thead class="thead-inverse">
        	<tr>
            <th>userId</th>
            <th>name</th>
            <th>email</th>
            <th>tel</th>
            <th>생성일시</th>
            <th>생성자</th>
        	</tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="info">
          <tr>
            <td><a href="/user/${info.uid}/edit">${info.userId}</a></td>
            <td>${info.name}</td>
            <td>${info.email}</td>
            <td>${info.tel}</td>
            <td>${info.createDate}</td>
            <td>${info.createUser.name}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
			<a href="/user/new" class="btn btn-primary-outline" role="button">사용자생성</a>
    </div>
	</div> <!-- /container -->

  <jsp:include page="../fragments/footer.jsp" />

</body>
</html>
