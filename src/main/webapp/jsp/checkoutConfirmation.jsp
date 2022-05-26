<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fitness Shop</title>
</head>
<body>

	<!-- header -->
	<%@include file="header.jsp" %>
	<section>
			
		<div class="container">	
				<div class = "row d-flex justify-content-center">
					<div class="col-md-1"></div>
					<div class="col-md-1  bg-secondary border border-secondary rounded-circle d-flex justify-content-center text-white" style="max-width:30px;">
					1
					</div>
					<div class="col-md-3 border-top mt-3 ">
						
					</div>
					<div class="col-md-1 bg-secondary border bg-secondary rounded-circle d-flex justify-content-center text-white" style="max-width:30px;">
						2
					</div>
					<div class="col-md-4 border-top mt-3">
						
					</div>
					<div class="col-md-1 bg-danger border border-danger rounded-circle d-flex justify-content-center text-white" style="max-width:30px;">
						3
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-2">
						<p class="text-sm-center">customer information</p>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-3">
						<p class="text-sm-center">Shipping & payment</p>
					</div>
					<div class="col-md-2"></div>
					<div class="col-md-2">
						<p class="text-sm-center text-danger">Order Validation</p>
					</div>
				</div>
				<div class="row mt-5 d-flex justify-content-center">
					<h3 class="text-success font-weight-bold">Thank you so much</h3>
				</div>
				<div class="row d-flex justify-content-center">
					<h3 class="text-success font-weight-bold">Your order have been registered successfully.</h3>
				</div>
				<div class="row d-flex justify-content-center">
					<h3 class="text-secondary text-uppercase ml-3 my-5">Your order History</h3>
				</div>
				<c:forEach items="${requestScope.vectors}" var="item" varStatus="loop">
					<div class = "row d-flex justify-content-center border-bottom border-left border-right rounded mx-3 mb-3 pb-1">
					
						<div class="col-md-3">
							<img class="img-fluid" alt="${item.name}" src="image/${item.imgPath}" style="max-width:68.8px; max-height:68.8px;">
						</div>
						<div class="col-md-3"></div>
						<div class = "col-md-4 ml-3">
							<p class="text-center font-weight-bold text-danger">${item.name}</p>
							<p class="text-center">USD
 <fmt:formatNumber value="${item.price*item.quantity}" maxFractionDigits="2"/>
								(<span>${item.price}</span> * <span>${item.quantity}</span>)
							</p>
						</div>
						
					</div>
				</c:forEach>
				<div class="row d-flex justify-content-center">
					<div class="col.md-2"></div>
					<div class="col-md-2">Shipping Address</div>
					<div class="col-md-5"></div>
					<div class="col-md-3">
						<p class="text-muted">
							<span>${requestScope.customer.firstName} ${requestScope.customer.lastName}</span><br>
							<span>${requestScope.address.street}</span><br>
							<span>${requestScope.address.zipCode} ${requestScope.address.city}</span><br>
							<span>${requestScope.address.country}</span><br>
						</p>
					</div>
				</div>
				<hr class="mb-4">
				<div class="d-flex justify-content-between pb-5">
					<h4 class="font-weight-bold">Order Total</h4>
					<h4 class="font-weight-bold">
						<c:set var="subTotal" value="${0}"/>
						<c:forEach items="${requestScope.vectors}" var="item" varStatus="loop">
							<c:set var="subTotal" value="${subTotal + item.price*item.quantity}" />
						</c:forEach>
						USD
 <fmt:formatNumber value="${subTotal}" maxFractionDigits="2" />
					</h4>
				</div>
			</div>
		
	</section>


	<!-- footer -->
	<%@include file="footer.jsp" %>
</body>
</html>