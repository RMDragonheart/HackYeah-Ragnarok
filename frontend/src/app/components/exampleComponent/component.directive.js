(function() {
	'use strict';

	angular
		.module('rampup')
		.directive('taskBar', TaskBar);

	/** @ngInject */
	function TaskBar() {
		return {
			restrict: 'E',
			templateUrl: 'app/components/exampleComponent/component.html',
			controller: 'ComponentController',
			controllerAs: 'componentCtrl',
			bindToController: true
		};
	}

})();
