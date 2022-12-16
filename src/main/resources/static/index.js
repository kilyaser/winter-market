angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/winter/api/v1/products'
    const cartContextPath = 'http://localhost:8189/winter/api/v1/cart'

    $scope.loadProducts = function () {
        $http.get(contextPath)
            .then(function (response) {
                $scope.productsList = response.data;
        });
    }

    $scope.loadCartProduct = function () {
        $http.get(cartContextPath)
            .then(function (response) {
                $scope.cartList = response.data;
        });
    }

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath + '/' + productId)
            .then(function (response){
                alert(response.data.title);
        });
    }

    $scope.addToCart = function (productId) {
        $http.get(cartContextPath + '/add/' + productId)
            .then(function (response) {
                $scope.loadCartProduct();
                $scope.cartSum();
                $scope.countProduct();
            });

    }

    $scope.deleteProductFromCart = function (productId) {
        $http.get(cartContextPath + '/delete/' + productId)
            .then(function (response) {
                $scope.loadCartProduct();
                $scope.cartSum();
                $scope.countProduct();
        })
    }

    $scope.cartSum = function () {
        $http.get(cartContextPath + '/sum')
            .then(function (response) {
                $scope.sum = response.data;
        });
    }
    $scope.countProduct = function () {
        $http.get(cartContextPath + '/count')
            .then(function (response) {
               $scope.count = response.data;
            });
    }

    $scope.loadCartProduct();
    $scope.loadProducts();
    $scope.cartSum();
    $scope.countProduct()




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