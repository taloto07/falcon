$(document).ready(function(){
	$("h2#otherVehicleHeader").hide();
	$(document).on("click", "a#loadUserInfo", function(){
		
		var email = $(this).attr("email");
		var data = {email : email};
//		$("a#loadUserInfo").parent().parent().css("background-color","white");
//		$(this).parent().parent().css("background-color","blue");
		
		$.ajax({
			type: "POST",
			url: "/FalconTowncar/api/v1/user",
			dataType: 'json',
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify(data),
			success: function(data){
				if (data.userType.toLowerCase() == "driver".toLowerCase())
					displayDriver(data);
				else
					displayCustomer(data);
			},
			error: function(xhr, status, error){
				$("h2#userHeader").html("xhr: " + xhr +
										"<br/>Status: " + status +
										"<br/>Error: " + error);
			}
		});
		
		// display driver information
		function displayDriver(data){
			displayJob(data);
			
			var image;
			if (data.userImage == null)
				image = "profile-default.png";
			else
				image = data.userImage;
			var header = "<img src='/FalconTowncar/img/profile/" + image + "' alt='profile image' class='img-circle'  width='100px' height='100px'>"; 
			header += " " + data.firstname + " " + data.lastname;
			
			var content = "<tr><td>Email</td><td>" + data.email + "</td></tr>";
			content += "<tr><td>Phone</td><td>" + data.phone + "</td></tr>";
			content += "<tr><td>Date of Birth</td><td>" + data.dob + "</td></tr>";
			content += "<tr><td>Bank</td><td>" + data.bankName + "</td></tr>";
			content += "<tr><td>Account</td><td>" + data.bankAccountNumber + "</td></tr>";
			content += "<tr><td>Adddress</td><td>" + data.house + " " + data.street + "</td></tr>";
			content += "<tr><td>City</td><td>" + data.city + "</td></tr>";
			content += "<tr><td>State</td><td>" + data.state + "</td></tr>";
			content += "<tr><td>Zipcode</td><td>" + data.zipcode + "</td></tr>";
			
			$("h2#userHeader").hide();
			$("table#userDetail").hide();
			$("h2#userHeader").html(header);
			$("h2#userHeader").show(500);
			$("table#userDetail").html(content);
			$("table#userDetail").show(500);
			
			// write current vehicle out
			var currentVehicle = "<div class='alert alert-danger' role='alert'> No Current Vehicle!</div>";
			if (data.currentVehicle){
				currentVehicle = "<tbody>" +
				"<tr>" +
					"<td>Make</td><td>" + data.currentVehicle.make + "</td>" +
				"</tr>" +
				"<tr>" +
					"<td>Model</td><td>" + data.currentVehicle.model + "</td>" +
				"</tr>" +
				"<tr>" +
					"<td>Year</td><td>" + data.currentVehicle.year + "</td>" +
				"</tr>" +
				"<tr>" +
					"<td>License Plate</td><td>" + data.currentVehicle.licensePlate + "</td>" +
				"</tr>" +
				"<tr>" +
					"<td>Capacity</td><td>" + data.currentVehicle.capacity + "</td>" +
				"</tr>" +
				"</tbody>";
			}
			
			// write other vehicles out
			var otherVehicles = "";
			if (data.vehicles.length){
				for (var i in data.vehicles){
					otherVehicles += "<table class='table table-striped'>" +
									"<caption class='alert alert-info'>Car " + (parseInt(i) + 1) + "</caption>" +
									"<tbody>" +
										"<tr><td>Make</td><td>" + data.vehicles[i].make + "</td></tr>" +
										"<tr><td>Model</td><td>" + data.vehicles[i].model + "</td></tr>" +
										"<tr><td>Year</td><td>" + data.vehicles[i].year + "</td></tr>" +
										"<tr><td>License Plage</td><td>" + data.vehicles[i].licensePlate + "</td></tr>" +
										"<tr><td>Capacity</td><td>" + data.vehicles[i].capacity + "</td></tr>" +
									"</tbody>" +
									"</table>";
				}
			}else{
				otherVehicles = "<div class='alert alert-info' role='alert'> No Other Vehicles.</div>";
			}
			// show other vehicles and header
			$("div#otherVehicleDetail").show();
			$("h2#otherVehicleHeader").show();
			
			// add current vehicle to page
			$("h2#currentVehicleHeader").html("Current Vehicle");
			$("table#currentVehicleDetail").html(currentVehicle);
			
			$("div#otherVehicleDetail").html(otherVehicles);
			
			$("div#driverCars").hide();
			$("div#driverCars").show(1000);
		}
		
		// display customer information
		function displayCustomer(data){
			displayJob(data);
			
			var header = data.firstname + " " + data.lastname;
			var content = "<tr><td>Email</td><td>" + data.email + "</td></tr>";
			content += "<tr><td>Phone</td><td>" + data.phone + "</td></tr>";
			content += "<tr><td>Credit Card</td><td>" + data.creditCardNumber + "</td></tr>";
			content += "<tr><td>Expiration</td><td>" + data.expiration + "</td></tr>";
			content += "<tr><td>CCV</td><td>" + data.ccv + "</td></tr>";
			content += "<tr><td>Zipcode</td><td>" + data.zipcode + "</td></tr>";
			
			$("h2#userHeader").hide();
			$("table#userDetail").hide();
			$("div#otherVehicleDetail").hide();
			$("h2#otherVehicleHeader").hide();
			$("h2#userHeader").html(header);
			$("table#userDetail").html(content);
			$("h2#userHeader").show(500);
			$("table#userDetail").show(500);
		}
	});
	
	// display job information
	function displayJob(data){
		var jobs = "<thead><tr>" +
		"<th>Customer</th>" +
		"<th>Driver</th>" +
		"<th>Departure</th>" +
		"<th>Destination</th>" +
		"<th>Distance</th>" +
		"<th>Cost</th>" +
		"<th>Date</th>" +
		"<th>Tip</th>" +
		"<th>Comments</th>" +
		"</tr></thead><tbody>";

		for (var index in data.jobs){
			var customerName = data.jobs[index].customerName;
			var customerEmail = data.jobs[index].customerEmail;
			var driverEmail = data.jobs[index].driverEmail;
			var driverName = data.jobs[index].driverName;
			var departure = data.jobs[index].departure;
			var destination = data.jobs[index].destination;
			var distance = data.jobs[index].distance;
			var cost = data.jobs[index].cost;
			var date = data.jobs[index].date;
			var tip = data.jobs[index].tip;
			var comments = data.jobs[index].comments;
			var commentList = "";
			
			// get all comments and put them into commentList
			for (var i = 0; i < comments.length; i++){
				var firstname = comments[i].firstname;
				var lastname = comments[i].lastname;
				
				firstname = firstname[0].toUpperCase() + firstname.slice(1);
				lastname = lastname[0].toUpperCase() + lastname.slice(1);
				
				var username = "<b>" + firstname + " " + lastname + "</b>";
				
				var comment = comments[i].comment;
				
				commentList += "<div class='panel panel-primary'>" +
								"<div class='panel-heading'>" +
									username +
								"</div>" +
								
								"<div class='panel-body'>" +
									comment +
								"</div>" +
							"</div>";
			}
			
			jobs += "<tr>" +
						"<td><a href='#' email='" + customerEmail + "' id='loadUserInfo'>" + customerName + "</a></td>" +
						"<td><a href='#' email='" + driverEmail + "' id='loadUserInfo'>" + driverName + "</a></td>" +
						"<td>" + departure + "</td>" +
						"<td>" + destination + "</td>" +
						"<td>" + distance + "</td>" +
						"<td>" + cost + "</td>" +
						"<td>" + date + "</td>" +
						"<td>" + tip + "</td>" +
						"<td>" +
							"<button type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#myModal" + index + "'>"+
  								"Comments <span class='badge' id='commentCount" + index + "'>" + comments.length + "</span>" +
							"</button>" +
							
							"<div class='modal fade' id='myModal" + index + "' tab-index='-1' role='dialog' aria-labeldby='myModalLabel' aria-hidden='true'>"+
								"<div class='modal-dialog'>" +
									"<div class='modal-content'>" +
										"<div class='modal-header'>" +
											"<button type='button' class='close' data-dismiss='modal'>"+
												"<span aria-hidden='true'>&times;</span>" +
												"<span class='rs-only'>Close</span>" +
											"</button>" +
											"<h4 class='modal-title' id='myModalLabel'>Modal Header</h4>" +
										"</div>" +
									
										"<div class='modal-body'>" +
											commentList +
										"</div>" +
									
										"<div class='modal-footer'>" +
											"<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>" +
											"<button type='button' class='btn btn-primary'>Save</button>" +
										"</div>" +
									"</div>" +
								"</div>" +
							"</div>" +
						"</td>" +
					"</tr>";
//			<a id='getComment' jobId='" + jobId + "'>" + comments + "</a>
		}
		
		jobs += "</tbody>";
		
		$("table#userJobHistory").hide();
		$("table#userJobHistory").html(jobs);
		$("table#userJobHistory").show(1000);
		
		$("h2#currentVehicleHeader").html("Car Detail Information Displayed Here");
		$("table#currentVehicleDetail").html("");
		$("div#driverCars").show(500);
		
		// hide other vehicles and header
		$("div#otherVehicleDetail").hide();
		$("h2#otherVehicleHeader").hide();
	}
	
	// retrieve alll jobs information
	$(document).on("click", "a#getAllJob", function(){
		$.ajax({
			type: "POST",
			url: "/FalconTowncar/api/v1/jobs",
			dataType: 'json',
			contentType: "application/json;charset=utf-8",
			
			success: function(data){
				displayJob(data);
				$("h2#userHeader").html("User Detail Information Displayed Here");
				$("table#userDetail").html("");
			},
			error: function(xhr, status, error){
				$("h2#userHeader").html("xhr: " + xhr +
										"<br/>Status: " + status +
										"<br/>Error: " + error);
			}
		});
	});
});
