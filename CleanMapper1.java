import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class CleanMapper1 extends Mapper<LongWritable, Text, Text, Text> {

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] tokens = line.split(",");

        // Skip rows with null values in the first column
        if (tokens[0] != null && !tokens[0].trim().isEmpty()) {
            context.write(new Text(tokens[0]), value);
        }
    }
}
