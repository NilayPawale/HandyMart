package com.example.demo.ResponseDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponseDTO {
    private int feedbackId;
    private int rating;
    private String comment;
    private int customerId;
    private int vendorId;
}

