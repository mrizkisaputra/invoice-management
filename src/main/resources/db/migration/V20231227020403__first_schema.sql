CREATE TABLE running_number
(
    id character varying(255) NOT NULL,
    prefix character varying(100) NOT NULL ,
    last_number bigint NOT NULL
);

CREATE TABLE customers
(
    id                 character varying(255) NOT NULL,
    created_by         character varying(255),
    created_date       timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    modified_by        character varying(255),
    status_record      character varying(255) NOT NULL,
    code               character varying(100) NOT NULL,
    name               character varying(255) NOT NULL,
    email              character varying(100) NOT NULL,
    mobile_phone       character varying(13)  NOT NULL
);

CREATE TABLE invoice_type
(
    id                 character varying(255) NOT NULL,
    created_by         character varying(255),
    created_date       timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    modified_by        character varying(255),
    status_record      character varying(255) NOT NULL,
    code               character varying(100) NOT NULL,
    description        oid                    NOT NULL,
    name               character varying(255) NOT NULL,
    CONSTRAINT invoice_type_status_record_check CHECK (((status_record)::text = ANY
        ((ARRAY ['ACTIVE':: character varying, 'INACTIVE':: character varying])::text[])
) )
);

CREATE TABLE invoices
(
    id                 character varying(255)      NOT NULL,
    created_by         character varying(255),
    created_date       timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    modified_by        character varying(255),
    status_record      character varying(255)      NOT NULL,
    amount             numeric(38, 2)              NOT NULL,
    description        oid                         NOT NULL,
    total_payment      numeric(38, 2)              NOT NULL,
    payment_status     varchar(50)                 NOT NULL,
    due_date           timestamp(6) with time zone NOT NULL,
    invoice_number     character varying(100)      NOT NULL,
    paid               boolean                     NOT NULL,
    id_invoice_type    character varying(255),
    id_customer        character varying(255)      NOT NULL
        CONSTRAINT invoices_amount_check CHECK ((amount >= (0)::numeric)),
    CONSTRAINT invoices_status_record_check CHECK (((status_record)::text = ANY
        ((ARRAY ['ACTIVE':: character varying, 'INACTIVE':: character varying])::text[])
) )
);

CREATE TABLE payment_providers
(
    id                 character varying(255) NOT NULL,
    created_by         character varying(255),
    created_date       timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    modified_by        character varying(255),
    status_record      character varying(255) NOT NULL,
    code               character varying(100) NOT NULL,
    logo               character varying(255),
    name               character varying(255) NOT NULL,
    CONSTRAINT payment_providers_status_record_check CHECK (((status_record)::text = ANY
        ((ARRAY ['ACTIVE':: character varying, 'INACTIVE':: character varying])::text[])
) )
);

CREATE TABLE invoice_type_providers
(
    id_invoice_type     character varying(255) NOT NULL,
    id_payment_provider character varying(255) NOT NULL
);

CREATE TABLE payments
(
    id                 character varying(255) NOT NULL,
    created_by         character varying(255),
    created_date       timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    modified_by        character varying(255),
    status_record      character varying(255) NOT NULL,
    amount             numeric(38, 2)         NOT NULL,
    provider_reference character varying(255) NOT NULL,
    transaction_time   timestamp(6) without time zone NOT NULL,
    id_virtual_account character varying(255) NOT NULL,
    CONSTRAINT payments_amount_check CHECK ((amount >= (1)::numeric)),
    CONSTRAINT payments_status_record_check CHECK (((status_record)::text = ANY
        ((ARRAY ['ACTIVE':: character varying, 'INACTIVE':: character varying])::text[])
) )
);

CREATE TABLE virtual_accounts
(
    id                   character varying(255) NOT NULL,
    created_by           character varying(255),
    created_date         timestamp(6) with time zone,
    last_modified_date   timestamp(6) with time zone,
    modified_by          character varying(255),
    status_record        character varying(255) NOT NULL,
    account_number       character varying(100) NOT NULL,
    company_id           character varying(255) NOT NULL,
    virtual_account_type character varying(255) NOT NULL,
    id_invoice           character varying(255) NOT NULL,
    id_payment_provider  character varying(255) NOT NULL,
    CONSTRAINT virtual_accounts_status_record_check CHECK (((status_record)::text = ANY
        ((ARRAY ['ACTIVE':: character varying, 'INACTIVE':: character varying])::text[])
) ),
    CONSTRAINT virtual_accounts_virtual_account_type_check CHECK (((virtual_account_type)::text = ANY
                                                                   ((ARRAY ['CLOSED'::character varying, 'OPEN'::character varying, 'INSTALLMENT'::character varying])::text[])))
);

-- PRIMARY KEY
ALTER TABLE ONLY running_number
    ADD CONSTRAINT running_number_pkey PRIMARY KEY (id);

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice_type
    ADD CONSTRAINT invoice_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoices
    ADD CONSTRAINT invoices_pkey PRIMARY KEY (id);

ALTER TABLE ONLY payment_providers
    ADD CONSTRAINT payment_providers_pkey PRIMARY KEY (id);

ALTER TABLE ONLY invoice_type_providers
    ADD CONSTRAINT invoice_type_providers_pkey PRIMARY KEY (id_invoice_type, id_payment_provider);

ALTER TABLE ONLY payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (id);

ALTER TABLE ONLY virtual_accounts
    ADD CONSTRAINT virtual_accounts_pkey PRIMARY KEY (id);

-- FOREIGN KEY

ALTER TABLE ONLY invoice_type_providers
    ADD CONSTRAINT invoice_type_providers_id_invoice_type_fk FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);

ALTER TABLE ONLY invoice_type_providers
    ADD CONSTRAINT invoice_type_providers_id_payment_provider_fk FOREIGN KEY (id_payment_provider) REFERENCES payment_providers(id);

ALTER TABLE ONLY virtual_accounts
    ADD CONSTRAINT fk9bi5jelarxfenempwevldyyx9 FOREIGN KEY (id_invoice) REFERENCES invoices(id);

ALTER TABLE ONLY invoices
    ADD CONSTRAINT fk9osnwpw345keg05e0la8m1e8t FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);

ALTER TABLE ONLY invoices
    ADD CONSTRAINT invoices_id_customer_fk FOREIGN KEY (id_customer) REFERENCES customers(id);


ALTER TABLE ONLY virtual_accounts
    ADD CONSTRAINT fkath8tjwus4ghigpsdfioar7pn FOREIGN KEY (id_payment_provider) REFERENCES payment_providers(id);

ALTER TABLE ONLY payments
    ADD CONSTRAINT fkmw5ut086gopp2tqrraj3dbgsp FOREIGN KEY (id_virtual_account) REFERENCES virtual_accounts(id);

