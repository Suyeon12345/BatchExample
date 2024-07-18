package org.example.batchexample;

import org.quartz.*;

public class MyJob implements Job {

    String message;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        //현재 실행 중인 작업의 JobKey
        JobKey key = context.getJobDetail().getKey();
        System.out.println("key : " + key);//DEFAULT.myJob

        //작업 실행시 필요한 데이터를 저장하는 맵
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        message = dataMap.getString("dataKey");
        System.out.println(message);

        try {
            NaverFinance.crawling();
        } catch (Exception e) {
            e.printStackTrace();
            throw new JobExecutionException(e);
        }
    }
}
