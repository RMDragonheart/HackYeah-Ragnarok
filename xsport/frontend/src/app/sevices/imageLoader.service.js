(function() {
    'use strict';

    angular.module('rampup')
        .service('$imageLoader', ImageLoader);

    /** @ngInject */
    function ImageLoader($q) {
        var loader = this;

        loader.load = function(imageUrl) {
            return $q(function(resolve, reject){
                var image = new Image();
                image.onload = function() {
                    resolve(image);
                };
                image.src = imageUrl;
            });
        }

        loader.loadMultiple = function(/*...*/) {
            return $q(function(resolve, reject){
                for (var i = 0; i < arguments.length; i++) {
                    loader.load(arguments[i]).then((function(image) {
                        resolve(this, image);
                    }).bind(arguments[i]))
                }
            });
        }
    }

})();
