# MultithreadedInteractiveSocketServer

Contoh penggunaan thread untuk menangani input dan output stream.

Pada contoh ini ada 3 thread yang akan berjalan untuk masing-masing koneksi:
1. `ThreadedServer` akan menghandle koneksi dari masing-masing client. Thread ini juga yang nantinya akan
   mengkoordinasi thread `ThreadedInput` dan `ThreadedOutput`.
2. `ThreadedInput`, subthread dari `ThreadedServer` untuk menangani seluruh input yang dikirim dari client ke server.
3. `ThreadedOutput`, subthread dari `ThreadedServer` untuk menangani seluruh output yang akan dkirim dari server ke
   client.
   
`ThreadedServer` akan membuat 2 thread yaitu `ThreadedInput` dan `ThreadedOutput` dan menginisialisasi object
`requests` dan `responses` sebagai buffer untuk menampung data `requests` dari `ThreadedInput` dan data output ke
`ThreadedOutput`.

Object `requests` dan `responses` akan digunakan oleh 2 thread, yaitu:
1. `requests` digunakan oleh `ThreadedInput` untuk menambahkan string yang dikirim oleh client dan digunakan juga oleh
   `ThreadedServer` untuk memproses string yang diterima.
2. `responses` digunakan oleh `ThreadedServer` yaitu untuk menyimpan output yang akan dikirim ke client dan oleh
   `ThreadedOutput` untuk mengirimkan output ke client.
   
Object yang digunakan oleh dua atau lebih thread dan akan ada operasi yang mengubah object harus dilakukan secara
berurutan dan atomic untuk menghalangi terjadinya *race condition* dimana dua atau lebih thread mengubah isi object
sehingga isi object tidak lagi konsisten. Hal ini bisa dilakukan melalui **synchronized** statement/method.

Keyword **synchronized** pada Java berfungsi untuk mengunci object sehingga hanya 1 thread saja yang bisa melakukan
operasi pada object pada satu saat.

Apabila untuk melakukan suatu operasi akan dilakukan lock terhadap 2 atau lebih object, programmer perlu lebih
berhati-hati menyusun urutan operasi yang dilakukan supaya tidak terjadi *deadlock*, *livelock* dan *starvation*.

*deadlock* terjadi apabila 1 thread perlu meng-lock 2 object (misalnya A dan B) untuk melakukan operasi. Tetapi karena
urutan lock yang tidak seragam sehingga satu lock dipegang oleh thread pertama dan lock lainnya dipegang oleh thread
kedua yang menyebabkan masing-masing thread akan saling menunggu. Hal ini menyebabkan aplikasi menjadi hang karena tidak
ada proses yang bisa dilakukan.

*livelock* terjadi apabila thread gagal melakukan lock terhadap seluruh object yang diperlukan dan kemudian melepaskan
lock dan mengulang lagi proses locking. Apabila timing seluruh thread sama, hal ini menyebabkan thread memcoba
mendapatkan lock, gagal, melepaskan kunci dan mengulang kembali. Hal ini juga menyebabkan aplikasi menjang hang karena
proses berulang terus pada logic locking.

*starvation* terjadi apabila strategi locking dilakukan secara prioritas. Apabila banyak thread dengan prioritas tinggi
melakukan lock terhadap object, thread dengan prioritas rendah tidak akan kebagian giliran untuk melakukan lock yang
menyebabkan proses thread prioritas rendah akan stuck karena tidak dapat menlanjutkan proses.

Ref:

* [Java 101: Understanding Java threads, Part 1: Introducing threads and runnables](https://www.javaworld.com/article/2074217/java-concurrency/java-101--understanding-java-threads--part-1--introducing-threads-and-runnables.html)
* [Java 101: Understanding Java threads, Part 2: Thread synchronization](https://www.javaworld.com/article/2074318/java-concurrency/java-101--understanding-java-threads--part-2--thread-synchronization.html)
* [Java 101: Understanding Java threads, Part 3: Thread scheduling, wait/notify, and thread interruption](https://www.javaworld.com/article/2071214/java-concurrency/java-101--understanding-java-threads--part-3--thread-scheduling-and-wait-notify.html)
* [Java 101: Understanding Java threads, Part 4: Thread groups, volatility, thread-local variables, timers, and thread death](https://www.javaworld.com/article/2071214/java-concurrency/java-101--understanding-java-threads--part-3--thread-scheduling-and-wait-notify.html)
