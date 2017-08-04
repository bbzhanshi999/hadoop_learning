package com.zql.chapter1.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

/**
 * 测试文件压缩系统
 * Created by Administrator on 2017/8/4.
 */
public class StreamCompressor {

    public static void main(String[] args) throws ClassNotFoundException, IOException {

        String compressorClassName = args[0];
        Class<?> compressorClass = Class.forName(compressorClassName);
        Configuration conf = new Configuration();
        CompressionCodec codec = (CompressionCodec)ReflectionUtils.newInstance(compressorClass, conf);
        CompressionOutputStream out = codec.createOutputStream(System.out);
        IOUtils.copyBytes(System.in,out,conf);
        out.finish();
    }
}
