import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom'; // Import useNavigate
import { useSelector } from 'react-redux';

const TrackOrderPage = () => {
  const location = useLocation(); // Access location object
  const navigate = useNavigate(); // Initialize navigate function
  const { paymentId, deliveryAddress } = location.state || {}; // Retrieve state from location
  const [orderDetails, setOrderDetails] = useState(null);
  const [error, setError] = useState('');

  const cartId = useSelector((state) => state.user.cartId);
  const customerId = useSelector((state) => state.user.customerId);

  useEffect(() => {
    if (paymentId) {
      console.log(`Fetching order details for Cart ID: ${cartId} and Payment ID: ${paymentId}`);
  
      // Fetch the order details from the API
      fetch(`http://localhost:8080/getOrder/${cartId}/${paymentId}`)
        .then((response) => {
          console.log('Response Status:', response.status);
          console.log('Response Status Text:', response.statusText);
          
          if (!response.ok) {
            return response.text().then((text) => {
              console.error('Response Text:', text);
              throw new Error('Network response was not ok');
            });
          }
          return response.json();
        })
        .then((data) => {
          setOrderDetails(data);
          setError('');
        })
        .catch((error) => {
          console.error('Error fetching order details:', error);
          setOrderDetails(null);
          setError('Order not found or an error occurred.');
        });
    } else {
      setError('Payment ID is required.');
    }
  }, [paymentId, cartId]); // Depend on paymentId and cartId to trigger the effect

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Track Your Order</h2>

      {error && <p className="alert alert-danger">{error}</p>}

      {orderDetails && (
        <div className="card">
          <div className="card-body">
            <h3 className="card-title mb-3">Order Details</h3>
            <ul className="list-group list-group-flush">
              <li className="list-group-item"><strong>Order ID:</strong> {orderDetails.orderId}</li>
              <li className="list-group-item"><strong>Tracking Number:</strong> {orderDetails.trackingNumber}</li>
              <li className="list-group-item"><strong>Order Date:</strong> {new Date(orderDetails.orderDate).toLocaleDateString()}</li>
              <li className="list-group-item"><strong>Delivery Address:</strong> {orderDetails.deliveryAddress}</li>
              <li className="list-group-item"><strong>Delivery Date:</strong> {new Date(orderDetails.deliveryDate).toLocaleDateString()}</li>
              <li className="list-group-item"><strong>Cart ID:</strong> {orderDetails.cartId}</li>
              <li className="list-group-item"><strong>Payment ID:</strong> {orderDetails.paymentId}</li>
              <li className="list-group-item"><strong>Payment Method:</strong> {orderDetails.paymentMethod}</li>
              <li className="list-group-item"><strong>Customer Name:</strong> {orderDetails.customerName}</li>
            </ul>
            <button
              className="btn btn-primary mt-3"
              onClick={() => navigate(`/customer-home/${customerId}`)} // Navigate to home page
            >
              Go to Home
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default TrackOrderPage;
