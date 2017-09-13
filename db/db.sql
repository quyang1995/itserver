SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL';
CREATE SCHEMA IF NOT EXISTS `itplus`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE `itplus`;

-- -----------------------------------------------------
-- Table `itplus`.`product` 产品
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`product` (
  `id`                     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`                   VARCHAR(100)    NOT NULL
  COMMENT '产品名称',
  `code`                   VARCHAR(100)    NOT NULL
  COMMENT '产品CODE',
  `descp`                  VARCHAR(3000)   NULL
  COMMENT '产品描述',
  `contact_account_id`     VARCHAR(50)     NOT NULL
  COMMENT '接口人账户id',
  `contact_employee_code`  BIGINT          NOT NULL
  COMMENT '接口人员工号',
  `contact_employee_name`  VARCHAR(50)     NOT NULL
  COMMENT '接口人员工姓名',
  `contact_full_dept_path` VARCHAR(100)    NOT NULL
  COMMENT '接口人部门完整路径',
  `like_program`           VARCHAR(500)    NULL
  COMMENT '关联项目id字符串，e.g. xx,xx,xx,...',
  `type`                   INT             NOT NULL
  COMMENT '公开类型：0=私有，1=公开',
  `status`                 INT             NOT NULL
  COMMENT '状态：0=已下线，1=正常，-1=全部',
  `modified_account_id`    VARCHAR(50)     NULL
  COMMENT '最后修改人账户id',
  `modified_name`          VARCHAR(50)     NULL
  COMMENT '最后修改人名称',
  `create_time`            TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`          TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

INSERT INTO itplus.product (name, descp, contact_account_id, contact_employee_code, contact_employee_name, contact_full_dept_path, like_program, type, status, modified_account_id, modified_name, create_time, modified_time)
VALUES ('IT+平台', 'IT项目管理平台，产品需求和bug收集、跟进、反馈、项目进度管理功能', 'wenting', 100118473, '闻婷', '集团总部-流程与信息管理部-系统管理中心', '', 0, 1,
                 'wenting', '闻婷',
        '2017-08-05 16:54:07', '2017-08-05 16:54:24');

-- -----------------------------------------------------
-- Table `itplus`.`employee_type` 人员类型
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`employee_type` (
  `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_text`     VARCHAR(20)     NOT NULL
  COMMENT '类型名称',
  `sort`          INT             NOT NULL
  COMMENT '用于显示排序',
  `create_time`   TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time` TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

INSERT INTO `itplus`.`employee_type` VALUES (NULL, '产品', 11, NOW(), NOW());
INSERT INTO `itplus`.`employee_type` VALUES (NULL, '产品负责人', 12, NOW(), NOW());
INSERT INTO `itplus`.`employee_type` VALUES (NULL, '开发', 21, NOW(), NOW());
INSERT INTO `itplus`.`employee_type` VALUES (NULL, '开发负责人', 22, NOW(), NOW());
INSERT INTO `itplus`.`employee_type` VALUES (NULL, 'UED', 31, NOW(), NOW());
INSERT INTO `itplus`.`employee_type` VALUES (NULL, 'UED负责人', 32, NOW(), NOW());

-- -----------------------------------------------------
-- Table `itplus`.`product_employee` 产品-人员
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`product_employee` (
  `id`                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id`         BIGINT          NOT NULL
  COMMENT '产品id',
  `account_id`         VARCHAR(50)     NOT NULL
  COMMENT '账户id',
  `employee_code`      BIGINT          NOT NULL
  COMMENT '员工号',
  `employee_name`      VARCHAR(50)     NOT NULL
  COMMENT '员工姓名',
  `full_dept_path`     VARCHAR(100)    NOT NULL
  COMMENT '部门完整路径',
  `employee_type`      INT             NOT NULL
  COMMENT '人员类型：1=责任人，2=成员',
  `employee_type_id`   BIGINT          NULL
  COMMENT '人员类型 id',
  `employee_type_text` VARCHAR(20)     NULL
  COMMENT '人员类型名称',
  `status`             INT             NOT NULL
  COMMENT '状态：0=不可用，1=正常',
  `create_time`        TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`      TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

INSERT INTO itplus.product_employee (product_id, account_id, employee_code, employee_name, full_dept_path, employee_type, status, create_time, modified_time)
VALUES (1, 'wenting', 100118473, '闻婷', '集团总部-流程与信息管理部-系统管理中心', 1, 1, '2017-08-05 16:56:41', '2017-08-05 16:59:12');

-- -----------------------------------------------------
-- Table `itplus`.`product_employee_change_log` 产品-人员-变更日志
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`product_employee_change_log` (
  `id`                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id`          BIGINT          NOT NULL
  COMMENT '产品id',
  `action_change_info`  VARCHAR(500)    NULL
  COMMENT '日志信息',
  `modified_account_id` VARCHAR(50)     NULL
  COMMENT '最后修改人账户id',
  `modified_name`       VARCHAR(50)     NULL
  COMMENT '最后修改人名称',
  `create_time`         TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`       TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `itplus`.`product_comment` 产品评论
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`product_comment` (
  `id`                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id`         BIGINT          NOT NULL
  COMMENT '产品uuid',
  `parent_id`          BIGINT          NOT NULL
  COMMENT '父评论id',
  `content`            VARCHAR(1500)   NULL
  COMMENT '内容',
  `account_id`         VARCHAR(50)     NOT NULL
  COMMENT '用户名',
  `employee_code`      BIGINT          NOT NULL
  COMMENT '员工号',
  `employee_name`      VARCHAR(50)     NOT NULL
  COMMENT '员工姓名',
  `full_dept_path`     VARCHAR(100)    NOT NULL
  COMMENT '部门完整路径',
  `employee_type_id`   BIGINT          NULL
  COMMENT '人员类型 id',
  `employee_type_text` VARCHAR(20)     NULL
  COMMENT '人员类型名称',
  `level_num`          INT             NOT NULL
  COMMENT '楼层数',
  `ip`                 VARCHAR(32)     NULL
  COMMENT 'IP',
  `reply_type`         INT             NOT NULL
  COMMENT '0未回复，1已回复',
  `status`             INT             NOT NULL
  COMMENT '状态：0=不可用，1=正常',
  `create_time`        TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`      TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

--
--
--
--
-- -----------------------------------------------------
-- Table `itplus`.`program` 项目
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`program` (
  `id`                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id`          BIGINT          NOT NULL
  COMMENT '归属产品',
  `product_name`        VARCHAR(100)    NOT NULL
  COMMENT '归属产品名称',
  `product_code`        VARCHAR(100)    NOT NULL
  COMMENT '产品CODE',
  `name`                VARCHAR(100)    NOT NULL
  COMMENT '项目名称',
  `descp`               VARCHAR(3000)   NULL
  COMMENT '项目描述',
  `commit_date`         DATETIME        NOT NULL
  COMMENT '立项日期',
  `start_date`          DATETIME        NOT NULL
  COMMENT '启动日期',
  `gray_release_date`   DATETIME        NOT NULL
  COMMENT '灰度日期',
  `release_date`        DATETIME        NOT NULL
  COMMENT '发布日期',
  `ued_date`            DATETIME        NOT NULL
  COMMENT 'UED日期',
  `architecture_date`   DATETIME        NOT NULL
  COMMENT '架构日期',
  `like_product`        VARCHAR(500)    NULL
  COMMENT '关联产品id字符串，e.g. 1,2,3,...',
  `like_program`        VARCHAR(500)    NULL
  COMMENT '关联项目id字符串，e.g. 1,2,3,...',
  `type`                INT             NOT NULL
  COMMENT '公开类型：0=私有，1=公开',
  `program_status`      INT             NOT NULL
  COMMENT '状态：0=未开始，1=进行中，2=已完成',
  `modified_account_id` VARCHAR(50)     NULL
  COMMENT '最后修改人账户id',
  `modified_name`       VARCHAR(50)     NULL
  COMMENT '最后修改人名称',
  `create_time`         TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`       TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

INSERT INTO itplus.program (product_id, product_name, product_code, name, descp, commit_date, start_date, gray_release_date, release_date, ued_date, architecture_date, like_product, like_program, type, program_status, modified_account_id, modified_name, create_time, modified_time)
VALUES
  (1, 'IT+平台', NULL, 'IT+1.0', '平台搭建，提供产品项目管理功能。', '2017-07-19 00:00:00', '2017-08-07 00:00:00', '2017-09-07 00:00:00',
      '2017-09-11 00:00:00', '2017-09-12 00:00:00','2017-09-13 00:00:00','', NULL, 1, 1, 'wenting', '闻婷', '2017-09-07 11:55:30', '2017-09-07 11:55:30');

-- -----------------------------------------------------
-- Table `itplus`.`program_employee` 项目-人员
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`program_employee` (
  `id`                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `program_id`         BIGINT          NOT NULL
  COMMENT '项目uuid',
  `account_id`         VARCHAR(50)     NOT NULL
  COMMENT '账户id',
  `employee_code`      BIGINT          NOT NULL
  COMMENT '员工号',
  `employee_name`      VARCHAR(50)     NOT NULL
  COMMENT '员工姓名',
  `full_dept_path`     VARCHAR(100)    NOT NULL
  COMMENT '部门完整路径',
  `employee_type`      INT             NOT NULL
  COMMENT '人员类型：1=责任人，2=成员',
  `employee_type_id`   BIGINT          NULL
  COMMENT '人员类型 id',
  `employee_type_text` VARCHAR(20)     NULL
  COMMENT '人员类型名称',
  `status`             INT             NOT NULL
  COMMENT '状态：0=不可用，1=正常',
  `create_time`        TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`      TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `itplus`.`program_employee_change_log` 项目-人员-变更日志
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`program_employee_change_log` (
  `id`                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `program_id`          BIGINT          NOT NULL
  COMMENT '项目id',
  `action_change_info`  VARCHAR(500)    NULL
  COMMENT '日志信息',
  `modified_account_id` VARCHAR(50)     NULL
  COMMENT '最后修改人账户id',
  `modified_name`       VARCHAR(50)     NULL
  COMMENT '最后修改人名称',
  `create_time`         TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`       TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `itplus`.`program_comment` 项目评论
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`program_comment` (
  `id`                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `program_id`         BIGINT          NOT NULL
  COMMENT '项目id',
  `parent_id`          BIGINT          NOT NULL
  COMMENT '父评论id',
  `content`            VARCHAR(1500)   NULL
  COMMENT '内容',
  `account_id`         VARCHAR(50)     NOT NULL
  COMMENT '用户名',
  `employee_code`      BIGINT          NOT NULL
  COMMENT '员工号',
  `employee_name`      VARCHAR(50)     NOT NULL
  COMMENT '员工姓名',
  `full_dept_path`     VARCHAR(100)    NOT NULL
  COMMENT '部门完整路径',
  `employee_type_id`   BIGINT          NULL
  COMMENT '人员类型 id',
  `employee_type_text` VARCHAR(20)     NULL
  COMMENT '人员类型名称',
  `level_num`          INT             NOT NULL
  COMMENT '楼层数',
  `ip`                 VARCHAR(32)     NULL
  COMMENT 'IP',
  `reply_type`         INT             NOT NULL
  COMMENT '0未回复，1已回复',
  `status`             INT             NOT NULL
  COMMENT '状态：0=不可用，1=正常',
  `create_time`        TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`      TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

--
--
--
--
-- -----------------------------------------------------
-- Table `itplus`.`demand` 需求
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`demand` (
  `id`                     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `feed_back_id`           BIGINT          NULL
  COMMENT '归属反馈id',
  `relation_id`            BIGINT          NOT NULL
  COMMENT '归属产品/项目id',
  `relation_type`          INT             NOT NULL
  COMMENT '归属类型，1=产品，2=项目',
  `name`                   VARCHAR(100)    NOT NULL
  COMMENT '需求名称',
  `descp`                  VARCHAR(5000)   NULL
  COMMENT '需求描述',
  `hope_date`              DATETIME        NOT NULL
  COMMENT '期望完成日期',
  `callon_account_id`      VARCHAR(50)     NOT NULL
  COMMENT '指派人用户名',
  `callon_employee_code`   BIGINT          NOT NULL
  COMMENT '指派人员工号',
  `callon_employee_name`   VARCHAR(50)     NOT NULL
  COMMENT '指派人员工姓名',
  `callon_full_dept_path`  VARCHAR(100)    NOT NULL
  COMMENT '指派人部门完整路径',
  `drafted_account_id`     VARCHAR(50)     NULL
  COMMENT '发起人用户名',
  `drafted_employee_code`  BIGINT          NULL
  COMMENT '发起人员工号',
  `drafted_employee_name`  VARCHAR(50)     NULL
  COMMENT '发起人员工姓名',
  `drafted_full_dept_path` VARCHAR(100)    NULL
  COMMENT '发起人部门完整路径',
  `cc_account`             VARCHAR(500)    NULL
  COMMENT '抄送人账号字符串，e.g. xx,xx,',
  `like_product`           VARCHAR(500)    NULL
  COMMENT '关联产品id字符串，e.g. 1,2,3,...',
  `like_program`           VARCHAR(500)    NULL
  COMMENT '关联项目id字符串，e.g. 1,2,3,...',
  `level`                  INT             NOT NULL
  COMMENT '优先级：0=低，1=中，2=高，3=非常高',
  `status`                 INT             NOT NULL
  COMMENT '状态：0=已取消，1=已关闭，2=待处理，3=处理中，4=已完成',
  `channel`                INT             NOT NULL
  COMMENT '渠道: 0=PC，1=OA，2=龙信，3=龙客',
  `modified_account_id`    VARCHAR(50)     NULL
  COMMENT '最后修改人账户id',
  `modified_name`          VARCHAR(50)     NULL
  COMMENT '最后修改人名称',
  `create_time`            TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`          TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `itplus`.`demand_file` 需求附件
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`demand_file` (
  `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `demand_id`   BIGINT          NULL
  COMMENT '需求id',
  `file_name`   VARCHAR(500)    NULL
  COMMENT '文件名称',
  `file_suffix` VARCHAR(50)     NULL
  COMMENT '文件扩展名',
  `file_size`   VARCHAR(50)     NULL
  COMMENT '文件大小',
  `file_path`   TEXT            NULL
  COMMENT '文件绝对路径',
  `create_time` TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `itplus`.`demand_change_log` 需求-变更日志
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`demand_change_log` (
  `id`                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `demand_id`           BIGINT          NOT NULL
  COMMENT '需求id',
  `bef_descp`           VARCHAR(5000)   NULL
  COMMENT '改之前需求描述',
  `action_change_info`  VARCHAR(500)    NULL
  COMMENT '日志信息',
  `type`                INT             NOT NULL
  COMMENT '日志修改类型：1=描述修改，2=其它修改',
  `modified_account_id` VARCHAR(50)     NULL
  COMMENT '最后修改人账户id',
  `modified_name`       VARCHAR(50)     NULL
  COMMENT '最后修改人名称',
  `create_time`         TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`       TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `itplus`.`demand_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`demand_comment` (
  `id`             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `demand_id`      BIGINT          NOT NULL
  COMMENT '需求id',
  `parent_id`      BIGINT          NOT NULL
  COMMENT '父评论id',
  `content`        VARCHAR(1500)   NULL
  COMMENT '内容',
  `account_id`     VARCHAR(50)     NOT NULL
  COMMENT '用户名',
  `employee_code`  BIGINT          NOT NULL
  COMMENT '员工号',
  `employee_name`  VARCHAR(50)     NOT NULL
  COMMENT '员工姓名',
  `full_dept_path` VARCHAR(100)    NOT NULL
  COMMENT '部门完整路径',
  `level_num`      INT             NOT NULL
  COMMENT '楼层数',
  `ip`             VARCHAR(32)     NULL
  COMMENT 'IP',
  `reply_type`     INT             NOT NULL
  COMMENT '0未回复，1已回复',
  `status`         INT             NOT NULL
  COMMENT '状态：0=不可用，1=正常',
  `create_time`    TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`  TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

--
--
--
--
-- -----------------------------------------------------
-- Table `itplus`.`bug_info` bug
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`bug_info` (
  `id`                     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `feed_back_id`           BIGINT          NULL
  COMMENT '归属反馈id',
  `relation_id`            BIGINT          NOT NULL
  COMMENT '归属产品/项目id',
  `relation_type`          INT             NOT NULL
  COMMENT '归属类型，1=产品，2=项目',
  `name`                   VARCHAR(100)    NOT NULL
  COMMENT 'BUG名称',
  `descp`                  VARCHAR(5000)   NULL
  COMMENT 'BUG描述',
  `reproduction_step`      VARCHAR(1000)   NULL
  COMMENT '复现步骤',
  `brower`                 VARCHAR(50)     NULL
  COMMENT '浏览器',
  `hope_date`              DATETIME        NOT NULL
  COMMENT '期望完成日期',
  `callon_account_id`      VARCHAR(50)     NOT NULL
  COMMENT '指派人用户名',
  `callon_employee_code`   BIGINT          NOT NULL
  COMMENT '指派人员工号',
  `callon_employee_name`   VARCHAR(50)     NOT NULL
  COMMENT '指派人员工姓名',
  `callon_full_dept_path`  VARCHAR(100)    NOT NULL
  COMMENT '指派人部门完整路径',
  `drafted_account_id`     VARCHAR(50)     NULL
  COMMENT '发起人用户名',
  `drafted_employee_code`  BIGINT          NULL
  COMMENT '发起人员工号',
  `drafted_employee_name`  VARCHAR(50)     NULL
  COMMENT '发起人员工姓名',
  `drafted_full_dept_path` VARCHAR(100)    NULL
  COMMENT '发起人部门完整路径',
  `cc_account`             VARCHAR(500)    NULL
  COMMENT '抄送人账号字符串，e.g. xx,xx,',
  `like_product`           VARCHAR(500)    NULL
  COMMENT '关联产品id字符串，e.g. 1,2,3,...',
  `like_program`           VARCHAR(500)    NULL
  COMMENT '关联项目id字符串，e.g. 1,2,3,...',
  `level`                  INT             NOT NULL
  COMMENT '优先级：0=低，1=中，2=高，3=非常高',
  `status`                 INT             NOT NULL
  COMMENT '状态：0=已取消，1=已关闭，2=待处理，3=处理中，4=已完成',
  `channel`                INT             NOT NULL
  COMMENT '渠道: 0=PC，1=OA，2=龙信，3=龙客',
  `modified_account_id`    VARCHAR(50)     NULL
  COMMENT '最后修改人账户id',
  `modified_name`          VARCHAR(50)     NULL
  COMMENT '最后修改人名称',
  `create_time`            TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`          TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `itplus`.`bug_file` BUG附件
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`bug_file` (
  `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bug_id`      BIGINT          NULL
  COMMENT 'BUGid',
  `file_name`   VARCHAR(500)    NULL
  COMMENT '文件名称',
  `file_suffix` VARCHAR(50)     NULL
  COMMENT '文件扩展名',
  `file_size`   VARCHAR(50)     NULL
  COMMENT '文件大小',
  `file_path`   TEXT            NULL
  COMMENT '文件绝对路径',
  `create_time` TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `itplus`.`bug_change_log` bug-变更日志
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`bug_change_log` (
  `id`                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bug_id`              BIGINT          NOT NULL
  COMMENT 'bug id',
  `bef_descp`           VARCHAR(5000)   NULL
  COMMENT '改之前bug描述',
  `action_change_info`  VARCHAR(500)    NULL
  COMMENT '日志信息',
  `type`                INT             NOT NULL
  COMMENT '日志修改类型：1=描述修改，2=其它修改',
  `modified_account_id` VARCHAR(50)     NULL
  COMMENT '最后修改人账户id',
  `modified_name`       VARCHAR(50)     NULL
  COMMENT '最后修改人名称',
  `create_time`         TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`       TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `itplus`.`bug_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`bug_comment` (
  `id`             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bug_id`         BIGINT          NOT NULL
  COMMENT 'BUGid',
  `parent_id`      BIGINT          NOT NULL
  COMMENT '父评论id',
  `content`        VARCHAR(1500)   NULL
  COMMENT '内容',
  `account_id`     VARCHAR(50)     NOT NULL
  COMMENT '用户名',
  `employee_code`  BIGINT          NOT NULL
  COMMENT '员工号',
  `employee_name`  VARCHAR(50)     NOT NULL
  COMMENT '员工姓名',
  `full_dept_path` VARCHAR(100)    NOT NULL
  COMMENT '部门完整路径',
  `level_num`      INT             NOT NULL
  COMMENT '楼层数',
  `ip`             VARCHAR(32)     NULL
  COMMENT 'IP',
  `reply_type`     INT             NOT NULL
  COMMENT '0未回复，1已回复',
  `status`         INT             NOT NULL
  COMMENT '状态：0=不可用，1=正常',
  `create_time`    TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`  TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `itplus`.`feed_back` 反馈
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`feed_back` (
  `id`                      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id`              BIGINT          NOT NULL
  COMMENT '归属产品',
  `name`                    VARCHAR(100)    NOT NULL
  COMMENT '产品名称',
  `product_code`            VARCHAR(100)    NOT NULL
  COMMENT '产品CODE',
  `problem_title`           VARCHAR(100)    NULL
  COMMENT '问题标题',
  `problem_descp`           VARCHAR(5000)   NULL
  COMMENT '问题描述',
  `reproduction_step`       VARCHAR(1000)   NULL
  COMMENT '复现步骤',
  `sys_environment`         VARCHAR(100)    NULL
  COMMENT '系统环境',
  `contact_account_id`      VARCHAR(50)     NOT NULL
  COMMENT '接口人账户id',
  `contact_employee_code`   BIGINT          NOT NULL
  COMMENT '接口人员工号',
  `contact_employee_name`   VARCHAR(50)     NOT NULL
  COMMENT '接口人员工姓名',
  `contact_full_dept_path`  VARCHAR(100)    NOT NULL
  COMMENT '接口人部门完整路径',
  `type`                    INT             NOT NULL
  COMMENT '反馈类型：0=功能异常，1=功能建议',
  `status`                  INT             NOT NULL
  COMMENT '状态：0=已取消，1=已关闭，2=待处理，3=处理中，4=已完成',
  `channel`                 INT             NOT NULL
  COMMENT '渠道: 0=PC，1=OA，2=龙信，3=龙客',
  `modified_account_id`     VARCHAR(50)     NULL
  COMMENT '提交人账户id',
  `modified_employee_code`  BIGINT          NULL
  COMMENT '提交人员工号',
  `modified_name`           VARCHAR(50)     NULL
  COMMENT '提交人名称',
  `modified_full_dept_path` VARCHAR(100)    NULL
  COMMENT '提交人部门完整路径',
  `file_path`               TEXT   NULL
  COMMENT '上传文件路径',
  `create_time`             TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`           TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;









