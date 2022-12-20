angular.module('app', [ngStorage]).controller('loginController', function ($scope, $http, $localStorage) {
    const authPath = 'http://localhost:8189/winter/auth'

    $scope.tryToAuth = function () {
        $http.post(authPath, $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.default.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response){

        });
    };


});