var app = angular.module('app', ['ui.router', 'chart.js']);

app.config(function($stateProvider, $urlRouterProvider, $locationProvider, ChartJsProvider) {
    $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: 'login.html',
            controller: 'LoginController'
        })
        .state('dashboard', {
            // abstract: true,
            url: "/dashboard",
            templateUrl: 'dashboard.html'
        })
        .state('dashboard.alcalde', {
            url: '/alcalde',
            templateUrl: 'alcalde.html',
            controller: 'AlcaldeController'
        })
        .state('dashboard.concejal', {
            url: '/concejal',
            templateUrl: 'concejal.html'
        })
    $urlRouterProvider.otherwise("/login");
    //          $locationProvider.html5Mode({ enabled: true, requireBase: true });
    ChartJsProvider.setOptions({ colors : [ '#803690', '#00ADF9', '#DCDCDC', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'] });
});


//Directives
app.directive('navbar', navbar);

function navbar() {
    var directive = {
        templateUrl: 'navbar.html',
        restrict: 'EA'
    };

    return directive;
}


//Services
app.factory('dataService', dataService);

dataService.$inject = ['$http'];

function dataService($http) {
    return {
        getSuggestions: getSuggestions,
        getSuggestion: getSuggestion
    };

    function getSuggestions() {
        return $http.get('/suggestion')
            .then(function(response) {
                return response.data;
            })
            .catch(function(error) {
                return error;
            });
    };

    function getSuggestion(id) {
        return $http.get('/suggestion/' + id)
            .then(function(response) {
                return response.data;
            })
            .catch(function(error) {
                return error;
            });
    };
}

//Controllers
app.controller('LoginController', LoginController);

LoginController.$inject = ['$scope', '$state'];

function LoginController($scope, $state) {
    console.log('LoginController');

    $scope.login = function(val) {
        if ($scope.email === 'alcalde@gmail.com') {
            $state.go('dashboard.alcalde');
        } else {
            $state.go('dashboard.concejal');
        }
    }
}

app.controller('AlcaldeController', AlcaldeController);

AlcaldeController.$inject = ['$scope', 'dataService'];

function AlcaldeController($scope, dataService) {
    console.log('AlcaldeController');

    activate();

    $scope.suggestions = [];
    $scope.suggestionslabels = [];
    $scope.suggestionsVotes = [];

    if (typeof(EventSource) !== "undefined") {
        var source = new EventSource('/streams');

        source.onmessage = function(event) {
            var data = JSON.parse(event.data);

            var pos = $scope.suggestions.map(function(obj) {
                return obj.id }).indexOf(data.suggestionId);
            //TODO Comparar si existe en suggetions para agregar o sumar
            if (pos !== -1) {
                //NOTE Agregando a una propuesta existente
                $scope.suggestions[pos].numberOfVotes += 1;
                $scope.$apply();
            } else {
                //NOTE Agregando nueva propuesta
                Materialize.toast('Se ha creado una nueva propuesta!', 4000);
                dataService.getSuggestion(data.suggestionId)
                    .then(function(res) {
                        $scope.suggestions.push(res);
                    });
            }
        };
    }

    function activate() {
        dataService.getSuggestions()
            .then(function(response) {
                $scope.suggestions = response;
                response.forEach(function(obj){
                    $scope.suggestionslabels.push(obj.title);
                    $scope.suggestionsVotes.push(obj.numberOfVotes);
                });
            });
    }   
}


app.controller("PolarAreaCtrl", PolarAreaCtrl);
              
PolarAreaCtrl.$inject = ['$scope'];

function PolarAreaCtrl($scope) {
  $scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales", "Tele Sales", "Corporate Sales"];
  $scope.data = [300, 500, 100, 40, 120];
}