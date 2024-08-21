import React, { useState, useEffect } from 'react';

const VendorProfile = () => {
  const [vendor, setVendor] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loginId = JSON.parse(localStorage.getItem("loggedIn")).login_id;
    console.log("Login ID:", loginId); // Debugging line

    // Fetch vendor data based on login ID
    fetch(`http://localhost:8080/getVendor?login_id=${loginId}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log("Vendor Data:", data); // Debugging line
        setVendor(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching vendor profile:', error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <div className="container mt-4">
        <h1>Loading...</h1>
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );
  }

  if (!vendor) {
    return <div>No vendor found!</div>; // Handle case when vendor not found
  }

  return (
    <div className="container mt-4">
      <h1>Vendor Profile</h1>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{vendor.firstname} {vendor.lastname}</h5>
          <p className="card-text"><strong>Email:</strong> {vendor.email}</p>
          <p className="card-text"><strong>Phone:</strong> {vendor.phone}</p>
          <p className="card-text"><strong>Address:</strong> {vendor.address}</p>
          <p className="card-text"><strong>Aadhar Number:</strong> {vendor.aadharnumber}</p>
          <p className="card-text"><strong>Skillset:</strong> {vendor.skillset}</p>
          {/* Add more vendor details as needed */}
        </div>
      </div>
    </div>
  );
};

export default VendorProfile;
