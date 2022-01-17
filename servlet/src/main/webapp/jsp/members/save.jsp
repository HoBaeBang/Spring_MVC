<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.io.PrintWriter" %><%
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);

    memberRepository.save(member);

//    PrintWriter w = response.getWriter();
//    w.write();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 성공</title>
</head>
<body>
<ul>
    <li>반갑습니다. <%=member.getUsername()%>님</li>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getUsername()%></li>
    <li>age = <%=member.getAge()%>인 회원이 등록되었습니다 :)</li>
</ul>
<a href="/index.html">메인화면</a>
</body>
</html>
