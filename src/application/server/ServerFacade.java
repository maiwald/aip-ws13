package application.server;

import application.materials_management.facades.MaterialsManagementFacade;
import application.order_management.facades.OrderManagementFacade;
import application.production.facades.ProductionFacade;

public class ServerFacade {


    MaterialsManagementFacade materialsManagement = new MaterialsManagementFacade();
    OrderManagementFacade orderManagement = new OrderManagementFacade();
    ProductionFacade production = new ProductionFacade();

    public ServerFacade() {

    }
}
