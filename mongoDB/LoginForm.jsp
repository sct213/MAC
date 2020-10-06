<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script type="text/javascript">
	function doLoginUserCheck(f){
		if(f.user_id.value==""){
			alert("아이디를 입력하세요.");
			f.user_id.focus();
			return false;
		}
		if(f.password.value=="")}
		alert("비밀번호를 입력하세요.");
		f.password.focus();
		return false;
	}
</script>
</head>
<body>

<h1>로그인화면</h1>
<br/>
<br/>
<form name="f" method="post" action="/user/getUserLoginCheck.do" onsubmit="return doLoginUserCheck(this);">
<table border="1">
<col width="150px">
<col width="150px">
<col width="150px">
<col width="150px">
<tr>
	<td>아이디</td>
	<td><input type="text" name="user_id" style="width:150px"/></td>
	<td>비밀번호</td>
	<td><input type="password" name="password" style="width:150px"></td>
</tr>Ω
</table>
<input type="submit" value="로그인">
</body>
</html>