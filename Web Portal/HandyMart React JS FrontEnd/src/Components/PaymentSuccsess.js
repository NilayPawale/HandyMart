import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

const PaymentSuccessPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  
  // Retrieve paymentId and deliveryAddress from the location state
  const { paymentId, deliveryAddress } = location.state || {};

  const handleGoToPaymentPage = () => {
    navigate('/paymentRecipt'); // Navigate to the Payment Receipt page
  };

  const handleGoToOrderPage = () => {
    navigate('/trackOrder', {
      state: {
        paymentId: paymentId, // Pass the paymentId to TrackOrderPage
        deliveryAddress: deliveryAddress // Pass the deliveryAddress if needed
      },
    });
  };

  return (
    <div className="payment-success-page">
      <h2>Payment Successful!</h2>
      <p>Thank you for your payment. Your transaction was successful.</p>
      <button className="btn btn-primary" onClick={handleGoToPaymentPage}>
        Click To Get Payment Receipt
      </button>
      <button className="btn btn-secondary" onClick={handleGoToOrderPage}>
        Click To Track Order
      </button>
    </div>
  );
};

export default PaymentSuccessPage;
