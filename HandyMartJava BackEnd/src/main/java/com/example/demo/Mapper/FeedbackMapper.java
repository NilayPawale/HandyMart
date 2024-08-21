package com.example.demo.Mapper;

import com.example.demo.entities.Feedback;
import com.example.demo.entities.FeedbackDTO;
import com.example.demo.ResponseDTO.FeedbackResponseDTO;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Vendor;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.VendorRepository;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {

    public static FeedbackResponseDTO toResponseDto(Feedback feedback) {
        return new FeedbackResponseDTO(
            feedback.getFeedbackId(),
            feedback.getRating(),
            feedback.getComment(),
            feedback.getCustomer().getCustomer_id(), // Assuming Customer entity has getCustomerId method
            feedback.getVendor().getVendor_id()   // Assuming Vendor entity has getVendorId method
        );
    }

    public static Feedback toEntity(FeedbackDTO feedbackDTO, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        Customer customer = customerRepository.findByCustomerIds(feedbackDTO.getCustomer_id());
            
        Vendor vendor = vendorRepository.findById(feedbackDTO.getVendor_id())
            .orElseThrow(() -> new RuntimeException("Vendor not found"));

        Feedback feedback = new Feedback();
        feedback.setRating(feedbackDTO.getRating());
        feedback.setComment(feedbackDTO.getComment());
        feedback.setCustomer(customer);
        feedback.setVendor(vendor);
        return feedback;
    }
}


