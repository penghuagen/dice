select * from deskaward;
select * from desk;
select * from award;
select * from dicinglog;
select * from user order by deskNo asc;

-- 桌子信息
delete from desk;
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(1,0,1,1,-1,'2025-07-31 14:40:00.0');

-- 每桌奖品信息
delete from deskaward;
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,32,32,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,16,16,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,8,8,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,4,4,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,2,2,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,1,1,1);

-- 删除博饼日志
delete from dicinglog;

-- 用户信息
delete from user;
-- 第1桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21281367,'13720892285',1,1,0,'刘国巍','https://hwcimg2.fcxms.com/b2fc8d8e1c2d6520e3e111fb8b8ec16d.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21281363,'13850083825',1,2,0,'蔡天从','https://hwcimg2.fcxms.com/9ff259366be9f3da43c47dc42470684f.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21281368,'15280010342',1,3,0,'郑秀平','https://hwcimg2.fcxms.com/9ff259366be9f3da43c47dc42470684f.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21281370,'17326503007',1,4,0,'汪金杨','https://hwcimg2.fcxms.com/9ff259366be9f3da43c47dc42470684f.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21281369,'18050083886',1,5,0,'秦刚','https://hwcimg2.fcxms.com/9ff259366be9f3da43c47dc42470684f.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(779504,'18959278172',1,6,0,'彭华根','https://hwcimg2.fcxms.com/9ff259366be9f3da43c47dc42470684f.jpg',null);