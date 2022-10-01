package utils.sn.communication;

import java.io.Serializable;

public final class DataPacketTransfer implements Serializable {
    private String data;
    private String endpoint;

    public DataPacketTransfer(String data, String endpoint) {
        this.data = data;
        this.endpoint = endpoint;
    }

    public DataPacketTransfer(){

    }

    public String getData() {
        return data;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
