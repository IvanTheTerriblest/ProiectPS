import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container, Paper, Button, Grid, Typography, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, MenuItem, Select, InputLabel, FormControl } from '@mui/material';
import IngredientList from './IngredientsList';

export default function Recipes() {
  const paperStyle = { padding: '50px 20px', width: 600, margin: "20px auto" };
  const [id, setId] = React.useState('');
  const [name, setName] = React.useState('');
  const [ingredient, setIngredient] = React.useState('');
  const [ingredientList, setIngredientList] = React.useState([]);
  const [timeForCooking, setTimeForCooking] = React.useState('');
  const [recipes, setRecipes] = React.useState([]);
  const [ingredients, setIngredients] = React.useState([]);
  const [open, setOpen] = React.useState(false);
  const [currentRecipe, setCurrentRecipe] = React.useState(null);

  React.useEffect(() => {
    fetchRecipes();
    fetchIngredients();
  }, []);

  const fetchRecipes = () => {
    fetch("http://localhost:6969/recipes")
      .then(res => res.json())
      .then(async (result) => {
        console.log('Fetched recipes:', result);
        const recipesWithIngredients = await Promise.all(result.map(async recipe => {
          // Fetch the ingredients for the current recipe
          const ingredientsResponse = await fetch(`http://localhost:6969/recipes/${recipe.id}/ingredients`);
          const ingredientsData = await ingredientsResponse.json();
          return { ...recipe, ingredientList: ingredientsData }; // Update the recipe with ingredients
        }));
        setRecipes(recipesWithIngredients); // Update the state with recipes including ingredients
      }).catch(error => {
        console.error('Error fetching recipes:', error);
      });
  };


  const fetchIngredients = () => {
    fetch("http://localhost:6969/ingredients")
      .then(res => res.json())
      .then((result) => {
        console.log('Fetched ingredients:', result);
        setIngredients(result);
        // Update the ingredient list based on the fetched ingredients
        const updatedIngredientList = ingredientList.map(ing => {
          const foundIngredient = result.find(item => item.id === ing.id);
          return foundIngredient ? foundIngredient : ing;
        });
        setIngredientList(updatedIngredientList);
      }).catch(error => {
        console.error('Error fetching ingredients:', error);
      });
  };

 const handleClick = (e) => {
  e.preventDefault();

  // Construct the recipe object with selected ingredient IDs
  const selectedIngredientIds = ingredientList.map(ing => ing.id); // Extract ingredient IDs
  const recipe = {
      id,
      name,
      ingredientList: selectedIngredientIds, // Use the array of ingredient IDs
      timeForCooking
  };

  console.log('Adding recipe:', recipe);

  // Send the request to add the recipe
  fetch("http://localhost:6969/recipes", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(recipe)
  }).then(() => {
      console.log("New recipe added");
      fetchRecipes();
      setId('');
      setName('');
      setIngredientList([]); // Clear the ingredient list after adding the recipe
      setTimeForCooking('');
  }).catch(error => {
      console.error('Error adding recipe:', error);
  });
};



  const handleDelete = (recipeId) => {
    fetch(`http://localhost:6969/recipes/${recipeId}`, {
      method: "DELETE"
    }).then(() => {
      console.log(`Recipe with id ${recipeId} deleted`);
      fetchRecipes();
    }).catch(error => {
      console.error('Error deleting recipe:', error);
    });
  };

  const handleUpdate = (recipeUpdate) => {
    console.log('Updating recipe:', recipeUpdate);
    setCurrentRecipe(recipeUpdate);
    setId(recipeUpdate.id);
    setName(recipeUpdate.name);
    setIngredientList(recipeUpdate.ingredientList || []);
    setTimeForCooking(recipeUpdate.timeForCooking);
    setOpen(true);
  };

  const handleUpdateSubmit = () => {
    const updatedRecipe = { id, name, ingredientList, timeForCooking };
    console.log('Submitting update:', updatedRecipe);
    fetch(`http://localhost:6969/recipes/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updatedRecipe)
    }).then(() => {
      console.log(`Recipe with id ${id} updated`);
      fetchRecipes();
      setOpen(false);
    }).catch(error => {
      console.error('Error updating recipe:', error);
    });
  };


  const handleAddIngredient = () => {
    if (ingredient.trim() !== '') {
      // Find the selected ingredient object
      const selectedIngredient = ingredients.find(ing => ing.name === ingredient);
      if (selectedIngredient) {
        // Update the ingredient list
        const updatedIngredientList = [...ingredientList, selectedIngredient.id];
        setIngredientList(updatedIngredientList);
        console.log('Updated Ingredient List:', updatedIngredientList);
        setIngredient('');
      }
    }
  };


  const handleIngredientChange = (event) => {
    const selectedIngredientName = event.target.value;
    setIngredient(selectedIngredientName);
  };

  const handleShowSlowCooked = () => {
    fetch("http://localhost:6969/recipes/slowFood")
      .then(res => res.json())
      .then(result => {
        console.log('Fetched slow-cooked recipes:', result);
        setRecipes(result);
      })
      .catch(error => {
        console.error('Error fetching slow-cooked recipes:', error);
      });
  };

  const handleShowFastCooked = () => {
    fetch("http://localhost:6969/recipes/fastFood")
      .then(res => res.json())
      .then(result => {
        console.log('Fetched fast-cooked recipes:', result);
        setRecipes(result);
      })
      .catch(error => {
        console.error('Error fetching fast-cooked recipes:', error);
      });
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
    <FormControl fullWidth style={{ marginBottom: '10px' }}>
      <InputLabel id="ingredient-select-label">Ingredient</InputLabel>
      <Select
        labelId="ingredient-select-label"
        id="ingredient-select"
        value={ingredient}
        onChange={handleIngredientChange}
      >
        {ingredients.map((ing) => (
          <MenuItem key={ing.id} value={ing.name}>
            {ing.name}
          </MenuItem>
        ))}
      </Select>
    </FormControl>
    <Button variant="contained" color="primary" style={{ marginBottom: '10px' }} onClick={handleAddIngredient}>
      Add Ingredient
    </Button>
    <div>
      {ingredientList.map((ingId, index) => (
        <Typography key={index} variant="body2">
          {ingredients.find(ing => ing.id === ingId)?.name} {/* Display ingredient name */}
        </Typography>
      ))}
    </div>
    <TextField
      id="outlined-basic"
      label="Time for cooking"
      variant="outlined"
      value={timeForCooking}
      onChange={(e) => setTimeForCooking(e.target.value)}
      style={{ marginBottom: '10px' }}
    />
    <Button variant="contained" color="success" style={{ marginBottom: '10px' }} onClick={handleClick}>
      Add recipe
    </Button>
    <div style={{ display: 'flex', justifyContent: 'space-between', width: '100%' }}>
    <Button variant="contained" onClick={handleShowSlowCooked} color="secondary">
        Show Slow Cooked Recipes
      </Button>
      <Button variant="contained" onClick={fetchRecipes}>
        Show All Recipes
      </Button>
      
      <Button variant="contained" onClick={handleShowFastCooked} color="error">
        Show Fast Cooked Recipes
      </Button>
    </div>
  </Paper>

  <Paper elevation={3} style={paperStyle}>
    <Grid container spacing={2}>
      {recipes.map(recipe => (
        <Grid item xs={12} key={recipe.id}>
          <Paper elevation={6} style={{ padding: '15px', textAlign: 'left' }}>
            <Typography variant="body1"><strong>Id:</strong> {recipe.id}</Typography>
            <Typography variant="body1"><strong>Name:</strong> {recipe.name}</Typography>
            <Typography variant="body1"><strong>Ingredients list:</strong></Typography>
            {recipe.ingredientList && recipe.ingredientList.map((ing, index) => (
              <Typography key={index} variant="body2">- {ing}</Typography>
            ))}
            <Typography variant="body1"><strong>Time for cooking:</strong> {recipe.timeForCooking}</Typography>
            <Button variant="contained" color="error" onClick={() => handleDelete(recipe.id)}>
              Delete
            </Button>
            <Button variant="contained" color="primary" onClick={() => handleUpdate(recipe)} style={{ marginLeft: '10px' }}>
              Update
            </Button>
          </Paper>
        </Grid>
      ))}
    </Grid>
  </Paper>
</Container>

<Dialog open={open} onClose={() => setOpen(false)}>
  <DialogTitle>Update Recipe</DialogTitle>
  <DialogContent>
    <DialogContentText>
      Update the details of the recipe.
    </DialogContentText>
    <TextField
      margin="dense"
      label="Name"
      fullWidth
      variant="outlined"
      value={name}
      onChange={(e) => setName(e.target.value)}
    />
    <FormControl fullWidth style={{ marginBottom: '10px' }}>
      <InputLabel id="ingredient-select-label">Ingredient</InputLabel>
      <Select
        labelId="ingredient-select-label"
        id="ingredient-select"
        value={ingredient}
        onChange={handleIngredientChange}
      >
        {ingredients.map((ing) => (
          <MenuItem key={ing.id} value={ing.name}>
            {ing.name}
          </MenuItem>
        ))}
      </Select>
    </FormControl>
    <Button variant="contained" color="primary" style={{ marginBottom: '10px' }} onClick={handleAddIngredient}>
      Add Ingredient
    </Button>
    <div>
      {ingredientList.map((ing, index) => (
        <Typography key={index} variant="body2">{ing}</Typography>
      ))}
    </div>
    <TextField
      margin="dense"
      label="Time for cooking"
      fullWidth
      variant="outlined"
      value={timeForCooking}
      onChange={(e) => setTimeForCooking(e.target.value)}
    />
  </DialogContent>
  <DialogActions>
    <Button onClick={() => setOpen(false)}>Cancel</Button>
    <Button onClick={handleUpdateSubmit} variant="contained" color="primary">Update</Button>
  </DialogActions>
</Dialog>
</Box>
);
}

