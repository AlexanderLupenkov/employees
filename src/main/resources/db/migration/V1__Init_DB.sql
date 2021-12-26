create table organization
(
    id   integer auto_increment,
    name varchar(50) not null,
    primary key (id)
);

create table status
(
    id   integer auto_increment,
    name varchar(20),
    primary key (id)
);

create table employees
(
    id             integer auto_increment,
    firstName      varchar(30) not null,
    lastName       varchar(30) not null,
    email          varchar(40) not null,
    organizationId integer,
    statusId integer,
    primary key (id),
    foreign key (organizationId) references organization (id),
    foreign key (statusId) references status(id)
);
