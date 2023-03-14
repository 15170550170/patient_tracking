package com.liyouling.patient_track;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.liyouling.patient_track.dao")
public class PatientTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientTrackApplication.class, args);
    }

}
