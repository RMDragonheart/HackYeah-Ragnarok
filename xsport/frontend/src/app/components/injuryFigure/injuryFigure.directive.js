(function() {
    'use strict';

    angular.module('rampup')
        .directive('injuryFigure', InjuryFigure);

    /** @ngInject */
    function InjuryFigure($window) {
        return {
            restrict: 'E',
            templateUrl: 'app/components/injuryFigure/injuryFigure.html',
            controller: 'InjuryFigureController',
            controllerAs: 'figureCtrl',
            bindToController: true,
            link: link
        };

        function link(scope, element, attrs, ctrl){
            angular.element($window).bind('resize', function(){
                ctrl.refreshCanvasPosition();
            });
        }
    }

})();
