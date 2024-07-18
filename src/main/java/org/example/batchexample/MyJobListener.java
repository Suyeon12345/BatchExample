package org.example.batchexample;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

//Job 상태 로깅
public class MyJobListener implements JobListener {
    @Override
    public String getName() {
        return MyJobListener.class.getName();
    }

    // Job 수행 전 상태
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println(String.format("[%-18s][%s] Job 시작", "jobToBeExecuted",
                context.getJobDetail().getKey().toString()));
    }

    // Job 중단 상태
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println(String.format("[%-18s][%s] Job 중단", "jobExecutionVetoed",
                context.getJobDetail().getKey().toString()));
    }

    // Job 수행 완료 상태
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        System.out.println(String.format("[%-18s][%s] Job 완료", "jobWasExecuted",
                context.getJobDetail().getKey().toString()));
    }
}
