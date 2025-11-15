import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;


public class Reduce extends Reducer<Text, IntWritable, Text, Text> {
    public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
        Iterator<IntWritable> i = values.iterator();
        int count=0;
        while(i.hasNext())
            count+=i.next().get();
        context.write(key, new Text(count+""));
    }
}
