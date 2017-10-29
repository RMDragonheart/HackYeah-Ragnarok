(function() {
    'use strict';

    angular.module('rampup')
        .value('BodyParts', {
            "head": { isOdd: false, imageUrl: "/img/head.png", key: "HEAD", displayName: "Head" },
            "upperTorso": { isOdd: false, imageUrl: "/img/torso.png", key: "FULL_BODY", displayName: "Upper torso" },
            "lowerTorso": { isOdd: false, imageUrl: "/img/lower_torso.png", key: "LOWER_BODY", displayName: "Lower torso" },
            "arm": {
                isOdd: true,
                left: { imageUrl: "/img/arm_left.png", key: "ARM", displayName: "Right arm" },
                right: { imageUrl: "/img/arm_right.png", key: "ARM", displayName: "Left arm" }
            },
            "forearm": {
                isOdd: true,
                left: { imageUrl: "/img/forearm_left.png", key: "FOREARM", displayName: "Right forearm" },
                right: { imageUrl: "/img/forearm_right.png", key: "FOREARM", displayName: "Left forearm" }
            },
            "hand": {
                isOdd: true,
                left: { imageUrl: "/img/hand_left.png", key: "HAND", displayName: "Right hand" },
                right: { imageUrl: "/img/hand_right.png", key: "HAND", displayName: "Left hand" } },
            "upperLeg": {
                isOdd: true,
                left: { imageUrl: "/img/upper_leg_left.png", key: "THIGH", displayName: "Right thigh" },
                right: { imageUrl: "/img/upper_leg_right.png", key: "THIGH", displayName: "Left thigh" }
            },
            "lowerLeg": {
                isOdd: true,
                left: { imageUrl: "/img/lower_leg_left.png", key: "CALF", displayName: "Right calf" },
                right: { imageUrl: "/img/lower_leg_right.png", key: "CALF", displayName: "Left calf" }
            },
            "foot": {
                isOdd: true,
                left: { imageUrl: "/img/foot_left.png", key: "FOOT", displayName: "Right foot" },
                right: { imageUrl: "/img/foot_right.png", key: "FOOT", displayName: "Left foot" }
            }
        })

})();
