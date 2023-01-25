angular.module('app', ['ngStorage']).controller('regController', function ($scope, $http, $location, $localStorage) {
    const registrationPath = 'http://localhost:8189/winter/registration'


    $scope.functionRegistration = function () {
        $http.post(registrationPath, $scope.reguser).then(function (response) {
                if (response.data.token) {
                    $http.default.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.reguser.username, token: response.data.token};
                    $localStorage.reguser = null;

                    $location.href = "/index.html";
                }

        });
    }
});