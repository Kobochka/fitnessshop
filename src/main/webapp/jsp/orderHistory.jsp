<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>История заказов</title>
</head>
<body>
	<!-- header -->
	<%@include file="header.jsp" %>
	<section>
		<div class="container">
			<c:choose>
				<c:when test="${requestScope.orders != null && requestScope.orders.size() > 0}">
					<div class = "row border-bottom shadow-sm pt-4 pb-0 mb-2 font-weight-bold">
						<div class="col-md-2">
							<p class="text-center">номер заказа</p>
						</div>
						<div class="col-md-2">
							<p class="text-center">Дата заказа</p>
						</div>
						<div class="col-md-2">
							<p class="text-center">Стоимость заказа</p>
						</div>
						<div class="col-md-3">
							<p class="text-center"> Статус заказа</p>
						</div>
						<div class="col-md-3">
							<p class="text-center">Заказать детали</p>
						</div>
					</div>
							
					<div class="mb-5">
						<c:forEach items="${requestScope.orders}" var="order" varStatus="loop">
							<div class="row border-bottom border-left border-right mb-1 pb-1">
								<div class="col-md-2">
									<p class="text-center text-danger font-weight-bold">${order.id}</p>
								</div>
								<div class="col-md-2">
									<p class="text-center">${order.date}</p>
								</div>
								<div class="col-md-2">
									<p class="text-center">USD
 ${order.price}</p>
								</div>
								<div class="col-md-3 d-flex justify-content-center">
									<span class="text-center px-3 py-2 bg-info border text-uppercase text-white">Отправленный</span>
								</div>
								<div class="col-md-3 d-flex justify-content-center">
									<a href="order-items?id=${order.id}" class="btn btn-md btn-secondary px-4">Подробности</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<p class="lead text-center">У вас еще нет заказа.</p>
					</div>
				</c:otherwise>
			</c:choose>
			
		</div>
		
	</section>
	
	<!-- footer -->
	<%@include file="footer.jsp" %>
</body>
</html>