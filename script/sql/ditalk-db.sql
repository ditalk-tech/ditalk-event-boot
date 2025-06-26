/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `state` varchar(100) NOT NULL COMMENT '状态（sys_normal_disable）',
  `name` varchar(255) NOT NULL COMMENT '配置项名称',
  `code` varchar(255) NOT NULL COMMENT '配置项键',
  `value` text NOT NULL COMMENT '配置项JSON值',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='配置信息';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_common_tags` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  `category` varchar(32) NOT NULL DEFAULT '' COMMENT '标签分类',
  `name` varchar(64) NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_unique` (`category`,`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='联系人常用标签';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_info` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  `name` varchar(64) NOT NULL COMMENT '名字',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `pinyin` varchar(64) DEFAULT NULL COMMENT '姓名拼音',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮件',
  `gender` varchar(100) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `place_of_origin` varchar(64) DEFAULT NULL COMMENT '户籍',
  `address` varchar(128) DEFAULT NULL COMMENT '居住地',
  `graduation_school` varchar(64) DEFAULT NULL COMMENT '毕业学校',
  `qualification` varchar(100) DEFAULT NULL COMMENT '学历',
  `organization` varchar(128) DEFAULT NULL COMMENT '公司组织',
  `position` varchar(64) DEFAULT NULL COMMENT '工作职务',
  `social_role` varchar(100) DEFAULT NULL COMMENT '社会角色',
  `description` text COMMENT '描述',
  `last_interaction_time` datetime DEFAULT NULL COMMENT '最近接触时间',
  `interaction_frequency` varchar(100) DEFAULT NULL COMMENT '接触频率',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='联系人';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_log` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  `contact_id` bigint(20) unsigned NOT NULL COMMENT '联系人ID',
  `operator_id` bigint(20) unsigned NOT NULL COMMENT '经办人ID',
  `channel` varchar(100) NOT NULL COMMENT '通讯渠道',
  `subject` varchar(255) NOT NULL COMMENT '主题',
  `description` text COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='联系记录';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_tags` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  `contact_id` bigint(20) unsigned NOT NULL COMMENT '联系人ID',
  `category` varchar(32) NOT NULL DEFAULT '' COMMENT '标签分类',
  `name` varchar(64) NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='联系人标签';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_info` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `cover_image` bigint(20) unsigned NOT NULL COMMENT '封面图片',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `application_deadline` datetime NOT NULL COMMENT '报名时间',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `quota` int(11) NOT NULL COMMENT '名额',
  `location` varchar(255) DEFAULT NULL COMMENT '地点',
  `arrangement` text NOT NULL COMMENT '活动安排',
  `members` text COMMENT '报名会员快照',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `ex_info` text COMMENT '扩展信息',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='活动信息';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_member` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `event_id` bigint(20) unsigned NOT NULL COMMENT '活动ID',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '会员ID',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `sign_code` int(11) NOT NULL DEFAULT '0' COMMENT '签到码',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='活动报名人';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_moment` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `event_id` bigint(20) unsigned NOT NULL COMMENT '活动ID',
  `image` bigint(20) unsigned NOT NULL COMMENT '图片ID',
  `summary` varchar(500) DEFAULT NULL COMMENT '摘要',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='活动瞬间信息';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL COMMENT '编号',
  `data_name` varchar(200) DEFAULT '' COMMENT '数据源名称',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL COMMENT '编号',
  `table_id` bigint(20) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen_template` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `ex_info` text COMMENT '扩展信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='表结构模板';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_info` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '账号',
  `nick_name` varchar(30) NOT NULL COMMENT '昵称',
  `user_type` varchar(10) DEFAULT 'mp_member' COMMENT 'UserType类型',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '性别',
  `avatar` bigint(20) DEFAULT NULL COMMENT '头像ID',
  `xcx_avatar` varchar(255) DEFAULT NULL COMMENT '小程序头像',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `marital_status` varchar(100) DEFAULT NULL COMMENT '婚姻状况（ditalk_marital_status）',
  `tall` int(11) DEFAULT NULL COMMENT '身高',
  `qualification` varchar(100) DEFAULT NULL COMMENT '学历',
  `career` varchar(100) DEFAULT NULL COMMENT '工作',
  `place_of_origin` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `hobby` varchar(100) DEFAULT NULL COMMENT '爱好',
  `profile` varchar(500) DEFAULT NULL COMMENT '简介',
  `open_state` varchar(100) DEFAULT '0' COMMENT '开放状态（0下线 1活跃）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `ex_info` text COMMENT '扩展信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='会员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_openid` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `state` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '会员ID',
  `app_id` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'App应用ID',
  `platform` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '平台代码',
  `open_id` varchar(36) COLLATE utf8mb4_bin NOT NULL COMMENT 'OpenID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_open_id` (`open_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='会员OpenId';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_photo` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `photo_id` bigint(20) unsigned NOT NULL COMMENT '照片ID',
  `member_id` bigint(20) unsigned NOT NULL COMMENT '会员ID',
  `caption` varchar(512) DEFAULT NULL COMMENT '图片描述',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='会员照片';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_info` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  `event_time` datetime NOT NULL COMMENT '发生时间',
  `content` varchar(50) NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='喜讯新闻';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_client` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `client_id` varchar(64) DEFAULT NULL COMMENT '客户端id',
  `client_key` varchar(32) DEFAULT NULL COMMENT '客户端key',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '客户端秘钥',
  `grant_type` varchar(255) DEFAULT NULL COMMENT '授权类型',
  `device_type` varchar(32) DEFAULT NULL COMMENT '设备类型',
  `active_timeout` int(11) DEFAULT '1800' COMMENT 'token活跃超时时间',
  `timeout` int(11) DEFAULT '604800' COMMENT 'token固定超时',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统授权表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `config_id` bigint(20) NOT NULL COMMENT '参数主键',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(500) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `dept_category` varchar(100) DEFAULT NULL COMMENT '部门类别编码',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` bigint(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL COMMENT '字典编码',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL COMMENT '字典主键',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE KEY `tenant_id` (`tenant_id`,`dict_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL COMMENT '访问ID',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `client_key` varchar(32) DEFAULT '' COMMENT '客户端',
  `device_type` varchar(32) DEFAULT '' COMMENT '设备类型',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  KEY `idx_sys_logininfor_s` (`status`) USING BTREE,
  KEY `idx_sys_logininfor_lt` (`login_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query_param` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_notice` (
  `notice_id` bigint(20) NOT NULL COMMENT '公告ID',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL COMMENT '日志主键',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(4000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(4000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(4000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  KEY `idx_sys_oper_log_bt` (`business_type`) USING BTREE,
  KEY `idx_sys_oper_log_s` (`status`) USING BTREE,
  KEY `idx_sys_oper_log_ot` (`oper_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_oss` (
  `oss_id` bigint(20) NOT NULL COMMENT '对象存储主键',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `file_name` varchar(255) NOT NULL DEFAULT '' COMMENT '文件名',
  `original_name` varchar(255) NOT NULL DEFAULT '' COMMENT '原名',
  `file_suffix` varchar(10) NOT NULL DEFAULT '' COMMENT '文件后缀名',
  `url` varchar(500) NOT NULL COMMENT 'URL地址',
  `ext1` text COMMENT '扩展字段',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '上传人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `service` varchar(20) NOT NULL DEFAULT 'minio' COMMENT '服务商',
  PRIMARY KEY (`oss_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='OSS对象存储表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_oss_config` (
  `oss_config_id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `config_key` varchar(20) NOT NULL DEFAULT '' COMMENT '配置key',
  `access_key` varchar(255) DEFAULT '' COMMENT 'accessKey',
  `secret_key` varchar(255) DEFAULT '' COMMENT '秘钥',
  `bucket_name` varchar(255) DEFAULT '' COMMENT '桶名称',
  `prefix` varchar(255) DEFAULT '' COMMENT '前缀',
  `endpoint` varchar(255) DEFAULT '' COMMENT '访问站点',
  `domain` varchar(255) DEFAULT '' COMMENT '自定义域名',
  `is_https` char(1) DEFAULT 'N' COMMENT '是否https（Y=是,N=否）',
  `region` varchar(255) DEFAULT '' COMMENT '域',
  `access_policy` char(1) NOT NULL DEFAULT '1' COMMENT '桶权限类型(0=private 1=public 2=custom)',
  `status` char(1) DEFAULT '1' COMMENT '是否默认（0=是,1=否）',
  `ext1` varchar(255) DEFAULT '' COMMENT '扩展字段',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`oss_config_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='对象存储配置表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_oss_md5` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `oss_id` bigint(20) unsigned NOT NULL COMMENT 'OSS_ID',
  `md5` varchar(32) NOT NULL COMMENT 'MD5',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='OSS对象MD5信息';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_category` varchar(100) DEFAULT NULL COMMENT '岗位类别编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限 5：仅本人数据权限 6：部门及以下或本人数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_social` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户id',
  `auth_id` varchar(255) NOT NULL COMMENT '平台+平台唯一id',
  `source` varchar(255) NOT NULL COMMENT '用户来源',
  `open_id` varchar(255) DEFAULT NULL COMMENT '平台编号唯一id',
  `user_name` varchar(30) NOT NULL COMMENT '登录账号',
  `nick_name` varchar(30) DEFAULT '' COMMENT '用户昵称',
  `email` varchar(255) DEFAULT '' COMMENT '用户邮箱',
  `avatar` varchar(500) DEFAULT '' COMMENT '头像地址',
  `access_token` varchar(2000) NOT NULL COMMENT '用户的授权令牌',
  `expire_in` int(11) DEFAULT NULL COMMENT '用户的授权令牌的有效期，部分平台可能没有',
  `refresh_token` varchar(255) DEFAULT NULL COMMENT '刷新令牌，部分平台可能没有',
  `access_code` varchar(2000) DEFAULT NULL COMMENT '平台的授权信息，部分平台可能没有',
  `union_id` varchar(255) DEFAULT NULL COMMENT '用户的 unionid',
  `scope` varchar(255) DEFAULT NULL COMMENT '授予的权限，部分平台可能没有',
  `token_type` varchar(255) DEFAULT NULL COMMENT '个别平台的授权信息，部分平台可能没有',
  `id_token` varchar(2000) DEFAULT NULL COMMENT 'id token，部分平台可能没有',
  `mac_algorithm` varchar(255) DEFAULT NULL COMMENT '小米平台用户的附带属性，部分平台可能没有',
  `mac_key` varchar(255) DEFAULT NULL COMMENT '小米平台用户的附带属性，部分平台可能没有',
  `code` varchar(255) DEFAULT NULL COMMENT '用户的授权code，部分平台可能没有',
  `oauth_token` varchar(255) DEFAULT NULL COMMENT 'Twitter平台用户的附带属性，部分平台可能没有',
  `oauth_token_secret` varchar(255) DEFAULT NULL COMMENT 'Twitter平台用户的附带属性，部分平台可能没有',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='社会化关系表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_tenant` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tenant_id` varchar(20) NOT NULL COMMENT '租户编号',
  `contact_user_name` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `company_name` varchar(30) DEFAULT NULL COMMENT '企业名称',
  `license_number` varchar(30) DEFAULT NULL COMMENT '统一社会信用代码',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `intro` varchar(200) DEFAULT NULL COMMENT '企业简介',
  `domain` varchar(200) DEFAULT NULL COMMENT '域名',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `package_id` bigint(20) DEFAULT NULL COMMENT '租户套餐编号',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `account_count` int(11) DEFAULT '-1' COMMENT '用户数量（-1不限制）',
  `status` char(1) DEFAULT '0' COMMENT '租户状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='租户表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_tenant_package` (
  `package_id` bigint(20) NOT NULL COMMENT '租户套餐id',
  `package_name` varchar(20) DEFAULT NULL COMMENT '套餐名称',
  `menu_ids` varchar(3000) DEFAULT NULL COMMENT '关联菜单id',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`package_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='租户套餐表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(10) DEFAULT 'sys_user' COMMENT '用户类型（sys_user系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` bigint(20) DEFAULT NULL COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_demo` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `order_num` int(11) DEFAULT '0' COMMENT '排序号',
  `test_key` varchar(255) DEFAULT NULL COMMENT 'key键',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `version` int(11) DEFAULT '0' COMMENT '版本',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='测试单表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_tree` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_id` varchar(20) DEFAULT '000000' COMMENT '租户编号',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父id',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `tree_name` varchar(255) DEFAULT NULL COMMENT '值',
  `version` int(11) DEFAULT '0' COMMENT '版本',
  `create_dept` bigint(20) DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='测试树表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uni_auth_config` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'ID',
  `create_dept` bigint(20) unsigned DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` varchar(20) NOT NULL DEFAULT '000000' COMMENT '租户ID',
  `platform` varchar(255) NOT NULL COMMENT '平台名称',
  `app_id` varchar(255) NOT NULL COMMENT 'AppId',
  `secret` varchar(255) NOT NULL COMMENT 'App密钥',
  `state` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态（sys_normal_disable）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='小程序变量';
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `sys_client` VALUES (1,'e5cd7e4891bf95d1d19206ce24a7b32e','pc','pc123','password,social','pc',18000,604800,'0','0',103,1,'2025-06-04 15:39:38',1,'2025-06-09 09:43:42'),(2,'428a8310cd442757ae699df5d894f051','app','app123','password,sms,social','android',1800,604800,'0','0',103,1,'2025-06-04 15:39:38',1,'2025-06-04 15:39:38'),(1931330511505543169,'b7af5d64ecd4249a1a90a99f14a057e4','05bff7fa66b0b33cc63ee2bb918acce1','mp-weixin','xcx','xcx',2678400,8035200,'0','1',103,1,'2025-06-07 20:40:51',1,'2025-06-07 20:46:11'),(1931331808413700098,'1ee401e271db3f15cf61c93c5884bdd2','uni_xcx','unused','xcx','xcx',2678400,8035200,'0','0',103,1,'2025-06-07 20:46:00',1,'2025-06-07 20:46:00');
INSERT INTO `sys_config` VALUES (1,'000000','主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y',103,1,'2025-06-04 15:39:37',NULL,NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'000000','用户管理-账号初始密码','sys.user.initPassword','123456','Y',103,1,'2025-06-04 15:39:37',NULL,NULL,'初始化密码 123456'),(3,'000000','主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y',103,1,'2025-06-04 15:39:37',NULL,NULL,'深色主题theme-dark，浅色主题theme-light'),(5,'000000','账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y',103,1,'2025-06-04 15:39:37',NULL,NULL,'是否开启注册用户功能（true开启，false关闭）'),(11,'000000','OSS预览列表资源开关','sys.oss.previewListResource','true','Y',103,1,'2025-06-04 15:39:37',1,'2025-06-20 00:25:39','true:开启, false:关闭');
INSERT INTO `sys_dept` VALUES (100,'000000',0,'0','XXX科技',NULL,0,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2025-06-04 15:39:37',NULL,NULL),(101,'000000',100,'0,100','深圳总公司',NULL,1,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2025-06-04 15:39:37',NULL,NULL),(102,'000000',100,'0,100','长沙分公司',NULL,2,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2025-06-04 15:39:37',NULL,NULL),(103,'000000',101,'0,100,101','研发部门',NULL,1,1,'15888888888','xxx@qq.com','0','0',103,1,'2025-06-04 15:39:37',NULL,NULL),(104,'000000',101,'0,100,101','市场部门',NULL,2,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2025-06-04 15:39:37',NULL,NULL),(105,'000000',101,'0,100,101','测试部门',NULL,3,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2025-06-04 15:39:37',NULL,NULL),(106,'000000',101,'0,100,101','财务部门',NULL,4,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2025-06-04 15:39:37',NULL,NULL),(107,'000000',101,'0,100,101','运维部门',NULL,5,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2025-06-04 15:39:37',NULL,NULL),(108,'000000',102,'0,100,102','市场部门',NULL,1,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2025-06-04 15:39:37',NULL,NULL),(109,'000000',102,'0,100,102','财务部门',NULL,2,NULL,'15888888888','xxx@qq.com','0','0',103,1,'2025-06-04 15:39:37',NULL,NULL);
INSERT INTO `sys_dict_data` VALUES (1,'000000',1,'男','0','sys_user_sex','','','Y',103,1,'2025-06-04 15:39:37',NULL,NULL,'性别男'),(2,'000000',2,'女','1','sys_user_sex','','','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'性别女'),(3,'000000',3,'未知','2','sys_user_sex','','','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'性别未知'),(4,'000000',1,'显示','0','sys_show_hide','','primary','Y',103,1,'2025-06-04 15:39:37',NULL,NULL,'显示菜单'),(5,'000000',2,'隐藏','1','sys_show_hide','','danger','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'隐藏菜单'),(6,'000000',1,'正常','0','sys_normal_disable','','primary','Y',103,1,'2025-06-04 15:39:37',NULL,NULL,'正常状态'),(7,'000000',2,'停用','1','sys_normal_disable','','danger','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'停用状态'),(12,'000000',1,'是','Y','sys_yes_no','','primary','Y',103,1,'2025-06-04 15:39:37',NULL,NULL,'系统默认是'),(13,'000000',2,'否','N','sys_yes_no','','danger','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'系统默认否'),(14,'000000',1,'通知','1','sys_notice_type','','warning','Y',103,1,'2025-06-04 15:39:37',NULL,NULL,'通知'),(15,'000000',2,'公告','2','sys_notice_type','','success','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'公告'),(16,'000000',1,'正常','0','sys_notice_status','','primary','Y',103,1,'2025-06-04 15:39:37',NULL,NULL,'正常状态'),(17,'000000',2,'关闭','1','sys_notice_status','','danger','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'关闭状态'),(18,'000000',1,'新增','1','sys_oper_type','','info','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'新增操作'),(19,'000000',2,'修改','2','sys_oper_type','','info','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'修改操作'),(20,'000000',3,'删除','3','sys_oper_type','','danger','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'删除操作'),(21,'000000',4,'授权','4','sys_oper_type','','primary','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'授权操作'),(22,'000000',5,'导出','5','sys_oper_type','','warning','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'导出操作'),(23,'000000',6,'导入','6','sys_oper_type','','warning','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'导入操作'),(24,'000000',7,'强退','7','sys_oper_type','','danger','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'强退操作'),(25,'000000',8,'生成代码','8','sys_oper_type','','warning','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'生成操作'),(26,'000000',9,'清空数据','9','sys_oper_type','','danger','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'清空操作'),(27,'000000',1,'成功','0','sys_common_status','','primary','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'正常状态'),(28,'000000',2,'失败','1','sys_common_status','','danger','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'停用状态'),(29,'000000',99,'其他','0','sys_oper_type','','info','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'其他操作'),(30,'000000',0,'密码认证','password','sys_grant_type','el-check-tag','default','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'密码认证'),(31,'000000',0,'短信认证','sms','sys_grant_type','el-check-tag','default','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'短信认证'),(32,'000000',0,'邮件认证','email','sys_grant_type','el-check-tag','default','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'邮件认证'),(33,'000000',0,'小程序认证','xcx','sys_grant_type','el-check-tag','default','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'小程序认证'),(34,'000000',0,'三方登录认证','social','sys_grant_type','el-check-tag','default','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'三方登录认证'),(35,'000000',0,'PC','pc','sys_device_type','','default','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'PC'),(36,'000000',0,'安卓','android','sys_device_type','','default','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'安卓'),(37,'000000',0,'iOS','ios','sys_device_type','','default','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'iOS'),(38,'000000',0,'小程序','xcx','sys_device_type','','default','N',103,1,'2025-06-04 15:39:37',NULL,NULL,'小程序'),(1930834637302079489,'000000',10,'下线 ','0','ditalk_member_open_state','','danger','N',103,1,'2025-06-06 11:50:25',1,'2025-06-06 11:50:47',''),(1930834700275359745,'000000',20,'活跃','1','ditalk_member_open_state','','success','N',103,1,'2025-06-06 11:50:40',1,'2025-06-06 11:50:40',''),(1932432816292343809,'000000',10,'随时','always','ditalk_interaction_frequency','','primary','N',103,1,'2025-06-10 21:41:01',1,'2025-06-10 21:41:01',''),(1932432876166033410,'000000',20,'每天','daily','ditalk_interaction_frequency','','primary','N',103,1,'2025-06-10 21:41:15',1,'2025-06-10 21:41:15',''),(1932432954775678978,'000000',30,'每周','weekly','ditalk_interaction_frequency','','primary','N',103,1,'2025-06-10 21:41:34',1,'2025-06-10 21:41:34',''),(1932433015794413570,'000000',40,'每月','monthly','ditalk_interaction_frequency','','primary','N',103,1,'2025-06-10 21:41:49',1,'2025-06-10 21:41:49',''),(1932433261769371650,'000000',50,'每季度','quarterly','ditalk_interaction_frequency','','primary','N',103,1,'2025-06-10 21:42:47',1,'2025-06-10 21:42:55',''),(1932433376387117058,'000000',60,'每年','yearly','ditalk_interaction_frequency','','primary','N',103,1,'2025-06-10 21:43:14',1,'2025-06-10 21:43:14',''),(1932637643798556674,'000000',10,'电话','call','ditalk_contact_channel','','primary','N',103,1,'2025-06-11 11:14:56',1,'2025-06-11 11:16:36',''),(1932637821255364610,'000000',20,'手机短信','SMS','ditalk_contact_channel','','primary','N',103,1,'2025-06-11 11:15:38',1,'2025-06-11 11:16:25',''),(1932638135576506369,'000000',30,'电邮','email','ditalk_contact_channel','','primary','N',103,1,'2025-06-11 11:16:53',1,'2025-06-11 11:16:53',''),(1932638181701267457,'000000',40,'微信','WeChat','ditalk_contact_channel','','primary','N',103,1,'2025-06-11 11:17:04',1,'2025-06-11 11:18:17',''),(1932638246557790209,'000000',50,'QQ','qq','ditalk_contact_channel','','primary','N',103,1,'2025-06-11 11:17:19',1,'2025-06-11 11:17:19',''),(1932638438187151361,'000000',60,'钉钉','DingTalk','ditalk_contact_channel','','primary','N',103,1,'2025-06-11 11:18:05',1,'2025-06-11 11:18:05',''),(1937151013641854978,'000000',10,'未婚','unmarried','ditalk_marital_status','','primary','N',103,1,'2025-06-23 22:09:27',1,'2025-06-23 22:09:27',''),(1937151059691118594,'000000',20,'已婚','married','ditalk_marital_status','','primary','N',103,1,'2025-06-23 22:09:38',1,'2025-06-23 22:09:38',''),(1937151110647717890,'000000',30,'离异','divorced','ditalk_marital_status','','primary','N',103,1,'2025-06-23 22:09:50',1,'2025-06-23 22:09:50',''),(1937151157477122050,'000000',40,'丧偶','widowed','ditalk_marital_status','','primary','N',103,1,'2025-06-23 22:10:01',1,'2025-06-23 22:10:01',''),(1937151202163236865,'000000',50,'分居','separated','ditalk_marital_status','','primary','N',103,1,'2025-06-23 22:10:12',1,'2025-06-23 22:10:12',''),(1937151401799524354,'000000',100,'未知','unknow','ditalk_marital_status','','primary','N',103,1,'2025-06-23 22:10:59',1,'2025-06-23 22:10:59','');
INSERT INTO `sys_dict_type` VALUES (1,'000000','用户性别','sys_user_sex',103,1,'2025-06-04 15:39:37',NULL,NULL,'用户性别列表'),(2,'000000','菜单状态','sys_show_hide',103,1,'2025-06-04 15:39:37',NULL,NULL,'菜单状态列表'),(3,'000000','系统开关','sys_normal_disable',103,1,'2025-06-04 15:39:37',NULL,NULL,'系统开关列表'),(6,'000000','系统是否','sys_yes_no',103,1,'2025-06-04 15:39:37',NULL,NULL,'系统是否列表'),(7,'000000','通知类型','sys_notice_type',103,1,'2025-06-04 15:39:37',NULL,NULL,'通知类型列表'),(8,'000000','通知状态','sys_notice_status',103,1,'2025-06-04 15:39:37',NULL,NULL,'通知状态列表'),(9,'000000','操作类型','sys_oper_type',103,1,'2025-06-04 15:39:37',NULL,NULL,'操作类型列表'),(10,'000000','系统状态','sys_common_status',103,1,'2025-06-04 15:39:37',NULL,NULL,'登录状态列表'),(11,'000000','授权类型','sys_grant_type',103,1,'2025-06-04 15:39:37',NULL,NULL,'认证授权类型'),(12,'000000','设备类型','sys_device_type',103,1,'2025-06-04 15:39:37',NULL,NULL,'客户端设备类型'),(1930834497212325890,'000000','会员开放状态','ditalk_member_open_state',103,1,'2025-06-06 11:49:52',1,'2025-06-10 21:32:54',''),(1932430564626378753,'000000','接触频率','ditalk_interaction_frequency',103,1,'2025-06-10 21:32:04',1,'2025-06-10 21:46:33','与联系人的联系、沟通频率'),(1932637450541805569,'000000','联系渠道','ditalk_contact_channel',103,1,'2025-06-11 11:14:10',1,'2025-06-11 11:14:10',''),(1937150940140871682,'000000','婚姻状况','ditalk_marital_status',103,1,'2025-06-23 22:09:09',1,'2025-06-23 22:12:50','');
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,'',1,0,'M','0','0','','system',103,1,'2025-06-04 15:39:37',NULL,NULL,'系统管理目录'),(2,'系统监控',0,3,'monitor',NULL,'',1,0,'M','0','0','','monitor',103,1,'2025-06-04 15:39:37',NULL,NULL,'系统监控目录'),(3,'系统工具',0,4,'tool',NULL,'',1,0,'M','0','0','','tool',103,1,'2025-06-04 15:39:37',NULL,NULL,'系统工具目录'),(5,'测试菜单',0,5,'demo',NULL,'',1,0,'M','0','0','','star',103,1,'2025-06-04 15:39:37',NULL,NULL,'测试菜单'),(6,'租户管理',0,2,'tenant',NULL,'',1,0,'M','0','0','','chart',103,1,'2025-06-04 15:39:37',NULL,NULL,'租户管理目录'),(100,'用户管理',1,1,'user','system/user/index','',1,0,'C','0','0','system:user:list','user',103,1,'2025-06-04 15:39:37',NULL,NULL,'用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index','',1,0,'C','0','0','system:role:list','peoples',103,1,'2025-06-04 15:39:37',NULL,NULL,'角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index','',1,0,'C','0','0','system:menu:list','tree-table',103,1,'2025-06-04 15:39:37',NULL,NULL,'菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index','',1,0,'C','0','0','system:dept:list','tree',103,1,'2025-06-04 15:39:37',NULL,NULL,'部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index','',1,0,'C','0','0','system:post:list','post',103,1,'2025-06-04 15:39:37',NULL,NULL,'岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index','',1,0,'C','0','0','system:dict:list','dict',103,1,'2025-06-04 15:39:37',NULL,NULL,'字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index','',1,0,'C','0','0','system:config:list','edit',103,1,'2025-06-04 15:39:37',NULL,NULL,'参数设置菜单'),(107,'通知公告',1,8,'notice','system/notice/index','',1,0,'C','0','0','system:notice:list','message',103,1,'2025-06-04 15:39:37',NULL,NULL,'通知公告菜单'),(108,'日志管理',1,9,'log','','',1,0,'M','0','0','','log',103,1,'2025-06-04 15:39:37',NULL,NULL,'日志管理菜单'),(109,'在线用户',2,1,'online','monitor/online/index','',1,0,'C','0','0','monitor:online:list','online',103,1,'2025-06-04 15:39:37',NULL,NULL,'在线用户菜单'),(113,'缓存监控',2,5,'cache','monitor/cache/index','',1,0,'C','0','0','monitor:cache:list','redis',103,1,'2025-06-04 15:39:37',NULL,NULL,'缓存监控菜单'),(115,'代码生成',3,2,'gen','tool/gen/index','',1,0,'C','0','0','tool:gen:list','code',103,1,'2025-06-04 15:39:37',NULL,NULL,'代码生成菜单'),(116,'修改生成配置',3,2,'gen-edit/index/:tableId(\\d+)','tool/gen/editTable','',1,1,'C','1','0','tool:gen:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(117,'Admin监控',2,5,'Admin','monitor/admin/index','',1,0,'C','0','0','monitor:admin:list','dashboard',103,1,'2025-06-04 15:39:37',NULL,NULL,'Admin监控菜单'),(118,'文件管理',1,10,'oss','system/oss/index','',1,0,'C','0','0','system:oss:list','upload',103,1,'2025-06-04 15:39:37',NULL,NULL,'文件管理菜单'),(120,'任务调度中心',2,6,'snailjob','monitor/snailjob/index','',1,0,'C','0','0','monitor:snailjob:list','job',103,1,'2025-06-04 15:39:37',NULL,NULL,'SnailJob控制台菜单'),(121,'租户管理',6,1,'tenant','system/tenant/index','',1,0,'C','0','0','system:tenant:list','list',103,1,'2025-06-04 15:39:37',NULL,NULL,'租户管理菜单'),(122,'租户套餐管理',6,2,'tenantPackage','system/tenantPackage/index','',1,0,'C','0','0','system:tenantPackage:list','form',103,1,'2025-06-04 15:39:37',NULL,NULL,'租户套餐管理菜单'),(123,'客户端管理',1,11,'client','system/client/index','',1,0,'C','0','0','system:client:list','international',103,1,'2025-06-04 15:39:37',NULL,NULL,'客户端管理菜单'),(130,'分配用户',1,2,'role-auth/user/:roleId(\\d+)','system/role/authUser','',1,1,'C','1','0','system:role:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(131,'分配角色',1,1,'user-auth/role/:userId(\\d+)','system/user/authRole','',1,1,'C','1','0','system:user:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(132,'字典数据',1,6,'dict-data/index/:dictId(\\d+)','system/dict/data','',1,1,'C','1','0','system:dict:list','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(133,'文件配置管理',1,10,'oss-config/index','system/oss/config','',1,1,'C','1','0','system:ossConfig:list','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(500,'操作日志',108,1,'operlog','monitor/operlog/index','',1,0,'C','0','0','monitor:operlog:list','form',103,1,'2025-06-04 15:39:37',NULL,NULL,'操作日志菜单'),(501,'登录日志',108,2,'logininfor','monitor/logininfor/index','',1,0,'C','0','0','monitor:logininfor:list','logininfor',103,1,'2025-06-04 15:39:37',NULL,NULL,'登录日志菜单'),(1001,'用户查询',100,1,'','','',1,0,'F','0','0','system:user:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1002,'用户新增',100,2,'','','',1,0,'F','0','0','system:user:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1003,'用户修改',100,3,'','','',1,0,'F','0','0','system:user:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1004,'用户删除',100,4,'','','',1,0,'F','0','0','system:user:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1005,'用户导出',100,5,'','','',1,0,'F','0','0','system:user:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1006,'用户导入',100,6,'','','',1,0,'F','0','0','system:user:import','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1007,'重置密码',100,7,'','','',1,0,'F','0','0','system:user:resetPwd','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1008,'角色查询',101,1,'','','',1,0,'F','0','0','system:role:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1009,'角色新增',101,2,'','','',1,0,'F','0','0','system:role:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1010,'角色修改',101,3,'','','',1,0,'F','0','0','system:role:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1011,'角色删除',101,4,'','','',1,0,'F','0','0','system:role:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1012,'角色导出',101,5,'','','',1,0,'F','0','0','system:role:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1013,'菜单查询',102,1,'','','',1,0,'F','0','0','system:menu:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1014,'菜单新增',102,2,'','','',1,0,'F','0','0','system:menu:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1015,'菜单修改',102,3,'','','',1,0,'F','0','0','system:menu:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1016,'菜单删除',102,4,'','','',1,0,'F','0','0','system:menu:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1017,'部门查询',103,1,'','','',1,0,'F','0','0','system:dept:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1018,'部门新增',103,2,'','','',1,0,'F','0','0','system:dept:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1019,'部门修改',103,3,'','','',1,0,'F','0','0','system:dept:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1020,'部门删除',103,4,'','','',1,0,'F','0','0','system:dept:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1021,'岗位查询',104,1,'','','',1,0,'F','0','0','system:post:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1022,'岗位新增',104,2,'','','',1,0,'F','0','0','system:post:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1023,'岗位修改',104,3,'','','',1,0,'F','0','0','system:post:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1024,'岗位删除',104,4,'','','',1,0,'F','0','0','system:post:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1025,'岗位导出',104,5,'','','',1,0,'F','0','0','system:post:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1026,'字典查询',105,1,'#','','',1,0,'F','0','0','system:dict:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1027,'字典新增',105,2,'#','','',1,0,'F','0','0','system:dict:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1028,'字典修改',105,3,'#','','',1,0,'F','0','0','system:dict:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1029,'字典删除',105,4,'#','','',1,0,'F','0','0','system:dict:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1030,'字典导出',105,5,'#','','',1,0,'F','0','0','system:dict:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1031,'参数查询',106,1,'#','','',1,0,'F','0','0','system:config:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1032,'参数新增',106,2,'#','','',1,0,'F','0','0','system:config:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1033,'参数修改',106,3,'#','','',1,0,'F','0','0','system:config:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1034,'参数删除',106,4,'#','','',1,0,'F','0','0','system:config:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1035,'参数导出',106,5,'#','','',1,0,'F','0','0','system:config:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1036,'公告查询',107,1,'#','','',1,0,'F','0','0','system:notice:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1037,'公告新增',107,2,'#','','',1,0,'F','0','0','system:notice:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1038,'公告修改',107,3,'#','','',1,0,'F','0','0','system:notice:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1039,'公告删除',107,4,'#','','',1,0,'F','0','0','system:notice:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1040,'操作查询',500,1,'#','','',1,0,'F','0','0','monitor:operlog:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1041,'操作删除',500,2,'#','','',1,0,'F','0','0','monitor:operlog:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1042,'日志导出',500,4,'#','','',1,0,'F','0','0','monitor:operlog:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1043,'登录查询',501,1,'#','','',1,0,'F','0','0','monitor:logininfor:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1044,'登录删除',501,2,'#','','',1,0,'F','0','0','monitor:logininfor:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1045,'日志导出',501,3,'#','','',1,0,'F','0','0','monitor:logininfor:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1046,'在线查询',109,1,'#','','',1,0,'F','0','0','monitor:online:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1047,'批量强退',109,2,'#','','',1,0,'F','0','0','monitor:online:batchLogout','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1048,'单条强退',109,3,'#','','',1,0,'F','0','0','monitor:online:forceLogout','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1050,'账户解锁',501,4,'#','','',1,0,'F','0','0','monitor:logininfor:unlock','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1055,'生成查询',115,1,'#','','',1,0,'F','0','0','tool:gen:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1056,'生成修改',115,2,'#','','',1,0,'F','0','0','tool:gen:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1057,'生成删除',115,3,'#','','',1,0,'F','0','0','tool:gen:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1058,'导入代码',115,2,'#','','',1,0,'F','0','0','tool:gen:import','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1059,'预览代码',115,4,'#','','',1,0,'F','0','0','tool:gen:preview','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1060,'生成代码',115,5,'#','','',1,0,'F','0','0','tool:gen:code','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1061,'客户端管理查询',123,1,'#','','',1,0,'F','0','0','system:client:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1062,'客户端管理新增',123,2,'#','','',1,0,'F','0','0','system:client:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1063,'客户端管理修改',123,3,'#','','',1,0,'F','0','0','system:client:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1064,'客户端管理删除',123,4,'#','','',1,0,'F','0','0','system:client:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1065,'客户端管理导出',123,5,'#','','',1,0,'F','0','0','system:client:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1500,'测试单表',5,1,'demo','demo/demo/index','',1,0,'C','0','0','demo:demo:list','#',103,1,'2025-06-04 15:39:37',NULL,NULL,'测试单表菜单'),(1501,'测试单表查询',1500,1,'#','','',1,0,'F','0','0','demo:demo:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1502,'测试单表新增',1500,2,'#','','',1,0,'F','0','0','demo:demo:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1503,'测试单表修改',1500,3,'#','','',1,0,'F','0','0','demo:demo:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1504,'测试单表删除',1500,4,'#','','',1,0,'F','0','0','demo:demo:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1505,'测试单表导出',1500,5,'#','','',1,0,'F','0','0','demo:demo:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1506,'测试树表',5,1,'tree','demo/tree/index','',1,0,'C','0','0','demo:tree:list','#',103,1,'2025-06-04 15:39:37',NULL,NULL,'测试树表菜单'),(1507,'测试树表查询',1506,1,'#','','',1,0,'F','0','0','demo:tree:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1508,'测试树表新增',1506,2,'#','','',1,0,'F','0','0','demo:tree:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1509,'测试树表修改',1506,3,'#','','',1,0,'F','0','0','demo:tree:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1510,'测试树表删除',1506,4,'#','','',1,0,'F','0','0','demo:tree:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1511,'测试树表导出',1506,5,'#','','',1,0,'F','0','0','demo:tree:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1600,'文件查询',118,1,'#','','',1,0,'F','0','0','system:oss:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1601,'文件上传',118,2,'#','','',1,0,'F','0','0','system:oss:upload','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1602,'文件下载',118,3,'#','','',1,0,'F','0','0','system:oss:download','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1603,'文件删除',118,4,'#','','',1,0,'F','0','0','system:oss:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1606,'租户查询',121,1,'#','','',1,0,'F','0','0','system:tenant:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1607,'租户新增',121,2,'#','','',1,0,'F','0','0','system:tenant:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1608,'租户修改',121,3,'#','','',1,0,'F','0','0','system:tenant:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1609,'租户删除',121,4,'#','','',1,0,'F','0','0','system:tenant:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1610,'租户导出',121,5,'#','','',1,0,'F','0','0','system:tenant:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1611,'租户套餐查询',122,1,'#','','',1,0,'F','0','0','system:tenantPackage:query','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1612,'租户套餐新增',122,2,'#','','',1,0,'F','0','0','system:tenantPackage:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1613,'租户套餐修改',122,3,'#','','',1,0,'F','0','0','system:tenantPackage:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1614,'租户套餐删除',122,4,'#','','',1,0,'F','0','0','system:tenantPackage:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1615,'租户套餐导出',122,5,'#','','',1,0,'F','0','0','system:tenantPackage:export','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1620,'配置列表',118,5,'#','','',1,0,'F','0','0','system:ossConfig:list','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1621,'配置添加',118,6,'#','','',1,0,'F','0','0','system:ossConfig:add','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1622,'配置编辑',118,6,'#','','',1,0,'F','0','0','system:ossConfig:edit','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1623,'配置删除',118,6,'#','','',1,0,'F','0','0','system:ossConfig:remove','#',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(1930832531845357570,'会员管理',0,1000,'member',NULL,NULL,1,0,'M','0','0',NULL,'peoples',103,1,'2025-06-06 11:42:03',1,'2025-06-06 11:42:03',''),(1930992379081875458,'会员信息',1930832531845357570,1,'info','member/info/index',NULL,1,0,'C','0','0','member:info:list','#',103,1,'2025-06-06 22:27:43',NULL,NULL,'会员信息菜单'),(1930992379081875459,'会员信息查询',1930992379081875458,1,'#','',NULL,1,0,'F','0','0','member:info:query','#',103,1,'2025-06-06 22:27:43',NULL,NULL,''),(1930992379081875460,'会员信息新增',1930992379081875458,2,'#','',NULL,1,0,'F','0','0','member:info:add','#',103,1,'2025-06-06 22:27:43',NULL,NULL,''),(1930992379081875461,'会员信息修改',1930992379081875458,3,'#','',NULL,1,0,'F','0','0','member:info:edit','#',103,1,'2025-06-06 22:27:43',NULL,NULL,''),(1930992379081875462,'会员信息删除',1930992379081875458,4,'#','',NULL,1,0,'F','0','0','member:info:remove','#',103,1,'2025-06-06 22:27:43',NULL,NULL,''),(1930992379081875463,'会员信息导出',1930992379081875458,5,'#','',NULL,1,0,'F','0','0','member:info:export','#',103,1,'2025-06-06 22:27:43',NULL,NULL,''),(1930992480588226561,'会员OpenId',1930832531845357570,1,'openid','member/openid/index',NULL,1,0,'C','0','0','member:openid:list','#',103,1,'2025-06-06 22:28:00',NULL,NULL,'会员OpenId菜单'),(1930992480588226562,'会员OpenId查询',1930992480588226561,1,'#','',NULL,1,0,'F','0','0','member:openid:query','#',103,1,'2025-06-06 22:28:00',NULL,NULL,''),(1930992480588226563,'会员OpenId新增',1930992480588226561,2,'#','',NULL,1,0,'F','0','0','member:openid:add','#',103,1,'2025-06-06 22:28:00',NULL,NULL,''),(1930992480588226564,'会员OpenId修改',1930992480588226561,3,'#','',NULL,1,0,'F','0','0','member:openid:edit','#',103,1,'2025-06-06 22:28:00',NULL,NULL,''),(1930992480588226565,'会员OpenId删除',1930992480588226561,4,'#','',NULL,1,0,'F','0','0','member:openid:remove','#',103,1,'2025-06-06 22:28:00',NULL,NULL,''),(1930992480588226566,'会员OpenId导出',1930992480588226561,5,'#','',NULL,1,0,'F','0','0','member:openid:export','#',103,1,'2025-06-06 22:28:00',NULL,NULL,''),(1931891356589436929,'喜讯管理',0,2000,'news',NULL,NULL,1,0,'M','0','0',NULL,'example',103,1,'2025-06-09 09:49:27',1,'2025-06-09 09:49:27',''),(1931891988935290881,'活动管理',0,3000,'event',NULL,NULL,1,0,'M','0','0',NULL,'workflow',103,1,'2025-06-09 09:51:58',1,'2025-06-09 09:51:58',''),(1931892188479303682,'喜讯新闻',1931891356589436929,1,'info','news/info/index',NULL,1,0,'C','0','0','news:info:list','#',103,1,'2025-06-09 09:56:37',NULL,NULL,'喜讯新闻菜单'),(1931892188479303683,'喜讯新闻查询',1931892188479303682,1,'#','',NULL,1,0,'F','0','0','news:info:query','#',103,1,'2025-06-09 09:56:37',NULL,NULL,''),(1931892188479303684,'喜讯新闻新增',1931892188479303682,2,'#','',NULL,1,0,'F','0','0','news:info:add','#',103,1,'2025-06-09 09:56:37',NULL,NULL,''),(1931892188479303685,'喜讯新闻修改',1931892188479303682,3,'#','',NULL,1,0,'F','0','0','news:info:edit','#',103,1,'2025-06-09 09:56:37',NULL,NULL,''),(1931892188479303686,'喜讯新闻删除',1931892188479303682,4,'#','',NULL,1,0,'F','0','0','news:info:remove','#',103,1,'2025-06-09 09:56:37',NULL,NULL,''),(1931892188479303687,'喜讯新闻导出',1931892188479303682,5,'#','',NULL,1,0,'F','0','0','news:info:export','#',103,1,'2025-06-09 09:56:37',NULL,NULL,''),(1932434529611001858,'联系人管理',0,4000,'contact',NULL,NULL,1,0,'M','1','1',NULL,'people',103,1,'2025-06-10 21:47:49',1,'2025-06-26 13:17:41',''),(1932434823396831233,'联系人',1932434529611001858,100,'info','contact/info/index',NULL,1,0,'C','0','0','contact:info:list','#',103,1,'2025-06-10 21:52:33',1,'2025-06-11 11:29:57','联系人菜单'),(1932434823396831234,'联系人查询',1932434823396831233,1,'#','',NULL,1,0,'F','0','0','contact:info:query','#',103,1,'2025-06-10 21:52:33',NULL,NULL,''),(1932434823396831235,'联系人新增',1932434823396831233,2,'#','',NULL,1,0,'F','0','0','contact:info:add','#',103,1,'2025-06-10 21:52:33',NULL,NULL,''),(1932434823396831236,'联系人修改',1932434823396831233,3,'#','',NULL,1,0,'F','0','0','contact:info:edit','#',103,1,'2025-06-10 21:52:33',NULL,NULL,''),(1932434823396831237,'联系人删除',1932434823396831233,4,'#','',NULL,1,0,'F','0','0','contact:info:remove','#',103,1,'2025-06-10 21:52:33',NULL,NULL,''),(1932434823396831238,'联系人导出',1932434823396831233,5,'#','',NULL,1,0,'F','0','0','contact:info:export','#',103,1,'2025-06-10 21:52:33',NULL,NULL,''),(1932638726302281730,'联系记录',1932434529611001858,200,'log','contact/log/index',NULL,1,0,'C','0','0','contact:log:list','#',103,1,'2025-06-11 11:22:36',1,'2025-06-11 11:30:09','联系记录菜单'),(1932638726302281731,'联系记录查询',1932638726302281730,1,'#','',NULL,1,0,'F','0','0','contact:log:query','#',103,1,'2025-06-11 11:22:36',NULL,NULL,''),(1932638726302281732,'联系记录新增',1932638726302281730,2,'#','',NULL,1,0,'F','0','0','contact:log:add','#',103,1,'2025-06-11 11:22:36',NULL,NULL,''),(1932638726302281733,'联系记录修改',1932638726302281730,3,'#','',NULL,1,0,'F','0','0','contact:log:edit','#',103,1,'2025-06-11 11:22:36',NULL,NULL,''),(1932638726302281734,'联系记录删除',1932638726302281730,4,'#','',NULL,1,0,'F','0','0','contact:log:remove','#',103,1,'2025-06-11 11:22:36',NULL,NULL,''),(1932638726302281735,'联系记录导出',1932638726302281730,5,'#','',NULL,1,0,'F','0','0','contact:log:export','#',103,1,'2025-06-11 11:22:36',NULL,NULL,''),(1932638726704934914,'联系人标签',1932434529611001858,300,'tags','contact/tags/index',NULL,1,0,'C','0','0','contact:tags:list','#',103,1,'2025-06-11 11:22:41',1,'2025-06-11 11:30:17','联系人标签菜单'),(1932638726704934915,'联系人标签查询',1932638726704934914,1,'#','',NULL,1,0,'F','0','0','contact:tags:query','#',103,1,'2025-06-11 11:22:41',NULL,NULL,''),(1932638726704934916,'联系人标签新增',1932638726704934914,2,'#','',NULL,1,0,'F','0','0','contact:tags:add','#',103,1,'2025-06-11 11:22:41',NULL,NULL,''),(1932638726704934917,'联系人标签修改',1932638726704934914,3,'#','',NULL,1,0,'F','0','0','contact:tags:edit','#',103,1,'2025-06-11 11:22:41',NULL,NULL,''),(1932638726704934918,'联系人标签删除',1932638726704934914,4,'#','',NULL,1,0,'F','0','0','contact:tags:remove','#',103,1,'2025-06-11 11:22:41',NULL,NULL,''),(1932638726704934919,'联系人标签导出',1932638726704934914,5,'#','',NULL,1,0,'F','0','0','contact:tags:export','#',103,1,'2025-06-11 11:22:41',NULL,NULL,''),(1932638727099199490,'常用标签',1932434529611001858,400,'commonTags','contact/commonTags/index',NULL,1,0,'C','0','0','contact:commonTags:list','#',103,1,'2025-06-11 11:22:30',1,'2025-06-11 11:30:25','常用标签菜单'),(1932638727099199491,'常用标签查询',1932638727099199490,1,'#','',NULL,1,0,'F','0','0','contact:commonTags:query','#',103,1,'2025-06-11 11:22:30',NULL,NULL,''),(1932638727099199492,'常用标签新增',1932638727099199490,2,'#','',NULL,1,0,'F','0','0','contact:commonTags:add','#',103,1,'2025-06-11 11:22:30',NULL,NULL,''),(1932638727099199493,'常用标签修改',1932638727099199490,3,'#','',NULL,1,0,'F','0','0','contact:commonTags:edit','#',103,1,'2025-06-11 11:22:30',NULL,NULL,''),(1932638727099199494,'常用标签删除',1932638727099199490,4,'#','',NULL,1,0,'F','0','0','contact:commonTags:remove','#',103,1,'2025-06-11 11:22:30',NULL,NULL,''),(1932638727099199495,'常用标签导出',1932638727099199490,5,'#','',NULL,1,0,'F','0','0','contact:commonTags:export','#',103,1,'2025-06-11 11:22:30',NULL,NULL,''),(1932991778561523713,'配置信息',1932995937402638337,100,'info','config/info/index',NULL,1,0,'C','0','0','config:info:list','#',103,1,'2025-06-12 10:49:27',1,'2025-06-18 21:38:07','配置信息菜单'),(1932991778561523714,'查询',1932991778561523713,1,'#','',NULL,1,0,'F','0','0','config:info:query','#',103,1,'2025-06-12 10:49:27',NULL,NULL,''),(1932991778561523715,'新增',1932991778561523713,2,'#','',NULL,1,0,'F','0','0','config:info:add','#',103,1,'2025-06-12 10:49:27',NULL,NULL,''),(1932991778561523716,'修改',1932991778561523713,3,'#','',NULL,1,0,'F','0','0','config:info:edit','#',103,1,'2025-06-12 10:49:27',NULL,NULL,''),(1932991778561523717,'删除',1932991778561523713,4,'#','',NULL,1,0,'F','0','0','config:info:remove','#',103,1,'2025-06-12 10:49:27',NULL,NULL,''),(1932991778561523718,'导出',1932991778561523713,5,'#','',NULL,1,0,'F','0','0','config:info:export','#',103,1,'2025-06-12 10:49:27',NULL,NULL,''),(1932995937402638337,'配置数据',0,90000,'config',NULL,NULL,1,0,'M','0','0',NULL,'component',103,1,'2025-06-12 10:58:39',1,'2025-06-12 10:58:39',''),(1935307226233720833,'小程序变量',1932995937402638337,200,'authConfig','config/authConfig/index',NULL,1,0,'C','0','0','config:authConfig:list','#',103,1,'2025-06-18 20:07:43',1,'2025-06-18 21:38:16','小程序变量菜单'),(1935307226233720834,'查询',1935307226233720833,1,'#','',NULL,1,0,'F','0','0','config:authConfig:query','#',103,1,'2025-06-18 20:07:43',NULL,NULL,''),(1935307226233720835,'新增',1935307226233720833,2,'#','',NULL,1,0,'F','0','0','config:authConfig:add','#',103,1,'2025-06-18 20:07:43',NULL,NULL,''),(1935307226233720836,'修改',1935307226233720833,3,'#','',NULL,1,0,'F','0','0','config:authConfig:edit','#',103,1,'2025-06-18 20:07:43',NULL,NULL,''),(1935307226233720837,'删除',1935307226233720833,4,'#','',NULL,1,0,'F','0','0','config:authConfig:remove','#',103,1,'2025-06-18 20:07:43',NULL,NULL,''),(1935307226233720838,'导出',1935307226233720833,5,'#','',NULL,1,0,'F','0','0','config:authConfig:export','#',103,1,'2025-06-18 20:07:43',NULL,NULL,''),(1935934266062503938,'用户首页横幅',1932995937402638337,300,'banner','config/banner/index',NULL,1,0,'C','0','0','config:banner:query','color',103,1,'2025-06-20 13:34:32',1,'2025-06-20 13:37:11',''),(1935935077521915906,'查询',1935934266062503938,1,'',NULL,NULL,1,0,'F','0','0','config:banner:query','',103,1,'2025-06-20 13:37:45',1,'2025-06-20 13:37:45',''),(1935935227472478209,'编辑',1935934266062503938,2,'',NULL,NULL,1,0,'F','0','0','config:banner:edit','',103,1,'2025-06-20 13:38:21',1,'2025-06-20 13:38:21',''),(1936687197737046018,'活动信息',1931891988935290881,100,'info','event/info/index',NULL,1,0,'C','0','0','event:info:list','#',103,1,'2025-06-22 15:30:18',NULL,NULL,'活动信息菜单'),(1936687197737046019,'查询',1936687197737046018,1,'#','',NULL,1,0,'F','0','0','event:info:query','#',103,1,'2025-06-22 15:30:18',NULL,NULL,''),(1936687197737046020,'新增',1936687197737046018,2,'#','',NULL,1,0,'F','0','0','event:info:add','#',103,1,'2025-06-22 15:30:18',NULL,NULL,''),(1936687197737046021,'修改',1936687197737046018,3,'#','',NULL,1,0,'F','0','0','event:info:edit','#',103,1,'2025-06-22 15:30:18',NULL,NULL,''),(1936687197737046022,'删除',1936687197737046018,4,'#','',NULL,1,0,'F','0','0','event:info:remove','#',103,1,'2025-06-22 15:30:18',NULL,NULL,''),(1936687197737046023,'导出',1936687197737046018,5,'#','',NULL,1,0,'F','0','0','event:info:export','#',103,1,'2025-06-22 15:30:18',NULL,NULL,''),(1937020328780312578,'活动瞬间',1931891988935290881,300,'moment','event/moment/index',NULL,1,0,'C','0','0','event:moment:list','#',103,1,'2025-06-23 13:37:20',NULL,NULL,'活动瞬间菜单'),(1937020328780312579,'查询',1937020328780312578,1,'#','',NULL,1,0,'F','0','0','event:moment:query','#',103,1,'2025-06-23 13:37:20',NULL,NULL,''),(1937020328780312580,'新增',1937020328780312578,2,'#','',NULL,1,0,'F','0','0','event:moment:add','#',103,1,'2025-06-23 13:37:20',NULL,NULL,''),(1937020328780312581,'修改',1937020328780312578,3,'#','',NULL,1,0,'F','0','0','event:moment:edit','#',103,1,'2025-06-23 13:37:20',NULL,NULL,''),(1937020328780312582,'删除',1937020328780312578,4,'#','',NULL,1,0,'F','0','0','event:moment:remove','#',103,1,'2025-06-23 13:37:20',NULL,NULL,''),(1937020328780312583,'导出',1937020328780312578,5,'#','',NULL,1,0,'F','0','0','event:moment:export','#',103,1,'2025-06-23 13:37:20',NULL,NULL,''),(1937020342772510721,'活动报名人',1931891988935290881,200,'member','event/member/index',NULL,1,0,'C','0','0','event:member:list','#',103,1,'2025-06-23 13:37:09',NULL,NULL,'活动报名人菜单'),(1937020342772510722,'查询',1937020342772510721,1,'#','',NULL,1,0,'F','0','0','event:member:query','#',103,1,'2025-06-23 13:37:09',NULL,NULL,''),(1937020342772510723,'新增',1937020342772510721,2,'#','',NULL,1,0,'F','0','0','event:member:add','#',103,1,'2025-06-23 13:37:09',NULL,NULL,''),(1937020342772510724,'修改',1937020342772510721,3,'#','',NULL,1,0,'F','0','0','event:member:edit','#',103,1,'2025-06-23 13:37:09',NULL,NULL,''),(1937020342772510725,'删除',1937020342772510721,4,'#','',NULL,1,0,'F','0','0','event:member:remove','#',103,1,'2025-06-23 13:37:09',NULL,NULL,''),(1937020342772510726,'导出',1937020342772510721,5,'#','',NULL,1,0,'F','0','0','event:member:export','#',103,1,'2025-06-23 13:37:09',NULL,NULL,''),(1937304380712247298,'会员照片',1930832531845357570,300,'photo','member/photo/index',NULL,1,0,'C','0','0','member:photo:list','#',103,1,'2025-06-24 08:23:01',NULL,NULL,'会员照片菜单'),(1937304380712247299,'查询',1937304380712247298,1,'#','',NULL,1,0,'F','0','0','member:photo:query','#',103,1,'2025-06-24 08:23:01',NULL,NULL,''),(1937304380712247300,'新增',1937304380712247298,2,'#','',NULL,1,0,'F','0','0','member:photo:add','#',103,1,'2025-06-24 08:23:01',NULL,NULL,''),(1937304380712247301,'修改',1937304380712247298,3,'#','',NULL,1,0,'F','0','0','member:photo:edit','#',103,1,'2025-06-24 08:23:01',NULL,NULL,''),(1937304380712247302,'删除',1937304380712247298,4,'#','',NULL,1,0,'F','0','0','member:photo:remove','#',103,1,'2025-06-24 08:23:01',NULL,NULL,''),(1937304380712247303,'导出',1937304380712247298,5,'#','',NULL,1,0,'F','0','0','member:photo:export','#',103,1,'2025-06-24 08:23:01',NULL,NULL,'');
INSERT INTO `sys_post` VALUES (1,'000000',103,'ceo',NULL,'董事长',1,'0',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(2,'000000',100,'se',NULL,'项目经理',2,'0',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(3,'000000',100,'hr',NULL,'人力资源',3,'0',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(4,'000000',100,'user',NULL,'普通员工',4,'0',103,1,'2025-06-04 15:39:37',NULL,NULL,'');
INSERT INTO `sys_role` VALUES (1,'000000','超级管理员','superadmin',1,'1',1,1,'0','0',103,1,'2025-06-04 15:39:37',NULL,NULL,'超级管理员'),(3,'000000','本部门及以下','test1',3,'4',1,1,'0','0',103,1,'2025-06-04 15:39:37',NULL,NULL,''),(4,'000000','仅本人','test2',4,'5',1,1,'0','0',103,1,'2025-06-04 15:39:37',NULL,NULL,'');
INSERT INTO `sys_role_menu` VALUES (3,1),(3,5),(3,100),(3,101),(3,102),(3,103),(3,104),(3,105),(3,106),(3,107),(3,108),(3,118),(3,123),(3,130),(3,131),(3,132),(3,133),(3,500),(3,501),(3,1001),(3,1002),(3,1003),(3,1004),(3,1005),(3,1006),(3,1007),(3,1008),(3,1009),(3,1010),(3,1011),(3,1012),(3,1013),(3,1014),(3,1015),(3,1016),(3,1017),(3,1018),(3,1019),(3,1020),(3,1021),(3,1022),(3,1023),(3,1024),(3,1025),(3,1026),(3,1027),(3,1028),(3,1029),(3,1030),(3,1031),(3,1032),(3,1033),(3,1034),(3,1035),(3,1036),(3,1037),(3,1038),(3,1039),(3,1040),(3,1041),(3,1042),(3,1043),(3,1044),(3,1045),(3,1050),(3,1061),(3,1062),(3,1063),(3,1064),(3,1065),(3,1500),(3,1501),(3,1502),(3,1503),(3,1504),(3,1505),(3,1506),(3,1507),(3,1508),(3,1509),(3,1510),(3,1511),(3,1600),(3,1601),(3,1602),(3,1603),(3,1620),(3,1621),(3,1622),(3,1623),(3,11616),(3,11618),(3,11619),(3,11622),(3,11623),(3,11629),(3,11632),(3,11633),(3,11638),(3,11639),(3,11640),(3,11641),(3,11642),(3,11643),(3,11701),(4,5),(4,1500),(4,1501),(4,1502),(4,1503),(4,1504),(4,1505),(4,1506),(4,1507),(4,1508),(4,1509),(4,1510),(4,1511);
INSERT INTO `sys_tenant` VALUES (1,'000000','管理组','15888888888','XXX有限公司',NULL,NULL,'多租户通用后台管理管理系统',NULL,NULL,NULL,NULL,-1,'0','0',103,1,'2025-06-04 15:39:37',NULL,NULL);
INSERT INTO `sys_user` VALUES (1,'000000',103,'admin','DiTalk','sys_user','ditalk@163.com','15888888888','0',NULL,'$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','0:0:0:0:0:0:0:1','2025-06-26 13:15:26',103,1,'2025-06-04 15:39:37',1,'2025-06-26 13:15:26','管理员'),(3,'000000',108,'test','本部门及以下 密码666666','sys_user','','','0',NULL,'$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne','0','0','127.0.0.1','2025-06-04 15:39:37',103,1,'2025-06-04 15:39:37',3,'2025-06-04 15:39:37',NULL),(4,'000000',102,'test1','仅本人 密码666666','sys_user','','','0',NULL,'$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne','0','0','127.0.0.1','2025-06-04 15:39:37',103,1,'2025-06-04 15:39:37',4,'2025-06-04 15:39:37',NULL);
INSERT INTO `sys_user_post` VALUES (1,1);
INSERT INTO `sys_user_role` VALUES (1,1),(3,3),(4,4);
INSERT INTO `sys_oss_config` VALUES (1,'000000','minio','IoyPgwBZrfxfKUqCgxS1','v00R320E1jI3GisWITRB8W7QCVL7QzRVOUJ2zLXW','vip','','192.168.200.150:9000','','N','','2','0','',103,1,'2025-06-04 15:39:38',1,'2025-06-20 00:29:44',''),(2,'000000','qiniu','XXXXXXXXXXXXXXX','XXXXXXXXXXXXXXX','ruoyi','','s3-cn-north-1.qiniucs.com','','N','','1','1','',103,1,'2025-06-04 15:39:38',1,'2025-06-04 15:39:38',NULL),(3,'000000','aliyun','XXXXXXXXXXXXXXX','XXXXXXXXXXXXXXX','ruoyi','','oss-cn-beijing.aliyuncs.com','','N','','1','1','',103,1,'2025-06-04 15:39:38',1,'2025-06-04 15:39:38',NULL),(4,'000000','qcloud','XXXXXXXXXXXXXXX','XXXXXXXXXXXXXXX','ruoyi-1240000000','','cos.ap-beijing.myqcloud.com','','N','ap-beijing','1','1','',103,1,'2025-06-04 15:39:38',1,'2025-06-04 15:39:38',NULL),(5,'000000','image','ruoyi','ruoyi123','ruoyi','image','127.0.0.1:9000','','N','','1','1','',103,1,'2025-06-04 15:39:38',1,'2025-06-04 15:39:38',NULL);
