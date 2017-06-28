package com.zql.chapter2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * example2-3
 * Created by Administrator on 2017/6/22.
 */
public class MaxTemperatureMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String year = line.substring(15,19);
        int airTemperature;
        if(line.charAt(87)=='+'){
            airTemperature = Integer.parseInt(line.substring(88,92));
        }else{
            airTemperature = Integer.parseInt(line.substring(87,92));
        }
        String quailty =line.substring(92,93);
        if(airTemperature!=9999&&quailty.matches("[01459]"))
            context.write(new Text(year),new IntWritable(airTemperature));

    }
}
