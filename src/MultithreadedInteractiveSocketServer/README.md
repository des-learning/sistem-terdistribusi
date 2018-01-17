# MultithreadedInteractiveSocketServer

Contoh penggunaan thread untuk menangani input dan output stream.

Masing-masing client yang terhubung ke server akan dilayani oleh `ThreadedServer`. `ThreadedServer` akan membuat lagi
dua thread yaitu `ThreadedInput` dan `ThreadedOutput` untuk meng-handle input stream dan output stream dari dan ke
client.

Tujuan dari penggunaan thread untuk handling input dan output stream ini adalah supaya `ThreadedServer` tidak blocking
pada saat menerima/mengirimkan data dari/ke client.

Pada contoh ini, `ThreadedInput` dan `ThreadedOutput` akan menggunakan shared object `Request`. Object `Request`
digunakan untuk menampung input dari `ThreadedInput` untuk dikonsumsi oleh `ThreadedOutput`.

Tantangan utama shared object pada aplikasi multithreaded adalah bagaimana mengkoordinasi thread supaya shared object
tidak diubah bersamaan oleh kedua thread (harus dilakukan secara serial/berurutan).

Penjelasan flow contoh ini:
1. Server membuat server socket yang mendengarkan koneksi pada port 1286.
2. Apabila ada client yang melakukan koneksi, buat buat instance socket dan kemudian buat thread `ThreadedServer`
   dengan socket bersangkutan untuk menghandle koneksi client.
3. Pada `ThreadedServer`, buat thread `ThreadedInput` dan `ThreadedOutput` untuk menghandle stream input dan output
   client bersangkutan.
   
   Pada class ini juga di-*instantiate* class `Request`. Object `Request` merupakan shared object yang nanti akan
   dipakai pada `ThreadedInput` dan `ThreadedOutput` untuk menampung request yang dikirim oleh client dan akan diproses
   pada `ThreadedOutput` untuk menentukan response yang akan dikirim ke client.
   
   Karena object `Request` akan dipakai oleh 2 thread yang berbeda, perubahan terhadap object ini harus dilakukan
   secara **synchronized** supaya tidak terjadi *race condition*.
   
4. `ThreadedOutput` akan mencoba untuk mengambil string request dari object `Request`, apabila request tidak ditemukan
   (return null), thread ini akan `wait` menunggu sudah ada request yang tersedia.
   Jika sudah ada request, proses request dan kirimkan output ke client.
   
5. `ThreadedInput` akan menunggu input dari client, apabila sudah ada, tambahkan input string dari client ke object
   `Request` dan kemudian `notify` thread yang lain untuk kembali melanjutkan prosesnya.
   
Pada contoh ini, object `Request` menjadi object monitor yang digunakan untuk mengkoordinasikan pekerjaan masing-masing
thread.
