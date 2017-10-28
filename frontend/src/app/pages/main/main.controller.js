(function() {
	'use strict';

	angular.module('rampup')
		.controller('MainPageController', MainPageController);

	/** @ngInject */
	function MainPageController() {
		var vm = this;

		vm.title = "Main Page"
	}

})();
