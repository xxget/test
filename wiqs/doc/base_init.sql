/* 初始化 [根组织] */
insert  into `org`(`org_id`,`org_no`,`org_name`,`org_pid`,`org_type`,`contant`) values 
('15d54261a0384977ab42d746f9264da9','0','根组织','0','001','');

/* 根组织下初始化[超级管理员] manager */
insert  into `user_info`(`user_id`,`org_id`,`user_name`,`real_name`,`password`,`address`,`salt`,`locked`) values 
('3ff1568ca297463287d27006f590bb4c','15d54261a0384977ab42d746f9264da9','manager','初始系统管理员','47ce2505cc89790baf66993a3f84af7e','13593133176','8516a292bfba7836908b56aa725e448e','0');

/* 角色表，增加[管理员]角色　 */
insert  into `role`(`role_id`,`role_name`,`role_is_lock`) values 
('bf64367e11964f6fa17313c7dbb5f78b','管理员','0');

/* 用户角色映射表，为 [超级管理员]manger赋[管理员]角色 */
insert  into `user_role`(`id`,`user_id`,`role_id`) values 
('8516a292bfba7836908b56aa725e448e','3ff1568ca297463287d27006f590bb4c','bf64367e11964f6fa17313c7dbb5f78b');

/*　权限表， [基础管理]权限列表 `permission` */
INSERT  INTO `permission`
(`per_id`,`per_name`,`per_type`,`per_url`,`percode`,`parentid`,`parentids`,`sortstring`) VALUES 
('652f59b40d9448e38e37757cc813606d','机构信息管理','menu','/org/manage.do','base:org:*','ffbdbb8965f841aea7d2829b428ce177',NULL,'001'),
('83683d00564a4f919ea41cc4bf171c34','用户信息管理','menu','/user/manage.do','base:user:*','ffbdbb8965f841aea7d2829b428ce177',NULL,'002'),
('8b5e4fc2c6b44282985f10d44348dae9','字典管理','menu','/dict/manage.do','base:dict:*','ffbdbb8965f841aea7d2829b428ce177',NULL,'600'),
('9d6e9c0420d249b88bcdfc5518a60441','角色权限管理','menu','/role/manage.do','base:role:*','ffbdbb8965f841aea7d2829b428ce177',NULL,'003'),
('f359d71fa06e4f0eb290fb471923fe70','资源权限管理','menu','/permission/manage.do','base:permission:*','ffbdbb8965f841aea7d2829b428ce177',NULL,'400'),
('ffbdbb8965f841aea7d2829b428ce177','基础信息管理','menu','','','0',NULL,'000');


/* 角色权限映射表， [基础管理]权限赋与[管理员]角色 */
insert  into `role_permission`(`id`,`role_id`,`per_id`) values 
('30ccbaad8f4d4aaebd2e98d69ee95aab','bf64367e11964f6fa17313c7dbb5f78b','83683d00564a4f919ea41cc4bf171c34'),
('893360dae6204ee49e265fa1f6525673','bf64367e11964f6fa17313c7dbb5f78b','8b5e4fc2c6b44282985f10d44348dae9'),
('dffb4c3a21d44de9b0486e5b593b3717','bf64367e11964f6fa17313c7dbb5f78b','f359d71fa06e4f0eb290fb471923fe70'),
('e0a9f1750d8a4e8185fce4ef6c2a1c40','bf64367e11964f6fa17313c7dbb5f78b','9d6e9c0420d249b88bcdfc5518a60441'),
('e546e6e80c834418b610c2c288b3589b','bf64367e11964f6fa17313c7dbb5f78b','652f59b40d9448e38e37757cc813606d'),
('e5e5c3727bb146119d9def18274a86a7','bf64367e11964f6fa17313c7dbb5f78b','ffbdbb8965f841aea7d2829b428ce177');

/* 字典表 */
insert  into `dict`(`dict_id`,`dict_type`,`dict_dkey`,`dict_value`,`text`) values 
('1944b786e35443509488bbf41e423ce4','locktype','0','未锁定','未锁定'),
('4e224f9749c94859bad5d803d88d0f8c','orgtype','001','总部','总部'),
('56d6ddf733a64791b875e67fdd698d8e','locktype','1','锁定','锁定'),
('9427d655904c460783fdb52a18c51179','orgtype','002','部室','部室');

`org`