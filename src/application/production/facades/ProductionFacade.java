package application.production.facades;

import application.materials_management.data_access.entities.PartDTO;
import application.materials_management.facades.MaterialsManagementFacade;
import application.order_management.data_access.dtos.OrderDTO;
import application.production.business_logic.ProductProducer;
import application.production.data_access.entities.ProductionOrder;
import application.production.data_access.repositories.ProductionOrderRepository;


public class ProductionFacade {
	
	public void produceOrder(OrderDTO orderDTO) {
		ProductionOrder productionOrder = ProductionOrderRepository.createProductionOrder(orderDTO);
		PartDTO part = MaterialsManagementFacade.getPartById(productionOrder.getPartId());
		ProductProducer.produceParts(part);
	}
}
