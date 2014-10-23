dashboard_driver(contextPath, errorMessage, user)::="
<div class='container'>
	This is driver Dashboard page.
	$if(errorMessage)$
		<div class='alert alert-danger' role='alert'>
			<h2>$errorMessage$</h2>
		</div>
	$else$
		<div class='row'>
			<div class='col-xs-6'>
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Basic Information</h3>
					</div>
		  			<div class='panel-body'>
		  				<h2>$user.firstname$ $user.lastname$</h2>
		  				<table class='table'>
		  					<tr>
		  						<td>Email</td>
		  						<td>$user.email$</td>
		  					</tr>
		  					<tr>
		  						<td>Phone</td>
		  						<td>$user.phoneNumber$</td>
		  					</tr>
		  					<tr>
		  						<td>Date of Birth</td>
		  						<td>$user.driver.date_of_birth$</td>
		  					</tr>
		  					<tr>
		  						<td>Bank</td>
		  						<td>$user.driver.bankName$</td>
		  					</tr>
		  					<tr>
		  						<td>Account</td>
		  						<td>$user.driver.bankAccountNumber$</td>
		  					</tr>
		  					<tr>
		  						<td>Address</td>
		  						<td>
		  							$user.driver.address.house.number$ 
		  							$user.driver.address.street.name$ 
		  						</td>
		  					</tr>
		  					<tr>
		  						<td>City</td>
		  						<td>$user.driver.address.city.name$</td>
		  					</tr>
		  					<tr>
		  						<td>State</td>
		  						<td>$user.driver.address.state.name$</td>
		  					</tr>
		  					<tr>
		  						<td>Zipcode</td>
		  						<td>$user.driver.address.zipcode.zipcode$</td>
		  					</tr>
		  				</table>
		  			</div>
		  			<div class='panel-footer'><a href='#'>Edit</a></div>
				</div>
			</div>
		
			<div class='col-xs-6'>
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Vehicles</h3>
					</div>
		  			<div class='panel-body'>
		  				$user.driver.vehicles:{vehicle|
		  					<table class='table'>
			  					<tr>
			  						<td>Make</td>
			  						<td>$vehicle.Make.name$</td>
			  					</tr>
			  					<tr>
			  						<td>Model</td>
			  						<td>$vehicle.model.name$</td>
			  					</tr>
			  					<tr>
			  						<td>Year</td>
			  						<td>$vehicle.year$</td>
			  					</tr>
			  					<tr>
			  						<td>License Plate</td>
			  						<td>$vehicle.licensePlateNumber$</td>
			  					</tr>
			  					<tr>
			  						<td>Capacity</td>
			  						<td>$vehicle.capacity$</td>
			  					</tr>
		  					</table>
		  				}$
		  			</div>
		  			<div class='panel-footer'><a href='#'>Edit</a></div>
				</div>
			</div>
		</div>
			
		<div class='row'>
			<div class='col-xs-12'>
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Job History</h3>
					</div>
		  			<div class='panel-body'>
		  				Show all jobs here...
		  			</div>
				</div>
			</div>
		</div>
	$endif$
</div>
";