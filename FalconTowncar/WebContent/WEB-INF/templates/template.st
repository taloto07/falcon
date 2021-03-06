template(contextPath, title, body, home, about, contact, history, login, username, script,
useremail, owner)::="
<!DOCTYPE html>
<html lang='en'>
  <head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <meta name='description' content=''>
    <meta name='author' content=''>
    <!-- <link rel='icon' href='../../favicon.ico'> -->

    <title>$title$</title>

    <!-- Bootstrap core CSS -->
    <link href='$contextPath$css/bootstrap.min.css' rel='stylesheet'>
    
    
    <!-- Bootstrap datepicker CSS -->
    <link href='$contextPath$css/datepicker.css' rel='stylesheet'>

    <!-- Custom styles for this template -->
    <link href='$contextPath$css/sticky-footer-navbar.css' rel='stylesheet'>
    
    <!-- Custom Fonts -->
    <link href='$contextPath$fonts/font-awesome-4.1.0/css/font-awesome.min.css' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src='../../assets/js/ie8-responsive-file-warning.js'></script><![endif]-->
    <!-- <script src='../../assets/js/ie-emulation-modes-warning.js'></script> -->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <script src='../../assets/js/ie10-viewport-bug-workaround.js'></script> -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js'></script>
      <script src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js'></script>
    <![endif]-->
  </head>

  <body>

    <!-- Fixed navbar -->
    <div class='navbar navbar-default navbar-fixed-top' role='navigation'>
      <div class='container'>
        <div class='navbar-header'>
          <button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'>
            <span class='sr-only'>Toggle navigation</span>
            <span class='icon-bar'></span>
            <span class='icon-bar'></span>
            <span class='icon-bar'></span>
          </button>
          <a class='navbar-brand' href='$contextPath$'>Falcon Towncar</a>
        </div>
        <div class='collapse navbar-collapse'>
          <ul class='nav navbar-nav'>
            <li class='$home$'><a href='$contextPath$'>Home</a></li>
            <li class='$about$'><a href='$contextPath$about.html'>About</a></li>
            <li class='$contact$'><a href='$contextPath$contact.html'>Contact</a></li>
          </ul>
          <ul class='nav navbar-nav navbar-right'>
          	$if(login)$
          		<li class='dropdown'>          		
              		<a href='#' class='dropdown-toggle' data-toggle='dropdown'><span class='glyphicon glyphicon-user'></span> $username$ <span class='caret'></span></a>
              		<ul class='dropdown-menu' role='menu'>
                		<li><a href='$contextPath$dashboard/$useremail$'>Dashboard</a></li>
                		$if(owner)$
                			<li><a href='$contextPath$register-driver'>Driver Registration</a></li>
                			<li><a href='$contextPath$admin/addjob'>Add Job</a></li>
                		$endif$
              		</ul>
            	</li>
          		<li><a href='$contextPath$logout'>Log Out</a></li>
          	$else$
          		<li><a href='$contextPath$login'>Log In</a></li>
          	$endif$
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

	$body$

    <div class='footer'>
      <div class='container'>
      	<div class='col-xs-4'>
        	<p class='text-muted'>&copy; Copyright <a href='$contextPath$home.html'>Falcon Towncar Transportation</a>.</p>
        </div>
        <div class='col-xs-4 col-xs-offset-4'>
        	<p class='text-muted'>All Right Reserved | <a href='$contextPath$termsandconditions.html'>Terms & Conditions</p>
        </div>
      </div>
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
    <script src='$contextPath$js/bootstrap.min.js'></script>
    
    $script$
    
  </body>
</html>
";