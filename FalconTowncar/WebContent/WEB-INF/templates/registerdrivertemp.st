registerdrivertemp(contextPath, errorMessage, firstname, lastname, email, emailMatch, password, passwordMatch, formValue, confirmPassword, confirmEmail)::="
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
		  	
		  	<div class='form-group'>
				<div class='col-sm-offset-2 col-sm-10'>
	  				<button type='submit' class='btn btn-default'>Register</button>
	  				<button type='reset' class='btn btn-default'>Reset</button>
				</div>
			</div>
		</form> 
	</div> <!-- /dive jumbotron-->
</div> <!-- /container-->
";