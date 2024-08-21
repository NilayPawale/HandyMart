import React from 'react';
import { useSelector } from 'react-redux';
import '../Styling/cartItem.css';

const CartItem = ({ item, onRemove }) => {
  const customerId = useSelector((state) => state.user.customerId);

  const handleRemoveItem = () => {
    const subProductId = item.categories?.subProductCategoryId;

    if (!Number.isInteger(subProductId)) {
      console.error('Invalid subProductId:', subProductId);
      return;
    }

    if (onRemove) {
      onRemove(subProductId); // Notify parent component to handle deletion
    }
  };

  return (
    <div className="cart-item">
      <h5>{item.categories.categoryName}</h5>
      <p>Quantity: {item.quantity}</p>
      <p>Price: {item.price ? `$${item.price}` : 'N/A'}</p>
      <button className="btn btn-danger" onClick={handleRemoveItem}>
        Remove
      </button>
    </div>
  );
};

export default CartItem;
