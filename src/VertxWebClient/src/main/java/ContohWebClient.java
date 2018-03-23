import io.vertx.core.Vertx;

public class ContohWebClient {
    public static void main(String args[]) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new XKCDVerticle());
    }
}
