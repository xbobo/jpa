<!DOCTYPE html>
<html lang="en">
	<head>
		<title>研判平台-登录</title>
		<link rel="shortcut icon" href="img/favicon.ico">
		<link rel="stylesheet" href="css/login.css" />
		<script type="text/javascript" src="lib/jquery/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="lib/security/security.js"></script>
	</head>
	<body>
		<div class="login-div">
			<h2 style="text-align:center">研判平台</h2>
			<form action="/login" onsubmit="return doLogin();" method="post" class="loginForm">
				<lable>
					登录名：
				</lable>
				<div>
					<img src="/img/user.png" class="user-img">
					<input placeholder="请输入用户名" type="text" id="username" name="username">	
				</div>
				<lable>
					登录密码：
				</lable>
				<div>
					<img src="/img/password.png" class="pwd-img">
					<input placeholder="请输入密码" type="password" id="password" name="password">				
				</div>
				<div class="submit-div">
					<input type="submit" value="提交">
				<div>
			</form>
		</div>
	</body>
</html>
<script type="text/javascript">
	function doLogin(){
		var username = $('input[name=username]').val();
	    var password = $('input[name=password]').val();
	    if (username && password) {
	    	var publicKeyExponent = "${publicKeyExponent}";
	    	var publicKeyModulus = "${publicKeyModulus}";
	    	if (publicKeyExponent == "" || publicKeyExponent == "") {
	    		location.href = "/login";
	    		return;
	    	}
	    	RSAUtils.setMaxDigits(200);
	    	var key = new RSAUtils.getKeyPair(publicKeyExponent, "", publicKeyModulus);
	    	var encrypedPwd = RSAUtils.encryptedString(key,password);
	    	$('input[name=password]').val(encrypedPwd);
	    } else {
	    	alert('用户名或密码不能为空!');
	    	return false;
	    }
	}
</script> 