package utils.sn;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.platform.commons.util.StringUtils;
import utils.sn.communication.DataPacketTransfer;

import java.io.IOException;

public class DataPackerConverterTest {

    private static Logger logger = LoggerFactory.getLogger(DataPackerConverterTest.class);

    @Test
    public <T> void testJsonDataToObjectShouldReturnsTargetedClassObject() throws JsonProcessingException {
        //Given
        final String jsonString = "{ \"data\" : \"a\", \"endpoint\" : \"r\"}";

        //When
        T result = (T) DataPacketConverter.jsonDataToObject(jsonString, DataPacketTransfer.class);

        //Then
        Assertions.assertEquals(DataPacketTransfer.class, result.getClass());
    }

    @Test
    public <T> void testObjectToJsonDataShouldReturnsJsonString() throws JsonProcessingException {
        //Given
        final DataPacketTransfer dataPacketTransfer = new DataPacketTransfer("test", "test");

        //When
        String result = DataPacketConverter.objectToJsonData(dataPacketTransfer);

        //Then
        Assertions.assertTrue(StringUtils.isNotBlank(result));
    }

    @Test
    public <T> void testPerfomanceObjectToJsonData() throws JsonProcessingException {
        //Given
        final DataPacketTransfer dataPacketTransfer = new DataPacketTransfer("test", "test");

        //When
        long millisBeforeExecution = System.currentTimeMillis();
        String resultObjectToJson = DataPacketConverter.objectToJsonData(dataPacketTransfer);
        T resultJsonToObject = (T) DataPacketConverter.jsonDataToObject(resultObjectToJson, DataPacketTransfer.class);

        long millisAfterExecution = System.currentTimeMillis();

        double executionTime = (millisAfterExecution - millisBeforeExecution) / 1000d;

        //Then
        logger.info(() -> new StringBuilder("Object to JSON performance time execution -> ").append(String.valueOf(executionTime)).toString());

    }

    @Test
    public <T> void testBinaryDataToObjectShouldReturnsTargetedClassObject() throws IOException, ClassNotFoundException {
        //Given
        byte[] bytes = DataPacketConverter.objectToBinaryData(new DataPacketTransfer("test", "test"));

        //When
        T result = (T) DataPacketConverter.binaryDataToObject(bytes, DataPacketTransfer.class);

        //Then
        Assertions.assertEquals(DataPacketTransfer.class, result.getClass());
    }

    @Test
    public <T> void testObjectToBinaryDataShouldReturnsBytesArray() throws IOException, ClassNotFoundException {
        //Given
        DataPacketTransfer dataPacketTransfer = new DataPacketTransfer("test", "test");

        //When
        byte[] result = DataPacketConverter.objectToBinaryData(dataPacketTransfer);

        //Then
        Assertions.assertTrue(result.length > 0);
    }

    @Test
    public <T> void testPerfomanceBinaryToObjectAndObjectToBinaryData() throws IOException, ClassNotFoundException {
        //Given
        DataPacketTransfer dataPacketTransfer = new DataPacketTransfer("test", "test");

        //When
        long millisBeforeExecution = System.currentTimeMillis();
        byte[] resultObjectToBinary = DataPacketConverter.objectToBinaryData(dataPacketTransfer);
        T resultBinaryToObject = (T) DataPacketConverter.binaryDataToObject(resultObjectToBinary, DataPacketTransfer.class);
        long millisAfterExecution = System.currentTimeMillis();

        double executionTime = (millisAfterExecution - millisBeforeExecution) / 1000d;

        //Then
        logger.info(() -> new StringBuilder("Object to Binary performance time execution -> ").append(String.valueOf(executionTime)).toString());
    }

}
