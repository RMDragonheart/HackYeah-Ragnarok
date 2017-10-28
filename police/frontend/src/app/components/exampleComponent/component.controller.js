(function() {
	'use strict';

	angular.module('rampup')
		.controller('ComponentController', ComponentController);

	/** @ngInject */
	function ComponentController() {
		var vm = this;

		vm.value = "Something"
	}

})();
