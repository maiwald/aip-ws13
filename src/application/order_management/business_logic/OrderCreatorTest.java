package application.order_management.business_logic;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import application.production.facades.Production;
import org.junit.Test;

import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;
import application.order_management.data_access.repositories.OrderRepository;
import application.production.facades.ProductionFacade;

public class OrderCreatorTest {

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private Production production = mock(ProductionFacade.class);

    @Test
    public void createOrder_givenNonExistingOfferId_returnsNull() throws Exception {
        int nonExistingOfferId = 1999999999;
        when(orderRepository.createOrder(nonExistingOfferId)).thenReturn(null);

        OrderCreator creator = new OrderCreator(orderRepository, production);

        assertThat(creator.createOrder(nonExistingOfferId), is(nullValue()));
    }

    @Test
    public void createOrder_givenExistingOfferId_returnsOrder() throws Exception {
        int existingOfferId = 1;

        Order order = mock(Order.class);
        order.setOfferId(existingOfferId);

        OrderDTO orderDTO = mock(OrderDTO.class);

        when(order.createDTO()).thenReturn(orderDTO);
        when(orderRepository.createOrder(existingOfferId)).thenReturn(order);

        OrderCreator creator = new OrderCreator(orderRepository, production);
        assertThat(creator.createOrder(existingOfferId), is(instanceOf(Order.class)));

        verify(order).createDTO();
        verify(production).produceOrder(orderDTO);
    }

}