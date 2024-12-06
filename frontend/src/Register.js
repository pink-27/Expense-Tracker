import React, { useState } from 'react';
import { Box, Input, InputGroup, Button, Heading, Center } from '@chakra-ui/react';
import { useDispatch } from "react-redux";
import { useNavigate } from 'react-router-dom';
import { login } from './store';
function Register() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [contactNumber, setContactNumber] = useState('');
  const navigate=useNavigate();
  const dispatch = useDispatch();

  const handleRegister = (e) => {
    e.preventDefault();
    const userData = {
      name,
      email,
      password,
      contactNumber,
    };

    // Send the data to the backend
    sendUserData(userData);
  };

  const sendUserData = async (userData) => {
    console.log(JSON.stringify(userData))
    let jwt,ret;
    await fetch('http://localhost:8081/auth/register', {
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
        setName('');
        setEmail('');
        setContactNumber('');
        setPassword('');
        navigate("/");

        console.log('User logged in');
      }

  };

  return (
  <Center
    boxShadow="dark-lg"
    p="6"
    rounded="md"
    bg="teal.100"
    alignSelf="center"
    mt="4"
    flexDirection="column"
  >
    <Heading as="h3">Register</Heading>
    <Box
      display="flex"
      flexDirection="column"
      as="form"
      onSubmit={handleRegister}
      alignSelf="center"
      mt="5"
    >
      <InputGroup mb={4}>
        <Input
          border="solid"
          borderColor="black"
          placeholder="Name"
          type="text"
          id="name"
          name="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </InputGroup>
      <InputGroup mb={4}>
        <Input
          border="solid"
          borderColor="black"
          placeholder="Email"
          type="email"
          id="email"
          name="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </InputGroup>
      <InputGroup mb={4}>
        <Input
          border="solid"
          borderColor="black"
          placeholder="Password"
          type="password"
          id="password"
          name="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </InputGroup>
      <InputGroup mb={4}>
        <Input
          border="solid"
          borderColor="black"
          placeholder="Contact Number"
          type="text"
          id="contactNumber"
          name="contactNumber"
          value={contactNumber}
          onChange={(e) => setContactNumber(e.target.value)}
        />
      </InputGroup>
      <Button type="submit" colorScheme="teal" color="black">
        Register
      </Button>
    </Box>
</Center>

  );
}

export default Register;
