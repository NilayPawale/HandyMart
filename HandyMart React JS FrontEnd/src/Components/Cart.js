import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import CartItem from './CartItem';
import { useParams, useNavigate } from 'react-router-dom';
import { setCustomerId, setCartId } from '../Reducer/customerSlice1';

const Cart = () => {
  const [cart, setCart] = useState(null);
  const { id } = useParams();
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const customerId = useSelector((state) => state.user.customerId);
  const cartId = useSelector((state) => state.user.cartId);

  const loginId = parseInt(id, 10);

  useEffect(() => {
    if (isNaN(loginId)) {
      console.error('Invalid login ID:', id);
      return;
    }

    fetch(`http://localhost:8080/getCustomerId/${loginId}`)
      .then((response) => {
        if (!response.ok) {
          return response.text().then((text) => { throw new Error(text); });
        }
        return response.text();
      })
      .then((data) => {
        const parsedId = parseInt(data, 10);
        if (!isNaN(parsedId)) {
          console.log('Fetched customer ID:', parsedId); // Log fetched customer ID
          dispatch(setCustomerId(parsedId));
        } else {
          throw new Error('Invalid customer ID response');
        }
      })
      .catch((error) => alert('Error fetching customer ID: ' + error.message));
  }, [loginId, dispatch]);

  useEffect(() => {
    console.log('Current customer ID from Redux:', customerId); // Log current customer ID

    if (Number.isInteger(customerId)) {
      fetch(`http://localhost:8080/getCart/${customerId}`)
        .then((response) => {
          if (!response.ok) {
            return response.json().then((data) => {
              throw new Error(data.message || 'Failed to fetch cart data');
            });
          }
          return response.json();
        })
        .then((data) => {
          console.log('Fetched cart data:', data);
          setCart(data);
          dispatch(setCartId(data.cartId));
        })
        .catch((error) => alert('Error fetching cart data: ' + error.message));
    }
  }, [customerId, dispatch]);

  const handleProceedToPayment = () => {
    navigate('/Payment');
  };

  const handleRemoveItemFromState = async (subProductId) => {
    try {
      const response = await fetch(`http://localhost:8080/${customerId}/items/${subProductId}`, {
        method: 'DELETE',
      });
  
      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText);
      }
  
      const cartResponse = await fetch(`http://localhost:8080/getCart/${customerId}`);
      if (!cartResponse.ok) {
        throw new Error('Failed to fetch updated cart data');
      }
      const updatedCartData = await cartResponse.json();
      setCart(updatedCartData);
  
      alert('Item removed successfully from cart.');
    } catch (error) {
      alert(`Error: ${error.message}`);
    }
  };

  if (!cart) {
    return <div>Loading...</div>;
  }

  return (
    <div className="cart">
      <h2>Cart ID: {cartId}</h2>
      <h4>Customer: {cart.customer.customerName}</h4>
      
      <button className="btn btn-success" onClick={handleProceedToPayment}>
        Proceed to Payment
      </button>
      <div id="cart-content" className="cart-items">
        {cart.items.map((item) => (
          <CartItem key={item.cartItemId} item={item} onRemove={handleRemoveItemFromState} />
        ))}
      </div>
    </div>
  );
};

export default Cart;
