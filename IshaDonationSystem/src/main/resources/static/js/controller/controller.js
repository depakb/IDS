'use strict';
var myApp = angular.module("loginApp",['ngRoute','ngResource','angularUtils.directives.dirPagination']);
myApp.controller("loginController",['$log','$scope','localService','$http','$resource','$window',function($log,$scope,localService,$http,$resource,$window){
	$log.log("------------Controller-----------");
	
	
	var self = this;
	self.registerData = {userName:'',name:'',email:'',
			phoneNo:'',password:'',confirmPassword:''};
	
	self.donor_info={donorName:"",donorPhoneNumber:"",donorEmail:"",donorRegion:"",donorState:"",donorCity:"",donorcenter:"",bankAccountholderName:"",
	         bankName:"",bankBranch:"",bankAccountNumber:"",bankMICR:"",bankIfscCode:"",bankAccountType:"",donationStartDate:"",
	         donationEndDate:"",donationFrequency:"",donorComments:"",amount:""};
	
	
	
/*	self.registerd = [];*/
	self.SuccessMessage='';

	self.DonorsList = [];
	self.successMessage = '';
    self.errorMessage = '';
	self.onlyNumbers = /^\d+([,.]\d+)?$/;
	
	/*var url='http://localhost:6512/donor1/';*/
	self.editUser = {};
	self.logs={phoneNo:'',password:''};
	var today = new Date();
/*	self.logedUser=[];*/

	/* drop down validation */	
	$scope.type = [{
        id: "a",
        value: "SB"
    }, {
        id: "b",
        value: "CA"
    },{
    	id: "c",
        value: "SB-NRE"
    },{ 
    	id: "d",
        value: "SB-NR0"
    },{
	    id: "e",
       value: "Other"
    }];
	
	$scope.amountFrequency = [{
        id: "a",
        value: "Daily"
    }, {
        id: "b",
        value: "Weekly"
    },{
    	id: "c",
        value: "Quarterly"
    },{ 
    	id: "d",
        value: "Semi-Annually"
    },{
	    id: "e",
        value: "Yearly"
    },{
        id: "f",
        value: "Bi-Monthly"
    },{
        id: "g",
        value: "As And When Presented"
    }];
 
	
	self.register = function register(){
		$log.log("-----Register Function-----");
		if(self.registerData.password == self.registerData.confirmPassword){
		localService.register(self.registerData)
			.then(
				function(d){
					self.registerd = d;
					self.SuccessMessage='New User Registerd SuccessFully';
					window.location.href="http://localhost:8080/index.html#!/dashboard";
					self.ErrorMessage='';
				},	
				function(errResponse){
					self.ErrorMessage='Error while Creating!Please try again later';
				}
			);
		}else {
			self.ErrorMessage="Passwords didn't match!! Please try again.";
			window.location.href="http://localhost:8080/login.html";
			$log.log("Error while Registering");
		}
		
	};

	self.fetchDonors = function fetchDonors(){
		$log.log("-----Fetch Donors------");
		localService.fetchDonors()
		.then(
				function(fetchResponse){
					$log.log("successfully fetched data");
					self.DonorsList = fetchResponse;
					self.OldDonors = self.DonorsList.length;
			
		},function(errResponse){
			$log.log("Error while fetching");
			/*alert("Error while fetching");*/
		})
		
	};
	self.fetchDonors();
	
	
	self.createDonor =  function createDonor() {
        console.log('create donor');
       
        console.log(self.donor_info);
        localService.createDonor(self.donor_info)
            .then(
                function (response) {
                    console.log('donor created successfully');
                    self.successMessage = 'donor created successfully';
                    alert('Donor Created successfully');
                    window.location.href="#!SuccessPage"; 
                    self.reload(); 
                    self.errorMessage='';
                    self.done = true;
                 /*   self.user={};*/
                },
                function (errResponse) {
                    console.error('Error while creating donor');
                    alert('Error while creating Donor');
                 
                 /*   self.errorMessage = 'Error while creating donor:' + errResponse.data.errorMessage;
                    self.successMessage='';*/
                }
            );
    };
    self.update = function update(id){
    	var   List = $resource("http://localhost:8080/updatedonor/:id",{},{save:{method:'PUT',params:{id:'@id'}}});
    	 //var index = self.DonorsList[index];
    	 var donors = {};
    	 donors.donorName = self.selectedContact.donorName;
    	 donors.donorPhoneNumber=self.selectedContact.donorPhoneNumber;
    	 donors.donorEmail=self.selectedContact.donorEmail;
    	/* donors.donorRefName1=self.selectedContact.donorRefName1;
    	 donors.donorRefName2=self.selectedContact.donorRefName2;*/
    	 donors.donorRegion=self.selectedContact.donorRegion;
    	 donors.donorState=self.selectedContact.donorState;
    	 donors.donorCity=self.selectedContact.donorCity;
    	 donors.donorcenter=self.selectedContact.donorcenter;
    	 donors.bankAccountholderName=self.selectedContact.bankAccountholderName;
    	 donors.bankName=self.selectedContact.bankName;
    	 donors.bankBranch=self.selectedContact.bankBranch;
    	 donors.bankAccountNumber=self.selectedContact.bankAccountNumber;
    	 donors.bankMICR=self.selectedContact.bankMICR;
    	 donors.bankIfscCode=self.selectedContact.bankIfscCode;
    	 donors.bankAccountType=self.selectedContact.bankAccountType;
    	 donors.donationStartDate=self.selectedContact.donationStartDate;	 
    	 donors.donationEndDate=self.selectedContact.donationEndDate;
    	 donors.donationFrequency=self.selectedContact.donationFrequency;
/*    	 donors.createdonordate=self.selectedContact.createdonordate; */
    	 donors.amount=self.selectedContact.amount;
    	 donors.donorComments=self.selectedContact.donorComments;
/*    	 donors.createdonordate = today.getDate();
  	     donors.creatorName = "sharath"; */
    	 self.DonorsList[self.selectedContact] = donors;
    	 List.save({id:self.selectedContact.donorId},donors);
    	 $log.log("successfully saved"); 
     };
     
	self.edit = function edit(id){
		$log.log("The id to be Edited"+id);
		for(var i=0; i<self.DonorsList.length;i++){
			if(self.DonorsList[i].consumerCode == id){
			self.donor_info = angular.copy(self.DonorsList[i]);
			break;
			}
		}
	}
	self.selectContact = function(donorId){
		console.log('index block');
		console.log(donorId);
		self.selectedContact = self.DonorsList[donorId];
		/*$log.log( self.selectedContact);*/
		self.StartDate = new Date(self.selectedContact.donationStartDate);
		self.EndDate = new Date(self.selectedContact.donationEndDate);
	}
	
	self.login=function login(){
		$log.log(self.logs);
		localService.login(self.logs)
		.then(function(d){
			self.logedUser = d;
			window.location.href="http://localhost:8080/index.html#!/dashboard";
		},function(error){
			$log.log("Error loging in controller");
			alert("Unable to log-in");
		});
		
	}
	self.reload = function reload(){
		$log.log("page is loading");
		$window.location.reload();
	}
	
	
}]);
myApp.config(function($routeProvider){
	$routeProvider
/*	.when("/",{
		templateUrl : "login.html"
	})*/
	.when("/dashboard",{
		templateUrl : "dashboard.html"
	})
	.when("/donor_info",{
		templateUrl : "donor_info.html"
		
	})
	.when("/CreateDonor",{
		templateUrl : "CreateDonor.html"
	})
	.when("/ViewDonor",{
		templateUrl : "ViewDonor.html"
	})
	.when("/SuccessPage",{
		templateUrl : "SuccessPage.html"
	})
	.when("/EditDonor",{
		templateUrl : "EditDonor.html"
	})
	.when("/BulkDonor",{
		templateUrl : "BulkDonor.html"
	})
	.when("/Reports",{
		templateUrl : "Reports.html"
	})
});