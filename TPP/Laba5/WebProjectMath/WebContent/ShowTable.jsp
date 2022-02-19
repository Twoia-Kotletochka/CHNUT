<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<%
		String[][] table = (String[][]) request.getAttribute("table");
		String amount = request.getParameter("amount");
	%>
	<center>
		<font size="+1"> <strong> 
				Тестування Math.random()<br>
				для вибірки обсягом <%=amount%>
		</strong>
		</font>
		<hr>
		<table border="1">
			<tr>
				<th>Average</th>
				<th>Dispersion</th>
				<th>Standard</th>
			</tr>
			<%
				for (String[] row : table) {
			%>
			<tr>
				<td align="center"><%=row[0]%></td>
				<td align="center"><%=row[1]%></td>
				<td align="center"><%=row[2]%></td>
			</tr>
			<%
				} // for
			%>
		</table>
	</center>
</body>
</html>
