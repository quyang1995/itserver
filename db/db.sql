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
  `descp`                  VARCHAR(500)    NULL
  COMMENT '产品描述',
  `contact_account_id`     VARCHAR(50)     NOT NULL
  COMMENT '接口人账户id',
  `contact_employee_code`  BIGINT          NOT NULL
  COMMENT '接口人员工号',
  `contact_employee_name`  VARCHAR(50)     NOT NULL
  COMMENT '接口人员工姓名',
  `contact_full_dept_path` VARCHAR(100)    NOT NULL
  COMMENT '接口人部门完整路径',
  `like_product`           VARCHAR(500)    NULL
  COMMENT '关联产品id字符串，e.g. xx,xx,xx,...',
  `type`                   INT             NOT NULL
  COMMENT '公开类型：0=私有，1=公开',
  `status`                 INT             NOT NULL
  COMMENT '状态：0=已关闭，1=未关闭',
  `modified_account_id`    VARCHAR(50)     NULL
  COMMENT '最后修改人账户id',
  `modified_name`          VARCHAR(50)     NULL
  COMMENT '最后修改人名称',
  `create_time`            TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  `modified_time`          TIMESTAMP                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;


INSERT INTO itplus.product (name, descp, contact_account_id, contact_employee_code, contact_employee_name, contact_full_dept_path, like_product, type, status, modified_account_id, modified_name, create_time, modified_time)
VALUES ('productName', '描述', 'zhangchi4', 1000000111, '小白', 'j集团总部-流程与信息中心', ',11，22', 1, 1, 'zhangchi4', '小黑',
        '2017-08-05 16:54:07', '2017-08-05 16:54:24');
INSERT INTO itplus.product (name, descp, contact_account_id, contact_employee_code, contact_employee_name, contact_full_dept_path, like_product, type, status, modified_account_id, modified_name, create_time, modified_time)
VALUES ('产品222', NULL, 'jojo', 1000000111, '小白', 'j集团总部-流程与信息中心', ',11，22', 0, 1, 'jojo', '小黑', '2017-08-05 16:55:12',
        '2017-08-05 17:04:32');
INSERT INTO itplus.product (name, descp, contact_account_id, contact_employee_code, contact_employee_name, contact_full_dept_path, like_product, type, status, modified_account_id, modified_name, create_time, modified_time)
VALUES ('产品AAA', NULL, 'zhangchi4', 1000000111, '小白', 'j集团总部-流程与信息中心', ',11，22', 0, 1, 'zhangchi4', '小黑',
        '2017-08-05 16:55:44', '2017-08-05 16:55:44');


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
VALUES (2, 'zhangchi4', 1000000111, 'x小白', '集团总部-流程', 1, 1, '2017-08-05 16:56:41', '2017-08-05 16:59:12');
INSERT INTO itplus.product_employee (product_id, account_id, employee_code, employee_name, full_dept_path, employee_type, status, create_time, modified_time)
VALUES (3, 'zhangchi4', 1000000111, 'x小白', '集团总部-流程', 2, 1, '2017-08-05 17:13:05', '2017-08-05 17:13:05');


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
ALTER TABLE `itplus`.`product_employee_change_log`
  ENGINE = MyISAM;

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
  `name`                VARCHAR(100)    NOT NULL
  COMMENT '项目名称',
  `descp`               VARCHAR(500)    NULL
  COMMENT '项目描述',
  `commit_date`         DATETIME        NOT NULL
  COMMENT '立项日期',
  `start_date`          DATETIME        NOT NULL
  COMMENT '启动日期',
  `gray_release_date`   DATETIME        NOT NULL
  COMMENT '灰度日期',
  `release_date`        DATETIME        NOT NULL
  COMMENT '发布日期',
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
ALTER TABLE `itplus`.`program_employee_change_log`
  ENGINE = MyISAM;

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
  `drafted_account_id`     VARCHAR(50)     NOT NULL
  COMMENT '发起人用户名',
  `drafted_employee_code`  BIGINT          NOT NULL
  COMMENT '发起人员工号',
  `drafted_employee_name`  VARCHAR(50)     NOT NULL
  COMMENT '发起人员工姓名',
  `drafted_full_dept_path` VARCHAR(100)    NOT NULL
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
ALTER TABLE `itplus`.`demand_change_log`
  ENGINE = MyISAM;

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
  `relation_id`            BIGINT          NOT NULL
  COMMENT '归属产品/项目id',
  `relation_type`          INT             NOT NULL
  COMMENT '归属类型，1=产品，2=项目',
  `name`                   VARCHAR(100)    NOT NULL
  COMMENT 'BUG名称',
  `descp`                  VARCHAR(5000)   NULL
  COMMENT 'BUG描述',
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
  `drafted_account_id`     VARCHAR(50)     NOT NULL
  COMMENT '发起人用户名',
  `drafted_employee_code`  BIGINT          NOT NULL
  COMMENT '发起人员工号',
  `drafted_employee_name`  VARCHAR(50)     NOT NULL
  COMMENT '发起人员工姓名',
  `drafted_full_dept_path` VARCHAR(100)    NOT NULL
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
ALTER TABLE `itplus`.`bug_change_log`
  ENGINE = MyISAM;

-- -----------------------------------------------------
-- Table `itplus`.`bug_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `itplus`.`bug_comment` (
  `id`             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `bug_id`         BIGINT          NOT NULL
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









