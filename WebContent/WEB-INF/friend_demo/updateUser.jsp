<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<title>update user</title>
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

var flag2 = true; //真实姓名
var flag4 = true; //手机号
var flag5 = true; //密码
var flag6 = true; //年龄

$(function(){
	getTime();
	
	//验证真实姓名
	var patrnName = /^([\u4e00-\u9fa5]+|([a-z]+\s?)+)$/;
	var rName = new RegExp(patrnName);
	var $name = $("input:[name='name']");
	//判定不能为空
	$name.blur(function(){
		var nameVal = $name.val();
		if(nameVal == null || nameVal == ""){
			flag2 = false;
			$("#nameErr").html("输入不能为空").css("color", "red");
		} else{
			var f = rName.test(nameVal);//检测是否匹配正则表达式
			if(f == true){
				flag2 = true;
				$("#nameErr").html("可以使用").css("color", "green");
			} else{
				flag2 = false;
				$("#nameErr").html("请输入中文或者英文名").css("color", "red");
			}
		}
	});
	
	//验证手机号
	var patrnPhone = /^1[3|4|5|8][0-9]\d{8}$/;
	var rPhone = new RegExp(patrnPhone);
	var $phone = $("input:[name='phone']");
	$phone.blur(function(){
		var phoneVal = $phone.val();
		if(phoneVal == null || phoneVal == ""){
			flag4 = false;
			$("#phoneErr").html("输入不能为空").css("color", "red");
		} else{
			var f = rPhone.test(phoneVal);//检测是否匹配正则表达式
			if(f == true){
				flag4 = true;
				$("#phoneErr").html("可以使用").css("color", "green");
			} else{
				flag4 = false;
				$("#phoneErr").html("请输入正确的手机号").css("color", "red");
			}
		}
	});
	
	//验证密码
	var patrnPwd =  /^[a-zA-Z0-9]{6,18}$/;
	var rPwd = new RegExp(patrnPwd);
	var $pwd = $("input:[name='password']");
	$pwd.blur(function(){
		var pwdVal = $pwd.val();
		if(pwdVal == null || pwdVal == ""){
			flag5 = false;
			$("#pwdErr").html("输入不能为空").css("color", "red");
		} else{
			var f = rPwd.test(pwdVal);//检测是否匹配正则表达式
			if(f){
				flag5 = true;
				$("#pwdErr").html("可以使用").css("color", "green");
			} else{
				flag5 = false;
				$("#pwdErr").html("请输入6-15位以字母和数字组成的密码").css("color", "red");
			}
		}
	});
	
	//验证年龄
	var patrnAge = /^\d{2}$/;
	var rAge = new RegExp(patrnAge);
	var $age = $("input:[name='age']");
	$age.blur(function(){
		var ageVal = $age.val();
		if(ageVal == null || ageVal == ""){
			flag6 = false;
			$("#ageErr").html("输入不能为空").css("color", "red");
		} else{
			var f = rAge.test(ageVal);//检测是否匹配正则表达式
			if(f){
				flag6 = true;
				$("#ageErr").html("可以使用").css("color", "green");
			} else{
				flag6 = false;
				$("#ageErr").html("只有10到99的年龄才可以注册哦").css("color", "red");
			}
		}
	});
});

function mysubmit(){
	return flag2 && flag4 && flag5 && flag6;
};
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
						<a href="#">Update</a>
					</h1>
				</div>
				<div id="navigation"></div>
			</div>

			<div id="content">
				<p id="whereami"></p>
				<h1>修改用户信息</h1>
				<form action="update.do" method="post" onsubmit="return mysubmit();">
					<table cellpadding="0" cellspacing="0" border="0"
						class="form_table">
						<tr>
							<input type="hidden" name="id" value="${cur_user.id}" /> 
							<td valign="middle" align="right">用户名:</td>
							<td valign="middle" align="left">
								<input readonly="readonly" class="inputgri" name="username" value="${cur_user.username}" /> 
							</td>
						</tr>
						<tr>
							<td valign="middle" align="right">真实姓名:</td>
							<td valign="middle" align="left">
								<input type="text" class="inputgri" name="name" value="${cur_user.name}" /> 
								<span id="nameErr"></span>
							</td>
						</tr>
						<tr>
							<td valign="middle" align="right">密码:</td>
							<td valign="middle" align="left">
								<input type="text" class="inputgri" name="password" value="${cur_user.password}" /> 
								<span id="pwdErr"></span>
							</td>
						</tr>
						<tr>
							<td valign="middle" align="right">年龄:</td>
							<td valign="middle" align="left">
								<input type="text"	class="inputgri" name="age" value="${cur_user.age}" /> 
								<span id="ageErr"></span>
							</td>
						</tr>
						<tr>
							<td valign="middle" align="right">性别:</td>
							<td valign="middle" align="left">
								<c:if test="${cur_user.gender==1}">
									男 <input type="radio" class="inputgri" name="gender" value="1" checked="checked" />
									女 <input type="radio" class="inputgri" name="gender" value="0" />
								</c:if>
								<c:if test="${cur_user.gender==0}">
									男 <input type="radio" class="inputgri" name="gender" value="1"  />
									女 <input type="radio" class="inputgri" name="gender" value="0" checked="checked" />
								</c:if>
							</td>
						</tr>
						<tr>
							<td valign="middle" align="right">电话:</td>
							<td valign="middle" align="left">
								<input type="text" class="inputgri" name="phone" value="${cur_user.phone}" /> 
								<span id="phoneErr"></span>
							</td>
						</tr>
					</table>
					<p>
						<input type="submit" class="button" value="Submit &raquo;" />
					</p>
				</form>
			</div>
		</div>
		<div id="footer">
			<div id="footer_bg">ABC@126.com</div>
		</div>
	</div>
</body>
</html>