package com.isha.donation.DAO;

import java.util.Map;
import org.springframework.stereotype.Repository;
import com.isha.donation.entity.Volunteer;

@Repository
public class VolunteerDAO {

    private static Map<Long, Volunteer> volunteersMap;
}
