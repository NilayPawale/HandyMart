package com.example.demo.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Login;
import com.example.demo.entities.OtpEntity;
import com.example.demo.password.OtpGenerator;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.LoginRepository;
import com.example.demo.repositories.OtpRepository;

@Service
public class PasswordResetService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private LoginRepository loginRepository;
    
    @Autowired
    private OtpRepository otpRepository;
    
    @Autowired
    private MailService mailService;

    public void sendOtp(String email) {
        Customer customer = customerRepository.findByUsername(email).get();
        if (customer == null) {
            throw new IllegalArgumentException("No customer found with email: " + email);
        }
        
        // Generate OTP
        String otp = OtpGenerator.generateOtp();

        // Save OTP to the database with expiration time
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setOtp(otp);
        otpEntity.setEmail(email);
        otpEntity.setExpirationTime(LocalDateTime.now().plusMinutes(10));  // OTP valid for 10 minutes
        otpRepository.save(otpEntity);
    //String to, String subject, String body
        // Send OTP to user's email
        mailService.sendEmail(email,"Forget Password OTP!!","hi this is your "+otp+"Please eneter within "+otpEntity.getExpirationTime());
    }

    public boolean validateOtp(String email, String otp) {
        OtpEntity otpEntity = otpRepository.findByEmailAndOtp(email, otp);
        if (otpEntity != null && otpEntity.getExpirationTime().isAfter(LocalDateTime.now())) {
            // OTP is valid
            otpRepository.delete(otpEntity); // Invalidate OTP after use
            return true;
        }
        return false;
    }

    public void resetPassword(String email, String otp, String newPassword) {
        if (validateOtp(email, otp)) {
            Customer customer = customerRepository.findByUsername(email).get();
            Login login = loginRepository.getLoginByUser(email).get();
            System.out.println(customer.getLogin_id()+customer.getLogin_id().getPassword_hash()+"***************************************************");
            login.setPassword_hash(newPassword);
           // customer.getLogin_id().setPassword_hash(newPassword); // Hash password in real applications
           loginRepository.save(login);
            System.out.println(login.getLogin_id()+login.getPassword_hash()+"*************");
            System.out.println("After update:");
            System.out.println("Customer Login ID: " + login.getLogin_id());
            System.out.println("Updated Password Hash: " + login.getPassword_hash());
        } else {
            throw new IllegalArgumentException("Invalid or expired OTP");
        }
    }
}

