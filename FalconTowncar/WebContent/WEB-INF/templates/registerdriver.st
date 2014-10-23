registerdriver(contextPath, formValue, errorMessage, states, makes, models, 
firstname, lastname, email, emailMatch, password, passwordMatch, address, city, 
state, zipcode, phoneNumber, drivingLicense, DLFH, bankName, bankAccountNumber,
vehicleMake, vehicleModel, vehicleYear, licensePlate, capacity, dob, drivingLicenseExpiration, DLFHExpiration,
confirmPassword, confirmEmail)::="

<div class='container'>
	<div class='jumbotron'>
		$if(errorMessage)$
			<div class='alert alert-danger'>
				<h4>$errorMessage$</h4>
			</div>
		$endif$
		
		<form class='form-horizontal' role='form' method='post' action='$contextPath$register-driver'>
			<h1><center>Register Driver</center></h1>
			
			<div class='form-group $if(firstname)$ has-error $endif$'>
		    	<label for='firstname' class='col-sm-2 control-label'>First Name</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(firstname)$ 
		    				placeholder='$firstname$' 
		    			$elseif(formValue.firstname)$ 
		    				value='$formValue.firstname$'
		    			$else$
		    				placeholder='First Name'
		    		 	$endif$ 
		    		 	type='text' class='form-control' id='firstname' name='firstname'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(lastname)$ has-error $endif$'>
		    	<label for='lastname' class='col-sm-2 control-label'>Last Name</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(lastname)$ 
		    				placeholder='$lastname$' 
		    			$elseif(formValue.lastname)$ 
		    				value='$formValue.lastname$'
		    			$else$ 
		    				placeholder='Last Name'
		    			$endif$ 
		    			type='text' class='form-control' id='lastname' name='lastname'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(email)$ has-error $endif$'>
		    	<label for='email' class='col-sm-2 control-label'>Email</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(email)$ 
		    				placeholder='$email$' 
		    			$elseif(formValue.email)$ 
		    				value='$formValue.email$'
		    			$else$
		    				placeholder='Email' 
		    			$endif$ 
		    			type='email' class='form-control' id='email' name='email'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(emailMatch)$ has-error $endif$'>
		    	<label for='confirmEmail' class='col-sm-2 control-label'>Confirm Email</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(emailMatch)$ 
		    				placeholder='$emailMatch$' 
		    			$elseif(formValue.confirmEmail)$ 
		    				value='$formValue.confirmEmail$'
		    			$else$
		    				placeholder='Confirm Email'
		    			$endif$ 
		    			type='email' class='form-control' id='confirmEmail' name='confirmEmail'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(dob)$ has-error $endif$'>
		    	<label for='dob' class='col-sm-2 control-label'>Date of Birth</label>
		    	<div class='col-sm-10 input-append date' id='dob-datepicker' data-date='12-02-1980' data-date-format='mm-dd-yyyy'>
					<input 
						$if(dob)$ 
							placeholder='$dob$' 
						$elseif(formValue.dob)$ 
							value='$formValue.dob$' 
						$else$
							placeholder='12-02-2014'
						$endif$ 
						size='16' type='text' id='dob' name='dob' readonly='readonly'>
					<span class='add-on'><span class='glyphicon glyphicon-calendar'></span></span>
				</div>
		  	</div>
		  	
		  	<div class='form-group $if(password)$ has-error $endif$'>
		    	<label for='inputPassword' class='col-sm-2 control-label'>Password</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(password)$ 
		    				placeholder='$password$' 
		    			$elseif(formValue.password)$ 
		    				value='$formValue.password$'
		    			$else$
		    				placeholder='Password' 
		    			$endif$ 
		    			type='password' class='form-control' id='inputPassword' name='password'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(passwordMatch)$ has-error $endif$'>
		    	<label for='inputConfirmPassword' class='col-sm-2 control-label'>Confirm Password</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(passwordMatch)$ 
		    				placeholder='$passwordMatch$' 
		    			$elseif(formValue.confirmPassword)$ 
		    				value='$formValue.confirmPassword$'
		    			$else$
		    				 placeholder='Confirm Password'
		    			$endif$ 
		    			type='password' class='form-control' id='inputConfirmPassword' name='confirmPassword'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(address)$ has-error $endif$'>
		    	<label for='address' class='col-sm-2 control-label'>Address</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(address)$ 
		    				placeholder='$address$' 
		    			$elseif(formValue.address)$ 
		    				value='$formValue.address$'
		    			$else$
		    				placeholder='House number Street number'
		    			$endif$ 
		    			type='text' class='form-control' id='address' name='address'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(city)$ has-error $endif$'>
		    	<label for='city' class='col-sm-2 control-label'>City</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(city)$ 
		    				placeholder='$city$' 
		    			$elseif(formValue.city)$ 
		    				value='$formValue.city$'
		    			$else$
		    				placeholder='City' 
		    			$endif$ 
		    			type='text' class='form-control' id='city' name='city'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(state)$ has-error $endif$'>
		    	<label for='state' class='col-sm-2 control-label'>State</label>
		    	<div class='col-sm-10'>
		    		<select type='text' class='form-control' id='state' placeholder='State' name='state'>
		    			<option value='0'>Select</option>
		    			$states$
		    		</select>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(zipcode)$ has-error $endif$'>
		    	<label for='zipcode' class='col-sm-2 control-label'>Zipcode</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(zipcode)$ 
		    				placeholder='$zipcode$' 
		    			$elseif(formValue.zipcode)$ 
		    				value='$formValue.zipcode$'
		    			$else$
		    				placeholder='Zipcode' 
		    			$endif$ 
		    			type='number' class='form-control' id='zipcode' name='zipcode'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(phoneNumber)$ has-error $endif$'>
		    	<label for='phoneNumber' class='col-sm-2 control-label'>Phone Number</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(phoneNumber)$ 
		    				placeholder='$phoneNumber$' 
		    			$elseif(formValue.phoneNumber)$ 
		    				value='$formValue.phoneNumber$'
		    			$else$
		    				placeholder='Phone Number' 
		    			$endif$ 
		    			type='number' class='form-control' id='phoneNumber' name='phoneNumber'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(drivingLicense)$ has-error $endif$'>
		    	<label for='drivingLicense' class='col-sm-2 control-label'>Driving License</label>
		    	<div class='col-sm-4'>
		    		<input 
		    			$if(drivingLicense)$ 
		    				placeholder='$drivingLicense$' 
		    			$elseif(formValue.drivingLicense)$ 
		    				value='$formValue.drivingLicense$'
		    			$else$
		    				placeholder='Driving License'
		    			$endif$ 
		    			type='text' class='form-control' id='drivingLicense' name='drivingLicense'>
		   		</div>
		   		
		   		<label for='drivingLicenseExpiration' class='col-sm-2 control-label'>Expiration</label>
		   		<div class='col-sm-4 input-append date' id='DL-datepicker' data-date='12-02-1980' data-date-format='mm-dd-yyyy'>
		    		<input 
		    			$if(drivingLicenseExpiration)$ 
		    				placeholder='$drivingLicenseExpiration$' 
		    			$elseif(formValue.drivingLicenseExpiration)$ 
		    				value='$formValue.drivingLicenseExpiration$'
		    			$else$
		    				placeholder='05-12-2014'
		    			$endif$ 
		    			 
		    			type='text' id='drivingLicenseExpiration' name='drivingLicenseExpiration' readonly='readonly'>
		    		<span class='add-on'><span class='glyphicon glyphicon-calendar'></span></span>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(DLFH)$ has-error $endif$'>
		    	<label for='DLFH' class='col-sm-2 control-label'>Driving License For Hiring</label>
		    	<div class='col-sm-4'>
		    		<input 
		    			$if(DLFH)$ 
		    				placeholder='$DLFH$' 
		    			$elseif(formValue.DLFH)$ 
		    				value='$formValue.DLFH$'
		    			$else$
		    				placeholder='Driving License For Hiring'
		    			$endif$ 
		    			type='text' class='form-control' id='DLFH' name='DLFH'>
		   		</div>
		   		
		   		<label for='DLFHExpiration' class='col-sm-2 control-label'>Expiration</label>
		   		<div class='col-sm-4 input-append date' id='DLFH-datepicker' data-date='12-02-1980' data-date-format='mm-dd-yyyy'>
		    		<input 
		    			$if(DLFHExpiration)$ 
		    				placeholder='$DLFHExpiration$' 
		    			$elseif(formValue.DLFHExpiration)$ 
		    				value='$formValue.DLFHExpiration$'
		    			$else$
		    				placeholder='05-12-2014'
		    			$endif$ 
		    			type='text' id='DLFHExpiration' name='DLFHExpiration' readonly='readonly'>
		    		<span class='add-on'><span class='glyphicon glyphicon-calendar'></span></span>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(bankName)$ has-error $endif$'>
		    	<label for='bankName' class='col-sm-2 control-label'>Bank Name</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(bankName)$ 
		    				placeholder='$bankName$' 
		    			$elseif(formValue.bankName)$ 
		    				value='$formValue.bankName$'
		    			$else$
		    				placeholder='Bank Name'
		    			$endif$ 
		    			type='text' class='form-control' id='bankName' name='bankName'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(bankAccountNumber)$ has-error $endif$'>
		    	<label for='backAccountNumber' class='col-sm-2 control-label'>Bank Account Number</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(bankAccountNumber)$ 
		    				placeholder='$bankAccountNumber$' 
		    			$elseif(formValue.bankAccountNumber)$ 
		    				value='$formValue.bankAccountNumber$'
		    			$else$
		    				placeholder='Bank Account Number'
		    			$endif$ 
		    			type='number' class='form-control' id='backAccountNumber' name='bankAccountNumber'>
		   		</div>
		  	</div>
		  	
		  	<div class='page-header'>
  				<h2 align='center'>Vehicle</h2>
			</div>
			
		  	<div class='form-group $if(vehicleMake)$ has-error $endif$'>
		    	<label for='vehicleMake' class='col-sm-2 control-label'>Make</label>
		    	<div class='col-sm-10'>
			    	<select type='text' class='form-control' id='vehicleMake' placeholder='Make' name='vehicleMake'>
			    			<option value='0'>Select</option>
			    			$makes$
			    	</select>
		    	</div>
		  	</div>
		  	
		  	<div class='form-group $if(vehicleModel)$ has-error $endif$'>
		    	<label for='vehicleModel' class='col-sm-2 control-label'>Model</label>
		    	<div class='col-sm-10'>
			    	<select type='text' class='form-control' id='vehicleModel' placeholder='Model' name='vehicleModel'>
			    			<option value='0'>Select</option>
			    			$models$
			    	</select>
		    	</div>
		  	</div>
		  	
		  	<div class='form-group $if(vehicleYear)$ has-error $endif$'>
		    	<label for='vehicleYear' class='col-sm-2 control-label'>Year</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(vehicleYear)$ 
		    				placeholder='$vehicleYear$' 
		    			$elseif(formValue.vehicleYear)$ 
		    				value='$formValue.vehicleYear$'
		    			$else$
		    				placeholder='Year'
		    			$endif$ 
		    			type='number' class='form-control' id='vehicleYear' placeholder='Year' name='vehicleYear'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(licensePlate)$ has-error $endif$'>
		    	<label for='vehicleLicensePlate' class='col-sm-2 control-label'>License Plate</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(licensePlate)$ 
		    				placeholder='$licensePlate$' 
		    			$elseif(formValue.licensePlate)$ 
		    				value='$formValue.licensePlate$'
		    			$else$
		    				placeholder='License Plate'
		    			$endif$ 
		    			type='text' class='form-control' id='vehicleLicensePlate' name='vehicleLicensePlate'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group $if(capacity)$ has-error $endif$'>
		    	<label for='vehicleCapacity' class='col-sm-2 control-label'>Capacity</label>
		    	<div class='col-sm-10'>
		    		<input 
		    			$if(capacity)$ 
		    				placeholder='$capacity$' 
		    			$elseif(formValue.capacity)$ 
		    				value='$formValue.capacity$'
		    			$else$
		    				placeholder='Capacity'
		    			$endif$ 
		    			type='number' class='form-control' id='vehicleCapacity' name='vehicleCapacity'>
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