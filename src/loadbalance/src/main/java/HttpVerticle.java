import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class HttpVerticle extends AbstractVerticle {
    private String name;

    public HttpVerticle(String name) {
        this.name = name;
    }

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route("/").handler(context -> {
            context.response().end(name);
        });

        router.route("/hello/:name").handler(context -> {
            String name = context.pathParam("name");
            context.response().end("Hello " + name + " from " + this.name);
        });


        server.requestHandler(request -> router.accept(request))
                .listen(8080);
    }
}
