angular.module('app', ['ngStorage']).controller('cartController', function ($scope, $http, $localStorage) {
    const cartContextPath = 'http://localhost:8189/winter/api/v1/cart'
    const orderContextPath = 'http://localhost:8189/winter/api/v1/orders'

    $scope.loadCart = function () {
        $http.get(cartContextPath)
            .then(function (response) {
                $scope.cart = response.data;
        });
    };

    $scope.loadOrders = function () {
        $http.get(cartContextPath)
            .then(function (response) {
                $scope.orders = response.data;
                console.log(response.data);
            });
    };

    $scope.addToCart = function (productId) {
        $http.get(cartContextPath + '/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.deleteProductFromCart = function (productId) {
        $http.get(cartContextPath + '/delete/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };
    $scope.deleteAllQuantityFromCart = function (productId) {
        $http.get(cartContextPath + '/deleteQuantity/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };
    $scope.deleteAllFromCart = function () {
        $http.get(cartContextPath + '/clear')
            .then(function (response) {
                $scope.loadCart();
        });
    };


    $scope.createOrder = function () {
        $http.post(orderContextPath)
            .then(function (response) {
                // $scope.deleteAllFromCart();
                alert("Заказ оформлен");
                $scope.loadOrders();
                $scope.loadCart();
            });
    };


    if ($localStorage.winterMarketUser) {
        try {
            let jwt = $localStorage.winterMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.ext) {
                console.log("Token is expired!!!");
                delete  $localStorage.winterMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
    };

    $scope.loadCart();
    $scope.loadOrders()


});