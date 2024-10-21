// src/store/index.js

import { configureStore } from '@reduxjs/toolkit';
import userReducer from '../Reducer/customerSlice1'; // Adjust the import path as needed

export const store = configureStore({
  reducer: {
    user: userReducer, // This should be 'user' to match the slice name
  },
});
