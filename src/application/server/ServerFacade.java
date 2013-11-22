package application.server;

import application.materials_management.facades.MaterialsManagement;
import application.materials_management.facades.MaterialsManagementFacade;
import application.order_management.data_access.dtos.OrderDTO;
import application.order_management.facades.OrderManagement;
import application.order_management.facades.OrderManagementFacade;
import application.production.facades.Production;
import application.production.facades.ProductionFacade;

public class ServerFacade {

	MaterialsManagement materialsManagement = new MaterialsManagementFacade();
	Production production = new ProductionFacade(this.materialsManagement);
	OrderManagement orderManagement = new OrderManagementFacade(this.production);

	public ServerFacade() {
		OrderDTO order = orderManagement.createOrder(2345);
		System.out.println(order);
	}

	public static void main(String[] args) {
		// new ServerFacade();
		try {
			int i = 4;
			while (i >= 0) {
				ServerEKG.sendLifeSign();
				Thread.sleep(2 * 1000);
				i--;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
