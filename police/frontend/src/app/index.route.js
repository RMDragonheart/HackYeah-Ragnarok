(function() {
	'use strict';

	angular.module('rampup')
		.config(routeConfig);

	function routeConfig($routeProvider) {
		$routeProvider
			.when('/', { templateUrl: 'app/pages/main/main.html' })
			.when('/login', { templateUrl: 'app/pages/login/login.html' })
			.when('/signup', { templateUrl: 'app/pages/signup/signup.html' })
			.otherwise({ redirectTo: '/' });
	}

})();
