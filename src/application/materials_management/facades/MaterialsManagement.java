package application.materials_management.facades;

import application.materials_management.data_access.entities.PartDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: felix_000
 * Date: 05.11.13
 * Time: 09:08
 * To change this template use File | Settings | File Templates.
 */
public interface MaterialsManagement {
    List<PartDTO> getPartsList(int partId);

    PartDTO getPartById(int partId);
}
