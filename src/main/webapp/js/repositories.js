angular.module('crawler.services')
    .factory('SiteService', ['$http', '$q', function ($http, $q) {
    return {
        query: function() {
            return $http.get("api/site/");
        },
        save: function(domain) {
            return $q(function (resolve, reject) {
                $http.post("api/site/", domain)
                    .success(function (data, status, headers, config) {
                        $http.get(headers('Location')).success(function(data){
                            resolve(data);
                        });
                    });
            });
        },
        parse: function(id) {
            $http.get("api/site/" + id + "/parse");
        },
        get: function(id) {
            return $http.get("api/site/" + id);
        },
        delete: function(id) {
            return $http.delete("api/site/" + id);
        },
        links: function(id) {
            return $http.get("api/site/" + id + "/link");
        }
    };
}]);