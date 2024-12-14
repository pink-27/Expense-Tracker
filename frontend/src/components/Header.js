import {React,useRef} from 'react';
import { Heading,Box, Text, Image, Drawer, DrawerBody, DrawerHeader, DrawerOverlay, DrawerContent, DrawerCloseButton, useDisclosure, Button, Spacer, VStack } from '@chakra-ui/react';
import { HamburgerIcon} from '@chakra-ui/icons';
import { useDispatch } from 'react-redux';
import logo from "../logo.png"
import bg from "../drawerbg.jpg"
import { Link } from 'react-router-dom';
import { logout } from '../store';
function Header({user}) {
  const { isOpen, onOpen, onClose } = useDisclosure()
  const btnRef = useRef()
  const dispatch=useDispatch();
  const handleLogout=()=>{
    dispatch(logout());
  }
  console.log(user)
  return (
    <>

    <Drawer
    isOpen={isOpen}
    placement='left'
    onClose={onClose}
    finalFocusRef={btnRef}
    colorScheme='teal'
    backdropFilter='blur(5px) brightness(0.8)'
    >
      <DrawerOverlay color='teal'/>
      <DrawerContent color='white' backgroundImage={bg} backgroundSize='cover'>
        <DrawerCloseButton />
        <DrawerHeader>ExpenseTracker</DrawerHeader>

        <DrawerBody>
          <VStack flexDir='column' alignItems='flex-start'>
            <Link onClick={onClose} to="/">Home</Link>
            <Link onClick={onClose} to="/trackExpense">Track Expensesss</Link>
            {/* <Link onClick={onClose} to="/">Groups</Link>
            <Link onClick={onClose} to="">Split Bills</Link> */}
            {/* <Link onClick={onClose} to="/">Contact</Link> */}
          </VStack>
        </DrawerBody>
      </DrawerContent>
    </Drawer>
    <Box display='flex' flexDirection='row' alignItems='center' h='100%'>
      {user!==null && user!==undefined ? 
    <Button ref={btnRef} m='2' p='1' color='teal.800' backgroundColor='white' onClick={onOpen}> <HamburgerIcon /></Button>:""}
    <Link to="/"><Image boxSize='50px' objectFit='cover' borderRadius='full' color='gray' src={logo}></Image></Link>
      <Heading as="h1" p='2' fontSize="4xl">ExpenseTracker</Heading>
      <Spacer/>
      {
        user===null || user===undefined?<>
        <Button as={Link} to="/register" m='2' >Sign-Up</Button>
        <Button as={Link} to="/login" m='2' >Login</Button>
        </>:<>
        <Text fontWeight='bold' color="white">{user!==null?user.split("@")[0]:""}</Text>
        <Button m='2' onClick={handleLogout}>Logout</Button>
        </>
      }

    </Box>
    </>
  );
}

export default Header;
