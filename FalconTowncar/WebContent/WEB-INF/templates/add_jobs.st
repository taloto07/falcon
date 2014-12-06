add_jobs(contextPath, form, customers, drivers, sourceStates, destStates, todayDate, driverId, customerId, 
sourceAddress, sourceCity, sourceStateId, sourceZipcode, destAddress, destCity, destStateId, destZipcode,
success)::="
	<div class='container'>
		<div class='jumbotron'>
			<form class='form-horizontal' role='form' method='post' action='$contextPath$admin/addjob'>
				$if(form)$
					<div class='alert alert-danger' role='alert'>
						<ul>
							$if(driverId)$ <li> Driver: $driverId$ </li> $endif$
							$if(customerId)$ <li> Customer: $customerId$ </li> $endif$
							
							$if(sourceAddress)$ <li> Departure Address: $sourceAddress$ </li> $endif$
							$if(sourceCity)$ <li> Departure City: $sourceCity$ </li> $endif$
							$if(sourceStateId)$ <li> Departure State: $sourceStateId$ </li> $endif$
							$if(sourceZipcode)$ <li> Departure Zipcode: $sourceZipcode$ </li> $endif$
							
							$if(destAddress)$ <li> Destination Address: $destAddress$ </li> $endif$
							$if(destCity)$ <li> Destination City: $destCity$ </li> $endif$
							$if(destStateId)$ <li> Destination State: $destStateId$ </li> $endif$
							$if(destZipcode)$ <li> Destination Zipcode: $destZipcode$ </li> $endif$
						</ul>
					</div>
				$endif$
				
				$if(success)$
					<div class='alert alert-success' role='alert'>Successfully add job.</div>
				$endif$
				
				<h1><center>Job</center></h1>
				
				<div class='form-group $if(driverId)$ has-error $endif$'>
			    	<label for='driver' class='col-sm-2 control-label'>Driver</label>
			    	<div class='col-sm-10'>
			    		<select class='form-control' id='driver' name='driver'>
			    			<option value='0'>Choose Driver</option>
			    			$drivers$
			    		</select>
			   		</div>
			  	</div>
			  	
			  	<div class='form-group $if(customerId)$ has-error $endif$'>
			    	<label for='customer' class='col-sm-2 control-label'>Customer</label>
			    	<div class='col-sm-10'>
			    		<select class='form-control' id='customer' name='customer'>
			    			<option value='0'>Choose Customer</option>
			    			$customers$
			    		</select>
			   		</div>
			  	</div>
			  	
			  	<div class='form-group'>
			  		<label for='sourceAddress' class='col-sm-2 control-label'>Departure</label>
			  		<div class='col-sm-4 $if(sourceAddress)$ has-error $endif$'>
			  			<input $if(form)$ value='$form.sourceAddress$' $endif$ type='text' class='form-control' placeholder='Address' name='sourceAddress'/>
			  		</div>
			  		
			  		<div class='col-sm-2 $if(sourceCity)$ has-error $endif$'>
			  			<input $if(form)$ value='$form.sourceCity$' $endif$ type='text' class='form-control' placeholder='City' name='sourceCity'/>
			  		</div>
			  		
			  		<div class='col-sm-2 $if(sourceStateId)$ has-error $endif$'>
			  			<select class='form-control' id='sourceState' name='sourceState'>
			    			<option value='0'>Choose State</option>
			    			$sourceStates$
			    		</select>
			  		</div>
			  		
			  		<div class='col-sm-2 $if(sourceZipcode)$ has-error $endif$'>
			  			<input $if(form)$ value='$form.sourceZipcode$' $endif$ type='number' class='form-control' placeholder='Zipcode' name='sourceZipcode'/>
			  		</div>
			  	</div>
				
				<div class='form-group'>
			  		<label for='destAddress' class='col-sm-2 control-label'>Destination</label>
			  		<div class='col-sm-4 $if(destAddress)$ has-error $endif$'>
			  			<input $if(form)$ value='$form.destAddress$' $endif$ type='text' class='form-control' placeholder='Address' name='destAddress'/>
			  		</div>
			  		
			  		<div class='col-sm-2 $if(destCity)$ has-error $endif$'>
			  			<input $if(form)$ value='$form.destCity$' $endif$ type='text' class='form-control' placeholder='City' name='destCity'/>
			  		</div>
			  		
			  		<div class='col-sm-2 $if(destStateId)$ has-error $endif$'>
			  			<select class='form-control' id='destState' name='destState'>
			    			<option value='0'>Choose State</option>
			    			$destStates$
			    		</select>
			  		</div>
			  		
			  		<div class='col-sm-2 $if(destZipcode)$ has-error $endif$'>
			  			<input $if(form)$ value='$form.destZipcode$' $endif$ type='number' class='form-control' placeholder='Zipcode' name='destZipcode'/>
			  		</div>
			  	</div>
			  	
			  	<div class='form-group'>
			    	<label for='jobDate' class='col-sm-2 control-label'>Date</label>
			    	<div class='col-sm-10 input-append date' id='dob-datepicker' data-date='$todayDate$' data-date-format='mm-dd-yyyy'>
						<input value='$if(form)$$form.jobDate$ $else$$todayDate$ $endif$' size='16' type='text' id='jobDate' name='jobDate' readonly='readonly'>
						<span class='add-on'><span class='glyphicon glyphicon-calendar'></span></span>
					</div>
			  	</div>
			  	
			  	<div class='form-group'>
					<div class='col-sm-offset-2 col-sm-10'>
		  				<button type='submit' class='btn btn-default'>Add</button>
		  				<button type='reset' class='btn btn-default'>Reset</button>
					</div>
				</div>
		  	</form>
		</div>
	</div>
";