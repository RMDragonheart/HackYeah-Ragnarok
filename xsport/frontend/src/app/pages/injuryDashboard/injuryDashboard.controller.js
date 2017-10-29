(function() {
    'use strict';

    angular.module('rampup')
        .controller('InjuryDashboardController', InjuryDashboardController);

    /** @ngInject */
    function InjuryDashboardController($scope, $injurySelector) {
        var vm = this;

        vm.selectedBodyPart = null;

        $scope.$watch(function() { return $injurySelector.selectedBodyPart; }, updateSelectedBodyPart);

        function updateSelectedBodyPart() {
            vm.selectedBodyPart = $injurySelector.selectedBodyPart;
        }
    }

})();
