'use strict';

angular.module('msplearningApp').controller('MainCtrl', function ($scope, userService) {
    $scope.isLogged = function() {
    	var user = userService.getAuthenticatedUser();
    	if(user == null) {
    		$scope.user = {};
    	} else {
    		$scope.user = user;
    	}
    	return user != null;
    };

    $scope.logout = function() {
    	userService.logout();
    };
 });