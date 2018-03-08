import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class WebVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        // handle request berupa koneksi websocket
        server.websocketHandler(socket -> {
            if (socket.path().equals("/socket")) {
                socket.textMessageHandler(socket::writeTextMessage);
            }
        });

        // handle request http
        server.requestHandler(router::accept);

        router.get("/").handler(context -> {
            context.response().sendFile("ws.html");

        });

        server.listen(8080);
    }
}
