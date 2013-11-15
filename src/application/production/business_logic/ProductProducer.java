package application.production.business_logic;

import java.util.List;

import application.materials_management.data_access.entities.PartDTO;
import application.materials_management.facades.MaterialsManagement;
import application.production.facades.Production;

public class ProductProducer {

    private final MaterialsManagement materialsManagement;
    private final Production production;

    public ProductProducer(MaterialsManagement materialsManagement, Production production) {
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