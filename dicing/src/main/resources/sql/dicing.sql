select * from deskaward;
select * from desk;
select * from award;
select * from dicinglog;
select * from user order by deskNo asc;

-- 桌子信息
delete from desk;
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(1,0,1,0,-1,'2021-09-15 16:45:26.0');
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(2,0,1,0,-1,'2021-09-15 16:45:26.0');
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(3,0,1,0,-1,'2021-09-15 16:45:26.0');
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(4,0,1,0,-1,'2021-09-15 16:45:26.0');
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(5,0,1,0,-1,'2021-09-15 16:45:26.0');
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(6,0,1,0,-1,'2021-09-15 16:45:26.0');
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(7,0,1,0,-1,'2021-09-15 16:45:26.0');
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(8,0,1,0,-1,'2021-09-15 16:45:26.0');
insert into `desk`(`deskNo`,`state`,`roundNo`,`curSeqNo`,`noRemainingRoundNo`,`startTime`) values(9,0,1,0,-1,'2021-09-15 16:45:26.0');

-- 每桌奖品信息
delete from deskaward;
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,0,32,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,0,16,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,0,8,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,0,4,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,0,2,1);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,0,1,1);

insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,0,32,2);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,0,16,2);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,0,8,2);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,0,4,2);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,0,2,2);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,0,1,2);

insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,0,32,3);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,0,16,3);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,0,8,3);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,0,4,3);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,0,2,3);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,0,1,3);

insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,0,32,4);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,0,16,4);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,0,8,4);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,0,4,4);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,0,2,4);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,0,1,4);

insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,0,32,5);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,0,16,5);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,0,8,5);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,0,4,5);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,0,2,5);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,0,1,5);

insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,0,32,6);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,0,16,6);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,0,8,6);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,0,4,6);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,0,2,6);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,0,1,6);

insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,0,32,7);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,0,16,7);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,0,8,7);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,0,4,7);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,0,2,7);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,0,1,7);

insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,0,32,8);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,0,16,8);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,0,8,8);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,0,4,8);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,0,2,8);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,0,1,8);

insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(2,0,32,9);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(3,0,16,9);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(4,0,8,9);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(5,0,4,9);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(6,0,2,9);
insert into `deskaward`(`awardId`,`counter`,`remaining`,`deskNo`) values(7,0,1,9);

-- 删除博饼日志
delete from dicinglog;

-- 用户信息
delete from user;
-- 第1桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2700,'13806008007',1,1,0,'邓志刚','https://hwcimg2.fcxms.com/b2fc8d8e1c2d6520e3e111fb8b8ec16d.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1,'15060789371',1,2,0,'肖承英','https://hwcimg2.fcxms.com/9ff259366be9f3da43c47dc42470684f.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(969402,'18950155892',1,3,0,'刘雪娇','https://hwcimg2.fcxms.com/9565854295badfbf905d81cf08892d8b.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(987104,'18020758078',1,4,0,'黄李玲','https://hwcimg2.fcxms.com/a07d4b83c2002d26e61bec415ceb6577.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(946066,'18060962819',1,5,0,'廖海清','https://hwcimg2.fcxms.com/4a47d376fd816c05f63bd08d50173f3e.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2437227,'18960066359',1,6,0,'何榕灿','https://hwcimg2.fcxms.com/263bb58f5b59eea2e454e6de992d3300.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2515931,'17318578826',1,7,0,'陈丽媛','https://hwcimg2.fcxms.com/55947d69bd4b9feea0afe847976f4943.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2876437,'17305925307',1,8,0,'曹泽昌','https://hwcimg2.fcxms.com/8f910d79135132988d8a97ea35a7a71b.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(7924705,'18059047877',1,9,0,'许佳云','https://hwtimg2.fcxms.com/e082619e23496c3562d1912a2ee1bfdb.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21423023,'17350028463',1,10,0,'赵娜','https://hwcimg2.fcxms.com/eaef6dc431f4f134f6fd44528a4cdd78.jpg',null);

