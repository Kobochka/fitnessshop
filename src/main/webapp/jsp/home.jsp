<!DOCTYPE html>

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
			<div class="row mb-5">
				<div class="col-md-6 bg-dark">
					<img class="img-fluid" alt="Фитнес -магазин" src="image/home-image-1.png">
				</div>
				<div class="col-md-6 text-uppercase bg-danger text-center align-middle d-flex align-items-center">
						<h1  class="font-weight-bold text-white">Добро пожаловать в фитнес <br>Магазин добавки</h1>
				</div>
			</div>
			
			
			
			<%@include file="productCatalog.jsp" %>
			
			</div> <!-- end of container -->
		
	</section> <!-- end of section -->
	
	<!-- footer -->
	<%@include file="footer.jsp" %>

</body>
</html>