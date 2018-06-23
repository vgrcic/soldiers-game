var app = angular.module('soldiers', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl: 'partials/home.html',
			controller: 'soldierController'
		});
});

app.controller('soldierController', function($scope, $http) {

	var loadSoldiers = function() {
		$http.get('/api/soldiers/')
			.success(function(data) {
				$scope.soldiers = data;
			})
	}

	$scope.deleteSoldier = function(id) {
		$http.delete('/api/soldiers/' + id)
			.success(function() {
				loadSoldiers();
			})
			.error(function() {
				alert('Could not delete soldier.');
			})
	}

	loadSoldiers();

});