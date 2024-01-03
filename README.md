# Aplikasi Invoice Management

Aplikasi ini dipakai untuk mengelola invoice dan menyambungkan dengan berbagai metode pembayaran. Diantara metode pembayaran yang akan di support antara lain:

### Virtual Account Bank
    -   Bank BNI
    -   Bank CIMB
    -   Bank BSI
### E-wallet
    -   OVO
    -   GOPAY
### QR Payment
    -   QRIS

### Tipe tagihan yang tersedia:

    * CLOSED : bayar sesuai nominal. Kalau tidak sesuai, ditolak
    * OPEN : pembayaran berapapun diterima
    * INSTALLMENT : pembayaran diterima selama total akumulasi lebih kecil atau sama dengan nilai tagihan

Fitur Aplikasi

* Manajemen Debitur
    * Registrasi Debitur
    * Rekap Tagihan Debitur
    * Histori Pembayaran
* Manajemen Invoice
    * Membuat Invoice
    * Mengganti nilai dan tanggal jatuh tempu
    * Membatalkan invoice

## Cara Setup Database #
```shell
    docker run --rm \
	--name invoice-db \
	-p 5433:5432 \
	-e POSTGRES_USER=invoice \
	-e POSTGRES_PASSWORD=2llXwU0QTR1naUOFQQEnKPIO3M43KMfl \
	-e POSTGRES_DB=invoicedb \
	-e PGDATA=/var/lib/postgresql/data/pgdata \
	-v "$PWD/invoicedb:/var/lib/postgresql/data" \
	postgres:16
```