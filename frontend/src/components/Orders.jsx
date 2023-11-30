import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { List, ListItem, ListItemText, Divider, Typography, Container, Box, Paper } from '@mui/material';

export default function ServiceProviderOrders() {
  const [orders, setOrders] = useState([]);
  const [serviceProvider, setServiceProvider] = useState(null);

  useEffect(() => {
    const fetchServiceProvider = async () => {
      try {
        const spResponse = await axios.get('http://localhost:8080/currentSP', { withCredentials: true });
        setServiceProvider(spResponse.data);
        console.log("Fetched Service Provider:", spResponse.data);  // Logging the service provider
        fetchService(spResponse.data.serviceProviderId);
      } catch (error) {
        console.error("Error fetching service provider:", error);
      }
    };

    const fetchService = async (serviceProviderId) => {
      try {
        const serviceResponse = await axios.get(`http://localhost:8080/Service/getServiceByProvider/${serviceProviderId}`);
        console.log("Fetched Service:", serviceResponse.data);
        fetchOrders(serviceResponse.data.service_id);
      } catch (error) {
        console.error("Error fetching service:", error);
      }
    };

    const fetchOrders = async (serviceId) => {
      try {
        const ordersResponse = await axios.get(`http://localhost:8080/order/getOrdersForService/${serviceId}`);
        console.log("Fetched Orders:", ordersResponse.data);
        const ordersData = ordersResponse.data;

        const detailedOrders = await Promise.all(ordersData.map(async (order) => {
          const userServiceResponse = await axios.get(`http://localhost:8080/getUser/${order.user_id}`);
          return {
            ...order,
            userName: `${userServiceResponse.data.first_name} ${userServiceResponse.data.last_name}`
          };
        }));

        setOrders(detailedOrders);
      } catch (error) {
        console.error("Error fetching orders:", error);
      }
    };

    fetchServiceProvider();
  }, []);

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" sx={{ my: 4 }}>
        {serviceProvider ? `${serviceProvider.username}'s Orders` : 'Service Provider Orders'}
      </Typography>
      <List>
        {orders.map((order, index) => (
          <React.Fragment key={order.order_id}>
            <Paper elevation={3} sx={{ my: 2, p: 2 }}>
              <ListItem alignItems="flex-start">
                <ListItemText
                  primary={`Order ID: ${order.order_id}`}
                  secondary={
                    <>
                      <Typography component="span" variant="body2" color="text.primary">
                        User: {order.userName}
                      </Typography>
                      <br />
                      {`Date: ${order.order_date}`}
                      <br />
                      {`Status: ${order.status}`}
                    </>
                  }
                />
              </ListItem>
            </Paper>
            {index < orders.length - 1 && <Divider variant="inset" />}
          </React.Fragment>
        ))}
      </List>
    </Container>
  );
}
