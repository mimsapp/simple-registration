create table mimsapp_user (
    id bigint(20) not null auto_increment,
    mobile_number varchar(12) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    date_of_birth char(10),
    gender char(1),
    email varchar(100) not null,
    primary key(id)
)