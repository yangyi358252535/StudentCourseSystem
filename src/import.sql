--数据库名   students_course_system

--权限信息
insert into t_authandsourceinfo values(1,'教师信息维护',1,'系统信息管理','../system/teacher/toMain.action');
insert into t_authandsourceinfo values(2,'班级信息维护',1,'系统信息管理','../system/clazz/toMain.action');
insert into t_authandsourceinfo values(3,'学院信息维护',1,'系统信息管理','../system/supervise/toMain.action');
insert into t_authandsourceinfo values(4,'专业信息维护',1,'系统信息管理','../system/specialty/toMain.action');
insert into t_authandsourceinfo values(5,'课程信息管理',1,'系统信息管理','../business/course/toMain.action');
insert into t_authandsourceinfo values(6,'学生信息管理',1,'系统信息管理','../business/student/toMain.action');

insert into t_authandsourceinfo values(7,'学生成绩管理',2,'业务管理','../business/mylecture/toMain.action');

insert into t_authandsourceinfo values(8,'我的成绩管理',3,'我的信息管理','../supervise/lecture/toMain.action');


insert into T_MANAGER values(1,0,'张三','admin','admin','15242686603');
