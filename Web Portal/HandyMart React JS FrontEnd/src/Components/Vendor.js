// import React, { useReducer } from 'react';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import { useNavigate } from 'react-router-dom';

// const init = {
//   firstname: { value: '', valid: false, touched: false, error: 'First Name is required' },
//   lastname: { value: '', valid: false, touched: false, error: 'Last Name is required' },
//   email: { value: '', valid: false, touched: false, error: 'Email is required' },
//   phone: { value: '', valid: false, touched: false, error: 'Phone Number is required' },
//   aadharnumber: { value: '', valid: false, touched: false, error: 'Aadhar Number is required' },
//   skillset: { value: '', valid: false, touched: false, error: 'Skillsets are required' },
//   username: { value: '', valid: false, touched: false, error: 'Username is required' },
//   password_hash: { value: '', valid: false, touched: false, error: 'Password is required' },
//   role_id: 3,
//   formValid: false,
// };

// const reducer = (state, action) => {
//   switch (action.type) {
//     case 'update':
//       const { key, value, touched, valid, error, formValid } = action.data;
//       return { ...state, [key]: { value, touched, valid, error }, formValid };
//     case 'reset':
//       return init;
//     default:
//       return state;
//   }
// };

// const VendorRegistration = () => {
//   const [vendor, dispatch] = useReducer(reducer, init);
//   const navigate = useNavigate();

//   const validateData = (key, val) => {
//     let valid = true;
//     let error = '';

//     switch (key) {
//       case 'firstname':
//       case 'lastname':
//         valid = /^[A-Za-z]+$/.test(val);
//         error = 'Field is required and should only contain alphabets';
//         break;
//       case 'email':
//         valid = /^\S+@\S+\.\S+$/.test(val);
//         error = 'Invalid email address';
//         break;
//       case 'phone':
//         valid = /^\d{10}$/.test(val);
//         error = 'Invalid phone number';
//         break;
//       case 'aadharnumber':
//         valid = /^\d{12}$/.test(val);
//         error = 'Invalid Aadhar number';
//         break;
//       case 'skillset':
//         valid = val.trim() !== '';
//         error = 'Skillsets are required';
//         break;
//       case 'username':
//         valid = /^[a-zA-Z0-9]+$/.test(val);
//         error = 'Username is required and should only contain alphanumeric characters';
//         break;
//       case 'password_hash':
//         valid = val.trim() !== '';
//         error = 'Password is required';
//         break;
//       default:
//         break;
//     }

//     return { valid, error };
//   };

//   const handleChange = (key, value) => {
//     const { valid, error } = validateData(key, value);

//     let formValid = true;
//     for (let k in vendor) {
//       if (vendor[k].valid === false || vendor[k].touched === false) {
//         formValid = false;
//         break;
//       }
//     }

//     dispatch({ type: 'update', data: { key, value, touched: true, valid, error, formValid } });
//   };

//   const submitData = (e) => {
//     e.preventDefault();

//     const vendorData = {
//       firstname: vendor.firstname.value,
//       lastname: vendor.lastname.value,
//       email: vendor.email.value,
//       phone: vendor.phone.value,
//       aadharnumber: vendor.aadharnumber.value,
//       skillset: vendor.skillset.value,
//       username: vendor.username.value,
//       password_hash: vendor.password_hash.value,
//       role_id: 3,
//     };

//     fetch('http://localhost:8080/regVendor', {
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json; charset=utf-8',
//       },
//       body: JSON.stringify(vendorData),
//     })
//       .then((response) => {
//         if (response.ok) {
//           alert('Vendor registered successfully!');
//           dispatch({ type: 'reset' });
//           navigate('/login');
//         } else {
//           response.text().then((errorMessage) => {
//             console.error('Registration failed. Error:', errorMessage);
//             alert('Registration failed. Please try again.');
//           });
//         }
//       })
//       .catch((error) => {
//         console.error('Error during registration:', error);
//         alert('An error occurred during registration. Please try again.');
//       });
//   };

//   return (
//     <div className="container mt-5">
//       <h1 className="text-center mb-4">Vendor Registration</h1>
//       <form>
//         <div className="mb-3">
//           <label className="form-label">First Name:</label>
//           <input
//             type="text"
//             className={`form-control ${vendor.firstname.touched && !vendor.firstname.valid ? 'is-invalid' : ''}`}
//             value={vendor.firstname.value}
//             onChange={(e) => handleChange('firstname', e.target.value)}
//             onBlur={(e) => handleChange('firstname', e.target.value)}
//           />
//           {vendor.firstname.touched && !vendor.firstname.valid && (
//             <div className="invalid-feedback">{vendor.firstname.error}</div>
//           )}
//         </div>

