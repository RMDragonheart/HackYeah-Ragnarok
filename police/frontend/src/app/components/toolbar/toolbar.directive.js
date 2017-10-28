(function() {
	'use strict';

	angular
		.module('rampup')
		.directive('toolbarComponent', ToolbarComponent);

	/** @ngInject */
	function ToolbarComponent() {
		return {
			restrict: 'E',
			templateUrl: 'app/components/toolbar/toolbar.html',
			controller: 'ToolbarController',
			controllerAs: 'toolbarCtrl',
			bindToController: true
		};
	}

})();
