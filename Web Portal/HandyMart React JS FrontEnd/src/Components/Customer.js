import React, { useReducer } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

const init = {
  //customer_id: { value: '', valid: false, touched: false, error: 'Customer ID is required' },
  first_name: { value: '', valid: false, touched: false, error: 'First Name is required' },
  last_name: { value: '', valid: false, touched: false, error: 'Last Name is required' },
  email: { value: '', valid: false, touched: false, error: 'Email is required' },
  phone_number: { value: '', valid: false, touched: false, error: 'Phone Number is required' },
  address: { value: '', valid: false, touched: false, error: 'Address is required' },
  role_id:2,
  formValid: false,
};

const reducer = (state, action) => {
  switch (action.type) {
    case 'update':
      const { key, value, touched, valid, error, formValid } = action.data;
      return { ...state, [key]: { value, touched, valid, error }, formValid };
    case 'reset':
      return init;
    default:
      return state;
  }
};

const CustomerRegistration = () => {
  const [customer, dispatch] = useReducer(reducer, init);
  const navigate=useNavigate();

  const validateData = (key, val) => {
    let valid = true;
    let error = '';

    switch (key) {
      // case 'customer_id':
      //   valid = val.trim() !== '';
      //   error = 'Customer ID is required';
      //   break;
      case 'first_name':
        valid = /^[A-Za-z]+$/.test(val);
        error = 'First Name is required';
        break;
      case 'last_name':
        valid = /^[A-Za-z]+$/.test(val);
        error = 'Last Name is required';
        break;
      case 'email':
        valid = /^\S+@\S+\.\S+$/.test(val);
        error = 'Invalid email address';
        break;
      case 'phone_number':
        valid = /^\d{10}$/.test(val);
        error = 'Invalid phone number';
        break;
      case 'address':
        valid = val.trim() !== '';
        error = 'Address is required';
        break;
      default:
        break;
    }

    return { valid, error };
  };

  const handleChange = (key, value) => {
    const { valid, error } = validateData(key, value);
  
    let formValid = true;
    for (let k in customer) {
      if (k !== key && (customer[k].valid === false || customer[k].touched === false)) {
        formValid = false;
        break;
      }
    }
  
    dispatch({ type: 'update', data: { key, value, touched: true, valid, error, formValid } });
  };
  

  const submitData = (e) => {
    e.preventDefault();

  // Hardcoded role IDs
  const adminRoleId = 1;
  const customerRoleId = 2;
  const vendorRoleId = 3;

  // Construct the customer data to send to the server
  const customerData = {
    //customer_id: customer.customer_id.value,
    first_name: customer.first_name.value,
    last_name: customer.last_name.value,
    email: customer.email.value,
    phone_number: customer.phone_number.value,
    address: customer.address.value,
    login_id: {
      username: 'some_username', // Replace with actual username
      password_hash: 'some_password', // Replace with actual password
      role_id: customerRoleId, // Use the hardcoded customer role ID
    },
  };

  fetch('http://localhost:8080/regCustomer', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(customerData),
  })
    .then((response) => {
      if (response.ok) {
        // Successful registration
        alert('Customer registered successfully!');
        dispatch({ type: 'reset' }); // Reset the form after successful registration

        // Navigate to the login page
        navigate('/login');
      } else {
        // Registration failed
        response.text().then((errorMessage) => {
          console.error('Registration failed. Error:', errorMessage);
          alert('Registration failed. Please try again.');
        });
      }
    })
    .catch((error) => {
      console.error('Error during registration:', error);
      alert('An error occurred during registration. Please try again.');
    });
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Customer Registration</h1>
      <form>
        {/* Customer ID */}
        {/* <div className="mb-3">
          <label className="form-label">Customer ID:</label>
          <input
            type="text"
            className={`form-control ${customer.customer_id.touched && !customer.customer_id.valid ? 'is-invalid' : ''}`}
            value={customer.customer_id.value}
            onChange={(e) => handleChange('customer_id', e.target.value)}
            onBlur={(e) => handleChange('customer_id', e.target.value)}
          />
          {customer.customer_id.touched && !customer.customer_id.valid && (
            <div className="invalid-feedback">{customer.customer_id.error}</div>
          )}
        </div> */}

        {/* First Name */}
        <div className="mb-3">
          <label className="form-label">First Name:</label>
          <input
            type="text"
            className={`form-control ${customer.first_name.touched && !customer.first_name.valid ? 'is-invalid' : ''}`}
            value={customer.first_name.value}
            onChange={(e) => handleChange('first_name', e.target.value)}
            onBlur={(e) => handleChange('first_name', e.target.value)}
          />
          {customer.first_name.touched && !customer.first_name.valid && (
            <div className="invalid-feedback">{customer.first_name.error}</div>
          )}
        </div>

        {/* Last Name */}
        <div className="mb-3">
          <label className="form-label">Last Name:</label>
          <input
            type="text"
            className={`form-control ${customer.last_name.touched && !customer.last_name.valid ? 'is-invalid' : ''}`}
            value={customer.last_name.value}
            onChange={(e) => handleChange('last_name', e.target.value)}
            onBlur={(e) => handleChange('last_name', e.target.value)}
          />
          {customer.last_name.touched && !customer.last_name.valid && (
            <div className="invalid-feedback">{customer.last_name.error}</div>
          )}
        </div>

        {/* Email */}
        <div className="mb-3">
          <label className="form-label">Email:</label>
          <input
            type="text"
            className={`form-control ${customer.email.touched && !customer.email.valid ? 'is-invalid' : ''}`}
            value={customer.email.value}
            onChange={(e) => handleChange('email', e.target.value)}
            onBlur={(e) => handleChange('email', e.target.value)}
          />
          {customer.email.touched && !customer.email.valid && (
            <div className="invalid-feedback">{customer.email.error}</div>
          )}
        </div>

        {/* Phone Number */}
        <div className="mb-3">
          <label className="form-label">Phone Number:</label>
          <input
            type="text"
            className={`form-control ${customer.phone_number.touched && !customer.phone_number.valid ? 'is-invalid' : ''}`}
            value={customer.phone_number.value}
            onChange={(e) => handleChange('phone_number', e.target.value)}
            onBlur={(e) => handleChange('phone_number', e.target.value)}
          />
          {customer.phone_number.touched && !customer.phone_number.valid && (
            <div className="invalid-feedback">{customer.phone_number.error}</div>
          )}
        </div>

        {/* Address */}
        <div className="mb-3">
          <label className="form-label">Address:</label>
          <input
            type="text"
            className={`form-control ${customer.address.touched && !customer.address.valid ? 'is-invalid' : ''}`}
            value={customer.address.value}
            onChange={(e) => handleChange('address', e.target.value)}
            onBlur={(e) => handleChange('address', e.target.value)}
          />
          {customer.address.touched && !customer.address.valid && (
            <div className="invalid-feedback">{customer.address.error}</div>
          )}
        </div>

        <button
          type="submit"
          className="btn btn-primary"
          disabled={!customer.formValid}
          onClick={submitData}
        >
          Submit
        </button>
      </form>
    </div>
  );
};

export default CustomerRegistration;
