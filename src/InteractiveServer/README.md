# InteractiveServer

Socket server interactive untuk berinteraksi dengan socket client interactive.

Flow aplikasi:
1. Listen ke port 1286
2. Loop *forever*, mulai sesi komunikasi dengan client

   2.1. Baca request dari client
   
   2.2. Proses request
   
   2.3. Kirimkan response ke client
   
   2.5. Jika terjadi exception, asumsi koneksi putus, keluar dari loop
   
3. Putus koneksi, bersihkan resource yang terbuka

4. Layani client berikutnya

Cara menjalankan:

1. Jalankan `InteractiveServer`

2. Jalankan `InteractiveClient`

3. Ketikkan perintah setelah terkoneksi