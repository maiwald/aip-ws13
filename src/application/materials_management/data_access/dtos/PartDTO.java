package application.materials_management.data_access.dtos;

public class PartDTO {

    private final int partId;

    public PartDTO(int partId) {
        this.partId = partId;
    }

    public int getId() {
        return this.partId;
    }

}