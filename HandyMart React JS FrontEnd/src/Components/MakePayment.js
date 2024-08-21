import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

const PaymentPage2 = () => {
  const [paymentMethod, setPaymentMethod] = useState('');
  const [deliveryAddress, setDeliveryAddress] = useState('');
  const [paymentId, setPaymentId] = useState(''); // Add state for paymentId if needed
  const [mapUrl, setMapUrl] = useState(''); // State for the map URL
  const navigate = useNavigate();

  const cartId = useSelector((state) => state.user.cartId);

  const handlePaymentSubmit = (e) => {
    e.preventDefault();

    const paymentData = {
      cartId: cartId,
      paymentMethod: paymentMethod,
      deliveryAddress: deliveryAddress, // Match this with the backend
    };

    fetch('http://localhost:8080/payments', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(paymentData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data) => {
        console.log('Payment successful:', data);
        setPaymentId(data.paymentId);
        navigate('/payment-success', {
          state: { 
            paymentId: data.paymentId, 
            deliveryAddress: deliveryAddress 
          },
        });
      })
      .catch((error) => {
        console.error('Error processing payment:', error);
      });
  };

  const handleAddressChange = (e) => {
    const newAddress = e.target.value;
    setDeliveryAddress(newAddress);

    // Update the map URL based on the new address
    const encodedAddress = encodeURIComponent(newAddress);
    const newMapUrl = `https://maps.google.com/maps?q=${encodedAddress}&t=&z=13&ie=UTF8&iwloc=&output=embed`;
    setMapUrl(newMapUrl);
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Make Payment</h2>
      <form onSubmit={handlePaymentSubmit}>
        <div className="form-group mb-3">
          <label className="form-label">Delivery Address</label>
          <input
            type="text"
            className="form-control"
            value={deliveryAddress}
            onChange={handleAddressChange}
            required
            placeholder="Enter your delivery address"
          />
        </div>
        <div className="form-group mb-4">
          <label className="form-label">Payment Method</label>
          <select
            className="form-select"
            value={paymentMethod}
            onChange={(e) => setPaymentMethod(e.target.value)}
            required
          >
            <option value="">Select a method</option>
            <option value="creditCard">Credit Card</option>
            <option value="debitCard">Debit Card</option>
            <option value="paypal">PayPal</option>
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Pay Now</button>
      </form>

      {/* Embedded Google Map */}
      <div className="mt-4">
        <h4 className="mb-3">Delivery Location</h4>
        <div className="embed-responsive embed-responsive-16by9">
          <iframe
            width="100%"
            height="450"
            src={mapUrl || "https://maps.google.com/maps?q=Shevagaon+Ahemdnagar&t=&z=13&ie=UTF8&iwloc=&output=embed"}
            frameBorder="0"
            scrolling="no"
            marginHeight="0"
            marginWidth="0"
            title="Google Maps"
          ></iframe>
        </div>
      </div>
    </div>
  );
};

export default PaymentPage2;
