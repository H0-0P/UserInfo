<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>login</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="../css/style.css" />
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
			$button=$("input:[name='mybutton']");
			$button.click(function(){
				var username=$("input:[name='username']").val();
				var pwd=$("input:[name='pwd']").val();
				$.ajax({
					url:"login.do",
					data:{"username":username,"pwd":pwd},
					dataType:"json",
					success:function(data){
						if(data.msg == "success"){
							$("#myform").submit();
						} else{
							$("#loginErr").html("用户名或者密码错误").css("color", "red");
						}
					},
					error:function() {
						alert("请求失败！");
					}
				});
			});
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
						login
					</h1>
					&nbsp;&nbsp;&nbsp;&nbsp;没有账号？<a href="toRegist.do">去注册</a>
					<form action="userList.do" method="post" id="myform">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									username:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="username" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									password:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="pwd" />
								</td>
							</tr>
						</table>
						<p>
							<input type="button" name="mybutton" class="button" value="Submit &raquo;" />
							<span id="loginErr"></span>
						</p>
						
					</form>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
					ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
