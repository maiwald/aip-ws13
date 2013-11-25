package application.production.facades;

import application.materials_management.data_access.dtos.PartDTO;
import application.materials_management.facades.MaterialsManagement;
import application.order_management.data_access.dtos.OrderDTO;
import application.production.business_logic.ProductProducer;
import application.production.data_access.entities.ProductionOrder;
import application.production.data_access.repositories.ProductionOrderRepository;

public class ProductionFacade implements Production {

    private final MaterialsManagement materialsManagement;
    private final ProductionOrderRepository productionOrderRepository;
    private final ProductProducer productProducer;

    public ProductionFacade(MaterialsManagement materialsManagement) {
        this.materialsManagement = materialsManagement;
        this.productionOrderRepository = new ProductionOrderRepository();
        this.productProducer = new ProductProducer(materialsManagement, this);
    }

    @Override
    public void produceOrder(OrderDTO orderDTO) {
        ProductionOrder productionOrder = productionOrderRepository.createProductionOrder(orderDTO);
        PartDTO part = materialsManagement.getPartById(productionOrder.getPartId());
        productProducer.produceParts(part);
    }

    @Override
    public void printWorkSchedule(PartDTO partDTO) {
        // TODO do stuff
    }
}
