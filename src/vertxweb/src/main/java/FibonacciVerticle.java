import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class FibonacciVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("fibonacci-request")
                .handler(message -> {
                    Question q = JsonObject.mapFrom(message.body())
                            .mapTo(Question.class);
                    Answer a = new Answer(deploymentID(), q.getQuestion(),
                            fibonacci(q.getQuestion()));
                    message.reply(JsonObject.mapFrom(a));
                });
    }

    private List<Integer> fibonacci(int n) {
        List<Integer> hasil = new ArrayList<>();
        int a = 0;
        int b = 1;
        int c = 0;
        hasil.add(b);
        for (int i = 0; c <= n; i++) {
            c = a + b;
            hasil.add(c);
            a = b;
            b = c;
        }
        return hasil;
    }
}
