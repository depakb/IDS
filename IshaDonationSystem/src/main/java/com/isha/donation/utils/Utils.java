package com.isha.donation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


import com.isha.donation.entity.Volunteer;

public class Utils {

    public String getCurrentTime() {
        String time = "";
        time = new SimpleDateFormat("yyyy.MM.dd", Locale.US).format(new Date());  
        return time;
    }

    public ResponseEntity<?> checkSignInCredentials(Volunteer volunteer, String password, UriComponentsBuilder ucBuilder) {
        if (volunteer.getPassword().equals(password)) {
            if (volunteer.getStatus().equalsIgnoreCase("active")) {
            	 
    			return new ResponseEntity<Volunteer>(volunteer, HttpStatus.OK);
              //  return "Login Successful :)";
            } else {
            	return new ResponseEntity("Login failed : Volunteer status is :"+volunteer.getStatus(),HttpStatus.CONFLICT);
               // return "Login failed : Volunteer status is : "+volunteer.getStatus();
            }
        }
        return new ResponseEntity("Login failed : Wrong Password",HttpStatus.CONFLICT);
        //return "Login failed : Wrong Password";
    }
}