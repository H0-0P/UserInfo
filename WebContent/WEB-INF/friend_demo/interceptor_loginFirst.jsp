<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>toLogin</title>
</head>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var code = 3;
		setInterval(function(){
			code--;
			$("#time").html(code);
			if(code==0){
				window.location.href = "toLogin.do";
				return false;
			}
		}, 1000);
	});
</script>

<body>
	<h1 align="center">您没有查看权限，请先登录！</h1> <br/>
	<div align="center">
		还有<span id="time" style="color: red">3</span>秒进入登录界面
	</div>
</body>
</html>