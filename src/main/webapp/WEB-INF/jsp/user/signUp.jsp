<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
	
	<title>메모 게시판_회원가입</title>
</head>
<body>
	<div id="wrap">
		<header class="bg-secondary text-light">
			<h1 class="ml-3 pt-2">Memo</h1>
		</header>
		
		<section class="content d-flex justify-content-center align-items-center">
			<div class="signup-box">
				<h2>회원가입</h2>
				<form>
					<input type="text" class="form-control" placeholder="Username">
					<input type="password" class="form-control mt-2" placeholder="....">
					<input type="text" class="form-control mt-2" placeholder="비밀번호 확인">
					<input type="text" class="form-control mt-2" placeholder="이름">
					<input type="text" class="form-control mt-2" placeholder="이메일 주소">
					<input type="submit" class="btn btn-info btn-block mt-2" value="가입">
				</form>
				
			</div>
		</section>
		
		<footer class="bg-secondary text-center text-light">
			<span>Copyright 2018. memo all rights reserved.</span>
		</footer>
	</div>

</body>
</html>