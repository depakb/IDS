CREATE DATABASE `idsdb`;
 
CREATE TABLE `donordetails` (
  `Donor_Details_ID` int(11) NOT NULL AUTO_INCREMENT ,
 `Donor_Phone_Num` varchar(45) NOT NULL,
 `Donor_Name` varchar(45) NOT NULL,
 `Donor_Email_id` varchar(45) NOT NULL,
  `Donor_Status` varchar(45) DEFAULT NULL,
 `Donor_Region` varchar(45) DEFAULT NULL,
 `Donor_State` varchar(45) DEFAULT NULL,
 `Donor_City` varchar(45) DEFAULT NULL,
 `Donor_Center` varchar(45) DEFAULT NULL,
 `Bank_Acct_Holder_Name` varchar(45) NOT NULL,
 `Bank_Name` varchar(45) DEFAULT NULL,
 `Bank_Branch` varchar(45) DEFAULT NULL,
 `Bank_Acct_Num` varchar(45) NOT NULL,
 `Bank_MICR` decimal(10,0) DEFAULT NULL,
 `Bank_IFSC_Cd` varchar(45) DEFAULT NULL,
 `Bank_Acct_Type` varchar(45) NOT NULL,
 `Donation_Start_Dt` date NOT NULL,
 `Donation_End_Dt` date NOT NULL,
 `amount` double not null,
 `Donation_Frequency` varchar(45) NOT NULL,
 `TPPS_Consumer_Code` varchar(45) DEFAULT NULL,
 `Create_Dt` varchar(45) NOT NULL,
 `Update_Comments` varchar(500) DEFAULT NULL,
 PRIMARY KEY (`Donor_Details_ID`),
 UNIQUE KEY `Donor_Details_ID_UNIQUE` (`Donor_Details_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `volunteerdetails` (
  `Volunteer_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Volunteer_User_Name` varchar(45) NOT NULL,
  `Volunteer_Name` varchar(45) NOT NULL,
  `Volunteer_Phone_Num` varchar(45) NOT NULL,
  `Volunteer_Password` varchar(45) NOT NULL,
  `Volunteer_Email_Id` varchar(45) DEFAULT NULL,
  `Volunteer_Status` varchar(45) DEFAULT NULL,
  `Create_Dt` varchar(45) DEFAULT NULL,
  `Update_Dt` varchar(45) DEFAULT NULL,
  `Comments` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Volunteer_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;