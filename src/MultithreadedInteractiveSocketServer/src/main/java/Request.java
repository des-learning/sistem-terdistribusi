import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class Request {
    private final Queue<String> requests;

    Request() {
        this.requests = new ConcurrentLinkedQueue<String>();
    }

    synchronized public boolean add(String request) {
        return requests.add(request);
    }

    synchronized public String get() {
        return requests.poll();
    }
}
