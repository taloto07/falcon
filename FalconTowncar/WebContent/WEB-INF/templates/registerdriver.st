registerdriver(contextPath, errorMessage, states, makes, models, errorFirstName, errorDOB)::="

<div class='container'>
	<div class='jumbotron'>
		$if(errorMessage)$
				<div class='alert alert-danger'>$errorMessage$</div>
		$endif$
		
		<form class='form-horizontal' role='form' method='post' action='$contextPath$register-driver'>
			<h1><center>Register Driver</center></h1>
			
			<div class='form-group $errorFirstName$'>
		    	<label for='firstname' class='col-sm-2 control-label'>First Name</label>
		    	<div class='col-sm-10'>
		    		<input value='' type='text' class='form-control' id='firstname' placeholder='First Name' name='firstname'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='lastname' class='col-sm-2 control-label'>Last Name</label>
		    	<div class='col-sm-10'>
		    		<input type='text' class='form-control' id='lastname' placeholder='Last Name' name='lastname'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='email' class='col-sm-2 control-label'>Email</label>
		    	<div class='col-sm-10'>
		    		<input type='email' class='form-control' id='email' placeholder='Email' name='email'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='confirmEmail' class='col-sm-2 control-label'>Confirm Email</label>
		    	<div class='col-sm-10'>
		    		<input type='email' class='form-control' id='confirmEmail' placeholder='Confirm Email' name='confirmEmail'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='dob' class='col-sm-2 control-label'>Date of Birth</label>
		    	<div class='col-sm-10 input-append date' id='dob-datepicker' data-date='12-02-1980' data-date-format='mm-dd-yyyy'>
					<input size='16' type='text' placeholder='12-02-2014' id='dob' name='dob' readonly='readonly'>
					<span class='add-on'><span class='glyphicon glyphicon-calendar'></span></span>
				</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputPassword' class='col-sm-2 control-label'>Password</label>
		    	<div class='col-sm-10'>
		    		<input type='password' class='form-control' id='inputPassword' placeholder='Password' name='password'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputConfirmPassword' class='col-sm-2 control-label'>Confirm Password</label>
		    	<div class='col-sm-10'>
		    		<input type='password' class='form-control' id='inputConfirmPassword' placeholder='Confirm Password' name='confirmPassword'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='address' class='col-sm-2 control-label'>Address</label>
		    	<div class='col-sm-10'>
		    		<input type='text' class='form-control' id='address' placeholder='House number Street number' name='address'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='city' class='col-sm-2 control-label'>City</label>
		    	<div class='col-sm-10'>
		    		<input type='text' class='form-control' id='city' placeholder='City' name='city'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='state' class='col-sm-2 control-label'>State</label>
		    	<div class='col-sm-10'>
		    		<select type='text' class='form-control' id='state' placeholder='City' name='state'>
		    			<option value='select'>Select</option>
		    			$states:{state|
		    				<option value='$state.id$'>$state.name$</option>
		    			}$
		    		</select>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='zipcode' class='col-sm-2 control-label'>Zipcode</label>
		    	<div class='col-sm-10'>
		    		<input type='number' class='form-control' id='zipcode' placeholder='Zipcode' name='zipcode'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='phoneNumber' class='col-sm-2 control-label'>Phone Number</label>
		    	<div class='col-sm-10'>
		    		<input type='number' class='form-control' id='phoneNumber' placeholder='Phone Number' name='phoneNumber'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='drivingLicense' class='col-sm-2 control-label'>Driving License</label>
		    	<div class='col-sm-4'>
		    		<input type='text' class='form-control' id='drivingLicense' placeholder='Driving License' name='drivingLicense'>
		   		</div>
		   		
		   		<label for='drivingLicenseExpiration' class='col-sm-2 control-label'>Expiration</label>
		   		<div class='col-sm-4 input-append date' id='DL-datepicker' data-date='12-02-1980' data-date-format='mm-dd-yyyy'>
		    		<input type='text' id='drivingLicenseExpiration' placeholder='Expiration' name='drivingLicenseExpiration' readonly='readonly'>
		    		<span class='add-on'><span class='glyphicon glyphicon-calendar'></span></span>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='DLFH' class='col-sm-2 control-label'>Driving License For Hiring</label>
		    	<div class='col-sm-4'>
		    		<input type='text' class='form-control' id='DLFH' placeholder='Driving License For Hiring' name='DLFH'>
		   		</div>
		   		
		   		<label for='DLFHExpiration' class='col-sm-2 control-label'>Expiration</label>
		   		<div class='col-sm-4 input-append date' id='DLFH-datepicker' data-date='12-02-1980' data-date-format='mm-dd-yyyy'>
		    		<input type='text' id='DLFHExpiration' placeholder='Expiration' name='DLFHExpiration' readonly='readonly'>
		    		<span class='add-on'><span class='glyphicon glyphicon-calendar'></span></span>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='bankName' class='col-sm-2 control-label'>Bank Name</label>
		    	<div class='col-sm-10'>
		    		<input type='text' class='form-control' id='bankName' placeholder='Bank Name' name='bankName'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='backAccountNumber' class='col-sm-2 control-label'>Bank Account Number</label>
		    	<div class='col-sm-10'>
		    		<input type='number' class='form-control' id='backAccountNumber' placeholder='Bank Account Number' name='bankAccountNumber'>
		   		</div>
		  	</div>
		  	
		  	<div class='page-header'>
  				<h2 align='center'>Vehicle</h2>
			</div>
			
		  	<div class='form-group'>
		    	<label for='vehicleMake' class='col-sm-2 control-label'>Make</label>
		    	<div class='col-sm-10'>
			    	<select type='text' class='form-control' id='vehicleMake' placeholder='Make' name='vehicleMake'>
			    			<option value='select'>Select</option>
			    			$makes:{make|
			    				<option value='$make.id$'>$make.name$</option>
			    			}$
			    	</select>
		    	</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='vehicleModel' class='col-sm-2 control-label'>Model</label>
		    	<div class='col-sm-10'>
			    	<select type='text' class='form-control' id='vehicleModel' placeholder='Model' name='vehicleModel'>
			    			<option value='select'>Select</option>
			    			$models:{model|
			    				<option value='$model.id$'>$model.name$</option>
			    			}$
			    	</select>
		    	</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='vehicleYear' class='col-sm-2 control-label'>Year</label>
		    	<div class='col-sm-10'>
		    		<input type='number' class='form-control' id='vehicleYear' placeholder='Year' name='vehicleYear'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='vehicleLicensePlate' class='col-sm-2 control-label'>License Plate</label>
		    	<div class='col-sm-10'>
		    		<input type='text' class='form-control' id='vehicleLicensePlate' placeholder='License Plate' name='vehicleLicensePlate'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='vehicleCapacity' class='col-sm-2 control-label'>Capacity</label>
		    	<div class='col-sm-10'>
		    		<input type='number' class='form-control' id='vehicleCapacity' placeholder='Capacity' name='vehicleCapacity'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
				<div class='col-sm-offset-2 col-sm-10'>
	  				<button type='submit' class='btn btn-default'>Register</button>
	  				<button type='reset' class='btn btn-default'>Reset</button>
				</div>
			</div>
	  	</form>
	</div>
</div>
";