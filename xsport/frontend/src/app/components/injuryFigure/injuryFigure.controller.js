(function() {
    'use strict';

    angular.module('rampup')
        .controller('InjuryFigureController', InjuryFigureController);

    /** @ngInject */
    function InjuryFigureController($imageLoader) {
        var vm = this;

        var canvas = document.getElementById("figureCanvas");
        var ctx2d = canvas.getContext('2d');

        $imageLoader.load('/img/silhouette.png').then(
            function(image) {
                ctx2d.drawImage(image, 0, 0);
            }
        )
    }

})();
