angular.module('crawler.controllers')
    .controller("navController", ['$scope', '$location',
        function ($scope, $location) {
            $scope.isActive = function (viewLocation) {
                return viewLocation === $location.path();
            };
        }
    ]
);

angular.module('crawler.controllers')
    .controller("mainController", ['$scope', 'SiteService',
        function ($scope, SiteService) {
            $scope.isError = false;
            $scope.isSuccess = false;

            $scope.parse = function (domain) {
                var re = new RegExp(/^((?:(?:(?:\w[\.\-\+]?)*)\w)+)((?:(?:(?:\w[\.\-\+]?){0,62})\w)+)\.(\w{2,6})$/);
                if (domain.match(re)) {
                    $scope.isError = false;
                    SiteService.save(domain).then(function (site) {
                        SiteService.parse(site.id);
                        $scope.parsed_domain = $scope.domain;
                        $scope.isSuccess = true;
                    });
                    $scope.domain = null;
                }
                else
                    $scope.isError = true;
            };
            $scope.changed = function () {
                $scope.isError = false;
                $scope.isSuccess = false;
            };
        }
    ]
);

angular.module('crawler.controllers')
    .controller('detailsController', ['$scope', '$routeParams', '$location', 'SiteService',
        function ($scope, $routeParams, $location, SiteService) {
            $scope.parse = function () {
                SiteService.parse();
                $scope.site.status = 'IN_PROGRESS';
                $scope.links = null;
                $scope.stats = null;
            };
            $scope.refresh = function () {
                $scope.stats = null;
                SiteService.get($routeParams.siteId).success(function (data) {
                    $scope.site = data;

                    if ($scope.site.status != 'NOT_PARSED') {
                        SiteService.links($routeParams.siteId).success(function (data) {
                            $scope.links = data;
                            var stats = {externals: 0, total: 0};
                            $scope.links.forEach(function (link) {
                                stats.externals += link.externals;
                            });
                            stats.total = $scope.links.length;
                            $scope.stats = stats;
                        });
                    }
                });
            };
            $scope.delete = function() {
                SiteService.delete($routeParams.siteId).success( function() {
                    $location.path("/");
                })
            };
            $scope.refresh();
        }
    ]
);

angular.module('crawler.controllers')
    .controller('parsedController', ['$scope', '$routeParams', 'SiteService',
        function ($scope, $routeParams, SiteService) {
            SiteService.query().success(function (data) {
                $scope.sites = data;
            });
        }
    ]
);