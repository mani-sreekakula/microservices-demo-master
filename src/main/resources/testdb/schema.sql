drop table T_PRODUCT if exists;

create table T_PRODUCT (ID bigint identity primary key, 
                        NAME varchar(50) not null, PRODUCT_TYPE varchar(50) not null, PRICE decimal(8,2));

ALTER TABLE T_PRODUCT ALTER COLUMN PRICE SET DEFAULT 0.0;