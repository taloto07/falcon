editpage(contextPath, formValue, errorMessage, firstname, lastname, email, 
emailMatch, password, passwordMatch, phoneNumber, dob,confirmPassword, confirmEmail)::="
<!-- Begin page content -->
<div class='container'>
	<div class='jumbotron'>
		$if(errorMessage)$
				<div class='alert alert-danger'>$errorMessage$</div>
		$endif$
		
		<form class='form-horizontal' role='form' method='post' action='$contextPath$registration'>
			<h1><center>New User Registration</center></h1>
			<h3><center>Please fill out the following items</center></h3>
			<br>
			
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
		  	
		  	<!-- This is not a part of Registration page, but rather in Edit Info Page.
		  	<div class='form-group'>
		    	<label for='address' class='col-sm-2 control-label'>Address</label>
		    	<div class='col-sm-10'>
		    		<input type='text' class='form-control' id='address' placeholder='House/Street number Street name' name='address'>
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
		    			<option>Select</option>
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
		  	-->
		  	<div class='form-group'>
		    	<label for='phoneNumber' class='col-sm-2 control-label'>Phone Number</label>
		    	<div class='col-sm-10'>
		    		<input type='number' class='form-control' id='phoneNumber' placeholder='Phone Number' name='phoneNumber'>
		   		</div>
		  	</div>
		  	
		  	<!-- Bank account info shouldn't be requested on signing up
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
		  	//-->
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