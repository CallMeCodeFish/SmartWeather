create table advertisement
(
	id int auto_increment,
	title varchar(100) null,
	description text null,
	seller_id int null,
	seller_city varchar(30) null,
	created_at bigint null,
	updated_at bigint null,
	constraint advertisement_pk
		primary key (id)
);
