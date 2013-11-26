package application.order_management.data_access.dtos;

import java.io.Serializable;

public class OrderDTO implements Serializable {

    private final int orderId;

    public OrderDTO(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}
