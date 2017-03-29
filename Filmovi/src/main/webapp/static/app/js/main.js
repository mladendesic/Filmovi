var filmoviApp= angular.module('filmoviApp',['ngRoute']);

filmoviApp.controller('filmCtrl', function($scope, $http, $routeParams){

	$http.get('/api/filmovi/'+ $routeParams.id).then(function(resp){
		$scope.film=resp.data;

	});
	
	$scope.nadji= function(film){
		$scope.film.naziv=angular.copy(film);
		var config={};
		$http.get('http://www.omdbapi.com/?t='+$scope.film.naziv, config).then(function (resp){
			$scope.movie=resp.data;
		})
		
	}

	});


filmoviApp.controller('myCtrl', function($scope, $http, $location){

$scope.brojacStranice=0;

$scope.changePage = function (i) {
    if ($scope.brojacStranice>=0) {
      $scope.brojacStranice += i;
    }
    ucitajSve();
  }

var ucitajSve=function(){

	var config ={'params':{
        'page':$scope.brojacStranice
      }
    }

	if($scope.filterFilm&&$scope.filterFilm.name){
		config.params.name=$scope.filterFilm.name;
	}
	
	$http.get('/api/filmovi',config).then(function(resp){
		$scope.filmovi=resp.data;
		$scope.totalPages = Number(resp.headers().totalpages);
		$scope.film={};
	})
	}

	ucitajSve();


	$scope.filtriraj = function () {
       $scope.brojacStranice = 0;
       ucitajSve();
     } 
	$scope.brisanje=function(id){
		$http.delete('/api/filmovi/'+id).then(ucitajSve);
	}

	$scope.unos=function(){
		if(!$scope.film.id){
		$http.post('/api/filmovi/',$scope.film).then(ucitajSve);
		}else{
			$http.put('/api/filmovi/'+$scope.film.id, $scope.film).then(ucitajSve);
		}
	}

	$scope.postaviFilm=function(f){
		$scope.film=angular.copy(f);
	}

	$scope.nadjiNaStranici=function(film){
		$location.path('/film/'+film.id);
	}

});


filmoviApp.config(function($routeProvider) {
    $routeProvider
        //http://localhost:8080/static/app/html/index.html/#!/
        .when("/", {
            templateUrl : '/static/app/html/partials/home.html'
        })
        //http://localhost:8080/static/app/html/index.html/#!/filmovi
        .when('/filmovi', {
             templateUrl : '/static/app/html/partials/filmovi.html'
        })
        //http://localhost:8080/static/app/html/index.html/#!/film
        .when('/film/:id',{
        	templateUrl:'/static/app/html/partials/film.html'
        	
        })
        //sve ostalo radi redirekciju na
        //http://localhost:8080/static/app/html/index.html/#!/
        .otherwise({
             redirectTo: '/'
        });

});
