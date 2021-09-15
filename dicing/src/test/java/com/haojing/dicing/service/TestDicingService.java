package com.haojing.dicing.service;

import com.haojing.dicing.entity.Award;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yeweiping on 2020/9/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({ "unit_test" })
public class TestDicingService {
    @Autowired
    private DicingService dicingService;

    int[] yixiu = new int[] {4,1,3,2,3,2};
    int[] erju = new int[] {4,6,3,4,3,2};
    int[] sijin1 = new int[] {1,1,1,1,2,2};
    int[] sijin2 = new int[] {2,2,2,2,3,4};
    int[] sijin3 = new int[] {3,3,3,3,6,4};
    int[] sanhong = new int[] {4,4,4,1,1,1};
    int[] duitang = new int[] {1,3,4,5,6,2};
    int[] sihong1 = new int[] {5,4,4,4,4,6};
    int[] sihong2 = new int[] {4,4,4,4,5,5};
    int[] wuzi1 = new int[] {2,2,2,2,2,5};
    int[] wuzi2 = new int[] {4,6,6,6,6,6};
    int[] wuhong1 = new int[]{4,4,4,4,4,1};
    int[] wuhong2 = new int[]{3,4,4,4,4,4};
    int[] liubohei1 = new int[]{2,2,2,2,2,2};
    int[] liubohei2 = new int[]{5,5,5,5,5,5};
    int[] biandijin = new int[]{1,1,1,1,1,1};
    int[] liubohong = new int[]{4,4,4,4,4,4};
    int[] zyjinhua = new int[]{4,4,4,4,1,1};

    @Test
    public void testCheckAward() {
        Award award = dicingService.checkAward(yixiu);
        Assert.assertEquals(award.getAwardName(), "一秀");
        award = dicingService.checkAward(erju);
        Assert.assertEquals(award.getAwardName(), "二举");
        award = dicingService.checkAward(sijin1);
        Assert.assertEquals(award.getAwardName(), "四进");
        award = dicingService.checkAward(sijin2);
        Assert.assertEquals(award.getAwardName(), "四进");
        award = dicingService.checkAward(sijin3);
        Assert.assertEquals(award.getAwardName(), "四进");
        award = dicingService.checkAward(sanhong);
        Assert.assertEquals(award.getAwardName(), "三红");
        award = dicingService.checkAward(duitang);
        Assert.assertEquals(award.getAwardName(), "对堂");
        award = dicingService.checkAward(sihong1);
        Assert.assertEquals(award.getAwardName(), "状元");
        Assert.assertEquals(11, award.getScore());
        award = dicingService.checkAward(sihong2);
        Assert.assertEquals(award.getAwardName(), "状元");
        Assert.assertEquals(10, award.getScore());
        award = dicingService.checkAward(wuzi1);
        Assert.assertEquals(award.getAwardName(), "状元");
        Assert.assertEquals(5, award.getScore());
        award = dicingService.checkAward(wuzi2);
        Assert.assertEquals(award.getAwardName(), "状元");
        Assert.assertEquals(4, award.getScore());
        award = dicingService.checkAward(wuhong1);
        Assert.assertEquals(award.getAwardName(), "状元");
        Assert.assertEquals(1, award.getScore());
        award = dicingService.checkAward(wuhong2);
        Assert.assertEquals(award.getAwardName(), "状元");
        Assert.assertEquals(3, award.getScore());
        award = dicingService.checkAward(liubohei1);
        Assert.assertEquals(award.getAwardName(), "状元");
        Assert.assertEquals(2, award.getScore());
        award = dicingService.checkAward(liubohei2);
        Assert.assertEquals(award.getAwardName(), "状元");
        Assert.assertEquals(5, award.getScore());
        award = dicingService.checkAward(biandijin);
        Assert.assertEquals(award.getAwardName(), "状元");
        award = dicingService.checkAward(liubohong);
        Assert.assertEquals(award.getAwardName(), "状元");
        award = dicingService.checkAward(zyjinhua);
        Assert.assertEquals(award.getAwardName(), "状元");
    }

}
