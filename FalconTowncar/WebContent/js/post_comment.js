$(document).ready(function(){
	
});

function postComment(jobId, userId, firstname, lastname){
	// set variable
	var $textarea = $("div#myModal"+jobId).find("textarea");	// get textarea element
	var comment = $textarea.val();	// get value form textarea
	var username = firstname + " " + lastname;	// set suername 
	var data = {jobId: jobId, userId: userId, comment: comment };
	
	// use ajax to put comment in database
	$.ajax({
		type: "POST",
		url: "/FalconTowncar/api/v1/comment",
		dataType: 'json',
		contentType: "application/json;charset=utf-8",
		data: JSON.stringify(data),
		success: function(data){
			updateView();
		},
		error: function(xhr, status, error){
			$("h2#userHeader").html("xhr: " + xhr +
									"<br/>Status: " + status +
									"<br/>Error: " + error);
		}
	});
	
	function updateView(){
		// set comment to modal header and body
		var $target = $('div#myModal'+jobId).find("div#showComments");
		$target.append("<div class='panel panel-primary'>" +
					"<div class='panel-heading'>" + username + "</div>" +
					"<div class='panel-body'>" + comment +"</div>" +
				"</div>");
		
		// clear textarea
		$textarea.val("");
		
		// increment comment count
		var $messageCountElement = $("span#commentCount"+jobId); 
		var messageCount = $messageCountElement.html();
		messageCount++;
		$messageCountElement.html(messageCount);
	}
}
	
