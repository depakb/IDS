package com.isha.donation.DAO;

import java.util.Map;
import org.springframework.stereotype.Repository;
import com.isha.donation.entity.Donor;

@Repository
public class DonorDAO {

    private static Map<Long, Donor> donorsMap;
}
