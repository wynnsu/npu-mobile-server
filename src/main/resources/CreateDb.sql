GRANT ALL PRIVILEGES ON npumobiledb.* TO 'npumobileuser'@'localhost' IDENTIFIED BY 'spring' WITH GRANT OPTION;
SHOW GRANTS FOR 'npumobileuser'@'localhost';

CREATE SCHEMA IF NOT EXISTS `npumobiledb` ;
USE `npumobiledb` ;
drop table if exists activity;
drop table if exists studentcourse;
drop table if exists tagging;
drop table if exists academic_event;
drop table if exists news;
drop table if exists tag;
drop table if exists course;
drop table if exists semester;
drop table if exists faculty;
drop table if exists department;
drop table if exists classroom;
drop table if exists building;
drop table if exists student;

create table activity(
	id integer not null auto_increment,
	week integer,
	due timestamp,
	pastdue integer,
	allowlate integer,
	title varchar(255),
	points double,
	total double,
	submitted integer,
	submit_time timestamp,
	stucourse_id int,
	primary key (id)
);

create table academic_event (
    id integer not null auto_increment,
    event_date date,
    content varchar(255),
    primary key (id)
);

create table semester(
	name varchar(50) not null,
	start date,
	end date,
	primary key (name)
);
 
create table news (
    id integer not null auto_increment,
    imageUrl varchar(255),
    title varchar(255),
    content varchar(255),
    primary key (id)
);

create table department (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)
);

create table building (
    id integer not null auto_increment,
    name varchar(255),
    address varchar(255),
    link varchar(255),
    map varchar(255),
    primary key (id)
);

create table course(
    id integer not null auto_increment,
    course_number varchar(50) not null,
    is_online varchar(255),
    title varchar(255),
    credits double precision default 0,
    instructor varchar(255),
    classroom varchar(255),
    time varchar(255),
    prerequisite varchar(255),
    semester varchar(50),
    primary key (id)
);

create table student(
	id varchar(50) not null,
	password varchar(255),
	name varchar(255),
	email varchar(255),
	address varchar(255),
	program varchar(255),
	cgpa double default 0,
	unit_progress double default 0,
	unit_total double default 0,
	primary key (id)
);

create table studentcourse(
	id integer not null auto_increment,
	student_id varchar(50) not null,
	course_id integer not null,
	attendance varchar(255),
	primary key (id)
);