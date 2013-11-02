package application.production.data_access.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import application.order_management.data_access.dtos.OrderDTO;

@Entity
@Table(name = "mpsProductionOrder")
public class ProductionOrder {

    private int productionOrderId;
    private int orderId;
    private int partId;

    public static ProductionOrder fromOrderDTO(OrderDTO orderDTO) {
        ProductionOrder p = new ProductionOrder();
        p.setOrderId(orderDTO.getOrderId());
        return p;
    }

    public ProductionOrder() {
        this.partId = 2349085;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
