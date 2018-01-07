
CREATE SEQUENCE ID_SEQ START WITH 1;

CREATE TABLE TIME_ZONE(
    ID bigint PRIMARY KEY,
    NAME VARCHAR NOT NULL);

CREATE TABLE SUBDIVISION_PU(
ID bigint PRIMARY KEY ,
NAME VARCHAR NOT NULL);

CREATE TABLE COMPANY(
id bigint PRIMARY KEY,
SHORT_NAME VARCHAR,
FULL_NAME VARCHAR,
LEGAL_ADRESS VARCHAR,
ACTUAL_ADRESS VARCHAR,
INN VARCHAR (12),
KPP VARCHAR (9),
FIAS VARCHAR,
PHONE VARCHAR (30),
EMAIL VARCHAR,
ACTUAL BOOLEAN,
VIP VARCHAR,
TIME_ZONE_ID BIGINT,
WORK_FROM time without time zone,
WORK_UNTIL time without time zone,
LUNCH_FROM TIME,
LUNCH_UNTIL TIME,
SUBDIVISION_DE_ID BIGINT,
PU_ID BIGINT,
NOTE VARCHAR);

alter table COMPANY add constraint fk_1 foreign key (TIME_ZONE_ID) REFERENCES TIME_ZONE (ID);
alter table COMPANY add constraint fk_2 foreign key (PU_ID) REFERENCES SUBDIVISION_PU (ID);

CREATE TABLE USER_TABLE(
id bigint PRIMARY KEY,
USER_NAME VARCHAR,
MOBILE_PHONE VARCHAR,
WORK_PHONE VARCHAR,
SKYPE VARCHAR,
ICQ VARCHAR,
POSITION VARCHAR,
COMPANY_ID BIGINT,
BLOCKING_CAUSE VARCHAR);

alter table USER_TABLE add constraint fk_3 foreign key (COMPANY_ID) REFERENCES COMPANY (ID);


create table contract_type(
id bigint PRIMARY KEY,
name VARCHAR );

CREATE TABLE contract(
id bigint PRIMARY KEY,
customer_company_id bigint,
contract_date DATE,
number VARCHAR,
pu_number VARCHAR,
status VARCHAR,
pu_project VARCHAR ,
subdivision_pu_id bigint,
valid_from date,
valid_until date,
is_valid boolean,
contract_type_id bigint
);

alter table contract add constraint fk_4 foreign key (customer_company_id) REFERENCES COMPANY (ID);
alter table contract add constraint fk_5 foreign key (subdivision_pu_id) REFERENCES subdivision_pu (ID);
alter table contract add constraint fk_6 foreign key (contract_type_id) REFERENCES contract_type (ID);

create table subsidiary(
id bigint PRIMARY KEY,
subsidiary_company_id bigint,
parent_company_id bigint
);

alter table subsidiary add constraint fk_7 foreign key (subsidiary_company_id) REFERENCES COMPANY (ID);
alter table subsidiary add constraint fk_8 foreign key (parent_company_id) REFERENCES COMPANY (ID);

create table customer_company(
id bigint PRIMARY KEY,
contract_id bigint,
subsidiary_id bigint
);

alter table customer_company add constraint fk_9 foreign key (contract_id) REFERENCES contract (ID);
alter table customer_company add constraint fk_10 foreign key (subsidiary_id) REFERENCES subsidiary (ID);

create table customer_company_user(
id bigint PRIMARY KEY,
customer_company_id bigint,
user_id bigint
);

alter table customer_company_user add constraint fk_11 foreign key (customer_company_id) REFERENCES customer_company (ID);
alter table customer_company_user add constraint fk_12 foreign key (user_id) REFERENCES user_table (ID);

create table product(
id bigint PRIMARY KEY,
name VARCHAR,
email VARCHAR
);

create table unit(
id bigint PRIMARY KEY,
name VARCHAR,
product_id bigint,
removedFromBalance boolean
);

alter table unit add constraint fk_13 foreign key (product_id) REFERENCES product (ID);

create table contract_subject(
id bigint PRIMARY KEY,
product_id bigint,
contract_id bigint
);

alter table contract_subject add constraint fk_14 foreign key (product_id) REFERENCES product (ID);
alter table contract_subject add constraint fk_15 foreign key (contract_id) REFERENCES contract (ID);

create table sold_unit(
id bigint PRIMARY KEY,
unit_id bigint,
contract_subject_id bigint
);

alter table sold_unit add constraint fk_16 foreign key (unit_id) REFERENCES unit (ID);
alter table sold_unit add constraint fk_17 foreign key (contract_subject_id) REFERENCES contract_subject (ID);

create table priority(
id bigint PRIMARY KEY,
name VARCHAR
);

create table service(
id bigint PRIMARY KEY,
name VARCHAR,
default_val VARCHAR
);


create table sla(
id bigint PRIMARY KEY,
customer_company_id bigint,
contract_subject_id bigint,
service_id bigint,
priority_id bigint,
sla_sec bigint
);

alter table sla add constraint fk_18 foreign key (customer_company_id) REFERENCES customer_company (ID);
alter table sla add constraint fk_19 foreign key (contract_subject_id) REFERENCES contract_subject (ID);
alter table sla add constraint fk_20 foreign key (service_id) REFERENCES service (ID);
alter table sla add constraint fk_21 foreign key (priority_id) REFERENCES priority (ID);
