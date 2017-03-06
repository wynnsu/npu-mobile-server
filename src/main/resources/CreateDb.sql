GRANT ALL PRIVILEGES ON npumobiledb.* TO 'npumobileuser'@'localhost' IDENTIFIED BY 'spring' WITH GRANT OPTION;
SHOW GRANTS FOR 'npumobileuser'@'localhost';

CREATE SCHEMA IF NOT EXISTS `npumobiledb` ;
USE `npumobiledb` ;
drop table if exists enroll;
drop table if exists prerequisite;
drop table if exists tagging;
drop table if exists course;
drop table if exists student;
drop table if exists academic_event;
drop table if exists news;
drop table if exists faculty;
drop table if exists department;
drop table if exists classroom;
drop table if exists building;
drop table if exists tag;

create table academic_event (
    id integer not null auto_increment,
    event_date date,
    content varchar(255),
    primary key (id)
);

create table tag (
    id integer not null auto_increment,
    title varchar(255),
    primary key (id)
);
 
create table news (
    id integer not null auto_increment,
    imageUrl varchar(255),
    title varchar(255),
    content varchar(255),
    primary key (id)
);

create table faculty (
    id integer not null auto_increment,
    name varchar(255),
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

create table classroom(
    id integer not null auto_increment,
    room_number varchar(255),
    building_id integer,
    primary key (id)
);

create table course(
    id integer not null auto_increment,
    course_number varchar(255),
    is_online varchar(255),
    title varchar(255),
    credits double precision,
    instructor_id integer,
    department_id integer,
    classroom_id integer,
    time varchar(255),
    week integer,
    primary key (id)
);

create table student(
	id varchar(50) not null,
	password varchar(255),
	name varchar(255),
	email varchar(255),
	address varchar(255),
	program varchar(255),
	cgpa double,
	unit_progress double,
	unit_total double,
	primary key (id)
);

create table enroll(
	id integer not null auto_increment,
	student_id varchar(50),
	course_id integer,
	primary key (id)
);

create table prerequisite(
    id integer not null auto_increment,
    prerequisite_course_id integer,
    advanced_course_id integer,
    primary key (id)
);

create table tagging (    
    id integer not null auto_increment,
    tag_id integer,
    faculty_id integer,
    primary key (id)
);

create table crendential (
	id varchar(50) not null,
	password varchar(255),
	primary key (id)
);

alter table tagging 
add constraint FK_tagging_tag 
foreign key (tag_id) 
references tag (id);

alter table tagging 
add constraint FK_tagging_faculty 
foreign key (faculty_id) 
references faculty (id);
 
alter table prerequisite 
add constraint FK_prerequisite_pre 
foreign key (prerequisite_course_id) 
references course (id);

alter table prerequisite 
add constraint FK_prerequisite_adv 
foreign key (advanced_course_id) 
references course (id);

alter table enroll 
add constraint FK_enroll_course
foreign key (course_id)
references course (id);

alter table enroll
add constraint FK_enroll_student
foreign key (student_id)
references student (id);
 
alter table course 
add constraint FK_course_department 
foreign key (department_id) 
references department (id);

alter table course 
add constraint FK_course_faculty
foreign key (instructor_id) 
references faculty (id);

alter table course 
add constraint FK_course_classroom 
foreign key (classroom_id) 
references classroom (id);

alter table classroom 
add constraint FK_classroom_building
foreign key (building_id) 
references building (id);