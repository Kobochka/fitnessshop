<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.fitnessshop.bean.Product"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Вставьте заголовок здесь</title>
</head>
<body>
	
	<%
				List<Product> products = (ArrayList)request.getAttribute("products");
				List<List<Product>> displays = new ArrayList<>();
				
				Iterator it = products.iterator();
				while(it.hasNext()){ 
					displays.add(new ArrayList<>());
					int i = 0;
					while(it.hasNext() && i<3){
						displays.get(displays.size()-1).add((Product)it.next());
						i++;
					}
				} 
				for(List<Product> line: displays){
					Iterator itColumn = line.iterator();
			%>
				<div class="card-deck mb-3 text-center">
			<%
					while(itColumn.hasNext()){ 
						Product item = (Product)itColumn.next();
			%>
						<div class="card mb-4 shadow-sm" style="max-width:350px;">
					      <div class="card-header">
					        <h4 class="my-0 font-weight-normal"><%=item.getName() %></h4>
					      </div>
					      <div class="card-body">
					        <img class="img-fluid" alt="<%=item.getName()%>" src="image/<%=item.getImgPath()%>" style="max-width:308px; max-height:308px;">
					        <form action="product" id="productForm" method="get">
						      	<input type="hidden" name="id" id="productId" value="<%=item.getId()%>"/>
						        <button class="btn btn-lg btn-block btn-outline-danger mt-5">Подробнее</button>
					     	</form>
					      </div>
					    </div>
			 <%
				
					}
			%>
			</div>
			<%
				}
			%>
		
</body>
</html>