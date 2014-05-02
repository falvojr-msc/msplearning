'use strict';

angular.module('msplearningApp').controller('ManageAppsCtrl', function ($scope, appService) {
	$scope.flows = {
		NEW : "NEW",
		APP_MANAGER : "APP_MANAGER"
	};

	$scope.flow = $scope.flows.NEW;
	$scope.app = {};
    $scope.apps = [];
    $scope.features = [];

    $scope.newApp = function(){
    	$scope.getFeactures();
    	$scope.flow = $scope.flows.NEW;
    	$scope.app = {};
    };

    $scope.getFeactures = function() {
    	var success = function(data) {
    		for (var i = 0; i < data.length; i++) {
    			data[i].selected = data[i].isMandatory;
    		}
    		$scope.features = data;
    	};

    	var error = function(msg) {
    		alert(msg);
    	};

    	appService.getFeactures(success, error);
    };
});
