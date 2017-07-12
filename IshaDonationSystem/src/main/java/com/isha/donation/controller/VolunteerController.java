package com.isha.donation.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.isha.donation.entity.Volunteer;
import com.isha.donation.Service.VolunteerService;
import com.isha.donation.utils.Utils;
 
@Controller
public class VolunteerController {

    @Autowired
    private VolunteerService mVolunteerService;
    private Utils mUtils = new Utils();

    @RequestMapping(value = "/ShowAllVolunteer", method = RequestMethod.GET)
    @ResponseBody
    public Object index() {
        return mVolunteerService.findAll();
    }

    @RequestMapping(value = "/createVolunteer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Volunteer volunteer, UriComponentsBuilder ucBuilder) {
        String volunteerId = "";
        try {
            volunteer.setCreateDate(mUtils.getCurrentTime());
            volunteer.setComments(volunteer.getName() + " : added.");
            mVolunteerService.save(volunteer);
            volunteerId = String.valueOf(volunteer.getId());
        } catch (Exception ex) {
        	 return new ResponseEntity("Error creating the user: ",HttpStatus.CONFLICT);
            //return "Error creating the user: " + ex.toString();
        }
         
        HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/createVolunteer").buildAndExpand(volunteer.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        
        //return "Voluteer succesfully created with id = " + volunteerId;
    }

    @RequestMapping("/deleteVolunteer/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            Volunteer user = mVolunteerService.findById(id);
            mVolunteerService.delete(user);
        } catch (Exception ex) {
        	 return new ResponseEntity("Error deleting the Volunteer: ",HttpStatus.CONFLICT);
            //return "Error deleting the Volunteer:" + ex.toString();
        }
        return new ResponseEntity("Volunteer succesfully deleted: ",HttpStatus.OK);
        //return "Volunteer succesfully deleted!";
    }

    @RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getByVolunteerPhoneNo(@RequestBody String userCredentials,UriComponentsBuilder ucBuilder) {
        Volunteer volunteer=null;
        JSONObject json=null;
        try {
             json = new JSONObject(userCredentials);
              volunteer = mVolunteerService.findByPhoneNo(json.getString("phoneNo"));
            if (volunteer != null) {
                return mUtils.checkSignInCredentials(volunteer, json.getString("password"),ucBuilder);
            }
        } catch (Exception ex) {
        	return new ResponseEntity("Error login ..! validate inputs",HttpStatus.CONFLICT);
            //return "Error login ..! validate inputs";
        }
        return new ResponseEntity("Volunteer not exist :(",HttpStatus.CONFLICT);
        //return "Volunteer not exist :(";
    }

    @RequestMapping(value="/updateVolunteer/{id}",method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody Volunteer volunteer, @PathVariable Long id) {
    	Volunteer volunteeTemp=null;
        try {
            volunteer.setUpdateDate(mUtils.getCurrentTime());
            volunteer.setId(id);
            volunteeTemp= mVolunteerService.save(volunteer);
        } catch (Exception ex) {
        	return new ResponseEntity("Error updating the Volunteer:",HttpStatus.CONFLICT);
           // return "Error updating the Volunteer: " + ex.toString();
        }
        return new ResponseEntity<Volunteer>(volunteeTemp, HttpStatus.OK);
		 
        
        //return "Volunteer succesfully updated!";
    }
}
