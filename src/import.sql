--数据库名   students_course_system

--权限信息institute
insert into t_authandsourceinfo values(1,'教师信息维护',1,'系统信息管理','../system/teacher/toMain.action');
insert into t_authandsourceinfo values(2,'班级信息维护',1,'系统信息管理','../system/clazz/toMain.action');
insert into t_authandsourceinfo values(3,'学院信息维护',1,'系统信息管理','../system/institute/toMain.action');
insert into t_authandsourceinfo values(4,'专业信息维护',1,'系统信息管理','../system/specialty/toMain.action');
insert into t_authandsourceinfo values(5,'课程信息管理',1,'系统信息管理','../system/course/toMain.action');
insert into t_authandsourceinfo values(6,'学生信息管理',1,'系统信息管理','../system/student/toMain.action');

insert into t_authandsourceinfo values(7,'学生成绩管理',2,'教务管理','../business/score/toMain.action');

insert into t_authandsourceinfo values(8,'我的成绩管理',3,'我的信息管理','../myinfo/score/toMain.action');


insert into T_MANAGER values(1,'张三','admin','admin','15242686603');
insert into t_master values(14,'003','','上半学期');
insert into t_master values(15,'003','','下半学期');
