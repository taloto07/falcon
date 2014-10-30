(function(){
	var app = angular.module('ownerApp', []);
	
	app.controller("userDetailController", ['$http', function($http){
		var myApp = this;
		myApp.userDetail = [];
		
		this.getUserDetail = function(email){
			alert(email);
		}
	}]);
})();