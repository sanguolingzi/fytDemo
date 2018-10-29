#用户表
create table userinfo(
	#用户id 主键
	user_id int PRIMARY key auto_increment comment '用户id 主键',
	#用户名字 唯一
	user_name VARCHAR(20) unique comment '用户名字 唯一',
	#用户密码
	user_password VARCHAR(20) comment '用户密码',
	#用户手机号 唯一
	user_phone int(15) unique comment '用户手机号 唯一',
	#用户邮箱 唯一
	user_mail VARCHAR(24) unique comment '用户邮箱 唯一',
	#用户删除状态
	user_delete int(10) comment '用户删除状态'
) comment '用户表'

#用户角色关系
create table userrelation(
	#用户id
	user_id int(10) comment '用户id',
	#角色id
	role_id int(10) comment '角色id'
) comment '用户角色关系'

#菜单表
create table menu(
	#菜单id 主键
	menu_id int primary key auto_increment comment '菜单id 主键',
	#菜单名称 唯一
	menu_name VARCHAR(30) unique comment '菜单名称 唯一',
	#上级菜单id
	menu_lastid int(10) comment '上级菜单id',
	#上级菜单名称
	menu_lastname VARCHAR(30) comment '上级菜单名称',
	#菜单链接地址
	menu_location VARCHAR(30) comment '菜单链接地址',
	#菜单状态
	menu_state VARCHAR(10) comment '菜单状态',
	#菜单删除状态
	menu_delete int(10) comment '菜单删除状态'
) comment '菜单表'

#权限表
create table title(
	#权限id 主键
	title_id int primary key auto_increment comment '权限id 主键',
	#权限名称 唯一
	title_name varchar(30) unique comment '权限名称 唯一',
	#权限状态
	title_state varchar(10) comment '权限状态',
	#权限删除状态
	title_delete int(10) comment '权限删除状态'
) comment '权限表'

#权限关系表
create table titlerelation(
	#权限id
	title_id int(10) comment '权限id',
	#菜单id
	menu_id int(10) comment '菜单id'
) comment '权限关系表'

#角色表
create table role(
	#角色id 主键
	role_id int primary key auto_increment comment '角色id 主键',
	#角色名称 唯一
	role_name varchar(30) unique comment '角色名称 唯一',
	#角色状态
	role_state varchar(10) comment '角色状态',
	#角色删除状态
	role_delete int(10) comment '角色删除状态'
) comment '角色表'

#角色关系表
create table rolerelation(
	#角色id
	role_id int(10) comment '角色id',
	#权限id
	title_id int(10) comment '权限id'
) comment '角色关系表'