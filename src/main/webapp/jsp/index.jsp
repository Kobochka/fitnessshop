<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Фитнес -магазин</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

</head>
<body>
 	<!-- Header -->
	<%@include file="header.jsp" %>
	
	<section>
		<div class ="container">
			 <c:if test="${sessionScope.username == null}"> 
				<div class="row mb-5">
					<div class="col-md-6 bg-dark">
						<img class="img-fluid" alt="fitness shop" src="image/home-image-1.png">
					</div>
					<div class="col-md-6 text-uppercase bg-danger text-center align-middle d-flex align-items-center">
							<h1  class="font-weight-bold text-white">Добро пожаловать в фитнес <br>Магазин добавки</h1>
					</div>
				</div>
		    </c:if>
			
			<%@include file="productCatalog.jsp" %>
			
			</div> <!-- end of container -->
		
	</section> <!-- end of section -->
	
	<!-- footer -->
	<%@include file="footer.jsp" %>

</body>
</html>