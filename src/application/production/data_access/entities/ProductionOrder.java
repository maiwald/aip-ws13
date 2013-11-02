package application.production.data_access.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import application.order_management.data_access.dtos.OrderDTO;

@Entity
public class ProductionOrder {

    private int productionOrderId;
    private int orderId;
    private int partId;

    public ProductionOrder(OrderDTO orderDTO) {
        this.orderId = orderDTO.getOrderId();
        this.partId = 2349085;
    }

    @Id
    public int getProductionOrderId() {
        return productionOrderId;
    }

    public void setProductionOrderId(int productionOrderId) {
        this.productionOrderId = productionOrderId;
    }

    @Column(nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getPartId() {
        return this.partId;
    }

}
