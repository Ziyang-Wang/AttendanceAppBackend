CREATE DATABASE IF NOT EXISTS `db_att` DEFAULT CHARACTER SET = utf8mb4;
USE `db_att`;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT 'full name',
  `email` varchar(255) DEFAULT 'example@gmail.com',
  `pid` varchar(255) DEFAULT NULL COMMENT 'personal identification code',
  `status` int(1)  DEFAULT '1' COMMENT '1 normal, 2 banned',
  `role` int(1)  DEFAULT 0 COMMENT '0 student, 1 teacher',
  `create_time` datetime DEFAULT NULL COMMENT 'user create time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_uni` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='system user info';
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (1, 'haha', '123', 'stu0', '33036', 1, 0, '2021-04-23 9:39:18');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (2, 'st1', '123', 'stu1', '33037', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (3, 'st2', '123', 'stu2', '33038', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (4, 'st3', '123', 'stu3', '33039', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (5, 'st4', '123', 'stu4', '33040', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (6, 'st5', '123', 'stu5', '33041', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (8, 'st7', '123', 'stu7', '33043', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (7, 'st6', '123', 'stu6', '33042', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (9, 'st8', '123', 'stu8', '33044', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (10, 'st9', '123', 'stu9', '33045', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (11, 'st10', '123', 'stu10', '33046', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (12, 'st11', '123', 'stu11', '33047', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (13, 'st12', '123', 'stu12', '33048', 1, 0, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (14, 'te1', '123', 'teacher1', '62020', 1, 1, '2021-04-23 9:39:23');
INSERT INTO `db_att`.`sys_user`(`id`, `username`, `password`, `name`, `pid`, `status`, `role`, `create_time`) VALUES (15, 'te2', '123', 'teacher2', '62021', 1, 1, '2021-04-23 9:39:23');


DROP TABLE IF EXISTS `student_class`;
CREATE TABLE `student_class` (
  `st_id` bigint(20) NOT NULL COMMENT 'student id(user id)',
  `class_id` bigint(20)  DEFAULT NULL COMMENT 'class id',
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='student class mapping, one student belongs to one class';
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (1, 1);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (2, 1);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (3, 1);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (4, 1);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (5, 1);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (6, 2);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (7, 2);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (8, 2);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (9, 2);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (10, 2);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (11, 2);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (12, 3);
INSERT INTO `db_att`.`student_class`(`st_id`, `class_id`) VALUES (13, 3);


DROP TABLE IF EXISTS `class_table`;
CREATE TABLE `class_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT 'class full name',
  `create_time` datetime DEFAULT NULL COMMENT 'class create time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='class info';
INSERT INTO `db_att`.`class_table`(`id`, `name`, `create_time`) VALUES (1, 'class1', '2021-04-23 11:41:31');
INSERT INTO `db_att`.`class_table`(`id`, `name`, `create_time`) VALUES (2, 'class2', '2021-04-23 11:41:43');
INSERT INTO `db_att`.`class_table`(`id`, `name`, `create_time`) VALUES (3, 'class3', '2021-04-23 11:41:57');



DROP TABLE IF EXISTS `teacher_class`;
CREATE TABLE `teacher_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `te_id` bigint(20) NOT NULL COMMENT 'teacher id (user id)',
  `class_id` bigint(20)  NOT NULL COMMENT 'class id the teacher teaches (additional attempt)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacher_class_uni` (`te_id`,`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='teacher class mapping, one teacher can teach many class';
INSERT INTO `db_att`.`teacher_class`(`id`, `te_id`, `class_id`) VALUES (1, 12, 1);
INSERT INTO `db_att`.`teacher_class`(`id`, `te_id`, `class_id`) VALUES (2, 13, 1);
INSERT INTO `db_att`.`teacher_class`(`id`, `te_id`, `class_id`) VALUES (3, 13, 2);



DROP TABLE IF EXISTS `st_att`;
CREATE TABLE `st_att` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `st_id` bigint(20) NOT NULL COMMENT 'student id (user id)',
  `class_id` bigint(20)  DEFAULT NULL COMMENT 'class the student belongs to',
  `att_mark` int(1) DEFAULT 1 COMMENT 'attendance mark, 1 for marked attendance',
  `create_time` datetime DEFAULT NULL COMMENT 'attendance marked time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='student attendance info';
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (1, 1, 1, 1, '2021-04-23 10:08:46');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (2, 2, 1, 1, '2021-04-23 10:08:50');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (5, 5, 1, 1, '2021-04-23 10:08:58');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (4, 4, 1, 1, '2021-04-23 10:08:54');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (6, 6, 2, 1, '2021-04-23 10:10:03');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (3, 3, 1, 1, '2021-04-23 10:08:52');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (7, 7, 2, 2, '2021-04-23 10:10:07');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (8, 8, 2, 2, '2021-04-23 10:10:11');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (9, 9, 2, 1, '2021-04-23 10:10:16');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (10, 10, 2, 1, '2021-04-23 10:10:20');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (11, 11, 2, 1, '2021-04-23 10:10:23');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (12, 12, 3, 1, '2021-04-23 10:10:23');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (13, 13, 3, 1, '2021-04-23 10:10:23');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (14, 1, 1, 1, '2021-04-24 10:10:37');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (15, 2, 1, 1, '2021-04-24 10:10:50');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (16, 3, 1, 1, '2021-04-24 10:10:54');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (17, 6, 2, 1, '2021-04-24 10:11:05');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (18, 7, 2, 1, '2021-04-24 10:11:23');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (19, 8, 2, 1, '2021-04-24 10:11:26');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (20, 2, 1, 1, '2021-04-25 10:11:46');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (21, 3, 1, 1, '2021-04-25 10:12:01');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (22, 8, 2, 1, '2021-04-25 10:12:13');
INSERT INTO `db_att`.`st_att`(`id`, `st_id`, `class_id`, `att_mark`, `create_time`) VALUES (23, 9, 2, 1, '2021-04-25 10:12:19');



DROP TABLE IF EXISTS `class_att_history`;
CREATE TABLE `class_att_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_id` bigint(20)  DEFAULT NULL COMMENT 'class id',
  `total_marks` int(4)  DEFAULT 0 COMMENT 'total # of students mark att in the class',
  `total_students` int(4)  DEFAULT 0 COMMENT 'total # of students in the class',
  `att_rate` int(4) DEFAULT 0 COMMENT 'class attendance rate',
  `create_time` datetime DEFAULT NULL COMMENT 'class attendance create time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='class history attendance for future reference';

INSERT INTO `db_att`.`class_att_history`(`id`, `class_id`, `total_marks`, `total_students`, `att_rate`, `create_time`) VALUES (1, 1, 5, 5, 100, '2021-04-22 17:00:01');
INSERT INTO `db_att`.`class_att_history`(`id`, `class_id`, `total_marks`, `total_students`, `att_rate`, `create_time`) VALUES (2, 2, 6, 6, 100, '2021-04-22 17:00:01');
INSERT INTO `db_att`.`class_att_history`(`id`, `class_id`, `total_marks`, `total_students`, `att_rate`, `create_time`) VALUES (3, 1, 3, 5, 60, '2021-04-23 17:00:01');
INSERT INTO `db_att`.`class_att_history`(`id`, `class_id`, `total_marks`, `total_students`, `att_rate`, `create_time`) VALUES (4, 2, 3, 6, 50, '2021-04-23 17:00:01');
