import * as React from 'react';
import { Button, CssBaseline, TextField, FormControlLabel, Checkbox, Link, Grid, Box, Typography, Container, createTheme, ThemeProvider } from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const serviceProviderTheme = createTheme({
  palette: {
    primary: {
      main: '#1976d2', 
    },
    secondary: {
      main: '#d32f2f', 
    },
  },
  typography: {
    h5: {
      fontWeight: 600,
    },
    button: {
      textTransform: 'none', 
    },
  },
});

export default function ServiceProviderLogIn() {
  const navigate = useNavigate();
  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
  
    const formData = new URLSearchParams();
    formData.append('username', data.get('email'));
    formData.append('password', data.get('password'));
  
    try {
      const response = await axios.post('http://localhost:8080/login', formData.toString(), {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      });
      console.log('Login successful', response.data);
      navigate('/ServiceBrowser');
    } catch (error) {
      console.error('Login failed', error.response ? error.response.data : error.message);
    }
  };

  return (
    <ThemeProvider theme={serviceProviderTheme}>
      <Container component="main" maxWidth="xs" sx={{ backgroundColor: '#f5f5f5', borderRadius: 2, boxShadow: 3, p: 3 }}>
        <CssBaseline />
        <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <Typography component="h1" variant="h5" color="primary">
            Service Provider Log In
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 2 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email"
              name="email"
              autoComplete="email"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
            />
            <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            />
            <Button type="submit" fullWidth variant="contained" color="secondary" sx={{ mt: 3, mb: 2 }}>
              Log In
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
