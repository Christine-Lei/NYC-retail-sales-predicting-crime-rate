import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class CleanReducer1 extends Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // As this is just data cleaning, we only need to write the non-null records to the output
        for (Text value : values) {
            context.write(null, value);
        }
    }
}
