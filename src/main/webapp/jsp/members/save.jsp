<%@ page import="hyun6ik.servlet.basic.domain.Member" %>
<%@ page import="hyun6ik.servlet.basic.domain.MemberRepository" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    WebApplicationContext waContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
    MemberRepository memberRepository = (MemberRepository) waContext.getBean("memberRepository");

    final String username = request.getParameter("username");
    final int age = Integer.parseInt(request.getParameter("age"));

    final Member member = memberRepository.save(Member.of(username, age));
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
