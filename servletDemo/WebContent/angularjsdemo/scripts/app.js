require.config({
	baseUrl:"./bower_components",
	paths:{
		"angular":"/angular/angular",
		"angular-ui-router":"/angular-ui-router/release/angular-ui-router",
		"angularAMD":"/angularAMD/angularAMD",
		"ngload":"/angularAMD//ngload"
	},
	shim:{
		"angular":{exports:"angular"},
		"angular-ui-router":["angular"],
		"angularAMD": ["angular"],
        "ngload": ["angularAMD"]
	}
});

define(["angular","angularAMD","angular-ui-router"],function(angular,angularAMD){
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