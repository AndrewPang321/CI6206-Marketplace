<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Marketplace</title>

    <!-- Bootstrap core CSS -->
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">
	<link href="css/util.css" rel="stylesheet">
    
    <!-- Bootstrap core JavaScript -->
    <script src="lib/jquery/jquery.min.js"></script>
    <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    
    <!-- JavaScript files -->
	<script src="js/util.js"></script>
    <script src="js/auth.js"></script>

  </head>
  <script>
      $(document).ready(function() {
          loginLogoutToggle();

          var itemsCollection;
          var str = "";
          $.get("userhome", function(output) {
              itemsCollection = output;
              if (itemsCollection != "-1") {
                  itemsCollection.forEach(function (item) {
                      str += '<div class="col-lg-4 col-md-6 mb-4">';
                      str += '<div class="card h-100">';
                      // str += '<a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>';
                      // str += '<a href="itemDetails.jsp?item_id=' + item.item_id + '"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>';
                      str += '<a href="http://localhost:8080/Assignment/Java Resources/src/servlets/ItemDetailsServlet?item_id=' + item.item_id + '"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>';
                      
                      //http://localhost:8080/Assignment/Java Resources/src/servlets/ItemDetailsServlet?item_id=
                      
                      str += '<div class="card-body">';
                      str += '<h4 class="card-title">';
                      // str += '<a id="itemTitle" data-toggle="modal" href="#itemModal">' + item.item_title + '</a>';
                      //str += '<a href="itemDetails.jsp"><div id="itemTitle" data-toggle="modal">' + item.item_title + '</div></a>';
                      str += '<a href="http://localhost:8080/Assignment/Java Resources/src/servlets/ItemDetailsServlet?item_id=' + item.item_id + '"><div id="itemTitle" data-toggle="modal">' + item.item_title + '</div></a>';
                      
                      //str += '<div id="itemId" style="display:none">' + item.item_id + '</div>';
                      str += '<div id="itemUserId" style="display:none">' + item.user_id + '</div>';
                      str += '</h4>';
                      str += '<h5>$' + item.selling_price + '</h5>';
                      str += '<p class="card-text">' + item.item_description + '</p>';
                      str += '</div>';
                      // str += '<div class="card-footer">';
                      // str += '<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>';
                      // str += '</div>';
                      str += '</div>';
                      str += '</div>';
                  });
              } else {
                  str += '<div class="card mx-auto">';
                  str += '<div class="card-body">';
                  str += '<h5>Please sign in first!</h5>';
                  str += '</div>';
                  str += '</div>';
              }
              $("#collection").html(str);
          });

          /* $("#itemModal").on("show.bs.modal", function(event) {
              var itemId = $(event.relatedTarget).parent().find("#itemId").text();
              var itemUserId = $(event.relatedTarget).parent().find("#itemUserId").text();
              var itemTitle = $(event.relatedTarget).parent().find("#itemTitle").text();
              $("#itemModalTitle").text(itemTitle);
              console.log("Item ID: " + itemId);
              console.log("User ID in item: " + itemUserId);
          }); */
      });

  </script>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="index.html">Marketplace</a>
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
            <li class="nav-item">
              <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Services</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Contact</a>
            </li>
            <li class="nav-item">
              <a id="loginNav" class="nav-link" href="login.jsp">Login</a>
              <button type="button" id="logoutNav" class="btn btn-dark" onclick="logout()">Logout</button>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <div class="col-lg-3">
          <h1 class="my-4">Shop Name</h1>
          <div class="list-group">
            <a href="userHome.jsp" class="list-group-item">User Home</a>
            <a href="listItem.jsp" class="list-group-item">List Item</a>
            <a href="userProfile.jsp" class="list-group-item">Profile</a>
          </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

          <div class="row mt-5" id="collection"></div>
          <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Modal -->
    <!-- <div class="modal fade" id="itemModal" tabindex="-1" role="dialog" aria-labelledby="itemModalTitle" aria-hidden="true">
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
            <button type="button" class="btn btn-primary">Delete Item</button>
          </div>
        </div>
      </div>
    </div> -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>

  </body>

</html>