<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Вход |Фитнес-шоп</title>
</head>
<body>
	<!-- Header -->
	<%@include file="header.jsp" %>
		<section>
		<div class="container d-flex justify-content-center">
			<c:if test="${requestScope.isRegistered}">
						<div class="alert alert-success mt-5 px-5" role="alert">
						  У вас успешно зарегистрироваться !
						</div>
			</c:if>
			<c:if test="${requestScope.isInvalidCredentials}">
						<div class="alert alert-danger mt-5 px-5" role="alert">
						  Неверное имя пользователя или пароль!
						</div>
					</c:if>
			<c:if test="${requestScope.isInvalidCaptcha}">
				<div class="alert alert-danger mt-5 px-5" role="alert">
					Неверный код из картинки
				</div>
			</c:if>
			</div>
			<div class="container">
					<div class="row">
						<div class ="col-md-2">
						</div>
						<div class="col-md-4 rounded-pill">
							<form class="form mt-2 mb-5 p-5 d-flex align-items-center flex-column bg-white border shadow-sm rounded" action="login" method="post" onsubmit="return validateLoginForm()">
								<h2 class="font-weight-bold">Авторизоваться</h2>
								<div class="my-3">
									<input id="username" name="username" class="form-control rounded-pill" type="text" placeholder="Логин" autofocus style="max-width:350px;" />
									<span id="usernameError" class="text-danger font-weight-bold">${errors.get("username")}</span>
								</div>
								<div class="my-3">
									<input id="password" name="password" class="form-control rounded-pill" type="password" placeholder="Пароль" style="max-width:350px;"/>
									<span id="passwordError" class="text-danger font-weight-bold">${errors.get("password")}</span>
								</div>
								<div class="my-3">
									<img src="captcha-image">
								</div>
								<div class="my-3">
									<input id="captcha" name="captcha" class="form-control rounded-pill" type="text" placeholder="Введите код из картинки" style="max-width:350px;"/>

								</div>
								<button class="btn btn-lg btn-primary btn-block my-5 rounded-pill" type="submit" style="max-width:350px;">Войти</button>
								<p class="pt-3 border-top">Нет учетной записи ? <a href="register">Зарегистрироваться</a></p>
							</form>
						</div>
						<div class="col-md-4 bg-danger d-flex align-items-center flex-column justify-content-center mt-2 mb-5 p-5 rounded">
							<div class="">
								<h1 class="text-uppercase text-white">Добро пожаловать в фитнес -магазины добавки</h1>
							</div>
						</div>
						<div class ="col-md-2">
						</div>
					</div>
				
				
				
			</div>
		</section>



	
	<!-- footer -->
	<%@include file="footer.jsp" %>
</body>
</html>