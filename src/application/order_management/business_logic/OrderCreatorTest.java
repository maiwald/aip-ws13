package application.order_management.business_logic;

import application.order_management.data_access.entities.Order;
import application.order_management.data_access.repositories.OrderRepository;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderCreatorTest {
    @Test
    public void createOrder_givenNonExistingOfferId_returnsNull() throws Exception {
        int nonExistingOfferId = 1999999999;
        OrderRepository repository = mock(OrderRepository.class);
        when(repository.createOrder(nonExistingOfferId)).thenReturn(null);
        OrderCreator creator = new OrderCreator(repository);

        assertThat(creator.createOrder(nonExistingOfferId), is(nullValue()));
    }

    @Test
    public void createOrder_givenExistingOfferId_returnsOrder() throws Exception {
        int existingOfferId = 1;
        OrderRepository repository = mock(OrderRepository.class);
        when(repository.createOrder(existingOfferId)).thenReturn(new Order(existingOfferId));
        OrderCreator creator = new OrderCreator(repository);

        assertThat(creator.createOrder(existingOfferId), is(instanceOf(Order.class)));
    }


}