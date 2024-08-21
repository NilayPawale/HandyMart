// src/slices/customerSlice.js

import { createSlice } from '@reduxjs/toolkit';

// Create the slice
const customerSlice = createSlice({
  name: 'customer',
  initialState: {
    customerId: null, // Default state for customerId
  },
  reducers: {
    setCustomerId: (state, action) => {
      state.customerId = action.payload; // Set the customerId from action payload
    },
  },
});

// Export actions
export const { setCustomerId } = customerSlice.actions;

// Export selector to get customerId from the state
export const selectCustomerId = (state) => state.customer.customerId;

// Export reducer
export default customerSlice.reducer;
