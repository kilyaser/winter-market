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

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data.content;
        });
    };

    $scope.productHtmlPage = function (productId) {
        $localStorage.productHtml = productId;
    }

    $scope.addToCart = function (productId) {
        $http.get(cartContextPath + '/' + $localStorage.winterMarketGuestCartId + '/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
                $scope.buttonAction();
            });

    };
    $scope.loadCart = function () {
        $http.get(cartContextPath + '/' + $localStorage.winterMarketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
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
    if (!$localStorage.winterMarketGuestCartId) {
        $http.get(cartContextPath + "/generate_uuid")
            .then(function successCallback(response) {
                $localStorage.winterMarketGuestCartId = response.data.value;
            })
    }


    $scope.getProduct();
    $scope.loadProducts();
    $scope.loadCart();
    $scope.data = {visible : true};

    $scope.buttonAction = function () {
        let btn = angular.element(document.querySelector(".mx-auto > .add-btn")).text("V");

        btn.removeClass("btn-dark").addClass("btn-light");
        btn.text("Pre-Order")
        $scope.data = {visible : false};
    };

});