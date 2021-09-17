select * from deskaward;
select * from desk;
select * from award;
select * from dicinglog;
select * from user order by deskNo asc;

-- 桌子信息
delete from desk;
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(1,0,1,0,-1,'2021-09-15 16:45:26.0');

-- 每桌奖品信息
delete from deskaward;
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,0,32,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,0,16,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,0,8,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,0,4,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,0,2,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,0,1,1);

-- 删除博饼日志
delete from dicinglog;

-- 用户信息
delete from user;
-- 第1桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2700,'13800000001',1,1,0,'测试1','https://hwcimg2.fcxms.com/b2fc8d8e1c2d6520e3e111fb8b8ec16d.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1,'13800000002',1,2,0,'测试2','https://hwcimg2.fcxms.com/9ff259366be9f3da43c47dc42470684f.jpg',null);