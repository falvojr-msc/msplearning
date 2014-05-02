'use strict';

angular.module('msplearningApp').service('appService', function ($http) {

	var BASE_URL = "http://localhost:8080/rest-app/rest/";

	this.getFeactures = function(success, error) {
		$http.get(BASE_URL + "feature/")
		.success(function(data, status, headers, config){
			success(data.entity);
		})
		.error(function(data, status, headers, config){
			error(data);
		});
	};

});