package utils.sn.communication;

public final class DataPacketTransfer {
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
