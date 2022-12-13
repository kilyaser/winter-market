angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/winter/api/v1/products'

    $scope.loadProducts = function () {
        $http.get(contextPath)
            .then(function (response) {
                $scope.productsList = response.data;
        });
    }

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath + '/' + productId)
            .then(function (response){
                alert(response.data.title);
        });
    }
    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });

    }

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