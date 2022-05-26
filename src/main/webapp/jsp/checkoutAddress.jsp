<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
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

	<!-- header -->
	<%@include file="header.jsp" %>
	<section>
			
		<div class="row">
			<div class="col-md-8">	
				<div class = "row">
					<div class="col-md-1"></div>
					<div class="col-md-1  bg-danger border border-danger rounded-circle d-flex justify-content-center text-white" style="max-width:30px;">
					1
					</div>
					<div class="col-md-3 border-top mt-3 ">
						
					</div>
					<div class="col-md-1 bg-secondary border border-secondary rounded-circle d-flex justify-content-center text-white" style="max-width:30px;">
						2
					</div>
					<div class="col-md-4 border-top mt-3">
						
					</div>
					<div class="col-md-1 bg-secondary border border-secondary rounded-circle d-flex justify-content-center text-white" style="max-width:30px;">
						3
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<div class="col-md-2">
						<p class="text-sm-center text-danger">Информация для покупателей</p>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-3">
						<p class="text-sm-center">Доставка и оплата</p>
					</div>
					<div class="col-md-2"></div>
					<div class="col-md-2">
						<p class="text-sm-center">Проверка заказа</p>
					</div>
				</div>
				<form action="checkout-address" method="post" class="border shadow-sm px-5 mt-2 mb-5 ml-4 rounded" style="max-width:800px;" onsubmit="return validateCheckoutAddressForm()">
					<h3 class="text-uppercase text-danger mt-5 mb-3">Адреса доставки</h3>
				 
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="firstName">Имя</label>
							<input id="firstName" name="firstName" class="form-control rounded-pill" type="text" value="${requestScope.customer.firstName}"/>
							<span id="firstNameError" class="text-danger font-weight-bold">${errors.get("firstName")}</span>
						</div>
						<div class="col-md-6 mb-3">
							<label for="lastName">Фамилия</label>
							<input id="lastName" name="lastName" class="form-control rounded-pill" type="text" value="${requestScope.customer.lastName}"/>
							<span id="lastNameError" class="text-danger font-weight-bold">${errors.get("lastName")}</span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<label for="country">Страна</label>
							<select id="country" name="country" class ="custom-select rounded-pill">
								<option value="${requestScope.address.country}">${requestScope.address.country}</option>
								<% 
									List<Locale> countries = getAllCountries();
									for(Locale country: countries){
								%>
									<option value="<%=country.getDisplayCountry()%>"><%=country.getDisplayCountry()%></option>
								<%
									}
								%>
							</select>
							<span id="countryError" class="text-danger font-weight-bold">${errors.get("country")}</span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 mb-3">
								<label for="street">Адрес улицы</label>
								<input id="street" name="street" class="form-control rounded-pill" type="text"  value="${requestScope.address.street}"/>
								<span id="streetError" class="text-danger font-weight-bold"></span>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-3 mb-3">
							<label for="zipCode">Почтовый/Zip</label>
							<input id="zipCode" name="zipCode" class="form-control rounded-pill" type="text" value="${requestScope.address.zipCode }"/>
							<span id="zipCodeError" class="text-danger font-weight-bold">${errors.get("zipCode")}</span>
						</div>
						<div class="col-md-9 mb-3">
							<label for="city">Город</label>
							<input id="city" name="city" class="form-control rounded-pill" type="text" value="${requestScope.address.city}"/>
							<span id="cityError" class="text-danger font-weight-bold">${errors.get("city")}</span>
						</div>
					</div>
					<button class="btn btn-success btn-lg px-5 my-5 rounded-pill text-uppercase">Сохранять</button>
					<a class="ml-3 mt-2 text-danger text-uppercase" href="checkout">Отмена</a>
				</form>
				
			</div>
			<div class="col-md-4 bg-light">
				<!-- user order -->
				<%@include file="checkoutOrder.jsp" %>
			</div>
		</div>
	</section>
	
	<%!
		public List<Locale> getAllCountries(){
			List<Locale> result = new ArrayList<>();
			for(String countryCode: Locale.getISOCountries()){
				result.add(new Locale("", countryCode));
			}
			return result;
		}
	%>	

	<!-- footer -->
	<%@include file="footer.jsp" %>
</body>
</html>