package application.materials_management.facades;

import java.util.ArrayList;
import java.util.List;

import application.materials_management.data_access.entities.PartDTO;

public class MaterialsManagementFacade {
	public static List<PartDTO> getPartsList(int partId) {
		List<PartDTO> list = new ArrayList<PartDTO>();
		list.add(new PartDTO(2345));
		list.add(new PartDTO(12316));
		return list;
	}

	public static PartDTO getPartById(int partId) {
		return new PartDTO(partId);
	}
}
