angular.module('crawler.controllers', []);
angular.module('crawler.services', []);

angular.module('crawlerApp', ['ngRoute', 'crawler.controllers', 'crawler.services'])
    .config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/', {
         templateUrl: 'views/main.html',
         controller: 'mainController'
    }).
    when(
        '/parsed', {
            templateUrl: 'views/parsed.html',
            controller: 'parsedController'
    }).
    when('/details/:siteId', {
         templateUrl: 'views/details.html',
         controller: 'detailsController'
    }).
    otherwise({
            redirectTo: '/'
    });
}]);