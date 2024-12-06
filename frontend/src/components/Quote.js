import React from "react";
import { Box } from "@chakra-ui/react";
const Quote = ({ children, boxSize = "lg", p = 4 }) => (
  <Box
    rounded="md"
    shadow="md"
    bg="gray.100"
    p={p}
    fontSize="lg"
    boxSize={boxSize}
		h="100"
  >
    {children}
  </Box>
);

export default Quote;
