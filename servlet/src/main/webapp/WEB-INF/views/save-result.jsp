<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: aslan0
  Date: 2022/01/31
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 성공</title>
</head>
<body>
<ul>
<%--    <li>id = <%=((Member)request.getAttribute("member")).getId()%></li>--%>
<%--    <li>username = <%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
<%--    <li>age = <%=((Member)request.getAttribute("member")).getAge()%></li>--%>

<%--    //jsp가 이것을 더 쉽게 만들어 주는데 이것을 이용하면--%>
    <li>id =${member.id}</li>
    <li>username = ${member.username}</li>
    <li>age = ${member.age}</li>

</ul>
<a href="/index.html">메인화면</a>
</body>
</html>

