package org.example.batchexample;

import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@SpringBootApplication
public class BatchExampleApplication {

    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(BatchExampleApplication.class, args);

        // SchedulerFactory 생성 및 Scheduler 인스턴스 생성
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        sched.start();

        // 실행할 Job
        JobDetail job = newJob(MyJob.class)
                .withIdentity("myJob")
                .usingJobData("dataKey", "MyJob Execute!!")
                .build();

        // 실행 주기(simpleSchedule)
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();
        // Listener 추가
        sched.getListenerManager().addJobListener(new MyJobListener());

        // Scheduler에 job, trigger 등록
        sched.scheduleJob(job, trigger);
    }
}
