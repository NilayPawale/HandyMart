package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Mapper.FeedbackMapper;
import com.example.demo.ResponseDTO.FeedbackResponseDTO;
import com.example.demo.entities.Feedback;
import com.example.demo.entities.FeedbackDTO;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.FeedbackRepository;
import com.example.demo.repositories.VendorRepository;

@Service
@Transactional
public class FeedbackService {
@Autowired
private CustomerRepository customerRepository;
@Autowired
private VendorRepository vendorRepository;

@Autowired
private FeedbackRepository feedbackRepository;
	
	
	 public FeedbackResponseDTO createFeedback(FeedbackDTO feedbackDTO) {
	        // Convert DTO to entity using static method with repositories passed as parameters
	        Feedback feedback = FeedbackMapper.toEntity(feedbackDTO, customerRepository, vendorRepository);

	        // Save the feedback entity to the database
	        Feedback savedFeedback = feedbackRepository.save(feedback);

	        // Convert the saved entity to a response DTO
	        return FeedbackMapper.toResponseDto(savedFeedback);
	    }
}
