import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FibonacciVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("fibonacci-request")
                .handler(message -> {
                    Integer q = (Integer) message.body();
                    List <Integer> answers = fibonacci(q);

                    message.reply(
                    answers.stream().map(Object::toString)
                            .collect(Collectors.joining(","))
                    );

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