-- 第2桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(34204,'18950077451',2,1,0,'蔡秀梅','http://hwcimg2.fcxms.com/6c9e6b1659f3fcb2f078492c7f9cb6c2.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(779504,'18959278172',2,2,0,'彭华根','https://hwcimg2.fcxms.com/e7d5bc5e5c559094bee2c965c469ae4b.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(823916,'18650011658',2,3,0,'许报恩','https://hwcimg2.fcxms.com/a9bd3543d4d534a184fa831723657c4b.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1387932,'18094011052',2,4,0,'黄婕','https://hwcimg2.fcxms.com/af3f2612d1fa8e73c84de9079a9f3b5a.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1980968,'13310837119',2,5,0,'廖益峰','https://hwcimg2.fcxms.com/67783e22082e4778e9e066c7696c3008.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2443797,'17305925357',2,6,0,'顾立栋','https://hwcimg2.fcxms.com/2286b15e3f018d89506301f763f64658.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2571466,'17305925363',2,7,0,'陈海源','https://hwcimg2.fcxms.com/0ac0f86dcd72f93119628ec333c37f6f.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2900885,'18020757908',2,8,0,'林文昌','https://hwcimg2.fcxms.com/d55473077c0a019f6f94061944bf8bc8.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2831419,'13395031365',2,9,0,'谢晨翔','https://hwcimg2.fcxms.com/bd1a82153ef5060fb49b4e5c5b9415d0.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21433597,'17305925325',2,10,0,'陈玉谨','https://hwcimg2.fcxms.com/e5c344612514f91b644b338d00c4d777.jpg',null);

-- 第3桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(15,'15960835767',3,1,0,'朱曙晖','https://hwcimg2.fcxms.com/2deb8816f649ebec3e198de3dbaa9430.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(18230,'18059894552',3,2,0,'刘亚昂','http://hwtimg2.fcxms.com/7a841a0176a9d2668cb62cc01f869fae.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1228652,'18850012074',3,3,0,'赖健','https://hwcimg2.fcxms.com/6cbf69e075007b11b53541dea83f1b41.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1414112,'18065982320',3,4,0,'吴俊远','http://hwcimg2.fcxms.com/f9bc932a4bba532d4082bbac74fdf442.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1966584,'18106096125',3,5,0,'阮李琦','http://hwcimg2.fcxms.com/932deb3832c412973d85189af50b0b42.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2458709,'17305925337',3,6,0,'刘经华','https://hwcimg2.fcxms.com/04bb716c51791c1347b834ca6da981e7.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2590026,'17305925330',3,7,0,'娄志辉','https://hwcimg2.fcxms.com/83f450e287ed652ec5d8a9146e8f1ae1.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2835058,'13395030652',3,8,0,'康南南','https://hwcimg2.fcxms.com/26995d3ec16981c3de3a9b9365acc2ff.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21264262,'13024804712',3,9,0,'黄智玟','https://hwcimg2.fcxms.com/951644f6f049720e40cede300395f5f9.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2503880,'18020758018',3,10,0,'刘娇茹','https://hwcimg2.fcxms.com/7d96aa71b72b3ee0be3b6e68ab275678.jpg',null);

--第4桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(679344,'15606900630',4,1,0,'李嫚莉','https://hwcimg2.fcxms.com/fb239ae7892faabb305a60bfbf7a3643.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(18271,'13799285677',4,2,0,'黄振红','https://hwcimg2.fcxms.com/a508acfedd41cffc2ad4c058bfe0d2cd.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(923996,'18650011958',4,3,0,'肖文乾','https://hwcimg2.fcxms.com/bec944c2eb38f81f86f0f7300dc0ef61.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1255134,'17305925316',4,4,0,'董嫚','https://hwcimg2.fcxms.com/dfe3c4b5b0a08e78fa145ea64e3e1742.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2031330,'17305925351',4,5,0,'杨涛','https://hwcimg2.fcxms.com/94fb5fc8434eb6c362d9d5d60378ee90.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2470405,'15060745303',4,6,0,'李文国','https://hwcimg2.fcxms.com/f657debba5ebb50853fe7a0d6587dd9b.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2641494,'17750650173',4,7,0,'马雪娇','https://hwcimg2.fcxms.com/dab9dac703a59d72bf5caa32455821e8.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2831237,'15396216872',4,8,0,'徐琳珍','https://hwcimg2.fcxms.com/905a68e905448795cf3fac6544cc19e4.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(943860,'17305925302',4,9,0,'乔斌','https://hwcimg2.fcxms.com/4317f015efed268b568598fdc4fc1bbb.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21442361,'15259203902',4,10,0,'钟海英','https://hwcimg2.fcxms.com/4317f015efed268b568598fdc4fc1bbb.jpg',null);

--第5桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(785944,'13606016537',5,1,0,'陈家智','https://hwcimg2.fcxms.com/dfd39175ba936cd2f4a2493de31732a9.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(20278,'17305925318',5,2,0,'王静','https://hwcimg2.fcxms.com/326298df1a8b04904bec6c4ca557c289.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(938094,'13055906526',5,3,0,'吴少春','https://hwcimg2.fcxms.com/dd310b19ea1ded1e1c25f9f83c41d75d.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1230238,'17305925317',5,4,0,'陈胜祥','https://hwcimg2.fcxms.com/5ab436f2c141f2641fe30151672b1dbd.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2030378,'18822800105',5,5,0,'朱卓越','https://hwcimg2.fcxms.com/0cce893252abf2f6cf49196cefb76011.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2487723,'15392047021',5,6,0,'刘其亚','https://hwcimg2.fcxms.com/fd80d9a641e73bfb17a3a7fe7d1fd8ec.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2623898,'18150401225',5,7,0,'林东阳','https://hwcimg2.fcxms.com/f0f248f3be7fe0935028c1bf14c350c8.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2926669,'18750977836',5,8,0,'许永健','http://hwnimg2.fcxms.com/202009/c64fd6126daa2f5b142876ac190217e4.jpeg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2957223,'18106087203',5,9,0,'李延海','https://hwcimg2.fcxms.com/87367d39885c592710c7706c8768cab7.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2651836,'18060997530',5,10,0,'谷栋梁','https://hwcimg2.fcxms.com/ccf7bcdd155f42da14a27fdb5ca04fda.jpg',null);

--第6桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(39845,'13328309730',6,1,0,'王琳','https://hwcimg2.fcxms.com/6abab968ccd30ea3d6e01a9428cbdc3a.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(822440,'13960916624',6,2,0,'张伟','https://hwcimg2.fcxms.com/13f0477eb4ab6273fe73c1dcf6ec7c53.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1585930,'15160032615',6,3,0,'林斌','https://hwcimg2.fcxms.com/88164cb081c35dc47863bf3fdd7a89ef.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1679964,'18016622973',6,4,0,'杨倩','https://hwcimg2.fcxms.com/32a57add4d813307a42c2a809510ad93.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2052226,'17305925312',6,5,0,'郑婷婷','https://hwcimg2.fcxms.com/c96ffaa80088357df16f0ad11bb289a5.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2487724,'17305925315',6,6,0,'张立建','https://hwcimg2.fcxms.com/31a804cd1d1c0472091ab7d5b85c6937.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2651712,'17305925352',6,7,0,'叶斌斌','https://hwcimg2.fcxms.com/74e1e7cd5be243674b489312a5f5c0ec.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2965419,'18106931293',6,8,0,'程睿','https://hwcimg2.fcxms.com/a91a5f1d3d7e263a9201895a4b649ddb.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2613133,'17306012986',6,9,0,'张豪','https://hwcimg2.fcxms.com/0d04d6c3fd587f4e6cd6aa0fb6360e4c.jpg',null);

-- 第7桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(856744,'13959278331',7,1,0,'何海','https://hwcimg2.fcxms.com/15a73f4b9c6d6e8fc72f1b2f6c707d94.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(701650,'18571642180',7,2,0,'万奇','https://hwcimg2.fcxms.com/be7e1277e4a7f084bde0d5b438114999.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1154220,'15605927020',7,3,0,'陈振强','https://hwcimg2.fcxms.com/8e38592f69ee9f2d08be44cfbca8a204.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1710656,'17305925361',7,4,0,'毛子香','https://hwcimg2.fcxms.com/6c8378d387e79f05ccdb9c168cc92091.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2064628,'13163928500',7,5,0,'张甄妮','https://hwcimg2.fcxms.com/eb2c26e0f1f5155f9f3e749c8a6082d8.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2502041,'18559736378',7,6,0,'刘文娟','https://hwcimg2.fcxms.com/70e694de28ffb9ced49d17bf05e3abb0.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2698504,'17305925331',7,7,0,'廖鹏','https://hwcimg2.fcxms.com/925be4b5ef4f83152b4d34d8cfc85b9b.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2985781,'18059808595',7,8,0,'雷丽云','https://hwcimg2.fcxms.com/8b0c563d389294d1a2fa650f60241720.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21393652,'18106095323',7,9,0,'余梦琴','https://hwcimg2.fcxms.com/a1a6ed7fb7204fccce982231d4fcc112.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21468831,'17305925335',7,10,0,'刘辉','',null);

--第8桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(3157,'13763850029',8,1,0,'阙彩煌','https://hwcimg2.fcxms.com/e67fd7c43d25425e8e46a04c3df27bb8.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(712066,'18050406928',8,2,0,'胡福发','https://hwcimg2.fcxms.com/a958bf76f575d3daa13749844386f196.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1171802,'17305925310',8,3,0,'连文河','https://hwcimg2.fcxms.com/db8e2366c4e2a1e2ec6831f48b49074e.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1867776,'13559651987',8,4,0,'饶一靖','https://hwcimg2.fcxms.com/b6968a1fe49e7537d063721aad814600.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(996786,'15392037375',8,5,0,'周青青','https://hwcimg2.fcxms.com/d6cc735f4718f8a59165e3ec44e0385f.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2513337,'15980995283',8,6,0,'张俊烽','https://hwcimg2.fcxms.com/bb806d633e8d5506887ce1f1c98d3c79.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2759781,'18505999669',8,7,0,'郑仁介','https://hwcimg2.fcxms.com/685950040894760dbcc2f01a4e1ca207.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2997625,'18759206100',8,8,0,'王文婷','https://hwuimg2.fcxms.com/screenshot/a13dda5a103ba87a5d05565543d63a13.png',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2597830,'17305925350',8,9,0,'马晓红','https://hwcimg2.fcxms.com/da5bdad550b7c6e2eac0ebf938ac0b11.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21487345,'13665055919',8,10,0,'郑伟','',null);

-- 第9桌
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(3036,'17305925320',9,1,0,'屈翠翠','https://hwcimg2.fcxms.com/997fbe0efcfb8119d98b04231a487602.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(743088,'18396534833',9,2,0,'文秀','http://hwuimg2.fcxms.com/screenshot/9de736018973dde0f9edfa9bfd599c80.png',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1226708,'15259207089',9,3,0,'杨明泉','https://hwcimg2.fcxms.com/7b9b44d9283dc6d24fe8f5595849b71b.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(1451018,'18050102232',9,4,0,'黄文彬','https://hwcimg2.fcxms.com/dae06236e36ac7aedb9352b060495686.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2218192,'15980891257',9,5,0,'林小燕','https://hwcimg2.fcxms.com/d5da9e4997690549b36077c64d0a55b8.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2513233,'18059026749',9,6,0,'林燕春','https://hwcimg2.fcxms.com/dc4c41e0bf35377f1f5ee3268811f0a5.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(2764644,'15285031803',9,7,0,'夏行会','https://hwcimg2.fcxms.com/b24e2ea8f8104764d930937e64a33039.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(3010271,'15396203719',9,8,0,'罗丹玲','https://hwcimg2.fcxms.com/b24e2ea8f8104764d930937e64a33039.jpg',null);
insert into `user`(`userId`,`phone`,`deskNo`,`seqNo`,`ready`,`userName`,`avator`,`orgName`) values(21399374,'15060245090',9,9,0,'姚艳萍','',null);