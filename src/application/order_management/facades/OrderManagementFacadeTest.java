package application.order_management.facades;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Before;
import org.junit.Test;

import application.order_management.business_logic.OrderCreator;
import application.order_management.data_access.dtos.OrderDTO;
import application.production.facades.Production;

public class OrderManagementFacadeTest {

    private OrderCreator orderCreatorMock;
    private OrderManagement orderManagement;

    @Before
    public void setUp() throws Exception {
        Production production = mock(Production.class);
        orderManagement = new OrderManagementFacade(production);
    }

    @Test(expected = OrderManagementFacade.InvalidOfferIdError.class)
    public void createOrder_givenInvalidOfferId_throwsException() throws Exception {
        verifyNoMoreInteractions(orderCreatorMock);
        orderManagement.createOrder(-1);
    }

    @Test
    public void createOrder_givenNonExistingOfferId_returnNull() throws Exception {
        assertThat(orderManagement.createOrder(-123), is(nullValue()));
    }

    @Test
    public void createOrder_givenExistingOfferId_returnOrderDTO() throws Exception {
        OrderDTO result = orderManagement.createOrder(1);

        assertThat(result, is(notNullValue()));
        assertThat(result, is(instanceOf(OrderDTO.class)));
    }
}
