import Generator.GenerateTemplate;
import Reader.PropertiesReader;
import ThreadControl.ThreadControl;
import ThreadControl.ThreadTask;

public class Main {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();


        PropertiesReader propertiesReader = new PropertiesReader();

        GenerateTemplate generateTemplate = new GenerateTemplate();
        generateTemplate.generateTemplateThread(propertiesReader);

        ThreadControl threadControl = new ThreadControl(propertiesReader);
        threadControl.control();


        long end = System.currentTimeMillis();
        long elapsedTime = end - start;

        System.out.println("Time taken for job completion :" + elapsedTime);

        System.out.println("Job Successfully completed");

    }
}
