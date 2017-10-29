(function() {
    'use strict';

    angular.module('rampup')
        .controller('InjuryDashboardController', InjuryDashboardController);

    /** @ngInject */
    function InjuryDashboardController($scope, $xsportsClient, $injuryReporter, MapPreview) {
        var vm = this;

        vm.selectedBodyPart = null;
        vm.selectedInjury = null;
        vm.injuriesForBodyPart = [];
        vm.searchWord = "";
        vm.availableSports = [];

        vm.filterAutocompleteResults = filterAutocompleteResults;
        vm.onInjuryChange = onInjuryChange;
        vm.onShowLocationClick = onShowLocationClick;

        $scope.$watch(function() { return $injuryReporter.selectedBodyPart; }, updateSelectedBodyPart);

        function updateSelectedBodyPart() {
            vm.selectedBodyPart = $injuryReporter.selectedBodyPart;
            vm.selectedInjury = $injuryReporter.tryGetReportedInjuryForBodyPart(vm.selectedBodyPart);
            vm.availableSports = [];

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
                var promise = $xsportsClient.retrieveCapabilities($injuryReporter.reportedInjuries);
                promise.then(function(sports) {
                    vm.availableSports = sports;
                });
            }
        }

        function onShowLocationClick(sport) {
            var promise = $xsportsClient.retrieveLocations(sport.id);
            promise.then(function(locations) {
                var mapPreview = new MapPreview(locations);
            });
        }
    }

})();
