package application.materials_management.data_access.dtos;

import java.io.Serializable;

public class PartDTO implements Serializable {

    private final int partId;

    public PartDTO(int partId) {
        this.partId = partId;
    }

    public int getId() {
        return this.partId;
    }

}
