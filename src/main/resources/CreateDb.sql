GRANT ALL PRIVILEGES ON npumobiledb.* TO 'npumobileuser'@'localhost' IDENTIFIED BY 'spring' WITH GRANT OPTION;
SHOW GRANTS FOR 'npumobileuser'@'localhost';

CREATE SCHEMA IF NOT EXISTS `npumobiledb` ;
USE `npumobiledb` ;
drop table if exists studentcourse;
drop table if exists semester;
drop table if exists tagging;
drop table if exists academic_event;
drop table if exists news;
drop table if exists tag;
drop table if exists course;
drop table if exists faculty;
drop table if exists department;
drop table if exists classroom;
drop table if exists building;
drop table if exists student;

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
    id varchar(50) not null,
    is_online varchar(255),
    title varchar(255),
    credits double precision default 0,
    instructor varchar(255),
    department_id integer,
    classroom varchar(255),
    time varchar(255),
    prerequisite varchar(255),
    semester_name varchar(50),
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
	id integer not null,
	student_id varchar(50) not null,
	course_id varchar(50) not null,
	primary key (id)
);

alter table course 
add constraint FK_course_department 
foreign key (department_id) 
references department (id);

alter table course
add constraint FK_course_semester
foreign key (semester_name)
references semester(name);

alter table studentcourse
add constraint FK_studentcourse_student
foreign key (student_id)
references student (id);

alter table studentcourse
add constraint FK_studentcourse_course
foreign key (course_id)
references course (id);