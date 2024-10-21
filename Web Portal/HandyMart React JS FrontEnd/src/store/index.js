// src/store/index.js

import { configureStore } from "@reduxjs/toolkit";
import customerReducer from "../Reducer/customerSlice"; // Adjust the import path as needed

export const stores = configureStore({
  reducer: {
    customer: customerReducer, // Match the name used in the slice
  },
});
