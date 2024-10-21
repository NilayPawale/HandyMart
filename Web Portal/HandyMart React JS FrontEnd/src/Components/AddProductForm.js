import React, { useState, useEffect } from 'react';
import { Alert } from 'react-bootstrap';

const AddProductForm = () => {
  const [productData, setProductData] = useState({
    productname: '',
    description: '',
    price: '',
    inventory: '',
    subproductname: '',
    productcategory_id: '',
    login_id: JSON.parse(localStorage.getItem("loggedIn")).login_id
  });

  const [productCategories, setProductCategories] = useState([]);
  const [products, setProducts] = useState([]); // State to hold the list of products

  // Fetch product categories and products when the component mounts
  useEffect(() => {
    const fetchProductCategories = async () => {
      try {
        const response = await fetch('http://localhost:8080/getallproductcategories');
        const data = await response.json();
        setProductCategories(data);
      } catch (error) {
        console.error('Error fetching product categories', error);
      }
    };

    const fetchProducts = async () => {
      try {
        const response = await fetch('http://localhost:8080/getallproducts'); // Adjust endpoint as needed
        const data = await response.json();
        setProducts(data);
      } catch (error) {
        console.error('Error fetching products', error);
      }
    };

    fetchProductCategories();
    fetchProducts();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProductData({ ...productData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/addproduct', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(productData),
      });

      if (response.ok) {
        const newProduct = await response.json(); // Assuming the backend returns the newly created product
        setProducts((prevProducts) => [...prevProducts, newProduct]); // Update local products state
        alert('Product added successfully!');
        // Optionally reset the form
        setProductData({
          productname: '',
          description: '',
          price: '',
          inventory: '',
          subproductname: '',
          productcategory_id: '',
          login_id: JSON.parse(localStorage.getItem("loggedIn")).login_id
        });
      } else {
        console.error('Failed to add product');
      }
    } catch (error) {
      console.error('Error adding product:', error);
    }
  };

  return (
    <div className="container mt-3">
      <h2>Add Product</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="productname" className="form-label">
            Product Name
          </label>
          <input
            type="text"
            className="form-control"
            id="productname"
            name="productname"
            value={productData.productname}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="description" className="form-label">
            Description
          </label>
          <textarea
            className="form-control"
            id="description"
            name="description"
            value={productData.description}
            onChange={handleChange}
            required
          ></textarea>
        </div>
        <div className="mb-3">
          <label htmlFor="price" className="form-label">
            Price
          </label>
          <input
            type="number"
            className="form-control"
            id="price"
            name="price"
            value={productData.price}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="inventory" className="form-label">
            Inventory
          </label>
          <input
            type="number"
            className="form-control"
            id="inventory"
            name="inventory"
            value={productData.inventory}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="subproductname" className="form-label">
            Subproduct Name
          </label>
          <input
            type="text"
            className="form-control"
            id="subproductname"
            name="subproductname"
            value={productData.subproductname}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="productcategory_id" className="form-label">
            Product Category
          </label>
          <select
            className="form-control"
            id="productcategory_id"
            name="productcategory_id"
            value={productData.productcategory_id}
            onChange={handleChange}
            required
          >
            <option value="" disabled>Select product category</option>
            {productCategories.map(category => (
              <option key={category.category_id} value={category.category_id}>
                {category.category_name}
              </option>
            ))}
          </select>
        </div>

        <button type="submit" className="btn btn-primary">
          Add Product
        </button>
      </form>
      <hr />
     
    </div>
  );
};

export default AddProductForm;
