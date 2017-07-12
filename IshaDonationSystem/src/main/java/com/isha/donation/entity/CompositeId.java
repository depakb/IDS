package com.isha.donation.entity;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.isha.donation.repository.IDonorRepository;

public class CompositeId {

	@Autowired
	private IDonorRepository mDonorRepository;

	@Autowired
	JdbcTemplate jtemp;

	public String generate() throws Exception {

		String sid = "SIDD-" + "001";
		try {

			List<Donor> listofdonor = (List<Donor>) mDonorRepository.findAll();

			int size = listofdonor.size();

			if (size != 0) {

				Donor o = listofdonor.get(size - 1);
				String TPPSConsumerCode = o.getTppsConsumerCode();
				System.out.println(o);
				String id = "";
				id = TPPSConsumerCode;
				System.out.println(id);
				String p2 = id.substring(5);

				int x = Integer.parseInt(p2);
				x = x + 1;
				if (x <= 9) {
					sid = "SIDD-" + "00" + x;
					// sid="SIDD-B00"+x;
				} else if (x <= 99) {

					sid = "SIDD-" + "0" + x;
					// sid="SIDD-B0"+x;

				} else if (x <= 999) {
					sid = "SIDD-" + "-" + x;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sid;
	}

}
