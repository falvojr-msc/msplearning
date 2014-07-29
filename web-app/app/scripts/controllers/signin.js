'use strict';

angular.module('msplearningApp').controller('SigninCtrl', function ($scope, $location, userService) {
	$scope.flows = {
		SIGNIN : "SIGNIN",
		LOGIN : "LOGIN"
	};

	$scope.flow = $scope.flows.LOGIN;
	$scope.user = { gender : 'M' };
	$scope.confirmPassword = "";
	$scope.alerts = [];

	$scope.register = function() {
		$scope.alerts = [];
		$scope.flow = $scope.flows.SIGNIN;
	}

	$scope.cancel = function() {
		$scope.alerts = [];
		$scope.user = { gender : 'M' };
		$scope.confirmPassword = "";
		$scope.flow = $scope.flows.LOGIN;
	}

	$scope.save = function() {
		$scope.alerts = [];
		var success = function(data) {
            $scope.success("Usuário registrado com sucesso!");
            $scope.flow = $scope.flows.LOGIN;
        };
		
		userService.save($scope.user, success, $scope.error);
	}

	$scope.login = function() {
		var success = function() {
			$location.path("/");
		};

		userService.login($scope.user, success, $scope.error);
	}

	$scope.error = function(message) {
        $scope.alerts.push({message:message, class:'alert-danger'});
    }

    $scope.success = function(message) {
        $scope.alerts.push({message:message, class:'alert-success'});
    };
});
