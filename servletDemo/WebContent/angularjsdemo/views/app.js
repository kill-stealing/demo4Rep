require.config({
    paths: {
        // angular
        "angular": "bower_components/angular/angular",
        
        // angular-ui
        "angular-ui-router": "bower_components/angular-ui-router/release/angular-ui-router",
        
        // angularAMD
        "angularAMD": "bower_components/angularAMD/angularAMD",
        "ngload": "bower_components/angularAMD//ngload"
    },
    shim: {        
        // angular
		"angular": { exports: "angular" },
        // angular-ui
        "angular-ui-router": ["angular"],
        // angularAMD
        "angularAMD": ["angular"],
        "ngload": ["angularAMD"]
    }
});

define(["angular", "angularAMD", "angular-ui-router"], function (angular, angularAMD) {
    var m=angular.module("app",["ui.router"]);
    m.config(["$stateProvider","$urlRouterProvider",function($stateProvider,$urlRouterProvider){
        $urlRouterProvider.otherwise("/home");
        $stateProvider.state("home",{
            url:"/home",
            templateUrl:"home.html",
            controllerUrl:"home.js"
        }).state("about",{
            url:"/about",
            templateUrl:"about.html",
            controllerUrl:"about.js"
        });
    }]);

    return angularAMD.bootstrap(m);
});