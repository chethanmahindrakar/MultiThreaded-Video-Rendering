package ThreadControl;

import Reader.PropertiesReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class ThreadTask implements Runnable{

    private String batch_file_execute;
    private final PropertiesReader prop;

    public ThreadTask(PropertiesReader prop , String batch_file_execute)
    {
        this.batch_file_execute = batch_file_execute;
        this.prop = prop;
    }

    public void run()
    {
        long thread_start = System.currentTimeMillis();
        //System.out.println(Thread.currentThread().getName());
        ProcessBuilder processBuilder = new ProcessBuilder();
        System.out.println(prop.getThread_input_files() + this.batch_file_execute);
        processBuilder.command(prop.getThread_input_files() + this.batch_file_execute);

        try{
            Process process = processBuilder.start();
            process.waitFor();
        }
        catch (IOException e)
        {
            System.out.println("Could not find the file");
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupted Exception occurred");
            e.printStackTrace();
        }

        long thread_end = System.currentTimeMillis();

        System.out.println("Thread Name: "+ Thread.currentThread().getName() + " Time taken: " + (thread_start - thread_end));

    }

}


