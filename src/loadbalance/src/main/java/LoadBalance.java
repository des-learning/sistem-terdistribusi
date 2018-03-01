import io.vertx.core.Vertx;

public class LoadBalance {
    public static void main(String []args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new HttpVerticle("server0"));
        vertx.deployVerticle(new HttpVerticle("server1"));
        vertx.deployVerticle(new HttpVerticle("server2"));
        vertx.deployVerticle(new HttpVerticle("server3"));
    }
}
