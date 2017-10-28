(function() {
	'use strict';

	angular
		.module('rampup')
		.directive('exampleComponent', ExampleComponent);

	/** @ngInject */
	function ExampleComponent() {
		return {
			restrict: 'E',
			templateUrl: 'app/components/exampleComponent/component.html',
			controller: 'ComponentController',
			controllerAs: 'componentCtrl',
			bindToController: true
		};
	}

})();