(function() {
    'use strict';

    angular.module('rampup')
        .value('BodyParts', {
            "head": { isOdd: false, imageUrl: "/img/head.png", key: "head", displayName: "Head" },
            "upperTorso": { isOdd: false, imageUrl: "/img/torso.png", key: "upperTorso", displayName: "Upper torso" },
            "lowerTorso": { isOdd: false, imageUrl: "/img/lower_torso.png", key: "lowerTorso", displayName: "Lower torso" },
            "arm": {
                isOdd: true,
                left: { imageUrl: "/img/arm_left.png", key: "arm", displayName: "Right arm" },
                right: { imageUrl: "/img/arm_right.png", key: "arm", displayName: "Left arm" }
            },
            "forearm": {
                isOdd: true,
                left: { imageUrl: "/img/forearm_left.png", key: "forearm", displayName: "Right forearm" },
                right: { imageUrl: "/img/forearm_right.png", key: "forearm", displayName: "Left forearm" }
            },
            "hand": {
                isOdd: true,
                left: { imageUrl: "/img/hand_left.png", key: "hand", displayName: "Right hand" },
                right: { imageUrl: "/img/hand_right.png", key: "hand", displayName: "Left hand" } },
            "upperLeg": {
                isOdd: true,
                left: { imageUrl: "/img/upper_leg_left.png", key: "upperLeg", displayName: "Right thigh" },
                right: { imageUrl: "/img/upper_leg_right.png", key: "upperLeg", displayName: "Left thigh" }
            },
            "lowerLeg": {
                isOdd: true,
                left: { imageUrl: "/img/lower_leg_left.png", key: "lowerLeg", displayName: "Right calf" },
                right: { imageUrl: "/img/lower_leg_right.png", key: "lowerLeg", displayName: "Left calf" }
            },
            "foot": {
                isOdd: true,
                left: { imageUrl: "/img/foot_left.png", key: "foot", displayName: "Right foot" },
                right: { imageUrl: "/img/foot_right.png", key: "foot", displayName: "Left foot" }
            }
        })

})();
