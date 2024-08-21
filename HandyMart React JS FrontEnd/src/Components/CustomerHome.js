import React, { useState, useEffect } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import LogoutForm from './LogoutForm';
import { ToastContainer, toast } from 'react-toastify'; 
import 'react-toastify/dist/ReactToastify.css'; 
import { setCustomerId } from '../Reducer/customerSlice1'; 

const CustomerHome = () => {
  const [products, setProducts] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [filteredProducts, setFilteredProducts] = useState([]);
  const dispatch = useDispatch();
  const customerId = useSelector((state) => state.user.customerId);
  const { id } = useParams(); 
  const loginId = parseInt(id, 10);
  const navigate = useNavigate(); // Add this line

  useEffect(() => {
    if (isNaN(loginId)) {
      console.error('Invalid login ID:', id);
      return;
    }

    fetch(`http://localhost:8080/getCustomerId/${loginId}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.text(); 
      })
      .then(data => {
        const parsedId = parseInt(data, 10);
        if (!isNaN(parsedId)) {
          dispatch(setCustomerId(parsedId)); 
          console.log('Customer ID set to:', parsedId); // Log customer ID after setting
        } else {
          throw new Error('Invalid customer ID response');
        }
      })
      .catch(error => console.error('Error fetching customer ID:', error));
  }, [loginId, dispatch]);

  useEffect(() => {
    if (customerId !== null) {
      console.log('Fetching products for customer ID:', customerId); // Log customer ID before fetching products
      fetch('http://localhost:8080/getallProducts') 
        .then(response => response.json())
        .then(data => {
          setProducts(data);
          setFilteredProducts(data); 
        })
        .catch(error => console.error('Error fetching products:', error));
    }
  }, [customerId]);

  useEffect(() => {
    if (searchQuery) {
      const filtered = products.filter(product =>
        product.productname.toLowerCase().includes(searchQuery.toLowerCase()) ||
        product.description.toLowerCase().includes(searchQuery.toLowerCase())
      );
      setFilteredProducts(filtered);
    } else {
      setFilteredProducts(products);
    }
  }, [searchQuery, products]);

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleAddToCart = (subproductId, productName) => {
    if (customerId === null) {
      console.error('Customer ID is not set');
      toast.error('Unable to add product to cart');
      return;
    }

    console.log('Adding product to cart with customer ID:', customerId); // Log customer ID before adding to cart

    const quantity = 1; 

    const payload = {
      quantity,
      customerId,
      subproductId,
    };

    console.log('Payload to be sent:', payload);

    fetch('http://localhost:8080/addcart', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(payload),
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.text(); 
      })
      .then(data => {
        console.log('Response from server:', data); 
        if (data.trim() === "Success") {
          toast.success(`Product "${productName}" added to cart!`);
        } else {
          toast.error('Unexpected response from server');
        }
      })
      .catch(error => {
        console.error('Error adding product to cart:', error);
        toast.error('Error adding product to cart');
      });
  };

  const handleFeedbackNavigation = () => {
    navigate(`/feedback`, { state: { customerId } }); // Pass customerId using state
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <div className="container">
          <a className="navbar-brand" href="#home">HOME</a>
          <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav me-auto">
              <li className="nav-item">
                <Link className="nav-link" to={`/cart/${id}`}>CART</Link>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/trackOrder">ORDERS</a>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/payment">PAYMENTS</Link>
              </li>
              <li className="nav-item">
                <a className="nav-link" onClick={handleFeedbackNavigation}>FEEDBACK & REVIEWS</a> {/* Use the handler */}
              </li>
            </ul>
            <ul className="navbar-nav">
              <LogoutForm />
            </ul>
          </div>
        </div>
      </nav>

      <div className="container mt-5">
        <h2 className="mb-4 text-center">Explore Our Products</h2>
        <div className="row mb-4">
          <div className="col-12">
            <input
              type="text"
              className="form-control"
              placeholder="Search for products..."
              value={searchQuery}
              onChange={handleSearchChange}
            />
          </div>
        </div>
        <div className="row">
          {filteredProducts.map((product) => (
            <div key={product.vendor_product_id} className="col-sm-6 col-md-4 mb-4">
              <div className="card shadow-sm">
                {/* Uncomment and use if you have an image URL */}
                {/* <img src={`data:image/jpeg;base64,${product.image}`} className="card-img-top" alt={product.productname} /> */}
                <div className="card-body">
                  <h5 className="card-title text-truncate">{product.productname}</h5>
                  <p className="card-text">{product.description}</p>
                  <p className="card-text"><strong>Price:</strong> ${product.price}</p>
                  <p className="card-text"><strong>Inventory:</strong> {product.inventory}</p>
                  <p className="card-text"><strong>Subproduct ID:</strong> {product.subProductCategory.subcategory_id}</p>
                  <button
                    className="btn btn-primary btn-block"
                    onClick={() => handleAddToCart(product.subProductCategory.subcategory_id, product.productname)}
                  >
                    Add To Cart
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <ToastContainer />
    </>
  );
};

export default CustomerHome;
