angular.module('app', []).controller('cartController', function ($scope, $http) {
    const cartContextPath = 'http://localhost:8189/winter/api/v1/cart'

    $scope.loadCart = function () {
        $http.get(cartContextPath)
            .then(function (response) {
                $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.get(cartContextPath + '/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }


    $scope.deleteProductFromCart = function (productId) {
        $http.get(cartContextPath + '/delete/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }
    $scope.deleteAllQuantityFromCart = function (productId) {
        $http.get(cartContextPath + '/deleteQuantity/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }
    $scope.deleteAllFromCart = function () {
        $http.get(cartContextPath + '/cleanAll/')
            .then(function (response) {
                $scope.loadCart();
        });
    }

    $scope.loadCart();


});