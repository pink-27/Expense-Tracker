import {React,useState,useEffect} from 'react';
import { Box, Heading, Grid, Text, Button, InputGroup, Input, Card,CardFooter,CardHeader, IconButton,Modal, ModalOverlay, ModalContent, ModalHeader, ModalBody, ModalFooter, FormControl, FormLabel } from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom';
import { SmallAddIcon,EditIcon,DeleteIcon } from '@chakra-ui/icons';

function ExpenseList({ expenses, onEdit, onDelete }) {
  const [selectedExpense, setSelectedExpense] = useState(null);

  const openEditModal = (expense) => {
    setSelectedExpense(expense);
  };

  const handleCloseModal = () => {
    setSelectedExpense(null);
  };
  return (
    <>
    <Grid gap={4} mb={4} templateColumns='repeat(3, 1fr)'>
      {expenses.map((expense) => (
        <Card key={expense.billId} bg="papayawhip" >
        <CardHeader>
          <Heading as="h3" size="sm">
            {expense.billName}
          </Heading>
          <Text fontSize="sm">
          &#8377; {expense.amount}
          </Text>
        </CardHeader>
        <CardFooter>
          <Box display="flex" flexDirection='row' alignItems='center' w="100%" gap={2}>
            <IconButton
              icon={<EditIcon />}
              colorScheme="teal"
              size="sm"
              onClick={() => openEditModal(expense)}
              alignSelf='flex-start'
              w="50%"
            />
            <IconButton
              icon={<DeleteIcon />}
              colorScheme="red"
              size="sm"
              variant="outline"
              onClick={() => onDelete(expense.billId)}
              alignSelf='flex-end'
              w="50%"
            />
          </Box>
        </CardFooter>
      </Card>
      ))}
    </Grid>
      {selectedExpense && (
            <EditExpenseForm
              initialExpense={selectedExpense}
              isOpen={selectedExpense !== null}
              onClose={handleCloseModal}
              onSubmit={(updatedExpense) => {
                onEdit(updatedExpense);
              }}
            />
      )}
      </>
  );
}
function EditExpenseForm({ initialExpense, isOpen, onClose, onSubmit }) {
  const [name, setName] = useState(initialExpense.billName);
  const [amount, setAmount] = useState(initialExpense.amount);

  const handleNameChange = (event) => setName(event.target.value);
  const handleAmountChange = (event) => setAmount(event.target.value);

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit({ billName: name, amount: amount, billId: initialExpense.billId }); // Add billId for update reference
    
    onClose();
  };

  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Edit Expense</ModalHeader>
        <ModalBody>
          <FormControl>
            <FormLabel htmlFor="name">Name</FormLabel>
            <Input
              id="name"
              type="text"
              value={name}
              onChange={handleNameChange}
            />
          </FormControl>
          <FormControl mt={4}>
            <FormLabel htmlFor="amount">Amount</FormLabel>
            <InputGroup>
              <Input
                id="amount"
                type="number"
                value={amount}
                onChange={handleAmountChange}
              />
            </InputGroup>
          </FormControl>
        </ModalBody>
        <ModalFooter>
          <Button colorScheme="gray" mr={3} onClick={onClose}>
            Cancel
          </Button>
          <Button colorScheme="teal" type="submit" onClick={handleSubmit}>
            Save
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
}

function AddExpenseForm({ onSubmit }) {
  const [name, setName] = useState("");
  const [amount, setAmount] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(e);
    setName("");
    setAmount(0);
  };

  return (
    <Box as="form" flexDirection='column' alignItems="center" color="teal.700" onSubmit={handleSubmit} p="2">
      <InputGroup>
        <Input placeholder="Expense Name" borderColor="black" m="2" value={name} onChange={(e) => setName(e.target.value)} />
      </InputGroup>
      <InputGroup>
        &#8377;<Input type="number" placeholder="Amount in &#8377;" borderColor="gray" m="2" value={amount} onChange={(e) => setAmount(e.target.value)} />
      </InputGroup>
      <Button leftIcon={<SmallAddIcon/>} type="submit" colorScheme="teal" m="2" size="lg">Add Expense</Button>
    </Box>
  );
}

