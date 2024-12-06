import { createSlice,configureStore } from "@reduxjs/toolkit";
import jwt_decode from 'jwt-decode';
const loginSlice = createSlice({
  name: "login",
  initialState: {
		user: null,
    token: null,
  },
  reducers: {
    login: (state, action) => {
      console.log("IN DISPATCH LOGIN with action:", action);
			console.log(action.payload)
			state.user= action.payload.user;
      state.token = action.payload.token;
    },
    logout: (state) => {
			state.user=null
      state.token = null;
      localStorage.setItem("reduxState", JSON.stringify(state));
      console.log("STORING THE STATE AFTER LOGOUT:", state);
    },
    checkValidity: (state) => {
      if (state.token != null) {
        const decodedJwt = jwt_decode(state.token);
        const expiryDate = new Date(decodedJwt.exp * 1000);
        const currDate = new Date();
        if (currDate > expiryDate) {
          state.token = null;
          state.role = null;
        }
      }
    },
  },
});
const persistedState = localStorage.getItem("reduxState")
  ? JSON.parse(localStorage.getItem("reduxState"))
  : {};

console.log("IN STORE PERSISTED STATE:", persistedState);

// export const actions = loginSlice.actions;

export const store = configureStore({
  reducer: loginSlice.reducer,
  preloadedState: persistedState,
});

export const { login, logout, checkValidity } = loginSlice.actions;

export default loginSlice.reducer;