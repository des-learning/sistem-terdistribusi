import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class WebVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer http = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.get("/:name").handler(request -> {
            String name = request.pathParam("name");
            vertx.eventBus().send("hello",
                    JsonObject.mapFrom(new Pesan(name)), reply -> {
                if (reply.succeeded()) {
                    Pesan pesan = JsonObject.mapFrom(
                            reply.result().body()).mapTo(Pesan.class);
                    request.response().end(pesan.getPesan());
                } else {
                    request.response().end("Error getting result");
                }
                    });
        });

        http.requestHandler(router::accept);

        http.listen(8080);
    }
}
