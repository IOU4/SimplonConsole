create table if not exists administrators (
	id serial primary key not null,
	name text not null,
	email text not null
);

create table if not exists instructors (
	id serial primary key,
	name text not null,
	email text not null
);

create table if not exists promos  (
	id serial primary key,
	instructor_id int
);

create table if not exists students (
	id serial primary key,
	name text not null,
	email text not null,
	promo_id int
);

create table if not exists briefs (
	id serial primary key,
	description text,
	launch_date date not null,
	deadline date not null,
	promo_id int not null
);
