angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/winter/api/v1/products'
    const cartContextPath = 'http://localhost:8189/winter/api/v1/cart'

    $scope.loadProducts = function () {
        $http.get(contextPath)
            .then(function (response) {
                $scope.productsList = response.data;
        });
    }

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


    // $scope.deleteProductFromCart = function (productId) {
    //     $http.get(cartContextPath + '/delete/' + productId)
    //         .then(function (response) {
    //             $scope.loadCart();
    //         });
    // }
    // $scope.deleteAllQuantityFromCart = function (productId) {
    //     $http.get(cartContextPath + '/deleteQuantity/' + productId)
    //         .then(function (response) {
    //             $scope.loadCart();
    //         });
    // }
    // $scope.deleteAllFromCart = function () {
    //     $http.get(cartContextPath + '/cleanAll/')
    //         .then(function (response) {
    //             $scope.loadCart();
    //     });
    // }
    //
    $scope.loadCart();
    $scope.loadProducts();

//
//     $scope.loadProducts = function (pageIndex = 1) {
//         $http({
//             url: contextPath + '/products',
//             method: 'GET',
//             params: {
//                 title_part: $scope.filter ? $scope.filter.title_part : null,
//                 min_price: $scope.filter ? $scope.filter.min_price : null,
//                 max_price: $scope.filter ? $scope.filter.max_price : null
//             }
//         }).then(function (response) {
//             $scope.ProductsPage = response.data;
//         });
//     };
//
//     $scope.loadProducts();
});