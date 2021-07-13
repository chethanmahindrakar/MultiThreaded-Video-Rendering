package Generator;

import Reader.PropertiesReader;
import exceptions.FrameDivisionException;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class GenerateFrames {

    public final PropertiesReader propertiesReader;
    private final int no_threads;
    private final int frames_per_thread;
    private final int total_frames;
    private final String thread_logger;


    public GenerateFrames(PropertiesReader propertiesReader)
    {
        this.propertiesReader = propertiesReader;
        this.no_threads = this.propertiesReader.getThread_Count();
        this.frames_per_thread = this.propertiesReader.getFrames_per_threads();
        this.total_frames = this.propertiesReader.getNo_of_frames();
        this.thread_logger = this.propertiesReader.getThread_log_output();
    }

    public ArrayList<Dictionary> frameDivision()
    {
        ArrayList<Dictionary> all_frames = new ArrayList<Dictionary>();
        int batches = total_frames/frames_per_thread;
        if(batches<no_threads)
        {
            new FrameDivisionException("Inadequate batches for all threads to process");
        }
        else
        {
            int start_frame =1;
            int temp_frame= 0;

            for(; start_frame<=total_frames ; start_frame = start_frame + frames_per_thread +1)
            {
                Dictionary frame_mapping = new Hashtable();

                if(start_frame >= total_frames-frames_per_thread)
                {
                    frame_mapping.put("start_frame" , start_frame);
                    frame_mapping.put("end_frame" , total_frames);
                }
                else
                {
                    temp_frame = start_frame + frames_per_thread;
                    frame_mapping.put("start_frame", start_frame);
                    frame_mapping.put("end_frame", temp_frame);

                }

                all_frames.add(frame_mapping);
                //System.out.println(all_frames);
            }
        }
        return all_frames;
    }


}
