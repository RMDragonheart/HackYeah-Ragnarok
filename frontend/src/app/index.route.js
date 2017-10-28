(function() {
	'use strict';

	angular.module('rampup')
		.config(routeConfig);

	function routeConfig($routeProvider) {
		$routeProvider
			.when('/', { templateUrl: 'app/pages/main/main.html' })
			.otherwise({ redirectTo: '/' });
	}

})();
