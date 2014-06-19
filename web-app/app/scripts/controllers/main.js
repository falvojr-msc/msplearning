'use strict';

angular.module('msplearningApp').controller('MainCtrl', function ($scope, userService) {
    
    $scope.init = function() {
        $scope.user = userService.getAuthenticatedUser();
    };

    $scope.isLogged = function() {
    	return userService.isLogged();
    };

    $scope.logout = function() {
    	userService.logout();
    };

 });