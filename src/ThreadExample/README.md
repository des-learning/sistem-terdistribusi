# Thread Example

Contoh aplikasi dengan menggunakan thread untuk menjalankan lebih dari satu (lightweight) process pada saat bersamaan.

Tujuan penggunaan thread adalah memungkinkan process untuk menjalankan beberapa aktivitas pada waktu bersamaan.
Aplikasi seperti ini disebut aplikasi *concurrent*. Dengan menggunakan metode ini, proses yang memakan waktu lama bisa
dijalankan pada thread tersendiri supaya proses utama tidak *block* atau seakan-akan *hang* menunggu proses tersebut
selesai.

Pada contoh ini akan ditampilkan cara penggunaan thread dengan menjalankan beberapa thread yang menghitung angka dari
awal sampai akhir dan masing-masing thread di-*sleep* untuk mensimulasikan waktu proses yang berbeda-beda.gt