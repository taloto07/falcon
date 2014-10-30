dashboard_owner(contextPath, errorMessage, users)::="
<div class='container'>
	This is Owner Dashboard page.
	
	$if(errorMessage)$
		<p>$errorMessage$</p>
	$else$
		<div class='row'> 
			<div class='col-sm-2'> <!-- Display customer here-->
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Users</h3>
					</div>
		  			<div class='panel-body'>
		  				<h3>Customers</h3>
		  				<table class='table table-hover'>
			  				$users:{user|
								$if(user.customer)$
									<tr>
										<td><a href='#' email='$user.email$' id='loadUserInfo'>$user.firstname$ $user.lastname$</a></td>
									</tr>	
								$endif$
							}$
						</table>
						
						<h3>Drivers</h3>
		  				<table class='table table-hover'>
			  				$users:{user|
								$if(user.driver)$
									<tr>
										<td><a href='#' email='$user.email$' id='loadUserInfo'>$user.firstname$ $user.lastname$</a></td>
									</tr>	
								$endif$
							}$
						</table>
						
						<h3>All Jobs</h3>
		  				<table class='table table-hover'>
			  				<tr>
			  					<td><a href='#' id='getAllJob'>Job</a></td>
			  				<tr>
						</table>
		  			</div>
				</div>
			</div> <!--/div Display Customer Here-->
			
			<div class='col-sm-5'> <!--User Detail Info-->
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Detail Information</h3>
					</div>
		  			<div class='panel-body'>
		  				<h2 id='userHeader'>User Detail Information Displayed Here</h2>
		  				<table class='table table-striped' id='userDetail'>
		  					
			  			</table>
		  			</div>
				</div>
			</div> <!-- /div Detail Info-->
			
			<div class='col-sm-5' id='driverCars'> <!--Cars Detail Info-->
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Crs</h3>
					</div>
		  			<div class='panel-body'>
		  				<h2 id='currentVehicleHeader'>Car Detail Information Displayed Here</h2>
		  				<table class='table table-striped' id='currentVehicleDetail'>
		  					
			  			</table>
			  			
			  			<h2 id='otherVehicleHeader'>Other Vehicle</h2>
		  				<div id='otherVehicleDetail'>
		  					
			  			</div>
		  			</div>
				</div>
			</div> <!-- /div Detail Info-->
		</div><!-- /div row-->
		
		<div class='row'>
			<div class='col-sm-12'>
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Jobs History</h3>
					</div>
					<div class='panel-body'>
						<h2 id='jobHeader'>All Jobs Related To User Displayed Here</h2>
						<table class='table table-striped' id='userJobHistory'>
						
						</table>
					</div>
				</div>
			</div>
		</div>
	$endif$
</div> <!--/div container-->
";