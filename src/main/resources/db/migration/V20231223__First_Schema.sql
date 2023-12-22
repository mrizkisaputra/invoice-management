create table invoice
(
    id varchar(36),
    primary key (id)
);

create table invoice_types
(
    id varchar(36),
    primary key (id)
);

create table payments
(
    id varchar(36),
    primary key (id)
);

create table payment_providers
(
    id varchar(36),
    primary key (id)
);

create table virtual_account
(
    id varchar(36),
    primary key (id)
);
