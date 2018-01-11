/**
 * Class ini yang akan dijalankan pada masing-masing thread
 */
public class ThreadHitung implements Runnable {
    private final String name;
    private final Integer dari, sampai;
    private final Long jeda;

    // constructor
    public ThreadHitung(String name, Integer dari, Integer sampai, Long jeda) {
        this.name = name;
        this.dari = dari;
        this.sampai = sampai;
        this.jeda = jeda;
    }

    // method run akan di eksekusi ketika thread di start
    @Override
    public void run() {
        System.out.println(String.format("%s mulai berhitung", name));
        for (Integer i = dari; i < sampai; i++) {
            System.out.println(String.format("%s: %d", name, i));
            try {
                Thread.sleep(jeda);
            } catch (InterruptedException e) {
                System.out.println(String.format("%s interrupted", name));
            }
        }
        System.out.println(String.format("%s selesai berhitung", name));
    }
}
