<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.example.fitnessshop.bean.Product"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Фитнес -магазин</title>
</head>
<body>
	
	<%
		Map<String, String> errors = (HashMap<String, String>)request.getAttribute("errors");
	%>
	
	<!-- Header -->
		<%@include file="header.jsp" %>
	<section>
		<div class ="container border-bottom pb-5 ">
			
			<% 
				Product product = (Product)request.getAttribute("product");
			%>
			
			<div class="my-5">
				<a class="float-left text-danger py-5" href="shop">Посмотреть все продукты</a>
			</div>
			
			<div class="row">
				<div class="col-6">
					<img class="img-fluid" alt="<%= product.getName() %>" src="image/<%=product.getImgPath()%>">
				</div>
				<div class="col-6">
					<h2 class="blog-post-title text-uppercase mb-3"><%=product.getName()%></h2>
					<h3 class="blog-post-title text-danger mb-3">USD
 <%=product.getPrice() %></h3>
					<form id="itemDescriptionForm" action="cart" method="post" onsubmit="return validateProductQuantityForm()">
						<div>
							<label class="text-uppercase" for="quantity">Количество</label>
							<input type="text" class="form-control bg-light" id="quantity" name="quantity" style="max-width:180px;">
							<span class="text-danger font-weight-bold" id="quantityError">${errors.get("quantity")}</span>
							<input type="hidden" name="id"  value="<%=product.getId()%>"/>
							<input type="hidden" name="name"  value="<%=product.getName()%>"/>
							<input type="hidden" name="price"  value="<%=product.getPrice()%>"/>
							<input type="hidden" name="description"  value="<%=product.getDescription()%>"/>
							<input type="hidden" name="imgPath"  value="<%=product.getImgPath()%>"/>
						</div>
						<button class="btn btn-danger btn-md text-white text-uppercase my-5 p-2">Далее</button>
					</form>
					
					<p class="lead"><%=product.getDescription() %></p>
				</div>
			</div>
		</div>
	</section>
	
	<!-- footer -->
	<%@include file="footer.jsp" %>
	</body>
</html>