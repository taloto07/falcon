$(document).ready(function(){
	$(document).on("click", "a#loadUserInfo", function(){
		
		var email = $(this).attr("email");
		var data = {email : email};
//		$("a#loadUserInfo").parent().parent().css("background-color","white");
//		$(this).parent().parent().css("background-color","blue");
		
		$.ajax({
			type: "POST",
			url: "/FalconTowncar/api/v1/get-user-detail-info",
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
		
		function displayDriver(data){
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
			$("h2#userHeader").show(1000);
			$("table#userDetail").html(content);
			$("table#userDetail").show(1000);
			
			displayJob(data);
		}
		
		function displayCustomer(data){
			var header = data.firstname + " " + data.lastname;
			var content = "<tr><td>Email</td><td>" + data.email + "</td></tr>";
			content += "<tr><td>Phone</td><td>" + data.phone + "</td></tr>";
			content += "<tr><td>Credit Card</td><td>" + data.creditCardNumber + "</td></tr>";
			content += "<tr><td>Expiration</td><td>" + data.expiration + "</td></tr>";
			content += "<tr><td>CCV</td><td>" + data.ccv + "</td></tr>";
			content += "<tr><td>Zipcode</td><td>" + data.zipcode + "</td></tr>";
			
			$("h2#userHeader").hide();
			$("table#userDetail").hide();
			$("h2#userHeader").html(header);
			$("table#userDetail").html(content);
			$("h2#userHeader").show(1000);
			$("table#userDetail").show(1000);
			
			displayJob(data);
		}
	});
	
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
			
			jobs += "<tr>" +
					"<td><a href='#' email='" + customerEmail + "' id='loadUserInfo'>" + customerName + "</a></td>" +
					"<td><a href='#' email='" + driverEmail + "' id='loadUserInfo'>" + driverName + "</a></td>" +
					"<td>" + departure + "</td>" +
					"<td>" + destination + "</td>" +
					"<td>" + distance + "</td>" +
					"<td>" + cost + "</td>" +
					"<td>" + date + "</td>" +
					"<td>" + tip + "</td>" +
					"</tr>";
		}
		
		jobs += "</tbody>";
		
		$("table#userJobHistory").hide();
		$("table#userJobHistory").html(jobs);
		$("table#userJobHistory").show(1000);
	}
	
	$(document).on("click", "a#getAllJob", function(){
		$.ajax({
			type: "POST",
			url: "/FalconTowncar/api/v1/get-all-jobs",
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
