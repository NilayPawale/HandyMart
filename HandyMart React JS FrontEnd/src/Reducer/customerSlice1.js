// src/slices/userSlice.js

import { createSlice } from '@reduxjs/toolkit';

const userSlice = createSlice({
  name: 'user', // This should match the name used in the store
  initialState: {
    customerId: null,
    cartId: null,
  },
  reducers: {
    setCustomerId: (state, action) => {
      state.customerId = action.payload;
    },
    setCartId: (state, action) => {
      state.cartId = action.payload;
    },
  },
});

export const { setCustomerId, setCartId } = userSlice.actions;
export default userSlice.reducer;
