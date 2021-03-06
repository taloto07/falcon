dashboard_customer(contextPath, errorMessage, user, jobs)::="
<div class='container'>
	
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
		  							<th>comments</th>
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
		  								<td>
		  									<button type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#myModal$job.id$'>
  												Comments <span class='badge' id='commentCount$job.id$'>$length(job.comments)$</span>
											</button>

											<div class='modal fade' id='myModal$job.id$' tab-index='-1' role='dialog' aria-labeldby='myModalLabel' aria-hidden='true'>
												<div class='modal-dialog'>
													<div class='modal-content'>

														<div class='modal-header'>
															<button type='button' class='close' data-dismiss='modal'>
																<span aria-hidden='true'>&times;</span>
																<span class='sr-only'>Close</span>
															</button>
															<h4 class='modal-title' id='myModalLabel'>Job id $job.id$</h4>
														</div>

														<div class='modal-body'>
															<div id='showComments'>
																$job.comments:{comment|
																	<div class='panel panel-primary'>
																	  	<div class='panel-heading'>
																	  		$comment.user.firstname$ $comment.user.lastname$
																	  	</div>
																	  	
																		<div class='panel-body'>
																	    	$comment.comment$
																	  	</div>
																	</div>
																}$
															</div>
															
															<div id='postComment'>	
																Comment: <textarea class='form-control' rows='3' style='resize:none'></textarea>
																<button type='button' onclick='postComment($job.id$, $user.id$, \"$user.firstname$\", \"$user.lastname$\")'>Submit</button>
															</div>
														</div>

														<div class='modal-footer'>
															<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
															<button type='button' class='btn btn-primary'>Save changes</button>
														</div>
													</div>
												</div>
											</div>

		  								</td>
		  							</tr>
		  						}$
		  					</tbody>
		  				</table>
		  			</div>
				</div>
			</div>
		</div>
		
		<div class='row'>
			<div class='col-md-12'>
				
			</div>
		</div>
	$endif$
</div>
";