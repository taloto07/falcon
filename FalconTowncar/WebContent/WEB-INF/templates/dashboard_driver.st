dashboard_driver(contextPath, errorMessage, user, currentVehicle, otherVehicles, jobs)::="
<div class='container'>
	This is driver Dashboard page.
	$if(errorMessage)$
		<div class='alert alert-danger' role='alert'>
			<h2>$errorMessage$</h2>
		</div>
	$else$
		<div class='row'>
			<div class='col-md-6'>
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Basic Information</h3>
					</div>
		  			<div class='panel-body'>
		  				<h2>
		  					<img src='$contextPath$img/profile/$if(user.driver.image)$$user.driver.image$ $else$profile-default.png $endif$' alt='profile image' class='img-circle'  width='100px' height='100px'>
		  					$user.firstname$ $user.lastname$
		  				</h2>
		  				<table class='table table-striped'>
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
		
			<div class='col-md-6'>
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Vehicles</h3>
					</div>
		  			<div class='panel-body'>
		  					<h2>Current Vehicle</h2>
		  					<table class='table table-striped'>
			  					<tr>
			  						<td>Make</td>
			  						<td>$currentVehicle.make.name$</td>
			  					</tr>
			  					<tr>
			  						<td>Model</td>
			  						<td>$currentVehicle.model.name$</td>
			  					</tr>
			  					<tr>
			  						<td>Year</td>
			  						<td>$currentVehicle.year$</td>
			  					</tr>
			  					<tr>
			  						<td>License Plate</td>
			  						<td>$currentVehicle.licensePlateNumber$</td>
			  					</tr>
			  					<tr>
			  						<td>Capacity</td>
			  						<td>$currentVehicle.capacity$</td>
			  					</tr>
		  					</table>
		  				$otherVehicles:{vehicle|
		  					<h2>Other Vehicle</h2>
		  					<table class='table table-striped'>
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
			<div class='col-md-12'>
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Job History</h3>
					</div>
		  			<div class='panel-body'>
		  				<table class='table table-striped'>
		  					<thead>
		  						<tr>
		  							<th>Customer</th>
		  							<th>Driver</th>
		  							<th>Departure</th>
		  							<th>Destination</th>
		  							<th>Distance</th>
		  							<th>Cost</th>
		  							<th>Date</th>
		  							<th>Tip</th>
		  						</tr>
		  					</thead>
		  					<tbody>
		  						$jobs:{job|
		  							<tr>
		  								<td>$job.customer.user.firstname$ $job.customer.user.lastname$</td>
		  								<td>$job.driver.user.firstname$ $job.driver.user.lastname$</td>
		  								<td>
		  									$if(job.sourceCoordination)$
		  										$job.sourceCoordination.latitude$, $job.sourceCoordination.longitude$
		  									$else$
		  										$job.sourceAddress.house.number$ $job.sourceAddress.street.name$, $job.sourceAddress.city.name$ $job.sourceAddress.state.abbreviation$ $job.sourceAddress.zipcode.zipcode$
		  									$endif$
		  								</td>
		  								<td>
		  									$if(job.destinationCoordination)$
		  										$job.destinationCoordination.latitude$, $job.destinationCoordination.longitude$
		  									$else$
		  										$job.destinationAddress.house.number$ $job.destinationAddress.street.name$, $job.destinationAddress.city.name$ $job.destinationAddress.state.abbreviation$ $job.destinationAddress.zipcode.zipcode$
		  									$endif$
		  								</td>
		  								<td>$job.distance$</td>
		  								<td>$job.cost$</td>
		  								<td>$job.date$</td>
		  								<td>$job.tip$</td>
		  							</tr>
		  						}$
		  					</tbody>
		  				</table>
		  			</div>
				</div>
			</div>
		</div>
	$endif$
</div>
";