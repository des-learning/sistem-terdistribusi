# MultithreadedSocketServer

Contoh aplikasi Socket Server dengan menggunakan thread untuk melayani masing-masing koneksi dari user.

Dengan menggunakan thread, aplikasi server dapat melayani banyak client dalam waktu bersamaan. Jumlah thread yang bisa
dibentuk tergantung dari jumlah resource komputer yang tersedia.

Langkah-langkah untuk menjalankan:
1. Jalankan `Server` dari project `MultithreadedSocketServer`
2. Telnet ke localhost 1286

   ```bash
   telnet localhost 1286
   ```
   
3. Ketikkan perintah untuk dieksekusi. Perintah yang dikenal:

   - `memory`
   
     menampilkan jumlah memory tersisa
     
   - `time`
   
     menampilkan waktu server
     
   - `stop`
   
     stop dan disconnect dari server