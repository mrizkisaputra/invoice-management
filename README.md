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

## Cara Setup Database #
```shell
    docker run --rm \
	--name invoice-db \
	-p 5433:5432 \
	-e POSTGRES_USER=invoice \
	-e POSTGRES_PASSWORD=2llXwU0QTR1naUOFQQEnKPIO3M43KMfl \
	-e POSTGRES_DB=invoicedb \
	-e PGDATA=/var/lib/postgresql/data/pgdata \
	-v "$PWD/invoicedb-data:/var/lib/postgresql/data" \
	postgres:16
```