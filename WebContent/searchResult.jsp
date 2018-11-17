<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.LinkedHashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">
    
    <!-- Bootstrap core JavaScript -->
    <script src="lib/jquery/jquery.min.js"></script>
    <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- JavaScript files -->
    <script src="js/util.js"></script>
    <script src="js/auth.js"></script>

</head>
<style>
.slidecontainer {
	width: 70%;
}

.slider {
	-webkit-appearance: none;
	width: 70%;
	height: 25px;
	background: #d3d3d3;
	outline: none;
	opacity: 0.7;
	-webkit-transition: .2s;
	transition: opacity .2s;
}

.slider:hover {
	opacity: 1;
}

.slider::-webkit-slider-thumb {
	-webkit-appearance: none;
	appearance: none;
	width: 25px;
	height: 25px;
	background: #4CAF50;
	cursor: pointer;
}

.slider::-moz-range-thumb {
	width: 25px;
	height: 25px;
	background: #4CAF50;
	cursor: pointer;
}
</style>
<body>

     <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="index.html">Marketplace</a>
       <form id="finalForm" action=SearchServlet method="get">
        <input name="searchValue" type="search" > 
        <input id="finalSubmit" type="submit" value="Search">
        </form>  
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="index.html">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <br><br><br><br>

   <div class="container" style="max-width: 1500px">
      <div class="row">
         <div class="col-lg-4 col-md-4 col-sm-4" style="background-color: #C0C0C0">
            <form id="finalForm" action=SearchServlet method="get">
               <br> <br> Search <br> <input name="searchValue" type="search"  style="min-width:80%"> <br> <br> Price range: <br> <input id="lowPrice" name="lowPrice" type="text" placeholder="$0" style="max-width: 100px"> <input id="highPrice" name="highPrice" type="text" placeholder="$&#x221e" style="max-width: 100px">
               <br>
               <span id="priceError" style="display:none;color:#8B0000">errrr</span>
                <br> Likes<br>
               <div class="slidecontainer">
                  <input name="minLike" type="range" min="0" max="1000" value="0" class="slider" id="myRange" style="min-width:170px">
                  <p>
                     <span id="slideRange"></span>
                  </p>
                  <p>
                     <span>Sort by </span> <select class="selectpicker" name="sortCriteria">
                        <option>No sort</option>
                        <option>Price : low -> high</option>
                        <option>Price : high -> low</option>
                        <option>Likes : low -> high</option>
                        <option>Likes : high -> low</option>
                     </select>
                  </p>
               </div>
               <input id="finalSubmit" type="submit" value="Search">
               <br><br>
            </form>

         </div>

         <div class="col-lg-8 col-md-8 col-sm-8">
            <div class="row">
               
               <c:forEach items="${sqlData}" var="sqlItem" varStatus="status">
                  <c:set var="keyName" value="${sqlItem.key}" />
                  <div class="col-lg-4 col-md-4 col-sm-6 portfolio-item tab-pane" id="tab1" style="min-width: 250px">
                     <div class="card h-100">
                        <a href="http://localhost:8080/Marketplace/buy.jsp?item_id=${sqlItem.key}"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                        <div class="card-body">
                           <h4 class="card-title">
                              <a href="http://localhost:8080/Marketplace/buy.jsp?item_id=${sqlItem.key}"><c:out value="${sqlData[keyName]['itemTitle']}" /></a>
                           </h4>
                           <p class="card-text">
                              $<c:out value="${sqlData[keyName]['price']}" />
                           </p>
                           <p>Likes: <c:out value="${sqlData[keyName]['item_like_count']}" />
                        </div>
                     </div>
                  </div>
               </c:forEach>
            </div>
         </div>
      </div>

   </div>
   
   
       <!-- Modal -->
    <div class="modal fade" id="itemModal" tabindex="-1" role="dialog" aria-labelledby="itemModalTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="itemModalTitle"></h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            ...
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save Changes</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>
   
   
   
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

   <script type="text/javascript" src="js/priceCheck.js"></script>
   
   
   
</body>
</html>