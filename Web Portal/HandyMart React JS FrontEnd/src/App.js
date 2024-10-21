import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './Components/LoginForm';
import VendorRegistration from './Components/Vendor';
import CustomerRegistration from './Components/Customer';
import VendorHome from './Components/VendorHome';
import CustomerHome from './Components/CustomerHome';
import AdminHome from './Components/AdminHome';
import CustomerReg from './Components/CustomerReg';
import AdminDashboard from './Components/AdminHome';
import WebHomePage from './Components/WebHome';
import VendorProductList from './Components/VendorProductList';
import AddProductForm from './Components/AddProductForm';
import VendorProfile from './Components/VendorProfile';
import Cart from './Components/Cart';

import PaymentPage1 from './Components/MakePayment';
import PaymentSuccessPage from './Components/PaymentSuccsess';
import PaymentPage from './Components/Payment';

import TrackOrderPage from './Components/TrackOrderPage';
import Feedback from './Components/Feedback';
function App() {
  return (
    <div>
    
      <Routes>
        <Route path='/' element={<WebHomePage/>}/>
        <Route path="/login" element={<Login />} />
        <Route path="/vendor-registration" element={<VendorRegistration />} />
        <Route path='/coustomer-registration' element={<CustomerRegistration/>}/>
        <Route path='/vendor-home' element={<VendorHome/>} />
        {/* <Route path='/customer-home' element={<CustomerHome/>} />
        <Route path='/admin-home' element={<AdminDashboard/>} />
         />  */}
        <Route path='/customer-reg' element={<CustomerReg/>} />
        <Route path='/product-list' element={<VendorProductList/>} />
        <Route path='/add-product' element={<AddProductForm/>} />
        <Route path="/admin-home/:id" element={<AdminHome />} />
        <Route path="/customer-home/:id" element={<CustomerHome />} />
        <Route path="/vendor-home/:id" element={<VendorHome />} />
        <Route path="/cart/:id" element={<Cart />} />
        <Route path='/vendor-profile' element={<VendorProfile />}/>
        <Route path="/payment" element={<PaymentPage1 />} />
        <Route path="/payment-success" element={<PaymentSuccessPage />} />
        <Route path="/paymentRecipt" element={<PaymentPage />} />
        <Route path="/trackOrder" element={<TrackOrderPage />} />
        <Route path="/feedback" element={<Feedback />} />

      </Routes>
    
      
    </div>
  );
}

export default App;
