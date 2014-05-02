'use strict';

angular.module('msplearningApp').controller('SigninCtrl', function ($scope, $location, userService) {
	$scope.flows = {
		SIGNIN : "SIGNIN",
		LOGIN : "LOGIN"
	};

	$scope.flow = $scope.flows.LOGIN;

	$scope.user = {};

	$scope.confirmPassword = "";

	$scope.register = function() {
		$scope.flow = $scope.flows.SIGNIN;
	}

	$scope.cancel = function() {
		$scope.user = {};
		$scope.flow = $scope.flows.LOGIN;
	}

	$scope.save = function() {
		userService.save($scope.user);
	}

	$scope.login = function() {
		var success = function() {
			$location.path("/");
		};

		var error = function(msg) {
			console.log(msg);
		};

		userService.login($scope.user, success, error);
	}
});
