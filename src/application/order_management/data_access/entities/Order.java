package application.order_management.data_access.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import application.order_management.data_access.dtos.OrderDTO;

@Entity
@Table(name = "mpsOrder")
public class Order {

    private int orderId;
    private int offerId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(nullable = false)
    public int getOfferId() {
        return this.offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public OrderDTO createDTO() {
        return new OrderDTO(this.offerId);
    }
}
