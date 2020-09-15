package com.test.bigdata.hadoop.join.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class JoinMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    private FileSystem fs = null;
    private Text k = new Text();
    private HashMap<String, String> codes = null;

    @Override
    protected void setup(Context context) throws InterruptedException, IOException {
        codes = new HashMap<>();
        URI[] uris = context.getCacheFiles();
        fs = FileSystem.get(context.getConfiguration());
        Arrays.stream(uris).filter(uri -> StringUtils.endsWith(new Path(uri).getName(), "code.txt")).forEach(this::loadCache);
    }

    private void loadCache(URI uri) {
        Path path = new Path(uri);
        FSDataInputStream fsdi = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fsdi = fs.open(path);
            isr = new InputStreamReader(fsdi);
            br = new BufferedReader(isr);

            String line = br.readLine();
            while (StringUtils.isNotBlank(line)) {
                String[] fields = StringUtils.split(line, ",");
                codes.put(fields[0], fields[1]);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(br);
            IOUtils.closeStream(isr);
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = StringUtils.split(line, ",");
        k.set(StringUtils.joinWith(",", codes.get(fields[1]), fields[1], fields[0], fields[2]));
        context.write(k, NullWritable.get());
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        IOUtils.closeStream(fs);
        codes.clear();
        codes = null;
    }
}