function ExpenseTracker({user,jwt}) {
  const [expenses, setExpenses] = useState([]); // Array to store expenses
  const navigate=useNavigate();
  const [totalExpense, setTotal]=useState(0);
  // console.log(user)
  useEffect(()=>{
    if(user===null || user===undefined){
      navigate("/login");
    }
    const fetchExpenses=async()=>{
      await fetch(`http://localhost:8081/personalBills/getBillByUser/${user}`,{
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${jwt}` 
      }
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok.');
      }
      return response.json();
    })
    .then((data) => {
      console.log(data); // Log the response data here
      setExpenses(data);
    })
    .catch((err) => {
      console.error('Error:', err);
    });
    await fetch(`http://localhost:8081/personalBills/getTotalExpense/${user}`,{
      method: 'GET',
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok.');
      }
      return response.json();
    })
    .then((data)=>{
      // console.log(data);
      setTotal(data);
    })
    }
    fetchExpenses();
  },[user,navigate,jwt]);
  const handleAddExpense = async (newExpense) => {
    // Update state with new expense
    let ret=-1
    const expenseData={
      "billName": newExpense.target[0].value,
      "amount": newExpense.target[1].value,
      "email": user
    }
    // console.log(JSON.stringify(expenseData))
    await fetch('http://localhost:8081/personalBills/addBills',{
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: ` ${jwt}` 
      },
      body: JSON.stringify(expenseData)
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok.');
      }
      return response.json();
    })
    .then((data) => {
      console.log(data); // Log the response data here
      expenseData["billId"]=data;
      ret = 0;
    })
    .catch((err) => {
      ret = -1;
      console.error('Error:', err);
    });


    if(ret===0) setExpenses([...expenses, {"billName": expenseData.billName, "amount": expenseData.amount, "billId": expenseData.billId}]);
  };

  const handleEditExpense = async (updatedExpense) => {
    // Update specific expense in state
    console.log(updatedExpense)
    await fetch(`http://localhost:8081/personalBills/updateBillById/${updatedExpense.billId}`,{
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${jwt}` 
      },
      body: JSON.stringify(updatedExpense)
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok.');
      }
      return response;
    })
    .then((data) => {
      // console.log(data); // Log the response data here
      const updatedExpenses = expenses.map(expense => expense.billId === updatedExpense.billId ? updatedExpense : expense);
    setExpenses(updatedExpenses);
    })
    .catch((err) => {
      console.error('Error:', err);
      alert("Couldn't update!")
    });
  };

  const handleDeleteExpense = async(event) => {
    // Update state by filtering out deleted expense
    console.log(event,jwt)
    await fetch(`http://localhost:8081/personalBills/deleteByBillId/${event}`,{
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${jwt}` 
      }
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok.');
      }
      return response;
    })
    .then((data) => {
      // console.log(data); // Log the response data here
      setExpenses(expenses.filter(expense => expense.billId !== event));
    })
    .catch((err) => {
      console.error('Error:', err);
      alert("Couldn't delete!")
    });
  };

  return (
    <Box display="flex" flexDirection="row" flexGrow={1} mt="3" ms="10">
      {/* Expense list on the left */}
      <Box p={4} backgroundColor="papayawhite" boxShadow='lg' m='2' rounded="md" w="65%">
        <Box display="flex" justifyContent="space-between">
        <Box alignSelf="flex-start">
          <Heading as="h2">Your Expenses</Heading>
        </Box>
        <Text>Total: &#8377;{totalExpense}</Text>
      </Box>
        <ExpenseList expenses={expenses} onEdit={handleEditExpense} onDelete={handleDeleteExpense} />
      </Box>
      {/* Add expense form on the right */}
      <Box flexDirection='column' alignItems="center" p={4} color="teal.700" bg="teal.100" rounded="md" boxShadow='lg' m='2' w="25%" maxH='320'>
        <Heading as="h4" m={3}>Add Expense</Heading>
        <AddExpenseForm onSubmit={handleAddExpense} />
      </Box>
    </Box>
  );
}


export default ExpenseTracker;
