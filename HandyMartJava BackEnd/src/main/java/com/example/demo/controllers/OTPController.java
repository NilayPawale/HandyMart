package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseDTO.PasswordResetRequestDTO;
import com.example.demo.services.PasswordResetService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OTPController {
	   @Autowired
	    private PasswordResetService passwordResetService;

	    @GetMapping("/request-otp/{email}")
	    public ResponseEntity<String> requestOtp(@PathVariable String email) {
	        try {
	            passwordResetService.sendOtp(email);
	            return ResponseEntity.ok("OTP sent to email.");
	        } catch (Exception e) {
	            // Log the exception
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Failed to send OTP. Please try again later.");
	        }
	    }


	    @PostMapping("/reset-password")
	    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequestDTO requestDTO) {
	        try {
	            passwordResetService.resetPassword(requestDTO.getEmail(), requestDTO.getOtp(), requestDTO.getNewPassword());
	            return ResponseEntity.ok("Password reset successful.");
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        } catch (Exception e) {
	            // Log the exception
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Failed to reset password. Please try again later.");
	        }
	    }
}
