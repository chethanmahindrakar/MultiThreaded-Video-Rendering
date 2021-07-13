package ThreadControl;

import Reader.PropertiesReader;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadControl {

    private final PropertiesReader propertiesReader;

    public ThreadControl(PropertiesReader pr)
    {
        this.propertiesReader = pr;
    }

    public void control() {
        String input = propertiesReader.getThread_input_files();
        File directoryPath = new File(input);
        String all_scripts[] = directoryPath.list();

        
        ArrayList<Runnable> all_jobs = new ArrayList<Runnable>();

        //Create jobs and append them to an Arraylist
        for (int i = 0; i < all_scripts.length; i++)
        {
            all_jobs.add(new ThreadTask(propertiesReader , all_scripts[i]));
        }

        //creating a thread pool
        ExecutorService pool = Executors.newFixedThreadPool(propertiesReader.getThread_Count());

        //executing all jobs
        for(int i=0 ; i<all_jobs.size(); i++)
        {
            pool.execute(all_jobs.get(i));
            System.out.println(all_jobs.get(i));
        }

        pool.shutdown();
    }
}
