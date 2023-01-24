CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table customers (
    id SERIAL primary key,
    public_id uuid DEFAULT uuid_generate_v4 (),
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    postal_address text,
    email_address varchar(100),
    phone_number varchar(30)
);

insert into customers(first_name,last_name,postal_address,email_address,phone_number) 
values('Bella','Rusden','50 Saggers Road NAIRIBIN WA 6350','BellaRusden@mailer.com.au', '+61890458058') ;

insert into customers(first_name,last_name,postal_address,email_address,phone_number)
values('Madeline','Namatjira','81 Thule Drive FORDS SA 5373','MadelineNamatjira@mailer.com.au', '+61883101682') ;

insert into customers(first_name,last_name,postal_address,email_address,phone_number)
values('Gabrielle','Cussen','24 Ghost Hill Road MOUNTAIN LAGOON NSW 2758','GabrielleCussen@mailer.com.au', '+61247075048') ;

insert into customers(first_name,last_name,postal_address,email_address,phone_number)
values('Zachary','Goold','37 Tooraweenah Road FORBES NSW 2871','ZacharyGoold@mailer.com.au', '+612940536700') ;
