dashboard_owner(contextPath, errorMessage, users)::="
<div class='container'>
	This is Owner Dashboard page.
	
	$if(errorMessage)$
		<p>$errorMessage$</p>
	$else$
		<div class='row'>
			<div class='col-xs-6'>
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Customers</h3>
					</div>
		  			<div class='panel-body'>
		  				<table class='table'>
			  				$users:{user|
								$if(user.customer)$
									<tr>
										<td>$user.firstname$ $user.lastname$</td>
									</tr>	
								$endif$
							}$
						</table>
		  			</div>
				</div>
			</div>
			
			<div class='col-xs-6'>
				<div class='panel panel-primary'>
					<div class='panel-heading'>
						<h3 class='panel-title'>Drivers</h3>
					</div>
		  			<div class='panel-body'>
		  				<table class='table'>
			  				$users:{user|
								$if(user.driver)$
									<tr>
										<td>$user.firstname$ $user.lastname$</td>
									</tr>	
								$endif$
							}$
						</table>
		  			</div>
				</div>
			</div>
		</div>
	$endif$
</div>
";