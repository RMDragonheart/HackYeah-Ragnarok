(function() {
    'use strict';

    angular.module('rampup')
        .controller('InjuryDashboardController', InjuryDashboardController);

    /** @ngInject */
    function InjuryDashboardController($scope, $injurySelector, $xsportsClient) {
        var vm = this;

        vm.selectedBodyPart = null;

        $scope.$watch(function() { return $injurySelector.selectedBodyPart; }, updateSelectedBodyPart);

        function updateSelectedBodyPart() {
            vm.selectedBodyPart = $injurySelector.selectedBodyPart;
            if (vm.selectedBodyPart) {
                $xsportsClient.retrieveInjuriesForBodyPart(vm.selectedBodyPart.key).then(function(response) {
                    // TODO: update view
                });
            }
        }
    }

})();
