'use strict';

angular.module('msplearningApp').service('userService', function ($cookies, $http) {

	var BASE_URL = "http://192.168.1.104:8080/rest-app/rest/";
	$cookies.user = null;

	this.getAuthenticatedUser = function() {
		var user = angular.fromJson($cookies.user);
		return user;
	};	

	this.login = function(user, success, error) {
		//$http.post(BASE_URL + "user/auth/", user)
		//.success(function(data, status, headers, config){
		//	$cookies.user = JSON.stringfy(data.entity);
			$cookies.user = JSON.stringify({firstName:"geremias"});
			success();
		//})
		//.error(function(data, status, headers, config){
		//	error(data);
		//});
	};

	this.logout = function() {
		$cookies.user = null;
	};

	this.save = function(user) {
		alert('#request for save: ' + user.username);
	};
});