(function() {
	'use strict';

	angular.module('rampup')
		.controller('ToolbarController', ToolbarController);

	/** @ngInject */
	function ToolbarController() {
		var vm = this;

		vm.name = "Vikings App"
	}

})();
