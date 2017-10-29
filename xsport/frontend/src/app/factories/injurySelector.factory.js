(function() {
    'use strict';

    angular.module('rampup')
        .factory('$injurySelector', InjurySelectorFactory);

    /** @ngInject */
    function InjurySelectorFactory() {
        function InjurySelector() {
            this.selectedBodyPart = null;
        }

        return new InjurySelector();
    }

})();
