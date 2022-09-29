package utils.sn;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import utils.sn.communication.DataPacketTransfer;

public class DataPackerConverterTest {

    @Test
    public <T> void testJsonDataToObjectShouldReturnsTargetedClassObject() throws JsonProcessingException {
        //Given
        final DataPacketTransfer dataPacketTransfer = new DataPacketTransfer("{ \"data\" : \"a\", \"endpoint\" : \"r\"}", "test");

        //When
        T result = (T) DataPacketConverter.jsonDataToObject(dataPacketTransfer, DataPacketTransfer.class);

        //Then
        Assertions.assertEquals(DataPacketTransfer.class, result.getClass());
    }

    @Test
    public <T> void testObjectToJsonData() throws JsonProcessingException {
        //Given
        final DataPacketTransfer dataPacketTransfer = new DataPacketTransfer("test", "test");

        //When
        String result = DataPacketConverter.objectToJsonData(dataPacketTransfer);

        //Then
        Assertions.assertTrue(StringUtils.isNotBlank(result));
    }
}
