registerdriver(contextPath, errorMessage)::="

<div class='container'>
	<div class='jumbotron'>
		$if(errorMessage)$
				<div class='alert alert-danger'>$errorMessage$</div>
		$endif$
		
		<form class='form-horizontal' role='form' method='post' action='register_driver_check'>
			<h1><center>Register Driver</center></h1>
			
			<div class='form-group'>
		    	<label for='firstname' class='col-sm-2 control-label'>First Name</label>
		    	<div class='col-sm-10'>
		    		<input type='text' class='form-control' id='firstname' placeholder='First Name' name='firstname'>
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
		    	<div class='col-sm-10 input-append date' id='dob-datepicker' data-date='12-02-1980' data-date-format='dd-mm-yyyy'>
					<input size='16' type='text' value='12-02-2014' id='dob' name='dob' disabled>
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
				<div class='col-sm-offset-2 col-sm-10'>
	  				<button type='submit' class='btn btn-default'>Log In</button>
	  				<button type='reset' class='btn btn-default'>Reset</button>
				</div>
			</div>
	  	</form>
	</div>
</div>
";