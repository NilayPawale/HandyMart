// import React, { useState, useEffect } from 'react';
// import { Link } from 'react-router-dom'; // Assuming you're using React Router for navigation
// import 'bootstrap/dist/css/bootstrap.min.css';

// const VendorProductList = () => {
//   const [products, setProducts] = useState([]);

//   useEffect(() => {

//     // const loginid=JSON.parse(localStorage.getItem("loggedUser")).login_id;
//     // console.log(loginid);

//     const loggedUser = JSON.parse(localStorage.getItem("loggedUser"));
//     const vendor_id = loggedUser && loggedUser.vendor_id;
    
//     fetch(`http://localhost:8080/byVendor?vendor_id=${vendor_id}`)
//       .then(response => response.json())
//       //.then(data => setProducts(data))
//       .then(data => {
//         localStorage.setItem("loggedVendor",JSON.stringify(data))
//         setProducts(data);
//       })
//       .catch(error => console.error('Error fetching products', error));
//   }, []);

//   return (
//     <div className="container mt-5">
//       <div className="d-flex justify-content-between align-items-center">
//         <h1>Vendor Product List</h1>
//         <Link to="/add-product">
//           <button className="btn btn-primary">Add Products</button>
//         </Link>
//       </div>
      
//       <div className="row mt-4">
//         {products.map(product => (
//           <div key={product.vendor_product_id} className="col-md-4 mb-4">
//             <div className="card">
//               <div className="card-body">
//                 <h5 className="card-title">{product.productname}</h5>
//                 <p className="card-text">{product.description}</p>
//                 <p className="card-text">Price: ${product.price}</p>
//                 {/* Add more details as needed */}
//               </div>
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default VendorProductList;


import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const VendorProductList = () => {
  const [products, setProducts] = useState([]);

  // useEffect(() => {
  //   const loginId = JSON.parse(localStorage.getItem("loggedUser")).login_id;

  //   fetch(`http://localhost:8080/byVendor?login_id=${loginId}`)
  //     .then(response => response.json())
  //     .then(data => {
  //       localStorage.setItem("loggedVendor", JSON.stringify(data))
  //       setProducts(data);
  //     })
  //     .catch(error => console.error('Error fetching products', error));
  // }, []);

  
  useEffect(() => {
    const loginId = JSON.parse(localStorage.getItem("loggedUser")).login_id;
    console.log("Login ID:", loginId);
  
    fetch(`http://localhost:8080/byVendor?login_id=${loginId}`)
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then(data => {
        console.log("Fetched products:", data);
  localStorage.setItem("loggedVendor", JSON.stringify(data))
  setProducts(data);
      })
      .catch(error => console.error('Error fetching products', error));
  }, []);
  

  return (
    <div className="container mt-5">
      <div className="d-flex justify-content-between align-items-center">
        <h1>Vendor Product List</h1>
        <Link to="/add-product">
          <button className="btn btn-primary">Add Products</button>
        </Link>
      </div>
      
      <div className="row mt-4">
        {products.map(product => (
          <div key={product.vendor_product_id} className="col-md-4 mb-4">
            <div className="card">
              <div className="card-body">
                <h5 className="card-title">{product.productname}</h5>
                <p className="card-text">{product.description}</p>
                <p className="card-text">Price: ${product.price}</p>
                {/* Add more details as needed */}
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default VendorProductList;



