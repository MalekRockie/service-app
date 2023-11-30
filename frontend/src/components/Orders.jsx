import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { List, ListItem, ListItemText, Divider, Typography, Container, Paper, Button, ButtonGroup } from '@mui/material';

export default function ServiceProviderOrders() {
  const [orders, setOrders] = useState([]);
  const [serviceProviderId, setServiceProviderId] = useState(null);
  const [serviceProviderName, setServiceProviderName] = useState(null);

  useEffect(() => {
    const fetchServiceProvider = async () => {
      try {
        const spResponse = await axios.get('http://localhost:8080/currentSP', { withCredentials: true });
        setServiceProviderId(spResponse.data.serviceProviderId);
        setServiceProviderName(spResponse.data.username);
        fetchOrdersForServiceProvider(spResponse.data.serviceProviderId);
      } catch (error) {
        console.error("Error fetching service provider:", error);
      }
    };

    fetchServiceProvider();
  }, []);

  const fetchOrdersForServiceProvider = async (spId) => {
    if (!spId) return;
    try {
      const serviceResponse = await axios.get(`http://localhost:8080/Service/getServiceByProvider/${spId}`);
      const ordersResponse = await axios.get(`http://localhost:8080/order/getOrdersForService/${serviceResponse.data.service_id}`);
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
      console.error("Error fetching orders for service provider:", error);
    }
  };

  const handleStatusChange = async (orderId, newStatus) => {
    try {
      await axios.post(`http://localhost:8080/order/OrderStatus`, null, {
        params: { id: orderId, status: newStatus },
        withCredentials: true
      });
      fetchOrdersForServiceProvider(serviceProviderId);
    } catch (error) {
      console.error(`Error updating status for order ${orderId}:`, error);
    }
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" sx={{ my: 4 }}>
        {serviceProviderId ? `Orders for ${serviceProviderName}` : 'Service Provider Orders'}
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
                <ButtonGroup variant="text" aria-label="text button group" sx={{ mt: 2 }}>
                  <Button onClick={() => handleStatusChange(order.order_id, 'Accepted')}>Accept</Button>
                  <Button onClick={() => handleStatusChange(order.order_id, 'Rejected')}>Reject</Button>
                  <Button onClick={() => handleStatusChange(order.order_id, 'Completed')}>Complete</Button>
                </ButtonGroup>
              </ListItem>
            </Paper>
            {index < orders.length - 1 && <Divider variant="inset" />}
          </React.Fragment>
        ))}
      </List>
    </Container>
  );
}
