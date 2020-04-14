create table seller
(
	id int auto_increment,
	user_id int null,
	name varchar(50) null,
	type varchar(20) null,
	street varchar(50) null,
	city varchar(30) null,
	state varchar(30) null,
	country varchar(30) null,
	description text null,
	created_at bigint null,
	updated_at bigint null,
	constraint seller_pk
		primary key (id)
);
