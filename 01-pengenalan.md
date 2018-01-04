# Sistem Terdistribusi
* apa yang dimaksud dengan sistem terdistribusi
* distrbuted system vs centralised system (vs desentralised system)
* apa manfaat atau masalah yang ingin diselesaikan oleh sistem terdistribusi
* tantangan atau masalah pada sistem terdistribusi
* fallacies of distributed system


## Apa itu sistem terdistribusi

Distributed system:

    A distributed system is a collection of autonomous computing elements that
    appears to its users as a single coherent system

Karakteristik sistem terdistribusi:

* terdiri dari kumpulan elemen (node) yang berdiri sendiri (autonomous)
* dari sudut pandang pengguna, sistem terdistribusi dilihat sebagai satu
  sistem yang coherent (satu kesatuan)

Contoh-contoh sistem terdistribusi:

* multithreaded application
* aplikasi client server (2-tier, 3-tier, n-tier)

  - 2-tier: aplikasi desktop yang terhubung ke sql server
  - 3-tier: aplikasi mobile/web yang terhubung dengan application server
    yang mengakses data di storage/sql server

* software as a service (cloud)

  dropbox, gmail, instant messaging (whatsapp, telegram, dll),
  (massive) multiplayer (online) game

* perangkat internet of things (IoT)

  sensor (suhu, cahaya, gerak, listrik, suara)
  cctv


## Distributed system vs centralized system (vs decentralized system)

