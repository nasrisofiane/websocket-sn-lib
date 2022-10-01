package utils.sn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.sn.communication.DataPacketTransfer;

import java.io.*;

public final class DataPacketConverter {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T>T jsonDataToObject(String jsonString, Class<T> type) throws JsonProcessingException {
        return mapper.readValue(jsonString, type);
    }

    public static <T>String objectToJsonData(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T>T binaryDataToObject(byte[] bytes, Class<T> type) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream is = new ObjectInputStream(in);
        return type.cast(is.readObject());
    }

    public static <T> byte[] objectToBinaryData (T object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        DataPacketTransfer dataPacketTransfer = new DataPacketTransfer("", "");
        os.writeObject(dataPacketTransfer);

        return out.toByteArray();
    }

}
