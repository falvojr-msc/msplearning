'use strict';

angular.module('msplearningApp').controller('ManageAppsCtrl', function ($scope, appService) {
	
    $scope.init = function() {
        $scope.flows = {
            NEW : "NEW",
            APP_MANAGER : "APP_MANAGER"
        };

        $scope.flow = $scope.flows.APP_MANAGER;
        $scope.app = {};
        $scope.apps = [];
        $scope.features = [];
    };

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

    $scope.create = function() {
        alert($scope.features);
    };

    $scope.cancel = function() {
        $scope.init();
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