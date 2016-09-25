package cn.jtduan.demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by djt on 9/24/16.
 */

public class ScheduledTasks {

    public void currentTime(){
        System.out.println(LocalDateTime.now());
    }
}
