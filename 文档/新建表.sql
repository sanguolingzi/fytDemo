#用户表
create table userinfo(
	#用户id 主键
	user_id int PRIMARY key auto_increment,
	#用户名字 唯一
	user_name VARCHAR(20) unique,
	#用户密码
	user_password VARCHAR(20),
	#用户手机号 唯一
	user_phone int(15) unique,
	#用户邮箱 唯一
	user_mail VARCHAR(24) unique,
	#用户删除状态
	user_delete int(10)
)

#用户角色关系
create table userrelation(
	#用户id
	user_id int(10),
	#角色id
	role_id int(10)
)

#菜单表
create table menu(
	#菜单id 主键
	menu_id int primary key auto_increment,
	#菜单名称 唯一
	menu_name VARCHAR(30) unique,
	#上级菜单id
	menu_lastid int(10),
	#上级菜单名称
	menu_lastname VARCHAR(30),
	#菜单链接地址
	menu_location VARCHAR(30),
	#菜单状态
	menu_state VARCHAR(10),
	#菜单删除状态
	menu_delect int(10)
)

#权限表
create table title(
	#权限id 主键
	title_id int primary key auto_increment,
	#权限名称 唯一
	title_name varchar(30) unique,
	#权限状态
	title_state varchar(10),
	#权限删除状态
	title_delete int(10)
)

#权限关系表
create table titlerelation(
	#权限id
	title_id int(10),
	#菜单id
	menu_id int(10)
)

#角色表
create table role(
	#角色id 主键
	role_id int primary key auto_increment,
	#角色名称 唯一
	role_name varchar(30) unique,
	#角色状态
	role_state varchar(10),
	#角色删除状态
	role_delete int(10)
)

#角色关系表
create table rolerelation(
	#角色id
	role_id int(10),
	#权限id
	title_id int(10)
)