/*Building*/
INSERT INTO `npumobiledb`.`building` (`id`, `name`, `address`, `link`) 
VALUES ('1', 'South Building', '47655 Warm Springs Blvd, Fremont, CA 94539', 'https://www.google.com/maps/place/47655+Warm+Springs+Blvd,+Fremont,+CA+94539/data=!4m2!3m1!1s0x808fc615f035c303:0x70cf1db2845c0cc2?sa=X&ei=F121VNOzOc_isASf5IC4AQ&ved=0CB0Q8gEwAA')
,('2', 'OEC Building', '47613 Warm Springs Blvd, Fremont, CA 94539', 'https://www.google.com/maps/place/47613+Warm+Springs+Blvd,+Fremont,+CA+94539/@37.4795097,-121.9240773,17z/data=!3m1!4b1!4m2!3m1!1s0x808fc61588e14fed:0xff24bbf56ef650bb')
,('3', 'East Building', '105 Fourier Ave, Fremont, CA 94539', 'https://www.google.com/maps/place/105+Fourier+Ave,+Fremont,+CA+94539/@37.4812368,-121.9249455,18z/data=!4m2!3m1!1s0x808fc63fc47971e1:0x47e55417cd43d1ba')
,('4', 'West Building', '117 Fourier Ave, Fremont, CA 94539', 'https://www.google.com/maps/place/117+Fourier+Ave,+Fremont,+CA+94539/@37.481344,-121.92562,18z/data=!3m1!4b1!4m2!3m1!1s0x808fc63fe95f5c99:0xf50e5cfc77e39185')
,('5', 'Admin Building', '47671 Westinghouse Dr, Fremont, CA 94539', 'https://www.google.com/maps/place/Northwestern+Polytechnic+University/@37.4782089,-121.9249059,18z/data=!4m7!1m4!3m3!1s0x808fc63fddbece71:0xe656f01c54db9ab4!2s47671+Westinghouse+Dr,+Fremont,+CA+94539!3b1!3m1!1s0x0000000000000000:0xd5ff57a2c424e7fb')
,('6', 'North Building - Lecture', '47102 Mission Falls Ct, Fremont, CA 94539', 'https://www.google.com/maps/place/47102+Mission+Falls+Ct,+Fremont,+CA+94539/@37.4864373,-121.9295849,15z/data=!4m2!3m1!1s0x808fc643fac72459:0xbf3da5d22ff2e353')
,('7', 'North Building - Laboratory 904', '47132 Mission Falls Ct, Fremont, CA 94539', 'https://www.google.com/maps/place/47132+Mission+Falls+Ct,+Fremont,+CA+94539/@37.485351,-121.9285557,17z/data=!3m1!4b1!4m2!3m1!1s0x808fc641695d7d43:0x643393282da554b2')
,('8', 'North Building - Laboratory 905', '47096 Mission Falls Ct, Fremont, CA 94539', 'https://www.google.com/maps/dir//47096+Mission+Falls+Ct,+Fremont,+CA+94539/@37.4856268,-121.9291308,18z/data=!4m8!4m7!1m0!1m5!1m1!1s0x808fc64141d5662b:0xcf1e2de3b661963f!2m2!1d-121.9291308!2d37.4856268');
/*Department*/
INSERT INTO `npumobiledb`.`department` (`id`,`name`)
VALUES ('1','School of Engineering')
,('2','School of Business & Information Technology')
,('3','General Studies')
,('4','Others');
/*Semester*/
INSERT INTO `npumobiledb`.`semester` (`name`,`start`,`end`)
VALUES('Spring 2017','2017-01-09','2017-04-23')
,('Summer 2017','2017-05-08','2017-08-22');
/*StudentCourse*/
INSERT INTO `npumobiledb`.`studentcourse` (`id`,`student_id`,`course_id`,`attendance`)
VALUES('7','15325sy','564','P P P P P P P P P P P P')
,('8','15325sy','557','P P A P P P P P P P P P')
/*Activity*/
INSERT INTO `npumobiledb`.`activity` (`week`, `due`, `pastdue`, `allowlate`, `title`, `points`, `total`, `submitted`, `submit_time`, `stucourse_id`) 
VALUES ('2', '2017-02-05 23:30:00', '1', '1', 'HW1', '10', '10', '1', '2017-01-31 22:14:55', '8')
,('4', '2017-02-23 23:30:00', '1', '1', 'HW2', '10', '10', '1', '2017-02-21 22:42:50', '8')
,('6', '2017-02-16 23:59:00', '1', '1', 'Quiz2', '5', '5', '1', '2017-02-16 21:12:00', '8')
,('8', '2017-03-02 21:00:00', '1', '0', 'Midterm', '20', '20', '1', '2017-03-02 19:21:08', '8')
,('9', '2017-03-16 23:30:00', '1', '1', 'HW3', '10', '10', '1', '2017-03-14 23:13:11', '8')
,('10', '2017-03-26 23:30:00', '1', '1', 'HW4', '10', '10', '1', '2017-03-26 16:29:09', '8')
,('11', '2017-03-23 21:00:00', '1', '0', 'Quiz3', '5', '5', '1', '2017-03-23 21:07:35', '8')
,('3', '2017-01-30 0:00:00', '1', '1', 'Proposal One Page', '100', '100', '1', '2017-01-28 11:41:49', '7')
,('3', '2017-01-30 0:00:00', '1', '1', 'Proposal PPT ', '100', '100', '1', '2017-01-28 11:41:26', '7')
,('5', '2017-02-14 23:30:00', '1', '1', 'Progress Report # 1', '100', '100', '1', '2017-02-08 1:19:47', '7')
,('7', '2017-02-28 23:30:00', '1', '1', 'Progress Report # 2', '100', '100', '1', '2017-02-21 21:05:18', '7')
,('11', '2017-03-28 23:30:00', '1', '1', 'Progress Report # 3', '0', '100', '1', '2017-03-26 5:56:16', '7')
,('13', '2017-04-9 23:30:00', '0', '1', 'Progress Report # 4', '0', '0', '0', NULL, '7');
