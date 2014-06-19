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
        controller: 'ManageAppsCtrl',
        auth: true
      })
      .otherwise({
        redirectTo: '/'
      });
  }).run(function($rootScope, $location, $route, userService) {
    var SERVER_URL = "http://localhost:8080/rest-app/rest/";
    
    $rootScope.getResourceAddress = function(resource) {
      return SERVER_URL + resource
    }

    $rootScope.$on('$locationChangeStart', function(evt, next, current) {
      var nextPath = $location.path();
      var nextRoute = $route.routes[nextPath];

      if (nextRoute && nextRoute.auth && !userService.isLogged()) {
        $location.path("/");
      }
    });
  });
