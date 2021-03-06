package com.zql.hadooplearning.chapter5.compress;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;


/**
 * 将文件系统中gz文件解压缩并存储值hdfs
 * Created by Administrator on 2017/8/4.
 */
public class FileDecompressor {

    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path inputPath = new Path(uri);
        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(inputPath);

        if(codec==null){
            System.err.print("no code find for "+uri);
            System.exit(1);
        }

        String outputUri = CompressionCodecFactory.removeSuffix(uri,codec.getDefaultExtension());

        InputStream in = null;
        OutputStream out = null;

        in = codec.createInputStream(fs.open(inputPath));
        out = fs.create(new Path(outputUri));
        IOUtils.copyBytes(in,out,conf);

        IOUtils.closeStream(in);
        IOUtils.closeStream(out);


    }
}
