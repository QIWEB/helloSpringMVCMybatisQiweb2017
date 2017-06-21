package com.lyx.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring batch ���첽����ִ�� reader processor writer֮����첽ִ�� ��������ʱ�䣺 108884ms
 *
 * @author Lenovo
 *
 */
public class AppMain5 {

    public static void main(String[] args)
            throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        long startTime = System.currentTimeMillis(); // ��ȡ��ʼʱ��

        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath:spring-batch-async.xml" });
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        Job job = (Job) context.getBean("addPeopleDescJob");
        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
        JobExecution result = launcher.run(job,
                jobParametersBuilder.toJobParameters());
        ExitStatus es = result.getExitStatus();
        if (es.getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
            System.out.println("�����������");
        } else {
            System.out.println("����ʧ�ܣ�exitCode=" + es.getExitCode());
        }

        long endTime = System.currentTimeMillis(); // ��ȡ����ʱ��
        System.out.println("��������ʱ�䣺 " + (endTime - startTime) + "ms");
    }

}