import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class Message {
    private final Queue<String> messages;

    Message() {
        this.messages = new ConcurrentLinkedQueue<>();
    }

    synchronized public boolean add(String message) {
        return messages.add(message);
    }

    synchronized public String get() {
        return messages.poll();
    }
}
