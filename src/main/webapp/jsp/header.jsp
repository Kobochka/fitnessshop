<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="js/fitness-shop.min.js" ></script>
</head>
<body>
	<!-- Header -->
	<header>
		<div class="navbar d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-light border-bottom shadow">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <h5 class="my-0 mr-md-auto font-weight-normal"><a class="navbar-brand" href="shop">Фитнес-магазин<a></a></h5>
		  <nav class="my-2 my-md-0 mr-md-3" id="navbarSupportedContent">
		  	<a class="p-2 text-dark" href="shop"><span class="oi oi-cart"></span>Магазин</a>
		    <a class="p-2 text-dark active text-lg" href="home">Главная</a>
		    <c:if test="${sessionScope.username!=null}">
		   		 <a class="p-2 text-dark" href="order-history"><span class="oi oi-cart"></span>История заказов</a>
		    </c:if>
		    <a class="p-2 text-dark" href="#"> <img class="img-fluid" style="width: 20px;height: 20px;" src="image/search-graphicon.png">Поиск</a>
		    <a class="p-2 text-dark" href="#">Twitter</a>
		    <a class="p-2 text-dark" href="cart">
		    	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shopping-cart"><circle cx="9" cy="21" r="1"></circle><circle cx="20" cy="21" r="1"></circle><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path></svg>
		    	<c:set var="totalQuantity" value="${0}"/>
				<c:forEach var="item" items="${sessionScope.cart.vectors}" varStatus="counter">
					<c:set var="totalQuantity" value="${totalQuantity + item.quantity}" />
				</c:forEach>
				<c:if test="${totalQuantity > 0}">
					(${totalQuantity})
				</c:if>
		    </a>
		  </nav>

		  <c:if test="${sessionScope.username == null}">
		   		 <a class="btn btn-outline-primary" href="login">Войти</a>
		  </c:if>
		  <c:if test="${sessionScope.username != null}">
		   		 <a class="btn btn-outline-primary" href="exit">Выход</a>
		  </c:if>

		</div>
	</header>
</body>
</html>
