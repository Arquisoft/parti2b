var app = angular.module('app', []);

app.directive('navbar', {
    templateUrl: 'navbar.html',
    restrict: 'EA'
//     controller: 'NavbarController'
});

app.controller('NavbarController', NavbarController);

NavbarController.$inject = [];

function NavbarController() {
    console.log('NavbarController');
}
