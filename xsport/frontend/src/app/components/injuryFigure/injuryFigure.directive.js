(function() {
    'use strict';

    angular.module('rampup')
        .directive('injuryFigure', InjuryFigure);

    /** @ngInject */
    function InjuryFigure() {
        return {
            restrict: 'E',
            templateUrl: 'app/components/injuryFigure/injuryFigure.html',
            controller: 'InjuryFigureController',
            controllerAs: 'figureCtrl',
            bindToController: true
        };
    }

})();
