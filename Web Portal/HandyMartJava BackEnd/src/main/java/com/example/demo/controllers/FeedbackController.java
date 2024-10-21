package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseDTO.FeedbackResponseDTO;
import com.example.demo.entities.FeedbackDTO;
import com.example.demo.services.FeedbackService;

@RestController
@RequestMapping("/feedback")
@CrossOrigin("*")
public class FeedbackController {
	  @Autowired
	    private FeedbackService feedbackService;

	    @PostMapping
	    public ResponseEntity<FeedbackResponseDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
	        // Use the FeedbackService to create feedback
	        FeedbackResponseDTO responseDTO = feedbackService.createFeedback(feedbackDTO);
	        
	        // Return the response with HTTP status 201 Created
	        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	    }
}
