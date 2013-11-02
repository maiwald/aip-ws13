package application.production.facades;

import application.materials_management.data_access.entities.PartDTO;
import application.materials_management.facades.MaterialsManagementFacade;
import application.order_management.data_access.dtos.OrderDTO;
import application.production.business_logic.ProductProducer;
import application.production.data_access.entities.ProductionOrder;
import application.production.data_access.repositories.ProductionOrderRepository;

public class ProductionFacade {

    private MaterialsManagementFacade materialsManagement = new MaterialsManagementFacade();
    private ProductionOrderRepository productionOrderRepository = new ProductionOrderRepository();
    private ProductProducer productProducer = new ProductProducer(materialsManagement, this);

    public void produceOrder(OrderDTO orderDTO) {
        ProductionOrder productionOrder = productionOrderRepository.createProductionOrder(orderDTO);
        PartDTO part = materialsManagement.getPartById(productionOrder.getPartId());
        productProducer.produceParts(part);
    }

    public void printWorkSchedule(PartDTO partDTO) {
        // TODO do stuff
    }
}
