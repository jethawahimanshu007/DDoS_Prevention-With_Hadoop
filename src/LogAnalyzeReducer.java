import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LogAnalyzeReducer extends
		Reducer<Text, LongWritable, Text, LongWritable> {

	public void reduce(Text key, Iterable<LongWritable> values, Context context)
			throws IOException, InterruptedException {
		
		
		long sum = 0;
	      for (LongWritable value : values) {
	        sum += value.get();
	      }

	      if(sum>1000)
	      context.write(key, new LongWritable(sum));
		
		 
	}
}
