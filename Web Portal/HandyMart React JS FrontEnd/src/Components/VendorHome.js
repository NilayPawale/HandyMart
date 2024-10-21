import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import LogoutForm from './LogoutForm';

const VendorHome = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const loginId = JSON.parse(localStorage.getItem("loggedIn")).login_id;

    // Fetch products by vendor_id
    fetch(`http://localhost:8080/byVendor?login_id=${loginId}`)
      .then(response => response.json())
      .then(data => {
        localStorage.setItem("loggedVendor", JSON.stringify(data));
        setProducts(data);
      })
      .catch(error => console.error('Error fetching products', error));
  }, []);

  const deleteProduct = (productId) => {
    fetch(`http://localhost:8080/byVendor/${productId}`, {
      method: 'DELETE',
    })
      .then(response => {
        if (response.ok) {
          // Filter out the deleted product from the state
          setProducts(products.filter(product => product.vendor_product_id !== productId));
        } else {
          console.error('Failed to delete product:', response.statusText);
        }
      })
      .catch(error => console.error('Error deleting product:', error));
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <div className="container">
          <Link className="navbar-brand" to="/vendor-home">
            Vendor Home
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav me-auto">
              <li className="nav-item">
                <Link className="nav-link" to="/add-product">
                  Products
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/vendor-profile">
                  Profile
                </Link>
              </li>
            </ul>
            <ul className="navbar-nav">
              <li className="nav-item">
                <Link className="nav-link" to="/add-product">
                  Add Product
                </Link>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#logout">
                  <LogoutForm />
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="container mt-3">
        <h2 className="mb-4"> Products</h2>
        <div className="row">
          {products.map(product => (
            <div key={product.vendor_product_id} className="col-md-4 mb-4">
              <div className="card">
                <div className="card-body">
                  <h5 className="card-title">{product.productname}</h5>
                  <p className="card-text">{product.description}</p>
                  <p className="card-text">Price: ${product.price}</p>
                  <button 
                    className="btn btn-danger" 
                     onClick={() => deleteProduct(product.vendor_product_id)} // Call delete function
                  >
                    delete 
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default VendorHome;
