(function() {
    'use strict';

    angular.module('rampup')
        .factory('MapPreview', MapPreviewFactory);

    /** @ngInject */
    function MapPreviewFactory($mdDialog, NgMap) {
        function MapPreview(coordinates) {

            $mdDialog.show({
                controller: function() {
                    var vm = this;

                    vm.startPoint = [ coordinates[0].x, coordinates[0].y ];
                    console.log(coordinates);
                    NgMap.getMap().then(function(map) {
                        map.setCenter({lat: coordinates[0].x, lng: coordinates[0].y})

                        for (var i = 0; i < coordinates.length; i++) {
                            new google.maps.Marker({
                                position: {lat: coordinates[i].x, lng: coordinates[i].y},
                                map: map
                            });
                        }
                    });
                },
                bindToController: true,
                templateUrl: 'app/components/mapPreview/mapPreview.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            })
        }

        return MapPreview;
    }

})();
