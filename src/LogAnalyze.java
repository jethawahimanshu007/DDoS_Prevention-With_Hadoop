import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


//import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;



public class LogAnalyze extends Configured{

	public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException
	{
		////Part for finding number of chapters
		//String fileName = args[0];
		String fileName="/user/training/input/localhost-Log";
		
		System.out.println(fileName);
        // This will reference one line at a time
        String line = null;
        int numberOfChapters=0;
        
      
       
        
        ///Part for setting variables
		Configuration conf = new Configuration(true);
		String timeInterval=args[1];
		long threshold=Long.parseLong(args[2]);
		conf.set("timeinterval",timeInterval);
		conf.set("threshold",Long.toString(threshold));
		Job job=new Job(conf);
		job.setInputFormatClass(TextInputFormat.class);
		job.setJarByClass(LogAnalyze.class);
		
		
		TextInputFormat.addInputPath(job, new Path(fileName));
		//job.getConfiguration().setInt(
		//		"mapreduce.input.lineinputformat.linespermap", 10000);
		//FileInputFormat.setInputPaths(job,new Path(args[0]));
		FileOutputFormat.setOutputPath(job,new Path(args[0]));
		//NLineInputFormat nlif=new NLineInputFormat();
		
		
		job.setMapperClass(LogAnalyzeMapper.class);
		job.setReducerClass(LogAnalyzeReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
	
		//job.setNumReduceTasks(0);
		//job.setOutputKeyClass(Text.class);
		//job.setOutputValueClass(LongWritable.class);
		
		

		System.exit(job.waitForCompletion(true)?0:-1);
		
	}
}