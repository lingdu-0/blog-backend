/*
MySQL Backup
Database: myblog
Backup Time: 2020-05-30 15:07:36
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `myblog`.`t_album`;
DROP TABLE IF EXISTS `myblog`.`t_article`;
DROP TABLE IF EXISTS `myblog`.`t_article_type`;
DROP TABLE IF EXISTS `myblog`.`t_comment`;
DROP TABLE IF EXISTS `myblog`.`t_essay`;
DROP TABLE IF EXISTS `myblog`.`t_leave_word`;
DROP TABLE IF EXISTS `myblog`.`t_log`;
DROP TABLE IF EXISTS `myblog`.`t_user`;
DROP TABLE IF EXISTS `myblog`.`t_zan`;
CREATE TABLE `t_album` (
                           `album_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片编号',
                           `album_title` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '图片标题',
                           `album_date` datetime NOT NULL COMMENT '图片创建时间',
                           `album_address` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '图片标志地点',
                           `album_img` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '图片地址',
                           PRIMARY KEY (`album_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;
CREATE TABLE `t_article` (
                             `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章编号',
                             `article_type_id` int(11) NOT NULL DEFAULT '0' COMMENT '文章类别编号',
                             `article_title` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '文章标题',
                             `article_date` datetime NOT NULL COMMENT '文章发布时间',
                             `article_detail` text COLLATE utf8_bin NOT NULL COMMENT '文章内容',
                             `article_describe` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文章摘要',
                             `article_access` int(10) unsigned DEFAULT '0' COMMENT '文章访问量',
                             `article_img` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文章图片',
                             `article_state` int(1) NOT NULL DEFAULT '0' COMMENT '文章状态 0、未发布 1、已发布',
                             PRIMARY KEY (`article_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;
CREATE TABLE `t_article_type` (
                                  `article_type_id` int(11) NOT NULL AUTO_INCREMENT,
                                  `article_type_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '文章类别',
                                  PRIMARY KEY (`article_type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;
CREATE TABLE `t_comment` (
                             `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论编号',
                             `comment_type` int(1) NOT NULL COMMENT '评论类别 1、文章；2、随笔',
                             `comment_date` datetime NOT NULL COMMENT '评论时间',
                             `comment_detail` tinytext COLLATE utf8_bin NOT NULL COMMENT '评论内容',
                             `compose_id` int(11) NOT NULL COMMENT '作品编号',
                             `user_id` int(11) NOT NULL COMMENT '评论用户编号',
                             PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
CREATE TABLE `t_essay` (
                           `essay_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '随笔编号',
                           `essay_title` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '随笔标题',
                           `essay_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '随笔发布时间',
                           `essay_detail` text COLLATE utf8_bin NOT NULL COMMENT '随笔内容',
                           `essay_state` int(1) NOT NULL COMMENT '随笔状态 0、未发布；1、已发布',
                           `essay_img` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '随笔图片',
                           PRIMARY KEY (`essay_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;
CREATE TABLE `t_leave_word` (
                                `leave_word_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言编号',
                                `leave_word_date` datetime NOT NULL COMMENT '留言时间',
                                `leave_word_detail` text COLLATE utf8_bin COMMENT '留言内容',
                                `user_id` int(11) NOT NULL COMMENT '留言者',
                                PRIMARY KEY (`leave_word_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;
CREATE TABLE `t_log` (
                         `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志编号',
                         `log_date` datetime NOT NULL COMMENT '日志时间',
                         `log_operation` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '所进行的操作',
                         `log_ip` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '当前用户用户IP',
                         `user_id` int(11) NOT NULL COMMENT '当前操作用户',
                         PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
CREATE TABLE `t_user` (
                          `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
                          `username` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
                          `password` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '密码',
                          `user_email` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '用户邮箱',
                          `user_level` int(1) NOT NULL DEFAULT '0' COMMENT '用户级别',
                          `user_state` int(1) NOT NULL DEFAULT '0' COMMENT '用户状态0正常1禁用',
                          PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
CREATE TABLE `t_zan` (
                         `zan_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞编号',
                         `zan_type` int(1) NOT NULL COMMENT '点赞作品类型 0、文章；1、随笔；3、相册',
                         `compose_id` int(11) NOT NULL COMMENT '作品编号',
                         `user_id` int(11) NOT NULL COMMENT '点赞用户编号',
                         `zan_state` int(1) NOT NULL COMMENT '点赞状态 0、取消；1、点赞',
                         PRIMARY KEY (`zan_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
