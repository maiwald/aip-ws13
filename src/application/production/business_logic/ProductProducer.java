package application.production.business_logic;

import java.util.List;

import application.materials_management.data_access.entities.PartDTO;
import application.materials_management.facades.MaterialsManagementFacade;

public class ProductProducer {

	public static void produceParts(PartDTO part) {
		List<PartDTO> parts = MaterialsManagementFacade.getPartsList(part.getId());

		for (PartDTO p : parts) {
			produceParts(p);
		}

	}
}