//         <div className="mb-3">
//           <label className="form-label">Last Name:</label>
//           <input
//             type="text"
//             className={`form-control ${vendor.lastname.touched && !vendor.lastname.valid ? 'is-invalid' : ''}`}
//             value={vendor.lastname.value}
//             onChange={(e) => handleChange('lastname', e.target.value)}
//             onBlur={(e) => handleChange('lastname', e.target.value)}
//           />
//           {vendor.lastname.touched && !vendor.lastname.valid && (
//             <div className="invalid-feedback">{vendor.lastname.error}</div>
//           )}
//         </div>

//         <div className="mb-3">
//           <label className="form-label">Email:</label>
//           <input
//             type="text"
//             className={`form-control ${vendor.email.touched && !vendor.email.valid ? 'is-invalid' : ''}`}
//             value={vendor.email.value}
//             onChange={(e) => handleChange('email', e.target.value)}
//             onBlur={(e) => handleChange('email', e.target.value)}
//           />
//           {vendor.email.touched && !vendor.email.valid && (
//             <div className="invalid-feedback">{vendor.email.error}</div>
//           )}
//         </div>

//         <div className="mb-3">
//           <label className="form-label">Phone Number:</label>
//           <input
//             type="text"
//             className={`form-control ${vendor.phone.touched && !vendor.phone.valid ? 'is-invalid' : ''}`}
//             value={vendor.phone.value}
//             onChange={(e) => handleChange('phone', e.target.value)}
//             onBlur={(e) => handleChange('phone', e.target.value)}
//           />
//           {vendor.phone.touched && !vendor.phone.valid && (
//             <div className="invalid-feedback">{vendor.phone.error}</div>
//           )}
//         </div>

//         <div className="mb-3">
//           <label className="form-label">Aadhar Number:</label>
//           <input
//             type="text"
//             className={`form-control ${vendor.aadharnumber.touched && !vendor.aadharnumber.valid ? 'is-invalid' : ''}`}
//             value={vendor.aadharnumber.value}
//             onChange={(e) => handleChange('aadharnumber', e.target.value)}
//             onBlur={(e) => handleChange('aadharnumber', e.target.value)}
//           />
//           {vendor.aadharnumber.touched && !vendor.aadharnumber.valid && (
//             <div className="invalid-feedback">{vendor.aadharnumber.error}</div>
//           )}
//         </div>

//         <div className="mb-3">
//           <label className="form-label">Skillset:</label>
//           <input
//             type="text"
//             className={`form-control ${vendor.skillset.touched && !vendor.skillset.valid ? 'is-invalid' : ''}`}
//             value={vendor.skillset.value}
//             onChange={(e) => handleChange('skillset', e.target.value)}
//             onBlur={(e) => handleChange('skillset', e.target.value)}
//           />
//           {vendor.skillset.touched && !vendor.skillset.valid && (
//             <div className="invalid-feedback">{vendor.skillset.error}</div>
//           )}
//         </div>

//         <div className="mb-3">
//           <label className="form-label">Username:</label>
//           <input
//             type="text"
//             className={`form-control ${vendor.username.touched && !vendor.username.valid ? 'is-invalid' : ''}`}
//             value={vendor.username.value}
//             onChange={(e) => handleChange('username', e.target.value)}
//             onBlur={(e) => handleChange('username', e.target.value)}
//           />
//           {vendor.username.touched && !vendor.username.valid && (
//             <div className="invalid-feedback">{vendor.username.error}</div>
//           )}
//         </div>

//         <div className="mb-3">
//           <label className="form-label">Password:</label>
//           <input
//             type="password"
//             className={`form-control ${vendor.password_hash.touched && !vendor.password_hash.valid ? 'is-invalid' : ''}`}
//             value={vendor.password_hash.value}
//             onChange={(e) => handleChange('password_hash', e.target.value)}
//             onBlur={(e) => handleChange('password_hash', e.target.value)}
//           />
//           {vendor.password_hash.touched && !vendor.password_hash.valid && (
//             <div className="invalid-feedback">{vendor.password_hash.error}</div>
//           )}
//         </div>

