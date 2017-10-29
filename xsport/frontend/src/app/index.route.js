(function() {
	'use strict';

	angular.module('rampup')
		.config(routeConfig);

	function routeConfig($routeProvider) {
		$routeProvider
			.when('/', { redirectTo: '/dashboard' })
			.when('/login', { templateUrl: 'app/pages/login/login.html' })
            .when('/dashboard', { templateUrl: 'app/pages/injuryDashboard/injuryDashboard.html' })
			.otherwise({ redirectTo: '/' });
	}

})();
