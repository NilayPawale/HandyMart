import React, { useState, useEffect } from 'react';
import LogoutForm from './LogoutForm';

const AdminHomePage = () => {
  const [vendors, setVendors] = useState([]);

  useEffect(() => {
    const fetchVendors = async () => {
      try {
        const response = await fetch('http://localhost:8080/getallVendors');
        const data = await response.json();
        setVendors(data);
      } catch (error) {
        console.error('Error fetching vendors', error);
      }
    };

    fetchVendors();
  }, []);

  const handleApprove = async (vendor_id) => {
    try {
      const response = await fetch(`http://localhost:8080/${vendor_id}/approve`, { method: 'GET' });
      if (response.ok) {
        console.log('Login updated successfully!');
        // Update the vendors state
        setVendors((prevVendors) =>
          prevVendors.map((vendor) =>
            vendor.login_id.login_id === vendor_id
              ? { ...vendor, login_id: { ...vendor.login_id, status_approve: true } }
              : vendor
          )
        );
      } else {
        console.error('Error updating login:', response.statusText);
      }
    } catch (error) {
      console.error('Error updating login:', error);
    }
  };

  const handleReject = async (vendor_id) => {
    try {
      const response = await fetch(`http://localhost:8080/${vendor_id}/reject`, { method: 'GET' });
      if (response.ok) {
        console.log('Vendor rejected successfully!');
        // Update the vendors state
        setVendors((prevVendors) =>
          prevVendors.map((vendor) =>
            vendor.login_id.login_id === vendor_id
              ? { ...vendor, login_id: { ...vendor.login_id, status_approve: false } }
              : vendor
          )
        );
      } else {
        console.error('Error rejecting vendor:', response.statusText);
      }
    } catch (error) {
      console.error('Error rejecting vendor:', error);
    }
  };

  return (
    <div>
      {/* Navbar */}
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">Admin Dashboard</a>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul className="navbar-nav">
             
              <li className="nav-item">
                <LogoutForm />
              </li>
            </ul>
          </div>
        </div>
      </nav>

      {/* Vendor List */}
      <div className="container mt-4">
        <h1>Vendor List</h1>
        <table className="table">
          <thead>
            <tr>
              <th scope="col">Firstname</th>
              <th scope="col">Lastname</th>
              <th scope="col">Email</th>
              <th scope="col">Aadhar Number</th>
              <th scope="col">Skillset</th>
              <th scope="col">Status</th>
              <th scope="col">Actions</th>
            </tr>
          </thead>
          <tbody>
            {vendors.map((vendor) => (
              <tr key={vendor.vendor_id}>
                <td>{vendor.firstname}</td>
                <td>{vendor.lastname}</td>
                <td>{vendor.email}</td>
                <td>{vendor.aadharnumber}</td>
                <td>{vendor.skillset}</td>
                <td>{vendor.login_id.status_approve ? 'Approved' : 'Rejected'}</td>
                <td>
                  <button className="btn btn-success" onClick={() => handleApprove(vendor.login_id.login_id)}>Accept</button>
                  <button className="btn btn-danger ms-2" onClick={() => handleReject(vendor.login_id.login_id)}>Reject</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AdminHomePage;
