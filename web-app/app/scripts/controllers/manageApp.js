'use strict';

angular.module('msplearningApp').controller('ManageAppsCtrl', function ($scope, appService) {
	$scope.flows = {
		NEW : "NEW",
		APP_MANAGER : "APP_MANAGER"
	};

	$scope.flow = $scope.flows.APP_MANAGER;
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
    		addSelectedAttribute(data);
    		$scope.features = data;
    	};

    	var error = function(msg) {
    		alert(msg);
    	};

    	appService.getFeactures(success, error);
    };

    $scope.isValid = function(features) {
        var valid = true;
        
        return valid;
    };

    $scope.test = function() {
        alert($scope.isValid($scope.features));
    };

    function addSelectedAttribute(features) {
        for (var i = 0; i < features.length; i++) {
            features[i].selected = features[i].isMandatory;
            if(features[i].children.length > 0) {
                addSelectedAttribute(features[i].children);
            }
        }
    }
});