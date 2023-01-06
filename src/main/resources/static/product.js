angular.module('app', ['ngStorage']).controller('productController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/winter/api/v1/products'
    const cartContextPath = 'http://localhost:8189/winter/api/v1/cart'
    const productId = $localStorage.productHtml;
    $scope.getProduct = function () {
         $http.get(contextPath + "/" + productId)
            .then(function (response) {
                $scope.product = response.data;
                console.log(response.data);
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

    $scope.getProduct();

});