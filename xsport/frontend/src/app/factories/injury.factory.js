(function() {
    'use strict';

    angular.module('rampup')
        .factory('Injury', InjuryFactory);

    /** @ngInject */
    function InjuryFactory() {
        function Injury(data) {
            this.id = data.id;
            this.name = data.name;
            this.bodyPartKey = data.bodyPart;

            this.bodyPartReference = null;
            this.data = data;
        }

        return Injury;
    }

})();
