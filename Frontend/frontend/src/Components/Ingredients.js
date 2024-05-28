import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container, Paper,Button } from '@mui/material';

export default function Ingredients() {
  const paperStyle={padding:'50px 20px',width:600,margin:"20px 400px"}
  const [id,setId]= React.useState('')
  const [name,setName]= React.useState('')
  const [expirationDate,setExpirationDate]= React.useState('')
  const [quantity,setQuantity]= React.useState('')


  const handleClick=(e)=> {
    e.preventDefault()
    const ingredient={id,name,expirationDate,quantity}
    console.log(ingredient)
    fetch("http://localhost:6969/ingredients",{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(ingredient)
  }
    ).then(()=>{console.log("New ingredient added")})
  }
  return (
    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <Container>
         <Paper elevation={3} style={paperStyle}>
         <TextField id="outlined-basic" label="Id" variant="outlined" 
         value = {id}
         onChange={(e)=>setId(e.target.value)}/>
         <TextField id="outlined-basic" label="Name" variant="outlined" 
         value = {name}
         onChange={(e)=>setName(e.target.value)}/>
         <TextField id="outlined-basic" label="Expiration date" variant="outlined"  
         value = {expirationDate}
         onChange={(e)=>setExpirationDate(e.target.value)}/>
         <TextField id="outlined-basic" label="Quantity" variant="outlined" 
         value = {quantity}
         onChange={(e)=>setQuantity(e.target.value)}/>
         <Button variant="contained" color="success" onClick={handleClick}>
        Add ingredient
        </Button>
         </Paper>
      </Container>
    </Box>
  );
}
