import React, { useState } from 'react';
import { Box, Input, InputGroup, Button, Heading } from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from "react-redux";
import { login } from '../store';
function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate=useNavigate();
    const dispatch = useDispatch();
    const handleSubmit = (e) => {
      e.preventDefault();
      const userData = {
        email,
        password
      };
      loginAPICall(userData);
      // navigate("/")
      console.log('Login submitted:', e);
    };
    const loginAPICall = async (userData) => {
      console.log(userData)
      let jwt,ret;
      await fetch('http://localhost:8081/auth/authenticate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData),
    }).then((data) => {
      // data = data.json();
      // console.log("Login data returned :", data);
      ret = data.ok;
      return data.json();
    })
    .then((data) => {
      console.log(data);
      jwt = data["token"];
    })
    .catch((err) => {
      ret = false;
      console.log(err.message);
    });

      if(ret && jwt){
        dispatch(login({"user": email,"token":jwt}));
        setEmail('');
        setPassword('');
        navigate("/");

        console.log('User logged in');
      }

    };
    return (
      <Box display='flex' flexDirection='column' alignItems='center' flexGrow='1'>
        <Box boxShadow='dark-lg' p='6' rounded='md' bg='teal.100' alignSelf='center' mt='4'>

        <Heading as='h3'> Login To Your Account</Heading>
        <Box display='flex' flexDirection='column' as="form" onSubmit={handleSubmit} alignSelf='center' mt='5' >
        <InputGroup mb={4}>
          <Input border='solid'
            borderColor='black'
            placeholder="Email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            />
        </InputGroup>
        <InputGroup mb={4}>
          <Input border='solid'
            borderColor='black'
            placeholder="Password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            />
        </InputGroup> 
        <Button type="submit" colorScheme="teal" color="black">Login</Button>
        </Box>
        </Box>
      </Box>
  );
}

export default Login;
