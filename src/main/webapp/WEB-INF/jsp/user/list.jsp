<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp" />

    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>userId</th>
                <th>name</th>
                <th>email</th>
                <th>tel</th>
                <th>action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="info">
                <tr>
                    <td><a href="/user/${info.uid}/view">${info.userId}</a></td>
                    <td>${info.name}</td>
                    <td>${info.email}</td>
                    <td>${info.tel}</td>
                    <td>
                        <a href="/user/${info.uid}/edit" class="btn btn-primary" role="button">수정</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
<a href="/user/new">사용자생성</a>
    <jsp:include page="../fragments/footer.jsp" />

</div> <!-- /container -->

</body>
</html>
