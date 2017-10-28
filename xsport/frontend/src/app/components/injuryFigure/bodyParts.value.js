(function() {
    'use strict';

    angular.module('rampup')
        .value('BodyParts', {
            "head": { isOdd: false, imageUrl: "/img/head.png", key: "head" },
            "upperTorso": { isOdd: false, imageUrl: "/img/torso.png", key: "upperTorso" },
            "lowerTorso": { isOdd: false, imageUrl: "/img/lower_torso.png", key: "lowerTorso" },
            "arm": {
                isOdd: true,
                key: "arm",
                left: { imageUrl: "/img/arm_left.png" },
                right: { imageUrl: "/img/arm_right.png" }
            },
            "forearm": {
                isOdd: true,
                key: "forearm",
                left: { imageUrl: "/img/forearm_left.png" },
                right: { imageUrl: "/img/forearm_right.png" }
            },
            "hand": {
                isOdd: true,
                key: "hand",
                left: { imageUrl: "/img/hand_left.png" },
                right: { imageUrl: "/img/hand_right.png" } },
            "upperLeg": {
                isOdd: true,
                key: "upperLeg",
                left: { imageUrl: "/img/upper_leg_left.png" },
                right: { imageUrl: "/img/upper_leg_right.png" }
            },
            "lowerLeg": {
                isOdd: true,
                key: "lowerLeg",
                left: { imageUrl: "/img/lower_leg_left.png" },
                right: { imageUrl: "/img/lower_leg_right.png" }
            },
            "foot": {
                isOdd: true,
                key: "foot",
                left: { imageUrl: "/img/foot_left.png" },
                right: { imageUrl: "/img/foot_right.png" }
            }
        })

})();
