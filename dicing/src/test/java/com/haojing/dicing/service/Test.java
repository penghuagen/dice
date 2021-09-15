package com.haojing.dicing.service;

import com.haojing.dicing.entity.User;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by yeweiping on 2020/9/19.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Set<User> users = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Long.valueOf(o1.getStartDicingTime() - o2.getStartDicingTime()).intValue();
            }
        });
//        Set<User> users = new HashSet<>();

        User user1 = new User();
        user1.setUserId(1L);
        user1.setStartDicingTime(0L);

        User user2 = new User();
        user2.setUserId(1L);
        user2.setStartDicingTime(1L);

        users.add(user1);
        users.add(user2);

        System.out.println(users.size());
    }
}
