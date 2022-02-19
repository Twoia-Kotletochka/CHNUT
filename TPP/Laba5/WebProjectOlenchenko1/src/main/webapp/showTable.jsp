<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
List<Map<String, Object>> mapList = (List<Map<String, Object>>) request.getAttribute("mapList");
Set<String> tableHeaderSet = mapList.get(0).keySet();
%>

<table border="1">
<!-- Create table header -->
<tr>
<% for (String str : tableHeaderSet){ %>
	<th width="200"><%= str %></th>
<%}%>
</tr>

<%for(Map<String, Object> map : mapList){ %>
<tr>
<%for (Object obj : map.values()) {
String str = "null";
if(obj != null)
	str = obj.toString();
%>
<td width="100" align ="center"><%=str%></td>
<%} %>
</tr>
<%} %>
</table>
</body>
</html>