// Login.js
import React, { useReducer } from 'react';
import { Link, useNavigate } from 'react-router-dom'; // Import Link for navigation
import 'bootstrap/dist/css/bootstrap.min.css';
import { useDispatch } from 'react-redux'; // Import useDispatch
import { setCustomerId } from '../Reducer/customerSlice1'; // Import the setCustomerId action

const init = {
  username: { value: '', valid: false, touched: false, error: 'Username is required' },
  password_hash: { value: '', valid: false, touched: false, error: 'Password is required' },
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

const Login = () => {
  const [loginData, dispatch] = useReducer(reducer, init);
  const reduxDispatch = useDispatch(); // Initialize dispatch
  let navigate = useNavigate();

  const validateData = (key, val) => {
    let valid = true;
    let error = '';

    switch (key) {
      case 'username':
        var pattern1 = /^[A-Za-z0-9_@]{4,20}$/;
        if (!pattern1.test(val)) {
          valid = false;
          error = "Invalid username";
        }
        break;

      case 'password_hash':
        var pattern2 = /[\w\d@]{3,20}$/;
        if (!pattern2.test(val)) {
          valid = false;
          error = "Invalid password";
        }
        break;
      default:
    }

    return { valid, error };
  };

  const handleChange = (key, value) => {
    const { valid, error } = validateData(key, value);

    let formValid = true;
    for (let k in loginData) {
      if (loginData[k].valid === false || loginData[k].touched === false) {
        formValid = false;
        break;
      }
    }

    dispatch({ type: 'update', data: { key, value, touched: true, valid, error, formValid } });
  };

  const submitData = (e) => {
    e.preventDefault();

    const reqOptions = {
      method: "POST",
      headers: { 'content-type': 'application/json' },
      body: JSON.stringify({
        username: loginData.username.value,
        password_hash: loginData.password_hash.value,
      }),
    };

    fetch("http://localhost:8080/checkLogin", reqOptions)
      .then((resp) => {
        if (resp.ok) {
          return resp.text();
        } else {
          console.log("in error else");
          document.getElementById("apr").innerHTML = "Incorrect email or password";
          throw new Error("Service Error");
        }
      })
      .then((text) => (text.length ? JSON.parse(text) : {}))
      .then((response) => {
        if (Object.keys(response).length === 0) {
          // Handle empty response
        } else {
          if (response.status_approve === false) {
            document.getElementById("apr").innerHTML = "Not approved";
          } else {
            // Store loggedIn information in local storage
            localStorage.setItem("loggedIn", JSON.stringify(response));

            // Retrieve and store customer ID in local storage
            const customerId = response.customer_id;
            localStorage.setItem("customer_id", customerId);

            // Set customer ID in Redux store
            reduxDispatch(setCustomerId(customerId));

            if (response.role_id.role_id === 1) {
              navigate(`/admin-home/${response.login_id}`);
            } else if (response.role_id.role_id === 2) {
              navigate(`/customer-home/${response.login_id}`);
            } else if (response.role_id.role_id === 3) {
              navigate(`/vendor-home/${response.login_id}`);
            }
          }
        }
      })
      .catch((error) => {
        console.error('Fetch error:', error);
      });
  };

  return (
    <div className="container-fluid d-flex justify-content-center align-items-center" style={{ height: '100vh' }}>
      <div className="card p-4" style={{ width: '400px' }}>
        <h1 className="text-center mb-4">Login</h1>
        <form>
          {/* Username */}
          <div className="mb-3">
            <label className="form-label">Username:</label>
            <input
              type="text"
              className={`form-control ${loginData.username.touched && !loginData.username.valid ? 'is-invalid' : ''}`}
              value={loginData.username.value}
              onChange={(e) => handleChange('username', e.target.value)}
              onBlur={(e) => handleChange('username', e.target.value)}
            />
            {loginData.username.touched && !loginData.username.valid && (
              <div className="invalid-feedback">{loginData.username.error}</div>
            )}
          </div>

          {/* Password */}
          <div className="mb-3">
            <label className="form-label">Password:</label>
            <input
              type="password"
              className={`form-control ${loginData.password_hash.touched && !loginData.password_hash.valid ? 'is-invalid' : ''}`}
              value={loginData.password_hash.value}
              onChange={(e) => handleChange('password_hash', e.target.value)}
              onBlur={(e) => handleChange('password_hash', e.target.value)}
            />
            {loginData.password_hash.touched && !loginData.password_hash.valid && (
              <div className="invalid-feedback">{loginData.password_hash.error}</div>
            )}
          </div>
          <div id="apr" name="apr" style={{ color: 'red' }}> </div>
          <button
            type="submit"
            className="btn btn-dark mx-auto d-block"
            disabled={!loginData.formValid}
            onClick={submitData}
          >
            Login
          </button>

          <div className="mt-3 text-center">
            <p className="mb-0">
              New Vendor? <Link to="/vendor-registration">Register here</Link>
            </p>
            <p className="mb-0">
              New Customer? <Link to="/customer-reg">Register here</Link>
            </p>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
