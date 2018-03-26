import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.util.List;
import java.util.stream.Collectors;

public class WebVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        // handle request berupa koneksi websocket
        server.websocketHandler(socket -> {
            if (socket.path().equals("/socket")) {
                socket.textMessageHandler(text -> {
                  try {
                      // Question q = new Question(Integer.parseInt(text));
                      vertx.eventBus().send("fibonacci-request",
                              Integer.parseInt(text), reply -> {
                          socket.writeTextMessage(processAnswer(reply));
                              });
                  } catch (Exception e) {
                      socket.writeTextMessage("need a number!");
                  }
                });
            }
        });

        // handle request http
        server.requestHandler(router::accept);

        router.get("/").handler(context -> {
            context.response().sendFile("ws.html");

        });

        server.listen(8080);
    }

    private String processAnswer(AsyncResult<Message<Object>> reply) {
       if (reply.succeeded()) {
          String answer = (String) reply.result().body();
          return answer;
      } else {
           return "Error getting result";
      }
    }
}
