import java.util.Arrays;
import java.util.List;

/**
 * main application
 */
public class ThreadExample {
    public static void main(String[] args) {
        // buat runnable thread untuk nanti dieksekusi
        List<ThreadHitung> threads = Arrays.asList(
                new ThreadHitung("budi", 1, 10, 2000L),
                new ThreadHitung("susi", 1, 10, 1000L),
                new ThreadHitung("andi", 1, 10, 3000L),
                new ThreadHitung("ali", 1, 10, 500L),
                new ThreadHitung("rudi", 1, 100, 0L),
                new ThreadHitung("tuti", 1, 10, 1500L)
        );

        // eksekusi thread
        for (Runnable thread: threads) {
            new Thread(thread).start();
        }
    }
}
