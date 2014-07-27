-- create activity
CREATE TABLE `wanba_activity` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `endTime` datetime DEFAULT NULL,
 `gameId` bigint(20) DEFAULT NULL,
 `sourceType` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '0为微信礼包1为其他',
 `intro` text,
 `startTime` datetime DEFAULT NULL,
 `plat` varchar(5) NOT NULL COMMENT '平台标识',
 PRIMARY KEY (`id`)
);
CREATE INDEX wanba_activity_plat ON wanba_activity(plat) ;

-- create wanba-activity-item
CREATE TABLE `wanba_activity_item` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `accountNumber` int(11) NOT NULL DEFAULT '0',
 `availableNumber` int(11) NOT NULL DEFAULT '0',
 `triggerTime` datetime DEFAULT NULL,
 `activityId` bigint(20) DEFAULT NULL,
 PRIMARY KEY (`id`)
);
CREATE INDEX wanba_activity_item_activityId ON wanba_activity_item(activityId) ;
CREATE INDEX wanba_activity_item_trigger ON wanba_activity_item(triggerTime,activityId) ;

-- wanba_activity_account
CREATE TABLE `wanba_activity_account` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `account` varchar(255) DEFAULT NULL,
 `activityId` bigint(20) DEFAULT NULL,
 `updateTime` datetime DEFAULT NULL,
 `userIp` varchar(255) DEFAULT NULL,
 `userName` varchar(255) DEFAULT NULL,
 `statusFlag` tinyint(4) NOT NULL DEFAULT '1',
 PRIMARY KEY (`id`),
);
CREATE INDEX wanba_activity_account_id_name ON wanba_activity_account(activityId,userName) ;

-- activity_limit_code
CREATE TABLE `activity_limit_code` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `activityId` bigint(20) DEFAULT NULL,
 `updateTime` datetime DEFAULT NULL,
 `userId` bigint(20) NOT NULL,
 `statusFlag` tinyint(4) NOT NULL DEFAULT '1',
 PRIMARY KEY (`id`)
);
CREATE INDEX activity_limit_code_id ON activity_limit_code(activityId,userId) ;

-- common-dict
CREATE TABLE `common_dict` (
 `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 `dictType` varchar(30) NOT NULL COMMENT '类型',
 `dictKey` varchar(50) NOT NULL COMMENT 'key',
 `dictValue` text NOT NULL COMMENT 'value',
 `dictOrder` varchar(20) DEFAULT NULL COMMENT '排序',
 PRIMARY KEY (`id`)
)