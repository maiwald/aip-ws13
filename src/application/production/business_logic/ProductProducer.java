package application.production.business_logic;

import java.util.List;

import application.materials_management.data_access.entities.PartDTO;
import application.materials_management.facades.MaterialsManagementFacade;
import application.production.facades.ProductionFacade;

public class ProductProducer {

    private final ProductionFacade production;
    private final MaterialsManagementFacade materialsManagement;

    public ProductProducer(MaterialsManagementFacade materialsManagement, ProductionFacade production) {
        this.materialsManagement = materialsManagement;
        this.production = production;
    }

	public void produceParts(PartDTO part) {
		List<PartDTO> parts = materialsManagement.getPartsList(part.getId());

		for (PartDTO p : parts) {
			produceParts(p);
			production.printWorkSchedule(p);
		}

	}
}
