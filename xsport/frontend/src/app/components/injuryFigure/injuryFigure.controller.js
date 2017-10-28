(function() {
    'use strict';

    angular.module('rampup')
        .controller('InjuryFigureController', InjuryFigureController);

    /** @ngInject */
    function InjuryFigureController($imageLoader) {
        var vm = this;

        var canvas = document.getElementById("figureCanvas");
        var canvasPosition = getElementPosition(canvas);
        var ctx2d = canvas.getContext('2d');

        vm.onCanvasClick = onCanvasClick;
        vm.onCanvasHover = onCanvasHover;

        $imageLoader.load('/img/silhouette.png').then(
            function(image) {
                ctx2d.drawImage(image, 0, 0);
            }
        );

        function onCanvasClick(event) {
            var bodyPart = tryGetBodyPartAt(event);
        }

        function onCanvasHover(event) {
            var bodyPart = tryGetBodyPartAt(event);
        }

        function tryGetBodyPartAt(event) {
            var inlineX = event.clientX - canvasPosition.x;
            var inlineY = event.clientY - canvasPosition.y;

            if (isBodyPartOverlapped(inlineX, inlineX)) {
                return {}; // TODO: get actual body part
            }

            return null;
        }

        function isBodyPartOverlapped(inlineX, inlineY) {
            return ctx2d.getImageData(inlineX, inlineY, 1, 1).data[3] !== 0;
        }

        function getElementPosition(obj) {
            var curleft = 0, curtop = 0;
            if (obj.offsetParent) {
                do {
                    curleft += obj.offsetLeft;
                    curtop += obj.offsetTop;
                } while (obj = obj.offsetParent);
                return { x: curleft, y: curtop };
            }
            return undefined;
        }
    }

})();
