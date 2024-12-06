// App.js
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import ExpenseTracker from './components/ExpenseTracker';
import Login from './components/Login';
import Register from './Register';
import Header from './components/Header';
import { Grid, GridItem,Box } from "@chakra-ui/react";
import { useSelector } from 'react-redux';
import Home from './components/Home';
function App() {
  const user=useSelector((state)=> state.user);
  const jwt=useSelector((state) => state.token);
  return (
    
      <Grid
        templateRows='10% 90%'
        color='teal.100'
        fontWeight='bold'
        h='100vh'
        w='100vw'
      >
      <GridItem pl='2' bg='green.600'>
        <Header user={user}/>
      </GridItem>
      <GridItem bg='papayawhip' color='teal.900'>
        <Box display='flex' flexDirection='column'>
          <Routes>
            <Route path="/" element={<Home user={user}/>} />
            <Route path="/trackExpense" element={<ExpenseTracker user={user} jwt={jwt}/>} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
          </Routes>
        </Box>
      </GridItem>
      </Grid>
  );
}



export default App;
