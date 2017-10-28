(function() {
    'use strict';

    angular.module('rampup')
        .service('$imageLoader', ImageLoader);

    /** @ngInject */
    function ImageLoader($q) {

        this.load = function(imageUrl) {
            return $q(function(resolve, reject){
                var image = new Image();
                image.onload = function() {
                    resolve(image);
                };
                image.src = imageUrl;
            });
        }
    }

})();
