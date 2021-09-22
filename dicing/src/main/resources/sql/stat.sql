select dicinglog.deskNo as '桌号',user.userName as '名称',award.awardName as '奖项', count(1) '个数' from dicinglog,user,award
where dicinglog.valid = 1 and dicinglog.awardId > 1
and dicinglog.userId = user.userId
and award.awardId = dicinglog.awardId
and award.awardId > 1
group by dicinglog.userid,dicinglog.awardid
order by dicinglog.deskNo, user.seqNo
