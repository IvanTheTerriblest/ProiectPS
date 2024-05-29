import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container, Paper, Button, Grid, Typography, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';

export default function Ingredients() {
  const paperStyle = { padding: '50px 20px', width: 600, margin: "20px auto" };
  const [id, setId] = React.useState('');
  const [name, setName] = React.useState('');
  const [expirationDate, setExpirationDate] = React.useState('');
  const [quantity, setQuantity] = React.useState('');
  const [ing, setIng] = React.useState([]);
  const [showExpired, setShowExpired] = React.useState(false);
  const [searchName, setSearchName] = React.useState('');
  const [open, setOpen] = React.useState(false);
  const [currentIngredient, setCurrentIngredient] = React.useState(null);

  const handleClick = (e) => {
    e.preventDefault();
    const ingredient = { id, name, expirationDate, quantity };
    console.log(ingredient);
    fetch("http://localhost:6969/ingredients", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(ingredient)
    }).then(() => {
      console.log("New ingredient added");
      fetchIngredients();
    });
  };

  const handleDelete = (ingredientId) => {
    fetch(`http://localhost:6969/ingredients/${ingredientId}`, {
      method: "DELETE"
    }).then(() => {
      console.log(`Ingredient with id ${ingredientId} deleted`);
      fetchIngredients();
    }).catch(error => {
      console.error('Error deleting ingredient:', error);
    });
  };

  const fetchIngredients = () => {
    fetch("http://localhost:6969/ingredients")
      .then(res => res.json())
      .then((result) => {
        setIng(result);
        setShowExpired(false);
      }).catch(error => {
        console.error('Error fetching ingredients:', error);
      });
  };

  const fetchExpiredIngredients = () => {
    fetch("http://localhost:6969/ingredients/expired")
      .then(res => res.json())
      .then((result) => {
        setIng(result);
        setShowExpired(true);
      }).catch(error => {
        console.error('Error fetching expired ingredients:', error);
      });
  };

  const fetchIngredientsByName = (name) => {
    fetch(`http://localhost:6969/ingredients/nume/${name}`)
      .then(res => res.json())
      .then((result) => {
        setIng([result]);
        setShowExpired(false);
      }).catch(error => {
        console.error('Error fetching ingredient by name:', error);
      });
  };

  const handleUpdate = (ingredient) => {
    setCurrentIngredient(ingredient);
    setId(ingredient.id);
    setName(ingredient.name);
    setExpirationDate(ingredient.expirationDate);
    setQuantity(ingredient.quantity);
    setOpen(true);
  };

  const handleUpdateSubmit = () => {
    const updatedIngredient = { id, name, expirationDate, quantity };
    fetch(`http://localhost:6969/ingredients/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updatedIngredient)
    }).then(() => {
      console.log(`Ingredient with id ${id} updated`);
      fetchIngredients();
      setOpen(false);
    }).catch(error => {
      console.error('Error updating ingredient:', error);
    });
  };

  React.useEffect(() => {
    fetchIngredients();
  }, []);

  const handleExpiredClick = () => {
    fetchExpiredIngredients();
  };

  const handleShowAllClick = () => {
    fetchIngredients();
  };

  const handleSearchClick = () => {
    if (searchName.trim() !== '') {
      fetchIngredientsByName(searchName);
    } else {
      fetchIngredients();
    }
  };

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
      <Paper elevation={3} style={{ ...paperStyle, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
  <TextField 
    id="outlined-basic" 
    label="Id" 
    variant="outlined"
    value={id}
    onChange={(e) => setId(e.target.value)}
    style={{ marginBottom: '10px' }}
  />
  <TextField 
    id="outlined-basic" 
    label="Name" 
    variant="outlined"
    value={name}
    onChange={(e) => setName(e.target.value)}
    style={{ marginBottom: '10px' }}
  />
  <TextField 
    id="outlined-basic" 
    label="Expiration date" 
    variant="outlined"
    value={expirationDate}
    onChange={(e) => setExpirationDate(e.target.value)}
    style={{ marginBottom: '10px' }}
  />
  <TextField 
    id="outlined-basic" 
    label="Quantity" 
    variant="outlined"
    value={quantity}
    onChange={(e) => setQuantity(e.target.value)}
    style={{ marginBottom: '10px' }}
  />
  <Button variant="contained" color="success" style={{ marginBottom: '10px' }} onClick={handleClick}>
    Add ingredient
  </Button>
  <div style={{ display: 'flex', justifyContent: 'space-between', width: '100%' }}>
    <Button variant="contained" color="primary" onClick={handleExpiredClick}>
      Show Expired Ingredients
    </Button>
    <Button variant="contained" onClick={handleShowAllClick}>
      Show All Ingredients
    </Button>
  </div>
</Paper>



        <Paper elevation={3} style={paperStyle}>
          <TextField 
            id="search-name" 
            label="Search by Name" 
            variant="outlined"
            value={searchName}
            onChange={(e) => setSearchName(e.target.value)}
            style={{ marginBottom: '20px' }}
          />
          <Button variant="contained" color="secondary" onClick={handleSearchClick}>
            Search
          </Button>
        </Paper>

        <Paper elevation={3} style={paperStyle}>
          <Typography variant="h6" gutterBottom>
            {showExpired ? "Expired Ingredients List" : "Ingredients List"}
          </Typography>
          <Grid container spacing={2}>
            {ing.map(ingredient => (
              <Grid item xs={12} key={ingredient.id}>
                <Paper elevation={6} style={{ padding: '15px', textAlign: 'left' }}>
                  <Typography variant="body1"><strong>Id:</strong> {ingredient.id}</Typography>
                  <Typography variant="body1"><strong>Name:</strong> {ingredient.name}</Typography>
                  <Typography variant="body1"><strong>Expiration Date:</strong> {ingredient.expirationDate}</Typography>
                  <Typography variant="body1"><strong>Quantity:</strong> {ingredient.quantity}</Typography>
                  <Button variant="contained" color="error" onClick={() => handleDelete(ingredient.id)}>
                    Delete
                  </Button>
                  <Button variant="contained" color="primary" onClick={() => handleUpdate(ingredient)} style={{ marginLeft: '10px' }}>
                    Update
                  </Button>
                </Paper>
              </Grid>
            ))}
          </Grid>
        </Paper>
      </Container>

      <Dialog open={open} onClose={() => setOpen(false)}>
        <DialogTitle>Update Ingredient</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Update the details of the ingredient.
          </DialogContentText>
          <TextField 
            margin="dense"
            label="Name"
            fullWidth
            variant="outlined"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <TextField 
            margin="dense"
            label="Expiration Date"
            fullWidth
            variant="outlined"
            value={expirationDate}
            onChange={(e) => setExpirationDate(e.target.value)}
          />
          <TextField 
            margin="dense"
            label="Quantity"
            fullWidth
            variant="outlined"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpen(false)} color="primary">
            Cancel
          </Button>
          <Button onClick={handleUpdateSubmit} color="primary">
            Update
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
}
