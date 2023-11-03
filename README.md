# PitjarusTest
Aplikasi untuk tes di Pitjarus Teknologi

## Demo

https://github.com/verindrarizya/PitjarusTest/assets/65704224/6eb26d7c-8ee4-447f-921e-b0c5ac08011a

** Emulator menggunakan Pixel 7 API 30 dengan Play Store Supported
** Untuk demo, pada emulator saya sudah set lokasi dalam radius 100M

## Requirements
Buat 5 halaman activity sesuai mockup berikut
Flow aplikasi
Login->Main Menu->Kunjungan->List Toko->Toko Detail
->Visit->Selesai->List Toko->Toko berikutnya

1. Untuk Login wajib online
dengan api berikut
http://dev.pitjarus.co/api/sariroti_md/index.php/login/loginTest
POST
username pitjarus
password admin

2. Di halaman store list maps wajib menggunakan api maps

3. Sebelum visit wajib foto dan tagging lokasi terlebih dahulu
apabila radius dari kita kurang dari 100m maka boleh visit
jika tidak tampilkan informasi jarak terlalu jauh

4. toko yang sudah selesai visit wajib tercentang

5. data toko yg didapat di login wajib disimpan di database local dan ditampilkan di list toko
