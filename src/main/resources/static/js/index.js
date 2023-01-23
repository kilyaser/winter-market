angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/winter/api/v1/products'
    const cartContextPath = 'http://localhost:8189/winter/api/v1/cart'
    const authPath = 'http://localhost:8189/winter/auth'

    // $scope.loadProducts = function () {
    //     $http.get(contextPath)
    //         .then(function (response) {
    //             $scope.productsList = response.data;
    //     });
    // };

    $scope.loadProducts = function () {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p : $scope.filter ? $scope.filter.p : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data.content;
            $scope.pagesData = response.data;
            console.log(response.data);
        });
    };



    $scope.loadCart = function () {
        $http.get(cartContextPath + '/' + $localStorage.winterMarketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http.get(cartContextPath + '/' + $localStorage.winterMarketGuestCartId + '/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.productHtmlPage = function (productId) {
        $localStorage.productHtml = productId;
    }
//========== Authorization code ==============================
    $scope.tryToAuth = function () {
        $http.post(authPath, $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.loadCart();

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response){

            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.winterMarketUser;
        $http.defaults.common.Authorization = '';

    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.winterMarketUser) {
            return true;
        } else {
            return false;
        }

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
    }
//================ end authorization ================================
    if (!$localStorage.winterMarketGuestCartId) {
        $http.get(cartContextPath + "/generate_uuid")
            .then(function successCallback(response) {
                $localStorage.winterMarketGuestCartId = response.data.value;
            })
    }
    $scope.loadCart();
    $scope.loadProducts();

});