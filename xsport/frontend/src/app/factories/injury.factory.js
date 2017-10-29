(function() {
    'use strict';

    angular.module('rampup')
        .factory('Injury', InjuryFactory);

    /** @ngInject */
    function InjuryFactory() {
        function Injury(data) {
            this.id = data.id;
            this.name = data.name;
            this.bodyPart = data.bodyPart;
        }

        return Injury;
    }

})();
