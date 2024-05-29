import React, { useState, useEffect } from 'react';
import {
  Paper,
  Typography,
  Button,
  Grid,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from '@mui/material';

export default function IngredientList({ ingredients, fetchIngredients }) {
  const [open, setOpen] = useState(false);
  const [name, setName] = useState('');
  const [expirationDate, setExpirationDate] = useState('');
  const [quantity, setQuantity] = useState('');
  const [currentIngredient, setCurrentIngredient] = useState(null);

  useEffect(() => {
    fetchIngredients();
  }, [fetchIngredients]);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    setCurrentIngredient(null);
    setName('');
    setExpirationDate('');
    setQuantity('');
  };

  const handleAddIngredient = () => {
    const ingredient = { name, expirationDate, quantity };
    fetch("http://localhost:6969/ingredients", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(ingredient)
    }).then(() => {
      console.log("New ingredient added");
      fetchIngredients();
      handleClose();
    }).catch(error => {
      console.error('Error adding ingredient:', error);
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

  const handleUpdate = (ingredient) => {
    setCurrentIngredient(ingredient);
    setName(ingredient.name);
    setExpirationDate(ingredient.expirationDate);
    setQuantity(ingredient.quantity);
    setOpen(true);
  };

  const handleUpdateSubmit = () => {
    const updatedIngredient = { name, expirationDate, quantity };
    fetch(`http://localhost:6969/ingredients/${currentIngredient.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updatedIngredient)
    }).then(() => {
      console.log(`Ingredient with id ${currentIngredient.id} updated`);
      fetchIngredients();
      handleClose();
    }).catch(error => {
      console.error('Error updating ingredient:', error);
    });
  };

  return (
    <>
      <Paper elevation={3}>
        <Grid container spacing={2} style={{ padding: '15px' }}>
          {ingredients.map(ingredient => (
            <Grid item xs={12} key={ingredient.id}>
              <Paper elevation={3} style={{ padding: '15px', textAlign: 'left' }}>
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

      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>{currentIngredient ? 'Update Ingredient' : 'Add Ingredient'}</DialogTitle>
        <DialogContent>
          <DialogContentText>
            {currentIngredient ? 'Update the details of the ingredient.' : 'Add details of the new ingredient.'}
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
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={currentIngredient ? handleUpdateSubmit : handleAddIngredient} color="primary">
            {currentIngredient ? 'Update' : 'Add'}
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
