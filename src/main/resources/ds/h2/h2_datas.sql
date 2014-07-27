INSERT INTO `wanba_activity` (`id`, `endTime`, `gameId`, `sourceType`, `intro`, `startTime`, `plat`) VALUES
(1, '2014-07-11 16:19:44', 456, 1, 'all&activitysplithttp://aaa&activitysplit大叔大叔大叔是', '2014-04-01 13:38:16', '1'),
(2, '2014-07-28 16:23:59', 571, 1, 'all&activitysplithttp://aaa&activitysplit大是的撒的事实', '2014-06-06 16:23:56', '1'),
(3, '2014-07-28 16:25:48', 571, 1, 'all&activitysplithttp://aaa&activitysplit的撒的事实', '2014-06-13 16:25:46', '1'),
(4, '2020-07-24 18:50:41', 571, 1, 's1&activitysplithttp://aaa&activitysplit12312312', '2014-07-15 18:50:39', '1');


INSERT INTO `wanba_activity_item` (`id`, `accountNumber`, `availableNumber`, `triggerTime`, `activityId`) VALUES
(1, 100, 100, '2014-08-30 17:00:00', 1),
(2, 1, 1, '2014-08-31 17:00:00', 1),
(3, 1, 1, '2014-09-01 17:00:00', 2),
(4, 1, 1, '2014-09-02 17:00:00', 2),
(5, 1, 1, '2014-09-03 17:00:00', 3),
(6, 1, 1, '2014-09-04 17:00:00', 3),
(7, 1, 1, '2014-09-05 17:00:00', 3),
(8, 1, 1, '2014-09-06 17:00:00', 1),
(9, 1, 1, '2014-09-07 17:00:00', 1),
(10, 1, 1, '2014-09-08 17:00:00', 1),
(11, 1, 1, '2014-09-09 17:00:00', 2);


INSERT INTO `common_dict` (`id`, `dictType`, `dictKey`, `dictValue`, `dictOrder`) VALUES
(150, 'controller', 'activity_activityCard_starttime', '2013-10-16 18:00:00', NULL),
(151, 'controller', 'activity_activityCard_endtime', '2014-11-31 18:00:00,2013-09-31 18:00:00,2013-07-31 18:00:00,2013-07-31 18:00:00,2012-07-31 18:00:00,2013-07-31 18:00:00,2013-07-31 18:00:00,2013-07-31 18:00:00,2013-07-31 18:00:00', NULL);
