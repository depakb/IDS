'use strict';
myApp.factory("localService",['$http','$log','$q',function($http,$log,$q){
	
	var factory = {
			register : registration	,
			fetchDonors : fetchDonors,
			createDonor: createDonor,
			update : update,
			login : login
	};
	return factory;
	
	function registration(registerData){
		var deferred = $q.defer();
		$log.log("---Register servivce entered---");

        $http({
            url: 'http://localhost:8080/createVolunteer', 
            method: 'PUT', 
            data:registerData, 
            headers: {'Content-Type': 'application/json'}
           })
		.then(function(SuccesResponse){
			$log.log("----Registerd Successfully----");
			deferred.resolve(SuccesResponse.data);
		},
		function(ErrorResponse){
			
			$log.log("----Error while Registering----"+ErrorResponse);
			deferred.reject(ErrorResponse);
		}
		);
		return deferred.promise;
	};
	
	function fetchDonors(){
		return $http.get("http://localhost:8080//ShowAlldonor")
		.then(function(fetchResponse){
			return fetchResponse.data;
			
		},function(errResponse){
			$log.error('Error while fetching Donors');
			return $q.reject(errResponse);
		}
		);
	};
	
	function createDonor(donor_info) {
	     console.log('Creating donor');
	     var deferred = $q.defer();
	     console.log('-------------------createDonor service--------------');
	     console.log(donor_info);
	     $http.put('http://localhost:8080/createdonor/',donor_info)
	         .then(
	             function (response) {
	            	 console.log('response block');
	            	 console.log(donor_info);
	                 deferred.resolve(response.data);
	             },
	             function (errResponse) { 
	                console.error('Error while creating Donor :');
	                deferred.reject(errResponse);
	             }
	         );
	     return deferred.promise;
	 };
	function update(id,selectedContact){
		var deferred = $q.defer();
		$log.log(id);
		$log.log(selectedContact);
		var url= "http://localhost:8080/updatedonor";
		
		$http.put("http://localhost:8080/updatedonor/",id,selectedContact)
		.then(function(response){
			$log.log("-----Successfully updated----");	
			return response.data
		},function(ErrorResponse){
			 $log.log(ErrorResponse);
			 $log.error(ErrorResponse);
			 deferred.reject(ErrorResponse);
		})
		 return deferred.promise;
	};
	 function login(logs){
		 var deferred = $q.defer();
		 $log.log("-----------Login service entered-------");
		 $http.put('http://localhost:8080/login',logs)
		 .then(function(SuccessResponse){
			 $log.log("-----Successfully loged in-----");
			 deferred.resolve(SuccessResponse);
		 },function(ErrorResponse){
			 $log.log("Error while logging-In");
			 deferred.reject(ErrorResponse);
		 })
		 return deferred.promise;
	 }

}]);
