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
  });
