package application.production.data_access.entities;

import application.production.data_access.dtos.ProductionOrderDTO;

public class ProductionOrder {
	
	public ProductionOrderDTO createDTO() {
		return new ProductionOrderDTO();
	}
}
