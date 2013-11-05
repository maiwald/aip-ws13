package application.production.business_logic;

import application.materials_management.data_access.entities.PartDTO;
import application.materials_management.facades.MaterialsManagement;
import application.materials_management.facades.MaterialsManagementFacade;
import application.production.facades.Production;
import application.production.facades.ProductionFacade;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ProductProducerTest {

    private MaterialsManagement materialsManagementMock;
    private Production productionMock;
    private ProductProducer productProducer;
    private PartDTO part;
    private final int id = 1;
    private final List<PartDTO> emptyList = new ArrayList<PartDTO>();

    @Before
    public void setUp() throws Exception {
        materialsManagementMock = mock(MaterialsManagementFacade.class);
        productionMock = mock(ProductionFacade.class);
        productProducer = new ProductProducer(materialsManagementMock, productionMock);
        part = mock(PartDTO.class);

        when(part.getId()).thenReturn(id);
    }

    @Test
    public void givenSimplePart_noWorkSchedulePrinted() throws Exception {
        when(materialsManagementMock.getPartsList(id)).thenReturn(emptyList);

        productProducer.produceParts(part);

        verify(materialsManagementMock).getPartsList(id);
        verify(productionMock, never()).printWorkSchedule(part);
    }

    @Test
    public void givenNonSimplePart_printSubPartsSchedules() throws Exception {
        List<PartDTO> parts = new ArrayList<PartDTO>();
        PartDTO subPart = mock(PartDTO.class);
        int subPartId = 2;
        parts.add(subPart);
        parts.add(subPart);

        when(subPart.getId()).thenReturn(subPartId);
        when(materialsManagementMock.getPartsList(id)).thenReturn(parts);
        when(materialsManagementMock.getPartsList(subPartId)).thenReturn(emptyList);

        productProducer.produceParts(part);

        verify(productionMock, times(subPartId)).printWorkSchedule(subPart);
    }
}