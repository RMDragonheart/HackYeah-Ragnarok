(function() {
    'use strict';

    angular.module('rampup')
        .value('XSPORTS_API_BASE_ADDRESS', 'http://127.0.0.1:8080/')
        .value('InjuryBase', [])
        .service('$xsportsClient', XSportsClient);

    /** @ngInject */
    function XSportsClient($http, $q, XSPORTS_API_BASE_ADDRESS, Injury, InjuryBase) {

        this.retrieveInjuriesForBodyPart = retrieveInjuriesForBodyPart;
        this.retrieveCapabilities = retrieveCapabilities;

        retrieveAllInjuries().then(function(parsedInjuries) {
            InjuryBase = parsedInjuries;
        });

        function retrieveInjuriesForBodyPart(bodyPart) {
            var url = XSPORTS_API_BASE_ADDRESS + "injuries/" + bodyPart.key.toLowerCase();
            return $http.get(url).then(function(response) {
                return parseInjuries(response.data).map(function(injury) {
                    injury.bodyPartReference = bodyPart;
                    return injury;
                });
            });
        };

        function retrieveCapabilities(injuries) {
            var url = XSPORTS_API_BASE_ADDRESS + "sports/";
            var requestBody = injuries.map(function(injury) {
                return injury.data;
            })
            return $http.get(url, { data: requestBody });
        };

        function retrieveAllInjuries() {
            var url = XSPORTS_API_BASE_ADDRESS + "injuries/";
            return $http.get(url).then(function(response) {
                return parseInjuries(response.data);
            });
        };

        function parseInjuries(array) {
            return array.map(function(injuryData) {
                return new Injury(injuryData);
            });
        }
    }

})();
