package com.isha.donation.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

import com.isha.donation.entity.CompositeId;
import com.isha.donation.entity.Donor;
import com.isha.donation.Service.DonorService;
import com.isha.donation.utils.Utils;
 
@Controller
public class DonorController {

	@Autowired
	 CompositeId compositeId;
	
    @Autowired
    private DonorService mdonorService;
    private Utils mUtils = new Utils();

    @RequestMapping(value = "/ShowAlldonor", method = RequestMethod.GET)
    @ResponseBody
    public Object index() {
        return mdonorService.findAll();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/createdonor", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Donor donor,UriComponentsBuilder ucBuilder) {
	
        @SuppressWarnings("unused")
		String donorId = "";
        try {
        	System.out.println(donor);
       
            donor.setCreateDonordate(mUtils.getCurrentTime());
            System.out.println(compositeId.generate());
            donor.setTppsConsumerCode(compositeId.generate());
           // donor.setDonorComments(donor.getCreatorName());
          Donor donortemp=  mdonorService.save(donor);
         
           donorId = String.valueOf(donor.getDonorId());
            
        } catch (Exception ex) {
        //    return "Error creating the donor: " + ex.toString();
        	return new ResponseEntity("Unable to create. A User with name " + 
					    " already exist.",HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/createdonor").buildAndExpand(donor.getDonorId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        
       // return "donor succesfully created with id = " + donorId;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updatedonor/{id}", method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?>  updateUser(@RequestBody Donor donor, @PathVariable Long id,UriComponentsBuilder ucBuilder) {
    	Donor donortemp=null;
        try {
            donor.setCreateDonordate(mUtils.getCurrentTime());
            donor.setDonorId(id);
              donortemp=mdonorService.save(donor);
        } catch (Exception ex) {
        	return new ResponseEntity("Error updating the donor: not found.",HttpStatus.NOT_FOUND);
          //  return "Error updating the donor: " + ex.toString();
        }
        return new ResponseEntity<Donor>(donortemp, HttpStatus.OK);              
       // return "donor succesfully updated!";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/searchdonor", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Donor> getByVolunteerPhoneNo(@RequestBody String searchString) {
        try {
        	System.out.println("==========="+searchString);
            JSONObject json = new JSONObject(searchString);
            System.out.println(json.getString("mobileNumber"));
            Donor donor = mdonorService.findByMobileNumber(json.getString("mobileNumber"));
            System.out.println(donor);
            if (donor != null) {
            	return new ResponseEntity<Donor>(donor,HttpStatus.OK);
             //   return donor;
            }
        } catch (Exception ex) {
        	
        	  return new ResponseEntity("No donor found for entered search.",
  					HttpStatus.NOT_FOUND);
        	  
           // return null; // here returning null means No donor found for entered
                            // search
        }
        return new ResponseEntity("No donor found for entered search.",
					HttpStatus.NOT_FOUND);
        //return null; // here returning null means No donor found for entered
                        // search
    }
}