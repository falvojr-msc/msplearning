'use strict';

angular.module('msplearningApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
  ]).config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html'
      })
      .when('/signin', {
        templateUrl: 'views/signin.html',
        controller: 'SigninCtrl'
      })
      .when('/apps', {
        templateUrl: 'views/manageApps.html',
        controller: 'ManageAppsCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  }).run(function($rootScope) {
    var SERVER_URL = "http://localhost:8080/rest-app/rest/";
    
    $rootScope.getResourceAddress = function(resource) {
      return SERVER_URL + resource
    }
  });
