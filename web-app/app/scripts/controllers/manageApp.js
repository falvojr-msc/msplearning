'use strict';

angular.module('msplearningApp').controller('ManageAppsCtrl', function ($scope, appService) {
	
    $scope.init = function() {
        $scope.flows = {
            NEW : "NEW",
            APP_MANAGER : "APP_MANAGER"
        };

        $scope.alerts = [];
        $scope.flow = $scope.flows.APP_MANAGER;
        $scope.app = {};
        $scope.apps = [];
        $scope.loadApps();
        $scope.features = [];
    };

    $scope.success = function(message) {
        $scope.alerts.push({message:message, class:'alert-success'});
    };

    $scope.error = function(message) {
        $scope.alerts.push({message:message, class:'alert-danger'});
    }

    $scope.loadApps = function() {
        var success = function(data) {
            $scope.apps = data;
        };

        appService.getApps(success, $scope.error);
    }

    $scope.newApp = function(){
    	 $scope.getFeactures();
    	 $scope.flow = $scope.flows.NEW;
         $scope.app = {};
    	 $scope.alerts = [];
    };

    $scope.getFeactures = function() {
    	var success = function(data) {
    		addSelectedAttribute(data);
    		$scope.features = data;
    	};

    	appService.getFeactures(success, $scope.error);
    };

    $scope.create = function() {
        $scope.alerts = [];
        $scope.app.features = $scope.features;
        
        var success = function(data) {
            $scope.flow = $scope.flows.APP_MANAGER;
            $scope.loadApps();
            $scope.success("Aplicação gerada com sucesso!");
        };
        
        appService.create($scope.app, success, $scope.error);
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