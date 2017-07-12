package com.isha.donation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.isha.donation.entity.Donor;

@Repository
public interface IDonorRepository extends CrudRepository<Donor,Long>{
    public Donor findByMobileNumber(@Param("mobileNumber") String mobileNumber);
    
    public Donor findById(@Param("TPPSConsumerCode") String TPPSConsumerCode);
}
