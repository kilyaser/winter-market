angular.module('app', ['ngStorage']).controller('orderController', function ($scope, $http, $localStorage) {
    const cartContextPath = 'http://localhost:8189/winter/api/v1/cart'
    const orderContextPath = 'http://localhost:8189/winter/api/v1/orders'

    $scope.loadCart = function () {
        $http.get(cartContextPath + '/' + $localStorage.winterMarketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
                console.log(response.data);
            });
    };

    $scope.loadOrders = function () {
        $http.get(orderContextPath)
            .then(function (response) {
                $scope.orders = response.data;
                console.log(response.data);
            });
    };


    $scope.productHtmlPage = function (productId) {
        $localStorage.productHtml = productId;
    }


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

    if (!$localStorage.winterMarketGuestCartId) {
        $http.get(cartContextPath + "/generate_uuid")
            .then(function successCallback(response) {
                $localStorage.winterMarketGuestCartId = response.data.value;
            });
    }
    $scope.loadCart();
    $scope.loadOrders()
});