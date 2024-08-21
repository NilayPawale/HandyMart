import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Rating from 'react-rating-stars-component'; // Import the star rating component
import { useLocation } from 'react-router-dom';

const Feedback = () => {
  const [rating, setRating] = useState(0); // Initialize rating as a number
  const [comment, setComment] = useState('');
  const [vendors, setVendors] = useState([]); // State for vendors
  const [selectedVendorId, setSelectedVendorId] = useState(''); // State for selected vendor ID
  const location = useLocation();
  const { customerId } = location.state || {}; // Get customerId from location state
  const cartId = useSelector((state) => state.user.cartId);
  const state = useSelector((state) => state);

  useEffect(() => {
    // Fetch vendors when component mounts
    fetch('http://localhost:8080/getallVendors')
      .then((response) => response.json())
      .then((data) => {
        console.log('Vendors fetched:', data); // Log fetched vendors
        setVendors(data);
      })
      .catch((error) => toast.error('Error fetching vendors: ' + error.message));
  }, []);

  useEffect(() => {
    console.log('Redux state:', state);
    console.log('Customer ID from Redux:', customerId); // Log customerId to check if it's being retrieved correctly
    console.log('Cart ID from Redux:', cartId); // Log customerId to check if it's being retrieved correctly
  }, [customerId]);

  const handleSubmit = (e) => {
    e.preventDefault();

    console.log('Submitting feedback');
    console.log('Customer ID:', customerId); // Log customerId before submitting
    console.log('Selected Vendor ID:', selectedVendorId); // Log selectedVendorId
    console.log('Rating:', rating); // Log rating
    console.log('Comment:', comment); // Log comment

    if (!customerId) {
      toast.error('You must be logged in to submit feedback');
      return;
    }

    const feedbackData = {
      rating,
      comment,
      customer_id: customerId, // Match the DTO field names
      vendor_id: parseInt(selectedVendorId, 10), // Convert to integer
    };

    fetch('http://localhost:8080/feedback', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(feedbackData),
    })
      .then((response) => {
        if (response.status === 201) {
          return response.json(); // Parse JSON response
        } else {
          throw new Error('Failed to submit feedback');
        }
      })
      .then((data) => {
        console.log('Response from server:', data); // Log the server response
        toast.success('Feedback submitted successfully!');
        resetForm(); // Reset the form and rating
      })
      .catch((error) => {
        toast.error('Error submitting feedback: ' + error.message);
      });
  };

  const resetForm = () => {
    setRating(0); // Reset rating
    setComment('');
    setSelectedVendorId(''); // Reset selected vendor
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Submit Feedback</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group mb-3">
          <label htmlFor="vendor">Vendor</label>
          <select
            id="vendor"
            className="form-control"
            value={selectedVendorId}
            onChange={(e) => setSelectedVendorId(e.target.value)}
            required
          >
            <option value="">Select a vendor</option>
            {vendors.map((vendor) => (
              <option key={vendor.vendor_id} value={vendor.vendor_id}>
                {vendor.firstname} {vendor.lastname}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group mb-3">
          <label htmlFor="rating">Rating</label>
          <Rating
            count={5}
            value={rating} // Bind value to state
            onChange={(newRating) => setRating(newRating)} // Handle changes
            size={24}
            activeColor="#ffd700"
          />
        </div>
        <div className="form-group mb-3">
          <label htmlFor="comment">Comment</label>
          <textarea
            id="comment"
            className="form-control"
            rows="4"
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            required
          ></textarea>
        </div>
        <button type="submit" className="btn btn-primary">Submit Feedback</button>
      </form>
      <ToastContainer />
    </div>
  );
};

export default Feedback;
