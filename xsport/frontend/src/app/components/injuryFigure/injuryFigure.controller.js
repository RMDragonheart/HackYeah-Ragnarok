(function() {
    'use strict';

    angular.module('rampup')
        .controller('InjuryFigureController', InjuryFigureController);

    /** @ngInject */
    function InjuryFigureController($q, $imageLoader, BodyParts) {
        var vm = this;

        var canvas = document.getElementById("figureCanvas");
        var canvasPosition = getElementPosition(canvas);
        var ctx2d = canvas.getContext('2d');

        vm.onCanvasClick = onCanvasClick;
        vm.onCanvasHover = onCanvasHover;

        loadAllBodyParts().then(function() {
            generateBodyPartsPositions();
            redrawFigure();
        });

        function onCanvasClick(event) {
            var bodyPart = tryGetBodyPartAt(event);
            if(bodyPart) console.log(bodyPart.key);
        }

        function onCanvasHover(event) {
            var bodyPart = tryGetBodyPartAt(event);
            if(bodyPart) console.log(bodyPart.key);
        }

        function tryGetBodyPartAt(event) {
            var inlineX = event.clientX - canvasPosition.x;
            var inlineY = event.clientY - canvasPosition.y;

            if (isBodyPartOverlapped(inlineX, inlineY)) {
                var overlappedPart = BodyParts.upperTorso;
                var closestDistance = distanceBetween(inlineX, inlineY, overlappedPart.x, overlappedPart.y)

                for (var key in BodyParts) {
                    var bodyPart = BodyParts[key];

                    if (bodyPart.isOdd) {
                        var distanceToLeftPart = distanceBetween(inlineX, inlineY, bodyPart.left.x, bodyPart.left.y);
                        var distanceToRightPart = distanceBetween(inlineX, inlineY, bodyPart.right.x, bodyPart.right.y)
                        bodyPart = distanceToLeftPart < distanceToRightPart ? bodyPart.left : bodyPart.right;
                    }

                    var distance = distanceBetween(inlineX, inlineY, bodyPart.x, bodyPart.y);
                    if (distance < closestDistance && isInBodyPartBoundaries(inlineX, inlineY, bodyPart))
                        overlappedPart = BodyParts[key];
                }
                return overlappedPart;
            }

            return null;
        }

        function isBodyPartOverlapped(inlineX, inlineY) {
            return ctx2d.getImageData(inlineX, inlineY, 1, 1).data[3] !== 0;
        }

        function isInBodyPartBoundaries(inlineX, inlineY, bodyPart) {
            var hw = bodyPart.width * 0.5;
            var hh = bodyPart.height * 0.5;

            return inlineX >= bodyPart.x - hw && inlineX <= bodyPart.x + hw &&
                inlineY >= bodyPart.y - hh && inlineY <= bodyPart.y + hh;
        }

        function distanceBetween(x1, y1, x2, y2) {
            var deltaX = x1 - x2;
            var deltaY = y1 - y2;
            return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
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

        function loadAllBodyParts() {
            return $q(function(resolve, reject) {
                var partsNumber = 0, counter = 0;
                for (var key in BodyParts)
                    partsNumber += BodyParts[key].isOdd ? 2 : 1;

                for (var key in BodyParts) {
                    var bodyPart = BodyParts[key];

                    if (bodyPart.isOdd) {
                        $imageLoader.load(bodyPart.left.imageUrl).then(
                            (function(image) {
                                this.image = image;
                                this.width = image.width;
                                this.height = image.height;
                                counter++;
                                if(counter >= partsNumber) resolve();
                            }).bind(bodyPart.left)
                        );

                        $imageLoader.load(bodyPart.right.imageUrl).then(
                            (function(image) {
                                this.image = image;
                                this.width = image.width;
                                this.height = image.height;
                                counter++;
                                if(counter >= partsNumber) resolve();
                            }).bind(bodyPart.right)
                        );
                    }
                    else {
                        $imageLoader.load(bodyPart.imageUrl).then(
                            (function(image) {
                                this.image = image;
                                this.width = image.width;
                                this.height = image.height;
                                counter++;
                                if(counter >= partsNumber) resolve();
                            }).bind(bodyPart)
                        );
                    }
                }
            });
        }

        function generateBodyPartsPositions() {
            var middle = { x: canvas.width * 0.5, y: canvas.height * 0.5 };
            var partsOffset = 6;

            // head
            BodyParts.head.x = middle.x;
            BodyParts.head.y = BodyParts.head.height * 0.5;

            // upper torso
            BodyParts.upperTorso.x = middle.x;
            BodyParts.upperTorso.y = BodyParts.head.y + BodyParts.head.height * 0.5;
            BodyParts.upperTorso.y += BodyParts.upperTorso.height * 0.5 + partsOffset;

            // lower torso
            BodyParts.lowerTorso.x = middle.x;
            BodyParts.lowerTorso.y = BodyParts.upperTorso.y + BodyParts.upperTorso.height * 0.5;
            BodyParts.lowerTorso.y += BodyParts.lowerTorso.height * 0.5 + partsOffset;

            // arms
            BodyParts.arm.left.x = middle.x - BodyParts.upperTorso.width * 0.5;
            BodyParts.arm.left.x += -BodyParts.arm.left.width * 0.5 - partsOffset;
            BodyParts.arm.left.y = BodyParts.upperTorso.y - BodyParts.upperTorso.height * 0.5;
            BodyParts.arm.left.y += BodyParts.arm.left.height * 0.5 + 21;

            BodyParts.arm.right.x = 2 * middle.x - BodyParts.arm.left.x;
            BodyParts.arm.right.y = BodyParts.arm.left.y;

            // forearms
            BodyParts.forearm.left.x = middle.x - BodyParts.upperTorso.width * 0.5;
            BodyParts.forearm.left.x += -BodyParts.forearm.left.width * 0.5 - partsOffset - 3;
            BodyParts.forearm.left.y = BodyParts.arm.left.y + BodyParts.arm.left.height * 0.5;
            BodyParts.forearm.left.y += BodyParts.forearm.left.height * 0.5 + partsOffset;

            BodyParts.forearm.right.x = 2 * middle.x - BodyParts.forearm.left.x;
            BodyParts.forearm.right.y = BodyParts.forearm.left.y;

            // hands
            BodyParts.hand.left.x = BodyParts.forearm.left.x - 25;
            BodyParts.hand.left.y = BodyParts.forearm.left.y + BodyParts.forearm.left.height * 0.5;
            BodyParts.hand.left.y += BodyParts.hand.left.height * 0.5 + partsOffset;

            BodyParts.hand.right.x = 2 * middle.x - BodyParts.hand.left.x;
            BodyParts.hand.right.y = BodyParts.hand.left.y;

            // upper legs
            BodyParts.upperLeg.left.x = middle.x - BodyParts.upperLeg.left.width * 0.5 - 2;
            BodyParts.upperLeg.left.y = BodyParts.lowerTorso.y + BodyParts.lowerTorso.height * 0.5;
            BodyParts.upperLeg.left.y += BodyParts.upperLeg.left.height * 0.5 + partsOffset;

            BodyParts.upperLeg.right.x = 2 * middle.x - BodyParts.upperLeg.left.x;
            BodyParts.upperLeg.right.y = BodyParts.upperLeg.left.y;

            // lower legs
            BodyParts.lowerLeg.left.x = BodyParts.upperLeg.left.x - 1;
            BodyParts.lowerLeg.left.y = BodyParts.upperLeg.left.y + BodyParts.upperLeg.left.height * 0.5;
            BodyParts.lowerLeg.left.y += BodyParts.lowerLeg.left.height * 0.5 + partsOffset;

            BodyParts.lowerLeg.right.x = 2 * middle.x - BodyParts.lowerLeg.left.x;
            BodyParts.lowerLeg.right.y = BodyParts.lowerLeg.left.y;

            // feet
            BodyParts.foot.left.x = BodyParts.lowerLeg.left.x + 10;
            BodyParts.foot.left.y = BodyParts.lowerLeg.left.y + BodyParts.lowerLeg.left.height * 0.5;
            BodyParts.foot.left.y += BodyParts.foot.left.height * 0.5 + partsOffset;

            BodyParts.foot.right.x = 2 * middle.x - BodyParts.foot.left.x;
            BodyParts.foot.right.y = BodyParts.foot.left.y;
        }

        function redrawFigure() {
            for (var key in BodyParts) {
                var bodyPart = BodyParts[key];

                if (bodyPart.isOdd) {
                    drawBodyPartImage(bodyPart.left);
                    drawBodyPartImage(bodyPart.right);
                }
                else {
                    drawBodyPartImage(bodyPart)
                }
            }
        }

        function drawBodyPartImage(bodyPart) {
            if (bodyPart.x && bodyPart.y) {
                var offsetX = bodyPart.x - bodyPart.image.width * 0.5;
                var offsetY = bodyPart.y - bodyPart.image.height * 0.5;
                ctx2d.drawImage(bodyPart.image, offsetX, offsetY);
            }
        }
    }

})();
