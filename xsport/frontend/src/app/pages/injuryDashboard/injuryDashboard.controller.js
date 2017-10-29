(function() {
    'use strict';

    angular.module('rampup')
        .controller('InjuryDashboardController', InjuryDashboardController);

    /** @ngInject */
    function InjuryDashboardController($scope, $xsportsClient, $injuryReporter) {
        var vm = this;

        vm.selectedBodyPart = null;
        vm.selectedInjury = null;
        vm.injuriesForBodyPart = [];
        vm.searchWord = "";

        vm.filterAutocompleteResults = filterAutocompleteResults;
        vm.onInjuryChange = onInjuryChange;

        $scope.$watch(function() { return $injuryReporter.selectedBodyPart; }, updateSelectedBodyPart);

        function updateSelectedBodyPart() {
            vm.selectedBodyPart = $injuryReporter.selectedBodyPart;
            vm.selectedInjury = $injuryReporter.tryGetReportedInjuryForBodyPart(vm.selectedBodyPart);

            if (vm.selectedBodyPart) {
                $xsportsClient.retrieveInjuriesForBodyPart(vm.selectedBodyPart).then(function(injuries) {
                    vm.injuriesForBodyPart = injuries;
                });
            }
        }

        function filterAutocompleteResults(searchWord) {
            if (searchWord) {
                return vm.injuriesForBodyPart.filter(function(injury) {
                    return injury.name.toLowerCase().indexOf(searchWord.toLowerCase()) === 0;
                });
            }
            else {
                return vm.injuriesForBodyPart;
            }
        }

        function onInjuryChange(injury) {
            $injuryReporter.removeReportedInjuryForSelectedBodyPart();

            if (injury) {
                $injuryReporter.includeToReport(injury);
            }
        }
    }

})();