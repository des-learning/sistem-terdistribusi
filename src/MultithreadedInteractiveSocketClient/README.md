# MultithreadedInteractiveSocketClient

Contoh multithreaded interactive socket client dengan menggunakan dua thread untuk menangani input dan output.

Tujuannya menggunakan thread terpisah adalah supaya proses membaca input dan menampilkan output tidak blocking sehingga
komunikasi antara client dan server dan berjalan tanpa harus menunggu user untuk mengetikkan input.

Ref:

* [Java 101: Understanding Java threads, Part 1: Introducing threads and runnables](https://www.javaworld.com/article/2074217/java-concurrency/java-101--understanding-java-threads--part-1--introducing-threads-and-runnables.html)
* [Java 101: Understanding Java threads, Part 2: Thread synchronization](https://www.javaworld.com/article/2074318/java-concurrency/java-101--understanding-java-threads--part-2--thread-synchronization.html)
* [Java 101: Understanding Java threads, Part 3: Thread scheduling, wait/notify, and thread interruption](https://www.javaworld.com/article/2071214/java-concurrency/java-101--understanding-java-threads--part-3--thread-scheduling-and-wait-notify.html)
* [Java 101: Understanding Java threads, Part 4: Thread groups, volatility, thread-local variables, timers, and thread death](https://www.javaworld.com/article/2071214/java-concurrency/java-101--understanding-java-threads--part-3--thread-scheduling-and-wait-notify.html)