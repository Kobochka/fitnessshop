<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Фитнес -магазин</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		updateProductQuantityInCart();
	});
</script>
</head>
<body>
	<!-- header -->
	<%@include file="header.jsp" %>
	<section>
		<div class = "container">
			<h2 class="text-center text-danger text-uppercase my-5 py-5 font-weight-bold">корзина</h2>
			<c:choose>
				<c:when test="${sessionScope.cart != null && sessionScope.cart.vectors.size() > 0}">
					<div class = "row border-bottom shadow-sm pt-4 pb-0 mb-2 font-weight-bold">
						<div class="col-md-6 pl-5">
							<p>Продукт</p>
						</div>
						<div class="col-md-2">
							<p class="text-center">Цена</p>
						</div>
						<div class="col-md-2">
							<p class="text-center"> Количество</p>
						</div>
						<div class="col-md-2">
							<p class="text-center">Общее</p>
						</div>
					</div>
					
					<div id ="shoppingCart" class="mb-5">
						<c:forEach items="${sessionScope.cart.vectors}" var="item" varStatus="loop">
							<div class = "row border-bottom mb-1 pb-1">
								<div class="col-md-6">
									<div class="d-flex">
										<img class="img-fluid" alt="${item.name}" src="image/${item.imgPath}" style="max-width:68.8px; max-height:68.8px;">
										<div class = "d-flex align-items-center ml-3">
											<p class="text-center font-weight-bold">${item.name}</p>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div>
									<p class="text-sm-center pt-3">${item.price}</p>
									</div>
								</div>
								<div class="col-md-2">
									
									<form id="form${item.id}" action="cart" method="post" class="form-inline mt-2">
							            <div class="btn-group" role="group">
								            <button type="button" id="${item.id}" class="quantityDown btn btn-danger">-</button>
								            <input  type="text" class="form-control" name="quantity" value="${item.quantity}" style="max-width:50px;"/>
							        		<input  type="hidden" class="form-control" name="id" value="${item.id}" style="max-width:50px;"/>
								            <button type="button" id="${item.id}" class="quantityUp btn btn-success">+</button>
								            <input type="hidden" name="_method" value="put" />
							       		</div>
								      </form>
								</div>
								<div class="col-md-2">
									<div>
										<p class="text-sm-center pt-3">USD

											<fmt:formatNumber value="${item.quantity*item.price}" maxFractionDigits="2" />
										</p>
									</div>
								</div>
						</div>
						</c:forEach>
						
					</div>
					<div class ="clearfix">
						<c:set var="subTotal" value="${0}"/>
						<c:forEach var="item" items="${sessionScope.cart.vectors}" varStatus="counter">
							<c:set var="subTotal" value="${subTotal + item.price*item.quantity}" />
						</c:forEach>
						<div class ="float-right">
							<p class="muted text-uppercase text-center"> Итого: 
								<span class="font-weight-bold">
									USD
 <fmt:formatNumber value="${subTotal}" maxFractionDigits="2" />
								</span>
							</p>
						</div>
					</div>
					<div class="clearfix">
						<div class = "float-right">
							<a href="checkout" class="btn btn-danger text-uppercase text-white px-5 py-2 font-weight-bold">Проверить</a>
						</div>
					</div>
					
					
				</c:when>
				<c:otherwise>
					<div>
						<p class="lead text-center">Ваша корзина пуста</p>
					</div>
				</c:otherwise>
			</c:choose>
				
		</div>
	</section>
	
	
	
	
	<!-- footer -->
	<%@include file="footer.jsp" %>
</body>
</html>