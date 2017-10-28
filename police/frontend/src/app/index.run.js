(function() {
	'use strict';

	angular.module('rampup')
		.run(runBlock);

	/** @ngInject */
	function runBlock($log, $rootScope) {
		$log.debug('runBlock end');
	}

})();
