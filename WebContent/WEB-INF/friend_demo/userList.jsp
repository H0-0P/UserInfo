<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<title>主页</title>
</head>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
function getTime(){
	var now= new Date();
	var year=now.getFullYear();
	var month=now.getMonth();
	var date=now.getDate();
	//获取相应ID
	document.getElementById("timeSpan").innerHTML=year+"/"+(month+1)+"/"+date;
};
$(function(){
	getTime();
});
</script>

<style>
	td,th{
		text-align:center;
	}
</style>

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
				<div id="navigation"></div>
			</div>

			<div id="content">
				<p id="whereami"></p>
				<h1>Welcome!<a href="userDetail.do?id=${cur_user.id}">${cur_user.username}</a></h1>
				<table class="table">
					<tr class="table_header">
						<td>ID</td>
						<td>Username</td>
						<td>Gendar</td>
						<td>Age</td>
						<td>操作</td>
					</tr>
					
					<%-- <c:forEach items="${list}" var="user" varStatus="i">
						<tr class="row${i.index%2+1}">
							<td>${user.id}</td>
							<td>${user.username}</td>
							<td>
							<c:if test="${user.gender==0}">女</c:if>
							<c:if test="${user.gender==1}">男</c:if>
							</td>
							<td>${user.age}</td>
							<td>
							<!-- user.id迭代出来的id cur_user.id当前的id -->
								<c:if test="${user.id==cur_user.id}">
									<a href="delete.do?id=${user.id}" onclick="return confirm('确定删除该用户吗');">注销</a>&nbsp;&nbsp;
									<a href="query.do?id=${user.id}">修改</a>&nbsp;&nbsp;
								</c:if>
								<a href="userDetail.do?id=${user.id}">查看详情</a>
							</td>
						</tr>
					</c:forEach> --%>
					
					
 					<c:choose>
						<c:when test="${not empty pageBean.pageData}">
							<c:forEach var="user" items="${pageBean.pageData}" varStatus="i">
								<tr class="row${i.index%2+1}">
									<td>${user.id}</td>
									<td>${user.username}</td>
									<td>
										<c:if test="${user.gender==0}">女</c:if>
										<c:if test="${user.gender==1}">男</c:if>
									</td>
									<td>${user.age}</td>
									<td>
									<!-- user.id迭代出来的id cur_user.id当前的id -->
										<c:if test="${user.id==cur_user.id}">
											<a href="delete.do?id=${user.id}" onclick="return confirm('确定删除该用户吗');">删除</a>&nbsp;&nbsp;
											<a href="query.do">修改</a>&nbsp;&nbsp;
										</c:if>
											<a href="userDetail.do?id=${user.id}">查看详情</a>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">对不起，没有你要找的数据</td>
							</tr>
						</c:otherwise>
					</c:choose>

					<!-- 分页 -->
					<tr>
  						<td colspan="5" align="center">
  							当前${pageBean.currentPage }/${pageBean.totalPage }页  &nbsp;&nbsp;&nbsp;&nbsp;
  							<a href="${pageContext.request.contextPath }/user/switchPage.do?currentPage=1">首页</a>&nbsp;&nbsp;
  							<a href="${pageContext.request.contextPath }/user/switchPage.do?currentPage=${pageBean.currentPage-1}">上一页 </a>&nbsp;&nbsp;
  							<a href="${pageContext.request.contextPath }/user/switchPage.do?currentPage=${pageBean.currentPage+1}">下一页 </a>&nbsp;&nbsp;
  							<a href="${pageContext.request.contextPath }/user/switchPage.do?currentPage=${pageBean.totalPage}">末页</a>
  						</td>
  					</tr>
					
					
				</table>
				<p>
					<a href="loginOut.do"><input type="button" value="退出系统" /></a>
				</p>
			</div>
		</div>
		<div id="footer">
			<div id="footer_bg">ABC@126.com</div>
		</div>
	</div>
</body>
</html>