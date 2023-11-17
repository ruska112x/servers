package karabalin.server;

public interface IServer {
    void executeRequest(String endPoint, String json);
}
