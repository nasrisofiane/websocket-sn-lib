package utils.sn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.sn.communication.DataPacketTransfer;

public final class DataPacketConverter {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T>T jsonDataToObject(DataPacketTransfer dataPacketTransfer, Class<T> type) throws JsonProcessingException {
        return mapper.readValue(dataPacketTransfer.getData(), type);
    }

    public static <T>String objectToJsonData(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
