import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class LogAnalyzeMapper extends Mapper<LongWritable,Text,Text,LongWritable>{

	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException
	{
		  
		String currentLine=value.toString();
		//context.write(new Text(currentLine),new LongWritable(1));
		
		String currentLineArray[]=currentLine.split("- -");
		//System.out.println(currentLineArray[0]);
		//context.write(new Text(currentLineArray[0]),new LongWritable(currentLineArray.length));
		if(currentLineArray.length>=2)
		{
		String ClientIp=currentLineArray[0].trim();
		//String timeStamp=currentLineArray[1].split("]")[0].replace("]","").split(" ")[0];
		String timeStamp=currentLineArray[1].split("]")[0].replace("[","").trim();
		//context.write(new Text(ClientIp+"----"+timeStamp),new LongWritable(1));
		String timeInterval=context.getConfiguration().get("timeinterval"); 
		String startTime=timeInterval.split("---")[0];
		String endTime=timeInterval.split("---")[1];
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);
		try
		{
			//context.write(new Text(endTime+"--"+startTime+"--"+ClientIp),new LongWritable(1));
			Date startTimestamp = df.parse(startTime);
			Date endTimestamp=df.parse(endTime);
			Date currentTimeStamp = df.parse(timeStamp);
			//context.write(new Text(startTimestamp+"--"+endTimestamp+"--"+currentTimeStamp),new LongWritable(1));
		if(currentTimeStamp.after(startTimestamp) && currentTimeStamp.before(endTimestamp))
		{
			context.write(new Text(ClientIp),new LongWritable(1));
		}
		}	
		
		catch(Exception e)
		{
			
		}
		
		//context.write(word, new LongWritable(chapterNumber));  
	}
		        	 
		
	}
	
	


}
