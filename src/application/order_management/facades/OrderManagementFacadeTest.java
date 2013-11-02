package application.order_management.facades;

import application.order_management.business_logic.OrderCreator;
import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.data_access.entities.Order;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class OrderManagementFacadeTest {

    private OrderCreator orderCreatorMock;
    private OrderManagementFacade orderManagement;

    @Before
    public void setUp() throws Exception {
        orderCreatorMock = mock(OrderCreator.class);
        orderManagement = new OrderManagementFacade(orderCreatorMock);
    }

    @Test(expected = OrderManagementFacade.InvalidOfferIdException.class)
    public void createOrder_givenInvalidOfferId_throwsException() throws Exception {
        verifyNoMoreInteractions(orderCreatorMock);
        orderManagement.createOrder(-1);
    }
    
    @Test
    public void createOrder_givenNonExistingOfferId_returnNull() throws Exception {
        when(orderCreatorMock.createOrder(anyInt())).thenReturn(null);

        assertThat(orderManagement.createOrder(1), is(nullValue()));
    }

    @Test
    public void createOrder_givenExistingOfferId_returnOrderDTO() throws Exception {
        int offerId = 1;
        Order order = mock(Order.class);
        OrderDTO expectedOrderDTO = mock(OrderDTO.class);
        when(orderCreatorMock.createOrder(offerId)).thenReturn(order);
        when(order.createDTO()).thenReturn(expectedOrderDTO);

        OrderDTO result = orderManagement.createOrder(offerId);

        assertThat(result, is(notNullValue()));
        assertThat(result, is(instanceOf(OrderDTO.class)));
    }
}