[Distributed vs centralized (vs decentralized) system](https://medium.com/@bbc4468/centralized-vs-decentralized-vs-distributed-41d92d463868)


## Manfaat sistem terdistribusi

Masalah yang ingin diselesaikan oleh sistem terdistribusi:

* skalabilitas: menangani pertumbuhan pengguna/request yang perlu dilayani
* reliabilitas: membangun sistem yang fault tolerant
* resource sharing: computation, storage, bandwidth

Skalabilitas

- jumlah user/request semakin banyak, tidak bisa lagi dilayani dengan baik oleh
  satu sistem, perlu sistem lain yang sama persis untuk berbagi beban
  (load balance)
- lokasi geografi user/request, untuk mengurangi latency dan meningkatkan
  respons, sistem direplikasi dan diletakkan pada jarak geografi yang
  berdekatan dengan asal user/request
- memproses jumlah data yang besar dengan cara melakukan komputasi pada
  banyak sistem dan kemudian menggabungkan hasilnya (map reduce)

Reliabilitas

- ada sistem cadangan yang masih dapat melayani user/request apabila sistem
  utama gagal (fail over)

Resource sharing

- cloud computing
  sumber daya/layanan komputasi disewakan kepada pengguna (menjadi perusahan
  utility seperti perusahaan listrik, air, telepon dan gas).

  - Infrastructure as a Service (IaaS)
    menyewakan infrastruktur IT seperti computing power, network infrastructure,
    storage untuk digunakan oleh pengguna. IaaS hanya menyediakan layanan
    pada sumber daya komputer yang sifatnya minimum (biasanya hanya berupa
    operating system). Pengguna harus mengisi sendiri aplikasi untuk layanan
    yang dibutuhkan (database, web server, dll).
    Pengguna harus mengelola sendiri sistem yang disewa, misalnya untuk
    masalah keamanan, backup, scale (up/down).
    Layanan ini biasanya disebut dengan layanan Virtual Private Server (VPS).
    Contoh: aws, digitalocean, linode, vultr, opennebula, openstack, eucalyptus,
    kvm, vmware, virtualbox
  
  - Platform as a Service (PaaS)
    layanan pada PaaS satu tingkat lebih tinggi dibandingkan dengan IaaS. PaaS
    menyewakan akses terhadap platform untuk meng-hosting aplikasi. Jadi selain
    menyediakan operating system, network dan storage, PaaS juga sudah
    dikonfigurasikan untuk dapat menjalankan aplikasi dengan platform tertentu.
    Misalnya contoh yang paling umum adalah shared hosting. Provider menyediakan
    layanan untuk meng-host aplikasi web PHP dan juga menyediakan akses ke
    database (biasanya mysql), web server (apache) dan layanan email
    (exim/postfix).
    Dengan menggunakan layanan ini, user cuma perlu meng-upload aplikasi yang
    ingin dihosting ke provider.

    Shared Hosting
    Shared hosting sudah dimulai sebelum adanya istilah PaaS. Layanan shared
    hosting dimungkinkan karena adanya teknologi virtual host yaitu provider
    dapat meng-host lebih dari satu domain pada web server yang sama.
    Kelemahan shared hosting adalah karena tenant menggunakan satu server
    bersama-sama dan virtualisasinya hanya pada pembedaan nama domain (secara
    cpu, memory, filesystem dan bahkan database tidak dipisahkan secara penuh)
    maka gangguan keamanan dan reliabilitas akan ber-efek pada semua tenant
    yang berada pada server yang sama.

    Sejak teknologi container mulai umum dikenal dan digunakan, PaaS menyediakan
    pembatasan yang lebih baik bagi masing-masing tenant. Seluruh tenant masih
    berbagi server yang sama tetapi secara cpu, memory, filesystem dan database
    sudah berdiri sendiri sehingga gangguan keamanan dan reliabilitas dari
    tenant lain dapat diminimalkan.

    Contoh: heroku, google app engine, azure, openshift

  IaaS dan PaaS bergantung pada teknologi virtualisasi untuk menyediakan
  layanannya.
  IaaS memanfaatkan teknologi virtualisasi hardware seperti KVM, VMware, Xen,
  virtualbox.
  Virtualisasi hardware memungkinkan 1 server fisik dibagi penggunaannya untuk
  menjalankan beberapa server virtual yang memiliki resource mandiri (cpu,
  memory, storage) dan dapat menjalankan operating system yang berbeda-beda.

  PaaS memanfaatkan teknologi container atau operating system virtualization.
  Teknologi container mempartisi resource sistem (cpu, memory, storage) untuk
  digunakan oleh masing-masing container. Hanya pada jenis virtualisasi ini,
  operating system yang digunakan adalah sistem operasi yang terpasang pada
  sistem (tidak dapat menggunakan sistem operasi yang berbeda).
  Contoh teknologi container: docker, rkt, lxd

  Secara kapasitas teknologi container membutuhkan kapasitas yang lebih
  kecil dibandingkan dengan Hardware Virtualization sehingga dapat menjalankan
  banyak container dibandingkan dengan hardware virtualization.

  - Software as a Service (SaaS)
    SaaS merupakan layanan yang paling populer digunakan. Istilah yang paling
    sering terdengar adalah cloud (computing).
    Pada layanan ini, provider menyediakan akses terhadap aplikasi yang sudah
    siap pakai. Seluruh aplikasi web bisa dikategorikan sebagai SaaS.

    Layanan ini merupakan tingkat layanan yang paling tinggi dan bisa dibilang
    paling kompleks. Provider harus memiliki infrastruktur (sendiri atau menyewa
    dari pihak ketiga), membangun aplikasi dan harus dapat men-scale sistemnya
    untuk melayani jumlah pengguna yang banyak dan tersebar.

    Contoh yang paling populer: gmail, dropbox, facebook, youtube, instagram,
    whatsapp, telegram, MMORPG, ecommerce, internet banking, dll.

- berbagi kemampuan komputasi (yang idle) oleh pengguna komputer yang terhubung
  ke Internet
  Pengguna memberikan akses kepada sistem yang terhubung pada jaringan yang sama
  untuk menggunakan resourcenya.
  Contoh: bittorrent, folding@home, seti@home, zombie host, blockchain?


## Tantangan dan Masalah Sistem Terdistribusi

Beberapa tantangan dan masalah yang dihadapi oleh sistem terdistribusi:

1. Koordinasi

   Bagaimana node autonomous dapat berkoordinasi mengenai task yang perlu
   diselesaikan? Bagaimana dengan akses terhadap shared resource?
   Bagaimana bila terjadi kegagalan pada satu/sebagian dari node?
   Bagaimana dengan masalah waktu (timestamp)?

2. Komunikasi

   Bagaimana pengguna dapat mengakses layanan pada sistem terdistribusi
   tanpa perlu mengetahui node-node yang membentuk sistem tersebut
   Bagaimana node bisa saling berkomunikasi baik dalam hal koordinasi
   ataupun dalam hal mengirimkan request dan menerima response dari hasil
   pekerjaan.

3. Keamanan

   Bagaimana masing-masing node dapat menjaga keamanan dalam komunikasi
   dengan node lainnya?

## [Distributed Computing Fallacies](https://en.wikipedia.org/wiki/Fallacies_of_distributed_computing)

Beberapa asumsi salah yang sering menyebabkan kegagalan proyek sistem terdistribusi:
  * The network is reliable.
  * Latency is zero.
  * Bandwidth is infinite.
  * The network is secure.
  * Topology doesn't change.
  * There is one administrator.
  * Transport cost is zero.
  * The network is homogeneous.
