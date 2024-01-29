package com.SignalLight.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SignalLight.userservice.Services.OtpService;

@RestController
@RequestMapping("/API/V1")
@CrossOrigin("*")
public class OtpMasterController { 
	
	@Autowired
	private OtpService otpService;
	
	
	@PostMapping("/generateOtp")
	public ResponseEntity<String> addUser(@RequestParam("mobileNumber") String mobileNumber) {
		if (otpService.validateMobilenumber(mobileNumber) == 1) {
			try {
				this.otpService.generateUserOtp(mobileNumber);
				return ResponseEntity.ok("Mobilenumber Varified");

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else if (otpService.validatePremiseMobilenumber(mobileNumber) == 1) {
			try {
				this.otpService.generateUserOtp(mobileNumber);
				return ResponseEntity.ok("Mobilenumber Varified");

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mobile Number not Found.");
		}

	}
	
	@PostMapping("/validateMobleNumber")
    public ResponseEntity<String> validateMobileNumber(@RequestParam("mobileNumber") String mobileNumber ) {

        int result = otpService.validateMobilenumber(mobileNumber);
        if (result == 1) {
            return ResponseEntity.ok("Mobilenumber Varified");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("InvalidMobile Number.");
        }
    }

	
	@PostMapping("/validateOtp")
    public ResponseEntity<String> validateOtp(@RequestParam("otp") int otp,@RequestParam("mobileNumber") String mobileNumber ) {

        int result = otpService.validateOtp(otp,mobileNumber);
        if (result == 1) {
            return ResponseEntity.ok("OTP validated successfully.");
        } else if (result == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expired OTP.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid OTP or Mobile Number.");
        }
    }
}

