create sequence employeeid_generator start 1 increment 1;
create sequence tripid_generator start 1 increment 1;

    create table trip (
       tripid int8 not null,
        destination varchar(255),
        merchandiseid int8,
        trip_end_date timestamp,
        trip_start_date timestamp,
        employee_id int8 not null,
        primary key (tripid)
    );

    create table trip_stops (
       stopid  bigserial not null,
        description varchar(255),
        stop_address varchar(255),
        tripid int8,
        primary key (stopid)
    );

    create table users (
       employee_id int8 not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        salary float4,
        user_name varchar(255),
        primary key (employee_id)
    );

    alter table trip 
       add constraint FKktb0yf0be4e93e8thr1et8x1g 
       foreign key (employee_id) 
       references users;
