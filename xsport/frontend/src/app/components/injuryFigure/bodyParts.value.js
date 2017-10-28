(function() {
    'use strict';

    angular.module('rampup')
        .value('BodyParts', {
            "head": { isOdd: false, imageUrl: "/img/head.png", key: "head" },
            "upperTorso": { isOdd: false, imageUrl: "/img/torso.png", key: "upperTorso" },
            "lowerTorso": { isOdd: false, imageUrl: "/img/lower_torso.png", key: "lowerTorso" },
            "arm": {
                isOdd: true,
                left: { imageUrl: "/img/arm_left.png", key: "arm" },
                right: { imageUrl: "/img/arm_right.png", key: "arm" }
            },
            "forearm": {
                isOdd: true,
                left: { imageUrl: "/img/forearm_left.png", key: "forearm" },
                right: { imageUrl: "/img/forearm_right.png", key: "forearm" }
            },
            "hand": {
                isOdd: true,
                left: { imageUrl: "/img/hand_left.png", key: "hand" },
                right: { imageUrl: "/img/hand_right.png", key: "hand" } },
            "upperLeg": {
                isOdd: true,
                left: { imageUrl: "/img/upper_leg_left.png", key: "upperLeg" },
                right: { imageUrl: "/img/upper_leg_right.png", key: "upperLeg" }
            },
            "lowerLeg": {
                isOdd: true,
                left: { imageUrl: "/img/lower_leg_left.png", key: "lowerLeg" },
                right: { imageUrl: "/img/lower_leg_right.png", key: "lowerLeg" }
            },
            "foot": {
                isOdd: true,
                left: { imageUrl: "/img/foot_left.png", key: "foot" },
                right: { imageUrl: "/img/foot_right.png", key: "foot" }
            }
        })

})();
