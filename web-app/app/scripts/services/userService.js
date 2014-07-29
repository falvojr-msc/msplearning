'use strict';

angular.module('msplearningApp').service('userService', function ($rootScope, $cookies, $http) {
	
	this.getAuthenticatedUser = function() {
		if(!$cookies.user) {
			$cookies.user = null;
		}
		return angular.fromJson($cookies.user);
	};

	this.isLogged = function() {
		return this.getAuthenticatedUser() != null;
	};

	this.login = function(user, success, error) {
		$http.post($rootScope.getResourceAddress('user/auth/'), user)
		.success(function(data, status, headers, config){
			$cookies.user = JSON.stringify(data.properties);
			success();
		})
		.error(function(data, status, headers, config){
			error(data);
		});
	};

	this.logout = function() {
		$cookies.user = null;
	};

	this.save = function(user, success, error) {
		$http.post($rootScope.getResourceAddress('teacher'), user)
		.success(function(data) {
			callBackSuccess(data, success);
		})
		.error(function(data) {
			callBackError(data, error);
		});
	};

	function callBackSuccess(data, callback) {
		if(callback !== undefined) {
			callback(data);
		}
	};

	function callBackError(data, callback) {
		if(callback !== undefined) {
			callback(data);
		}
	};
});