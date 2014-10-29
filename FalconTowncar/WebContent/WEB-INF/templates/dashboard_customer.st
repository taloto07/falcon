dashboard_customer(contextPath, errorMessage, user, jobs)::="
<div class='container'>
	This is customer Dashboard page.
	$if(errorMessage)$
		<p>$errorMessage$</p>
	$else$
		<div class='row'>
			<div class='col-md-12'>
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Basic Information</h3>
					</div>
		  			<div class='panel-body'>
		  				<h2>$user.firstname$ $user.lastname$</h2>
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
		  						<td>Credit Card</td>
		  						<td>$user.customer.creditCardNumber$</td>
		  					</tr>
		  					<tr>
		  						<td>Expiration Date</td>
		  						<td>$user.customer.expiration$</td>
		  					</tr>
		  					<tr>
		  						<td>CCV</td>
		  						<td>$user.customer.ccvNumber$</td>
		  					</tr>
		  					<tr>
		  						<td>Zipcode</td>
		  						<td>$user.customer.zipcode.zipcode$</td>
		  					</tr>
		  				</table>
		  			</div>
		  			<div class='panel-footer'><a href='#'>Edit</a></div>
				</div>
			</div>
		</div>
		<!-- History -->
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