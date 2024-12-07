import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Box , Heading, Text, Button, List, ListItem, Icon, HStack,Stack, Divider,Center,Grid, Spacer, GridItem } from "@chakra-ui/react";
import { CheckIcon, CalendarIcon } from '@chakra-ui/icons';
import Quote from './Quote';

function Home({user}) {
  const navigate=useNavigate();
  useEffect(()=>{
    if(user===null){
      navigate("/login");
    }
  },[user,navigate]);
  return (
<Grid templateColumns="repeat(2, 50%)" m={5} gap={4} >
      {/* Hero Section */}
      <GridItem 
        bgSize="cover"
        bgPosition="center"
				bgColor="green.700"
        p={2} // Reduced padding for better balance
				h="auto"
				boxShadow="dark-lg" rounded='md'
				colSpan={1}
				>
        <Stack spacing={6} align="center" mt={4} p={2}>
          <Heading
            as="h1"
            fontSize="4xl"
            color="teal.300"
            textAlign="center" // Center text for better focus
						>
            Tracker got Mini!
          </Heading>
          <Text fontSize="lg" color="teal.300" textAlign="center">
            Track group expenses easily, share bills, and settle up in seconds.
          </Text>
					<Spacer/>
          <Button
            colorScheme="pink"
            size="lg"
            // variant="outline"
            borderRadius="full" // Rounded button for better flow
						>
            Get Started Free
          </Button>
        </Stack>
      </GridItem>

      {/* Features Section */}
      <Box p={4} boxShadow="dark-lg" rounded='md' bgColor="green.500" me={3}>
        <Heading as="h2" fontSize="3xl" textAlign="center" p={2}>
          Say goodbye to spreadsheet struggles!
        </Heading>
				<Center>

        <HStack mt={4}>
          <List spacing={6}>
            <ListItem>
              <Icon as={CheckIcon} mr={2} />
              Effortlessly track shared expenses.
            </ListItem>
            <ListItem>
              <Icon mr={2} />
              Automatically split bills evenly or by custom amounts.
            </ListItem>
            <ListItem>
              <Icon as={CalendarIcon} mr={2} />
              Set due dates and get reminders to settle up.
            </ListItem>
          </List>
        </HStack>
				</Center>
      </Box>

      {/* Benefits Section */}
      <Box p={6} boxShadow="dark-lg" rounded='md' bgColor="green.100" mt={2} h="auto" >
        <Heading as="h2" fontSize="3xl" textAlign="center">
          Live life simpler, splitwise!
        </Heading>
        <Text fontSize="lg" textAlign="center">
          This tracker takes the hassle out of your personal finances:
        </Text>
				<Center>

        <List // Remove unnecessary indentation
          spacing={6}
          mt={4} mb={3}>
          <ListItem>- No more messy spreadsheets or forgotten IOUs.</ListItem>
          <ListItem>- Pay instantly with secure in-app transactions.</ListItem>
          <ListItem>- Stay organized with detailed expense history and reports.</ListItem>
        </List>
				</Center>
      </Box>

      {/* Testimonials Section */}
      <Box p={4} boxShadow="dark-lg" rounded='md' bgColor="green.50" mt={2} display="flex" flexDirection="column" me={3}>
        <Heading as="h2" fontSize="3xl" textAlign="center" >
          Don't just take our word for it!
        </Heading>
        <HStack spacing={6} mt={4}>
          <Quote boxSize="sm" p={4}>
            "ExpenseTracker is a lifesaver for our travel plans!" - John Doe
          </Quote>
          <Quote boxSize="sm" p={4}>
            "Finally, a way to split bills fairly and without drama." - Jane Smith
          </Quote>
        </HStack>

      </Box>
      <Divider mt={8} />
    </Grid>
  );
}

export default Home;