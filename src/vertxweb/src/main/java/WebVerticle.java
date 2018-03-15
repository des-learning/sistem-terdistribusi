import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

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
                      Question q = new Question(Integer.parseInt(text));
                      vertx.eventBus().send("fibonacci-request",
                              JsonObject.mapFrom(q), reply -> {
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
          Answer a = JsonObject.mapFrom(reply.result().body())
                  .mapTo(Answer.class);
          String answer = a.getId() + ": " + a.getAnswers().stream()
                  .map(x -> x.toString())
                  .collect(Collectors.joining(","));
          return answer;
      } else {
           return "Error getting result";
      }
    }
}
