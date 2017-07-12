package com.isha.donation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.isha.donation.entity.Volunteer;

@Repository
public interface IVolunteerRepository extends CrudRepository<Volunteer,Long> {
    public Volunteer findByPhoneNo(@Param("PhoneNo") String phoneNo);
}
