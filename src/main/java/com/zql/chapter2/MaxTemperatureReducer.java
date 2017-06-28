package com.zql.chapter2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * example 2-4
 * Created by Administrator on 2017/6/22.
 */
public class MaxTemperatureReducer extends Reducer<Text,IntWritable,Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int max = Integer.MIN_VALUE;
        for(IntWritable value:values){
            max = Math.max(max,value.get());
        }
        context.write(key,new IntWritable(max));
    }
}
