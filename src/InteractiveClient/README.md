# InteractiveClient

Socket client interactive untuk berinteraksi dengan socket server interactive.

Flow aplikasi:
1. Konek ke server
2. Loop *forever*

   2.1. Baca input dari keyboard
   
   2.2. Kirim request ke server
   
   2.3. Terima response dari server
   
   2.4. Tampilkan response ke monitor
   
   2.5. Jika terjadi exception, asumsi koneksi putus, keluar dari loop
   
3. Putus koneksi, bersihkan resource yang terbuka

Cara menjalankan:

1. Jalankan `InteractiveServer`

2. Jalankan `InteractiveClient`

3. Ketikkan perintah setelah terkoneksi