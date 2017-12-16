var m=angular.module("myApp",["ui.router","myService","myController"])
m.config(['$stateProvider','$urlRouterProvider',function ($stateProvider,$urlRouterProvider) {
    $urlRouterProvider.otherwise("");
    $stateProvider.state('default',{
        url:"",
        templateUrl:"views/home.html",
        controller:"IndexController"
        // url:"",
        // template:"<h1>后盾人</h1>"
    });
}]);