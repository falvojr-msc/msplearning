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

});