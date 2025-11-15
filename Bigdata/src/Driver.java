import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.io.Text;
import  org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class Driver {
    public static void main(String[] args) {
        Configuration conf=new Configuration();
        String[] ourArgs= null;
        try {
            ourArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Job job= null;
        try {
            job = new Job(conf, "Compteur de mots");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        job.setJarByClass(Driver.class);
        job.setMapperClass(Mapper.class);
        job.setReducerClass(Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        try {
            FileInputFormat.addInputPath(job, new Path(ourArgs[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileOutputFormat.setOutputPath(job, new Path(ourArgs[2]));

        try {
            if(job.waitForCompletion(true))
                System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.exit(-1);

    }
}