'use strict';

angular.module('msplearningApp').service('appService', function ($rootScope, $http) {

	this.getFeactures = function(success, error) {
		$http.get($rootScope.getResourceAddress('feature/'))
		.success(function(data) {
			callBackSuccess(data, success);
		})
		.error(function(data) {
			callBackError(data, error);
		});
	};

	this.getApps = function(success, error) {
		$http.get($rootScope.getResourceAddress('app/'))
		.success(function(data) {
			callBackSuccess(data, success);
		})
		.error(function(data) {
			callBackError(data, error);
		});
	};

	this.create = function(app, success, error) {
		var appFeatures = [];

		for (var i = 0; i < app.features.length; i++) {
			var feature = app.features[i];
			appFeatures.push({
				id : {feature : feature}
			});
		};

		app.appFeatures = appFeatures;

		$http.post($rootScope.getResourceAddress('app/'), app)
		.success(function(data) {
			callBackSuccess(data, success);
		})
		.error(function(data) {
			callBackError(data, error);
		});
	};

	function callBackSuccess(data, callback) {
		if(callback !== undefined) {
			callback(data.entity);
		}
	};

	function callBackError(data, callback) {
		if(callback !== undefined) {
			callback(data.entity);
		}
	};
});