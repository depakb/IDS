package com.isha.donation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.isha.donation.entity")
@SpringBootApplication
public class IshaDonationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(IshaDonationSystemApplication.class, args);
    }
}
