(function() {
    'use strict';

    angular.module('rampup')
        .factory('$injuryReporter', InjuryReporterFactory);

    /** @ngInject */
    function InjuryReporterFactory($rootScope) {
        function InjuryReporter() {
            this.selectedBodyPart = null;
            this.reportedInjuries = [];
        }

        InjuryReporter.prototype.includeToReport = function(injury) {
            var oldValue = this.reportedInjuries.slice(0);
            this.reportedInjuries.push(injury);
            $rootScope.$emit('reportedInjuries/change', [ oldValue, this.reportedInjuries ]);
        };

        InjuryReporter.prototype.removeFromReport = function(injury) {
            var oldValue = this.reportedInjuries.slice(0);
            this.reportedInjuries.splice(this.reportedInjuries.indexOf(injury), 1);
            $rootScope.$emit('reportedInjuries/change', [ oldValue, this.reportedInjuries ]);
        };

        InjuryReporter.prototype.removeReportedInjuryForSelectedBodyPart = function() {
            var reportedInjury = this.tryGetReportedInjuryForBodyPart(this.selectedBodyPart);

            if (reportedInjury) {
                var oldValue = this.reportedInjuries.slice(0);
                this.removeFromReport(reportedInjury);
                $rootScope.$emit('reportedInjuries/change', [ oldValue, this.reportedInjuries ]);
            }
        };

        InjuryReporter.prototype.tryGetReportedInjuryForBodyPart = function(bodyPart) {
            for (var i = 0; i < this.reportedInjuries.length; i++) {
                if (this.reportedInjuries[i].bodyPartReference === bodyPart)
                    return this.reportedInjuries[i];
            }
        };

        InjuryReporter.prototype.hasReportedInjuresForBodyPart = function(bodyPart) {
            return this.tryGetReportedInjuryForBodyPart(bodyPart) ? true : false;
        };

        return new InjuryReporter();
    }

})();
