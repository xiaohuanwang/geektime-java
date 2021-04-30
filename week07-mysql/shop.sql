-- 创建数据库
create database `shops` default character set utf8mb4 collate utf8mb4_general_ci;

-- 用户表
DROP TABLE IF EXISTS `tz_user`;

CREATE TABLE `user` (
  `user_id` varchar(36) NOT NULL DEFAULT '' COMMENT 'ID',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `user_mail` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
  `login_password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `pay_password` varchar(50) DEFAULT NULL COMMENT '支付密码',
  `user_name` varchar(50) DEFAULT NULL COMMENT '账号登陆使用的账号',
  `user_mobile` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `user_regtime` datetime NOT NULL COMMENT '注册时间',
  `user_regip` varchar(50) DEFAULT NULL COMMENT '注册IP',
  `user_memo` varchar(500) DEFAULT NULL COMMENT '备注',
  `sex` char(1) DEFAULT NULL COMMENT 'M(男) or F(女)',
  `birth_date` char(10) DEFAULT NULL COMMENT '例如：2009-11-27',
  `pic` varchar(255) DEFAULT NULL COMMENT '头像图片路径',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 1 正常 0 无效',
  `level` bigint(20) DEFAULT NULL COMMENT '会员等级（冗余字段）',
  `vip_end_time` datetime DEFAULT NULL COMMENT 'vip结束时间',
  `level_type` tinyint(4) DEFAULT NULL COMMENT '等级条件 0 普通会员 1 付费会员',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `ud_user_mail` (`user_mail`),
  UNIQUE KEY `ud_user_unique_mobile` (`user_mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE='utf8mb4_general_ci' COMMENT='用户表';

-- 商品表


DROP TABLE IF EXISTS `prod`;

CREATE TABLE `prod` (
  `prod_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `prod_name` varchar(300) NOT NULL DEFAULT '' COMMENT '商品名称',
  `ori_price` decimal(15,2) DEFAULT '0.00' COMMENT '原价',
  `price` decimal(15,2) DEFAULT NULL COMMENT '现价',
  `brief` varchar(500) DEFAULT '' COMMENT '简要描述,卖点等',
  `content` mediumtext COMMENT '详细描述',
  `pic` varchar(255) DEFAULT NULL COMMENT '商品主图',
  `video` varchar(150) DEFAULT NULL COMMENT '商品视频',
  `imgs` varchar(1000) DEFAULT NULL COMMENT '商品图片，以,分割',
  `status` int(1) DEFAULT '0' COMMENT '默认是1，表示正常状态, -1表示删除, 0下架',
  `sold_num` int(11) DEFAULT NULL COMMENT '销量',
  `total_stocks` int(11) DEFAULT '0' COMMENT '总库存',
  `create_time` datetime DEFAULT NULL COMMENT '录入时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `putaway_time` datetime DEFAULT NULL COMMENT '上架时间',
  `version` int(11) DEFAULT NULL COMMENT '版本 乐观锁',
  PRIMARY KEY (`prod_id`),
  KEY `shop_id` (`shop_id`),
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE='utf8mb4_general_ci' COMMENT='商品';


-- 订单表

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `prod_name` varchar(1000) NOT NULL DEFAULT '' COMMENT '产品名称,多个产品将会以逗号隔开',
  `user_id` varchar(36) NOT NULL COMMENT '订购用户ID',
  `addr_order_id` bigint(20) DEFAULT NULL COMMENT '用户订单地址Id',
  `order_number` varchar(50) NOT NULL COMMENT '订购流水号',
  `total` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '总值',
  `actual_total` decimal(15,2) DEFAULT NULL COMMENT '实际总值',
  `pay_type` int(11) DEFAULT NULL COMMENT '支付方式 ',
  `remarks` varchar(255) DEFAULT NULL COMMENT '买家备注',
  `shop_remarks` varchar(255) DEFAULT NULL COMMENT '卖家备注',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败',
  `dvy_id` bigint(20) DEFAULT NULL COMMENT '配送方式ID',
  `dvy_flow_id` varchar(100) DEFAULT '' COMMENT '物流单号',
  `freight_amount` decimal(15,2) DEFAULT '0.00' COMMENT '订单运费',
  `product_nums` int(11) DEFAULT NULL COMMENT '订单商品总数',
  `create_time` datetime NOT NULL COMMENT '订购时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `dvy_time` datetime DEFAULT NULL COMMENT '发货时间',
  `finally_time` datetime DEFAULT NULL COMMENT '完成时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `is_payed` tinyint(1) DEFAULT NULL COMMENT '是否已经支付，1：已经支付过，0：，没有支付过',
  `delete_status` int(11) DEFAULT '0' COMMENT '用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除',
  `refund_status` int(11) DEFAULT NULL COMMENT '订单退款状态（1:申请退款 2:退款成功 3:部分退款成功 4:退款失败）',
  `platform_amount` decimal(15,2) DEFAULT NULL COMMENT '平台优惠金额',
  `reduce_amount` decimal(15,2) DEFAULT NULL COMMENT '优惠总额',
  `close_type` tinyint(4) DEFAULT NULL COMMENT '订单关闭原因 1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易',
  `receiver_name` varchar(255) DEFAULT NULL COMMENT '收件人姓名',
  `receiver_mobile` varchar(255) DEFAULT NULL COMMENT '收件人电话',
  `receiver_addr` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_number_unique_ind` (`order_number`),
  UNIQUE KEY `order_number_userid_unique_ind` (`user_id`,`order_number`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE='utf8mb4_general_ci' COMMENT='订单表';