//         <button
//           type="submit"
//           className="btn btn-primary"
//           disabled={!vendor.formValid}
//           onClick={submitData}
//         >
//           Submit
//         </button>
//       </form>
//     </div>
//   );
// };

// export default VendorRegistration;


import React, { useReducer, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

const init = {
  firstname: { value: '', valid: false, touched: false, error: 'First Name is required' },
  lastname: { value: '', valid: false, touched: false, error: 'Last Name is required' },
  email: { value: '', valid: false, touched: false, error: 'Email is required' },
  phone: { value: '', valid: false, touched: false, error: 'Phone Number is required' },
  aadharnumber: { value: '', valid: false, touched: false, error: 'Aadhar Number is required' },
  skillset: { value: '', valid: false, touched: false, error: 'Skillsets are required' },
  username: { value: '', valid: false, touched: false, error: 'Username is required' },
  password_hash: { value: '', valid: false, touched: false, error: 'Password is required' },
  role_id: 3,
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

const VendorRegistration = () => {
  const [vendor, dispatch] = useReducer(reducer, init);
  const [registrationMessage, setRegistrationMessage] = useState('');
  const navigate = useNavigate();

  const validateData = (key, val) => {
    let valid = true;
    let error = '';

    switch (key) {
      case 'firstname':
      case 'lastname':
        valid = /^[A-Z][a-z]{3,}$/.test(val);
        error = 'Field is required and should only contain alphabets';
        break;
      case 'email':
        valid = /^[a-zA-Z0-9._%+-]+@(gmail\.com|knowit\.co\.in)$/.test(val);
        error = 'Invalid email address';
        break;
      case 'phone':
        valid = /^\d{10}$/.test(val);
        error = 'Invalid phone number';
        break;
      case 'aadharnumber':
        valid = /^\d{12}$/.test(val);
        error = 'Invalid Aadhar number';
        break;
      case 'skillset':
        valid = val.trim() !== '';
        error = 'Skillsets are required';
        break;
      case 'username':
        valid = /^[A-Za-z0-9_@]{4,20}$/.test(val);
        error = 'Username is required and should only contain alphanumeric characters';
        break;
      case 'password_hash':
        valid = /[\w\d@]{3,20}$/.test(val);
        error = 'Password is required';
        break;
      default:
        break;
    }

    return { valid, error };
  };

  const handleChange = (key, value) => {
    const { valid, error } = validateData(key, value);

    let formValid = true;
    for (let k in vendor) {
      if (vendor[k].valid === false || vendor[k].touched === false) {
        formValid = false;
        break;
      }
    }

    dispatch({ type: 'update', data: { key, value, touched: true, valid, error, formValid } });
  };

  const submitData = (e) => {
    e.preventDefault();

    const vendorData = {
      firstname: vendor.firstname.value,
      lastname: vendor.lastname.value,
      email: vendor.email.value,
      phone: vendor.phone.value,
      aadharnumber: vendor.aadharnumber.value,
      skillset: vendor.skillset.value,
      username: vendor.username.value,
      password_hash: vendor.password_hash.value,
      role_id: 3,
    };

    fetch('http://localhost:8080/regVendor', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      },
      body: JSON.stringify(vendorData),
    })
      .then((response) => {
        if (response.ok) {
          setRegistrationMessage('Vendor registered successfully!');
          dispatch({ type: 'reset' });
          navigate('/login');
        } else {
          response.text().then((errorMessage) => {
            console.error('Registration failed. Error:', errorMessage);
            setRegistrationMessage('Registration failed. Please try again.');
          });
        }
      })
      .catch((error) => {
        console.error('Error during registration:', error);
        setRegistrationMessage('An error occurred during registration. Please try again.');
      });
  };

  return (
    <div className="container mt-5">
      <div className="card p-4 mx-auto" style={{ maxWidth: '400px' }}>
        <h1 className="text-center mb-4">Vendor Registration</h1>
        {registrationMessage && (
          <div className="alert alert-info" role="alert">
            {registrationMessage}
          </div>
        )}
        <form>
          <div className="mb-3">
            <label className="form-label">First Name:</label>
            <input
              type="text"
              className={`form-control ${vendor.firstname.touched && !vendor.firstname.valid ? 'is-invalid' : ''}`}
              value={vendor.firstname.value}
              onChange={(e) => handleChange('firstname', e.target.value)}
              onBlur={(e) => handleChange('firstname', e.target.value)}
            />
            {vendor.firstname.touched && !vendor.firstname.valid && (
              <div className="invalid-feedback">{vendor.firstname.error}</div>
            )}
          </div>

          <div className="mb-3">
            <label className="form-label">Last Name:</label>
            <input
              type="text"
              className={`form-control ${vendor.lastname.touched && !vendor.lastname.valid ? 'is-invalid' : ''}`}
              value={vendor.lastname.value}
              onChange={(e) => handleChange('lastname', e.target.value)}
              onBlur={(e) => handleChange('lastname', e.target.value)}
            />
            {vendor.lastname.touched && !vendor.lastname.valid && (
              <div className="invalid-feedback">{vendor.lastname.error}</div>
            )}
          </div>

          <div className="mb-3">
            <label className="form-label">Email:</label>
            <input
              type="text"
              className={`form-control ${vendor.email.touched && !vendor.email.valid ? 'is-invalid' : ''}`}
              value={vendor.email.value}
              onChange={(e) => handleChange('email', e.target.value)}
              onBlur={(e) => handleChange('email', e.target.value)}
            />
            {vendor.email.touched && !vendor.email.valid && (
              <div className="invalid-feedback">{vendor.email.error}</div>
            )}
          </div>

          <div className="mb-3">
            <label className="form-label">Phone Number:</label>
            <input
              type="text"
              className={`form-control ${vendor.phone.touched && !vendor.phone.valid ? 'is-invalid' : ''}`}
              value={vendor.phone.value}
              onChange={(e) => handleChange('phone', e.target.value)}
              onBlur={(e) => handleChange('phone', e.target.value)}
            />
            {vendor.phone.touched && !vendor.phone.valid && (
              <div className="invalid-feedback">{vendor.phone.error}</div>
            )}
          </div>

          <div className="mb-3">
            <label className="form-label">Aadhar Number:</label>
            <input
              type="text"
              className={`form-control ${vendor.aadharnumber.touched && !vendor.aadharnumber.valid ? 'is-invalid' : ''}`}
              value={vendor.aadharnumber.value}
              onChange={(e) => handleChange('aadharnumber', e.target.value)}
              onBlur={(e) => handleChange('aadharnumber', e.target.value)}
            />
            {vendor.aadharnumber.touched && !vendor.aadharnumber.valid && (
              <div className="invalid-feedback">{vendor.aadharnumber.error}</div>
            )}
          </div>

          <div className="mb-3">
            <label className="form-label">Skillset:</label>
            <input
              type="text"
              className={`form-control ${vendor.skillset.touched && !vendor.skillset.valid ? 'is-invalid' : ''}`}
              value={vendor.skillset.value}
              onChange={(e) => handleChange('skillset', e.target.value)}
              onBlur={(e) => handleChange('skillset', e.target.value)}
            />
            {vendor.skillset.touched && !vendor.skillset.valid && (
              <div className="invalid-feedback">{vendor.skillset.error}</div>
            )}
          </div>

          <div className="mb-3">
            <label className="form-label">Username:</label>
            <input
              type="text"
              className={`form-control ${vendor.username.touched && !vendor.username.valid ? 'is-invalid' : ''}`}
              value={vendor.username.value}
              onChange={(e) => handleChange('username', e.target.value)}
              onBlur={(e) => handleChange('username', e.target.value)}
            />
            {vendor.username.touched && !vendor.username.valid && (
              <div className="invalid-feedback">{vendor.username.error}</div>
            )}
          </div>

          <div className="mb-3">
            <label className="form-label">Password:</label>
            <input
              type="password"
              className={`form-control ${vendor.password_hash.touched && !vendor.password_hash.valid ? 'is-invalid' : ''}`}
              value={vendor.password_hash.value}
              onChange={(e) => handleChange('password_hash', e.target.value)}
              onBlur={(e) => handleChange('password_hash', e.target.value)}
            />
            {vendor.password_hash.touched && !vendor.password_hash.valid && (
              <div className="invalid-feedback">{vendor.password_hash.error}</div>
            )}
          </div>

          <button
            type="submit"
            className="btn btn-primary"
            disabled={!vendor.formValid}
            onClick={submitData}
          >
            Submit
          </button>
        </form>
      </div>
    </div>
  );
};

export default VendorRegistration;
