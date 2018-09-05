<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="en">
	<head>
		<title>用户详情</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="../css/style.css" />
	</head>
	
	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			var now= new Date();
			var year=now.getFullYear();
			var month=now.getMonth();
			var date=now.getDate();
			document.getElementById("timeSpan").innerHTML=year+"/"+(month+1)+"/"+date;
		});
	</script>
	
	<body>
		<div id="wrap">
			<div id="top_content">
				<div id="header">
					<div id="rightheader">
						<p>
							<span id="timeSpan"></span>
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="#">Main</a>
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>

				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						User Detail:
					</h1>
					<table class="table">
						<tr>
							<td>
								姓名
							</td>
							<td>
								电话
							</td>
						</tr>
						<tr>
							<td>
								${vis_user.name}
							</td>
							<td>
								${vis_user.phone}
							</td>
						</tr>
					</table>
					<c:if test="${vis_user.id == cur_user.id}">
						<h1>
							Load Photo:
						</h1>
						<form action="msgUpload.do?id=${vis_user.id}" method="post"
							enctype="multipart/form-data">
							Upload File Name:
							<input type="file" name="file1" />
							<input type="submit" value="confirm" />
						</form>
					</c:if>
					
					<h1>
						view photo:
					</h1>
					<table>
						<c:forEach items="${pList}" var="p">
							<tr>
								<td>
									<img src="${pageContext.request.contextPath}/upLoad/${p.picName}" width="300" height="200" />
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div>
					<br/>&nbsp;&nbsp;&nbsp;
					<a href="userList.do"><input type="button" value="返回主界面" /></a>
				</div>
				<br/>
			</div>
			<div id="footer">
				<div id="footer_bg">
					ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
