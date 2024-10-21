import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

const PaymentPage = () => {
  const [cart, setCart] = useState(null);
  const customerId = useSelector((state) => state.user.customerId);
  const cartId = useSelector((state) => state.user.cartId);
  const navigate = useNavigate();

  useEffect(() => {
    console.log('Customer ID from Redux:', customerId);
    console.log('Cart ID from Redux:', cartId);

    if (cartId !== null) {
      fetch(`http://localhost:8080/getCart/${customerId}`)
        .then((response) => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then((data) => {
          setCart(data);
        })
        .catch((error) => console.error('Error fetching cart data:', error));
    }
  }, [cartId, customerId]);

  const handleDownloadPDF = () => {
    const input = document.getElementById('cart-content');
    html2canvas(input).then((canvas) => {
      const imgData = canvas.toDataURL('image/png');
      const pdf = new jsPDF();
      const imgWidth = 210;
      const pageHeight = 295;
      const imgHeight = canvas.height * imgWidth / canvas.width;
      let heightLeft = imgHeight;

      let position = 0;

      pdf.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
      heightLeft -= pageHeight;

      while (heightLeft >= 0) {
        position = heightLeft - imgHeight;
        pdf.addPage();
        pdf.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
        heightLeft -= pageHeight;
      }

      pdf.save(`cart_invoice_${cart.cartId}.pdf`);
    });
  };

  const handleFeedbackNavigation = () => {
    navigate(`/customer-home/${customerId}`);
  };

  if (!cart) {
    return <div>Loading cart...</div>;
  }

  const totalAmount = cart.items.reduce((sum, item) => sum + item.price, 0);

  return (
    <div className="container mt-3">
      <h2>Payment Page</h2>
      <div className="mb-4">
        <h4>Customer ID: {customerId !== null ? customerId : 'Not available'}</h4>
        <h4>Cart ID: {cartId !== null ? cartId : 'Not available'}</h4>
      </div>
      <h3>Invoice</h3>
      <div id="cart-content">
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>Item Name</th>
              <th>Quantity</th>
              <th>Price</th>
              <th>Total</th>
            </tr>
          </thead>
          <tbody>
            {cart.items.map((item) => (
              <tr key={item.cartItemId}>
                <td>{item.categories.categoryName}</td>
                <td>{item.quantity}</td>
                <td>${(item.price / item.quantity).toFixed(2)}</td>
                <td>${(item.quantity * (item.price / item.quantity)).toFixed(2)}</td>
              </tr>
            ))}
            <tr>
              <td colSpan="3" className="text-right"><strong>Total Amount:</strong></td>
              <td>${totalAmount.toFixed(2)}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <button className="btn btn-primary mt-3" onClick={handleDownloadPDF}>
        Download PDF Invoice
      </button>
      <button className="btn btn-secondary mt-3" onClick={handleFeedbackNavigation}>
        GO TO Home
      </button>
    </div>
  );
};

export default PaymentPage;
