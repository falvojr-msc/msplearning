'use strict';

angular.module('msplearningApp').service('appService', function ($rootScope, $http) {

	this.getFeactures = function(success, error) {
		$http.get($rootScope.getResourceAddress('feature/'))
		.success(function(data, status, headers, config){
			success(data.entity);
		})
		.error(function(data, status, headers, config){
			error(data);
		});
	};

	this.create = function(app) {
		var appFeatures = [];

		for (var i = 0; i < app.features.length; i++) {
			var feature = app.features[i];
			appFeatures.push({
				id : {feature : feature}
			});
		};

		app.appFeatures = appFeatures;

		$http.post($rootScope.getResourceAddress('app/'), app)
		.success(function(data, status, headers, config){
			alert('sucesso!');
		})
		.error(function(data, status, headers, config){
			alert('erro!');
		});
	};

});