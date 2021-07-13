package Reader;

import exceptions.FileNotFound;

import java.io.FileReader;
import java.util.Properties;

public class PropertiesReader {

    private final Properties prop = new Properties();
    private final String ffmpeg_path;

    private final String ADOBE_PROJECT_PATH;
    private final String OUTPUT_PATH;
    private final int thread_count;
    private final int no_of_frames;
    private final int frames_per_thread;
    private final int frames_skip;
    private final String thread_log_output;
    private final String thread_input_files;
    private final String composition_name;
    private final String project_name;

    public PropertiesReader() {
        {
            try {
                FileReader reader = new FileReader("src/main/resources/application.properties");
                prop.load(reader);
            }

            catch (Exception e) {
                new FileNotFound("The file has not been found", e);
            }

            this.ffmpeg_path = this.setFfmpeg_path();

            this.ADOBE_PROJECT_PATH = this.setADOBE_PROJECT_PATH();
            this.OUTPUT_PATH = this.setOUTPUT_PATH();
            this.thread_count = this.setThread_Count();
            this.no_of_frames = this.setNo_of_frames();
            this.frames_per_thread = this.setFrames_per_thread();
            this.frames_skip = this.setFrames_skip();
            this.thread_log_output = this.setThread_log_output();
            this.thread_input_files = this.setThread_input_files();
            this.composition_name = this.setComposition_name();
            this.project_name = this.setProject_name();
        }
    }

    private String setProject_name()
    {
        return prop.getProperty("PROJECT_NAME");
    }

    public String getProject_name()
    {
        return this.project_name;
    }

    private String setFfmpeg_path() {
        return prop.getProperty("FFMPEG_PATH");
    }

    public String getFfmpeg_path()
    {
        return this.ffmpeg_path;
    }

    private String setComposition_name() {
        return prop.getProperty("COMPOSITION_NAME");
    }

    public String getComposition_name()
    {
        return this.composition_name;
    }

    private String setThread_input_files() {
        return prop.getProperty("THREAD_INPUT");
    }

    public String getThread_input_files()
    {
        return this.thread_input_files;
    }

    private String setThread_log_output() {
        return prop.getProperty("THREAD_LOG_OUTPUT");
    }

    public String getThread_log_output()
    {
        return this.thread_log_output;
    }

    private int setFrames_skip() {
        return Integer.parseInt(prop.getProperty("FRAME_SKIP"));
    }

    private int setFrames_per_thread() {
        return Integer.parseInt(prop.getProperty("FRAMES_PER_THREAD"));
    }

    private int setNo_of_frames() {
        return Integer.parseInt(prop.getProperty("NO_OF_FRAMES"));
    }

    public int getFrames_per_threads()
    {
        return this.frames_per_thread;
    }

    public int getFrames_skip()
    {
        return this.frames_skip;
    }

    public int getNo_of_frames() {
        return this.no_of_frames;
    }

    private int setThread_Count() {
       return Integer.parseInt(prop.getProperty("THREAD_COUNT"));
    }

    public int getThread_Count() {
        return this.thread_count;
    }

    private String setOUTPUT_PATH() {
        return prop.getProperty("VIDEO_OUTPUT");
    }

    public String getOUTPUT_PATH()
    {
        return this.OUTPUT_PATH;
    }

    private String setADOBE_PROJECT_PATH() {
        return prop.getProperty("ADOBE_PROJECT");
    }

    public String getADOBE_PROJECT_PATH() {
        return this.ADOBE_PROJECT_PATH;
    }
}
