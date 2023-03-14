package com.liyouling.patient_track;

import com.liyouling.patient_track.dao.UserMapper;
import com.liyouling.patient_track.dao.po.User;
import com.liyouling.patient_track.dao.po.UserPo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PatientTrackApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    void select() {
        //List<UserPo> users = userMapper.selectList(null);
        //users.forEach(System.out::println);
    }
    /*@Test
    void insert() {
        int result = userMapper.insert(new User(null,"doctor02","医生2号","13388888888","2860292323@qq.com","123456",null,null,null,"doctor",1,new Date(),new Date()));
    }*/
    @Test
    void contextLoads() {Date date = new Date();
        System.out.println(date);

    }

}
