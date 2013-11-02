package application.order_management.data_access.dtos;

public class OrderDTO {

    private final int orderId;

    public OrderDTO(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}
