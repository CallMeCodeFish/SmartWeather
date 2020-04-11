create table user
(
	id int auto_increment,
	name varchar(50) null,
	account_type int null,
	account_id varchar(50) null,
	avatar_url varchar(100) null,
	token char(36) null,
	created_at bigint null,
	updated_at bigint null,
	constraint user_pk
		primary key (id)
);