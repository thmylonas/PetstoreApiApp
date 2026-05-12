-- Create Tables

create table if not exists Categories
(
    Id   serial primary key not null,
    Name varchar(255)
);

create table if not exists Pets
(
    Id          bigserial primary key not null,
    Name        varchar(255),
    Status      varchar(10),
    Category_Id integer,
    constraint check_pets_status
        check ((Status)::text = ANY
               ((ARRAY ['AVAILABLE'::character varying, 'PENDING'::character varying, 'SOLD'::character varying])::text[])),
    constraint fk_pets foreign key (Category_Id) references categories (Id)
);

create table if not exists Orders
(
    Id        bigserial primary key not null,
    Quantity  integer,
    Ship_Date timestamp(8),
    Status    varchar(10),
    Complete  boolean,
    Pet_Id    bigint,
    constraint check_orders_status
        check ((status)::text = ANY
               ((ARRAY ['PLACED'::character varying, 'APPROVED'::character varying, 'DELIVERED'::character varying])::text[])),
    constraint fk_tags foreign key (Pet_Id) references pets (Id)
);

create table if not exists Tags
(
    Id     bigserial primary key not null,
    Name   varchar(255),
    Pet_Id bigint,
    constraint fk_tags foreign key (Pet_Id) references pets (Id)
);

create table if not exists Photo_Urls
(
    Id     bigserial primary key not null,
    Name   varchar(255),
    Pet_Id bigint,
    constraint fk_photo_urls foreign key (Pet_Id) references pets (Id)
);

create table if not exists Users
(
    Id          bigserial primary key not null,
    Username    varchar(15),
    First_Name  varchar(255),
    Last_Name   varchar(255),
    Email       varchar(255),
    Password    varchar(255),
    Phone       varchar(9),
    User_Status smallint,
    constraint check_users_user_status
        check ((User_Status >= 0) AND (User_Status <= 1))
);

-- Rename Sequences

-- alter sequence categories_id_seq rename to Categories_Sequence;
-- alter sequence orders_id_seq rename to Orders_Sequence;
-- alter sequence pets_id_seq rename to Pets_Sequence;
-- alter sequence photo_urls_id_seq rename to Photo_Urls_Sequence;
-- alter sequence tags_id_seq rename to Tags_Sequence;
-- alter sequence users_id_seq rename to Users_Sequence;
