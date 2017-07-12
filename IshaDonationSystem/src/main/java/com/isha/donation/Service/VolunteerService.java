package com.isha.donation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isha.donation.entity.Volunteer;
import com.isha.donation.repository.IVolunteerRepository;

@Service
public class VolunteerService {

    @Autowired
    private IVolunteerRepository mVolunteerRepository;

    public Object findAll() {
        return mVolunteerRepository.findAll();
    }

    public Volunteer findById(Long id) {
        return mVolunteerRepository.findOne(id);
    }

    public Volunteer save(Volunteer volunteer) {
        return mVolunteerRepository.save(volunteer);
    }

    public void delete(Volunteer volunteer) {
        mVolunteerRepository.delete(volunteer);
        return;
    }

    public Volunteer findByPhoneNo(String phoneNo) {
        return mVolunteerRepository.findByPhoneNo(phoneNo);
    }
}
