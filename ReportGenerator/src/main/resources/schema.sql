create table customerdetails (customer_id bigint not null, build_duration bigint, contract_id bigint, geozone varchar(255), project_code varchar(255), team_code varchar(255), primary key (customer_id))
create table user_info (id integer generated by default as identity, email varchar(255), name varchar(255), password varchar(255), roles varchar(255), primary key (id))