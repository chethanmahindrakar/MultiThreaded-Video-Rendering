package Generator;

import Reader.PropertiesReader;

import freemarker.template.Configuration;
import exceptions.TemplateException;
import freemarker.template.Template;

import java.io.*;
import java.util.*;


public class GenerateTemplate {

    private final String generic_job_template = "Resource-Folder/Input/Template/Generic_Template.ftl";

    public static String readBatchFile(File file) throws IOException {
        StringBuffer read = new StringBuffer();

        BufferedReader in = new BufferedReader(new FileReader(file));

        try {
            String curr_line = null;
            while ((curr_line = in.readLine()) != null) {
                read.append(curr_line).append(System.lineSeparator());
            }
        }
        finally {
            in.close();
        }
        return read.toString();
    }



    public void generateTemplateThread(PropertiesReader propertiesReader) {

        Configuration config = new Configuration();

        try{
            GenerateFrames generateFrames = new GenerateFrames(propertiesReader);
            ArrayList<Dictionary> all_frames = new ArrayList<>();
            all_frames = generateFrames.frameDivision();

            String output = propertiesReader.getThread_input_files();

            String templateString = readBatchFile(new File(generic_job_template));


            for(int i =0 ; i <all_frames.size() ; i++)
            {
                Template template = new Template("Thread_script" , new StringReader(templateString) , config);
                Map<String, Object> data = new HashMap<String, Object>();

                data.put("PROJECT_NAME" , propertiesReader.getProject_name());
                data.put("ABS_OUTPUTFILENAME" , propertiesReader.getOUTPUT_PATH()+"/ABS/"+i+".avi");
                data.put("MPG_OUTPUTFILENAME" , propertiesReader.getOUTPUT_PATH()+"/MPG/"+i+".avi");
                data.put("FFMPEG_PATH", propertiesReader.getFfmpeg_path());
                data.put("PROJECT_PATH", propertiesReader.getADOBE_PROJECT_PATH());

                data.put("COMP_NAME", propertiesReader.getComposition_name());
                data.put("START_FRAME" , all_frames.get(i).get("start_frame"));
                data.put("END_FRAME" , all_frames.get(i).get("end_frame"));

                //System.out.println((all_frames.get(i).get("start_frame")));

                //data.put("THREAD_LOG_FILE" , propertiesReader.getThread_log_output()+"/thread"+i+"LOG.txt");

                Writer file = new FileWriter (new File(output+"thread_script"+i+".bat"));
                template.process(data , file);
                file.flush();
                file.close();
            }
        }
        catch (IOException e) {
            new TemplateException("Template could not be found or was not generated" , e);
        }
        catch (freemarker.template.TemplateException e) {
            e.printStackTrace();
        }


    }

    }
