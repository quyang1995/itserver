alter table program add column demo_approval_date datetime  COMMENT 'Demo评审日期' NULL;
alter table program add column bidding_date datetime COMMENT '招标日期' NULL;
alter table program add column winning_bid_date datetime COMMENT '中标日期' NULL;
alter table program add column prod_approval_date datetime COMMENT '产品评审日期' NULL;
alter table program add column dev_approval_date datetime COMMENT '开发评审日期' NULL;
alter table program add column test_approval_date datetime COMMENT '测试评审日期' NULL;
alter table program add column online_plan_date datetime COMMENT '上线计划日期' NULL;
alter table program add column dev_type int(11) COMMENT '研发方式：1=招投标，2=自研' NULL;
alter table program add column analyzing_conditions int(11) COMMENT '判断条件：1=地产，2=商业，3=数据，4=冠寓，5=养老，6=产城，7=基础中心' NULL;
alter table program add column dev_workload int(11) COMMENT '研发工作量预估' NULL;
alter table program add column overall_cost DECIMAL(11, 2) COMMENT '整体费用预估' NULL;
alter table program add column bid_dev_workload int(11) COMMENT '研发工作量' NULL;
alter table program add column bid_overall_cost DECIMAL(11, 2) COMMENT '整体费用' NULL;
alter table program add column approval_status int(11) COMMENT '审批状态：100=审核中，110=审核通过，120=审核驳回，130=变更审核中，140=变更审核驳回' NULL;

alter table program_employee_change_log add column program_status int(11) COMMENT '项目状态：100=未立项，110=立项，120=Demo评审，130=招投标申请，140=中标申请，150=产品评审，160=开发评审，170=测试评审，180=上线计划，190=灰度发布，200=延期上线，210=需求变更，900=完成，999=终止' NULL;

CREATE TABLE IF NOT EXISTS `itplus`.`program_file` (
  `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `program_id`  BIGINT          NOT NULL
  COMMENT '项目id',
  `file_name`   VARCHAR(500)    NULL
  COMMENT '文件名称',
  `file_suffix` VARCHAR(50)     NULL
  COMMENT '文件扩展名',
  `file_size`   VARCHAR(50)     NULL
  COMMENT '文件大小',
   `type`      INT             NULL
  COMMENT '流程类型：1-立项申请，2-Demo评审，3-招标文件，4-中标通知，5-产品评审，6-进入开发，7-测试部署，8-上线计划，9-灰度发布，10-延期上线，11-变更需求，12-终止项目',
  `file_path`   TEXT            NULL
  COMMENT '文件绝对路径',
  `create_time` TIMESTAMP                DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

CREATE TABLE program_approval_snapshot (
    id                  	bigint(20) UNSIGNED AUTO_INCREMENT NOT NULL,
    product_id          	bigint(20) COMMENT '归属产品'  NOT NULL,
    product_name        	varchar(100) COMMENT '归属产品名称'  NOT NULL,
    product_code        	varchar(100) NULL,
    name                	varchar(100) NULL,
    descp               	varchar(3000) COMMENT '项目描述'  NULL,
    commit_date         	datetime NULL,
    start_date          	datetime NULL,
    gray_release_date   	datetime NULL,
    release_date        	datetime NULL,
    ued_date            	datetime NULL,
    architecture_date   	datetime NULL,
    like_product        	varchar(500) COMMENT '关联产品id字符串，e.g. 1,2,3,...'  NULL,
    like_program        	varchar(500) COMMENT '关联项目id字符串，e.g. 1,2,3,...'  NULL,
    type                	int(11) NULL,
    program_status      	int(11) NULL,
    modified_account_id 	varchar(50) COMMENT '最后修改人账户id'  NULL,
    modified_name       	varchar(50) COMMENT '最后修改人名称'  NULL,
    create_time         	timestamp NULL ,
    modified_time       	timestamp  NULL ,
    account_type        	int(11) COMMENT '账户类型：0或者null或者空位内部账号，1-供方账号'  NULL,
    demo_approval_date  	datetime COMMENT 'Demo评审日期'  NULL,
    bidding_date        	datetime COMMENT '招标日期'  NULL,
    winning_bid_date    	datetime COMMENT '中标日期'  NULL,
    prod_approval_date  	datetime COMMENT '产品评审日期'  NULL,
    dev_approval_date   	datetime COMMENT '开发评审日期'  NULL,
    test_approval_date  	datetime COMMENT '测试评审日期'  NULL,
    online_plan_date    	datetime COMMENT '上线计划日期'  NULL,
    dev_type            	int(11) COMMENT '研发方式：1=招投标，2=自研'  NULL,
    analyzing_conditions	int(11) COMMENT '判断条件：1=地产，2=商业，3=数据，4=冠寓，5=养老，6=产城，7=基础中心'  NULL,
    dev_workload        	int(11) COMMENT '研发工作量预估'  NULL,
    overall_cost        	decimal(11,2) COMMENT '整体费用预估'  NULL,
    bid_dev_workload    	int(11) COMMENT '研发工作量'  NULL,
    bid_overall_cost    	decimal(11,2) COMMENT '整体费用'  NULL,
    approval_status     	int(11) COMMENT '审批状态：100=审核中，110=审核通过，120=审核驳回，130=变更审核中，140=变更审核驳回'  NULL,
    `bpm_code`               VARCHAR(100)    NULL,
     `remark`                 VARCHAR(3000)   NOT NULL COMMENT '内容摘要',
    `approval_view`                 VARCHAR(3000)   NULL COMMENT '审核意见',
    PRIMARY KEY(id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 86
GO

