(function() {
    'use strict';

    angular.module('rampup')
        .factory('BodyPart', BodyPartFactory);

    /** @ngInject */
    function BodyPartFactory() {

        function BodyPart(type) {
            this.type = type;

        }

        return BodyPart;
    }

})();
