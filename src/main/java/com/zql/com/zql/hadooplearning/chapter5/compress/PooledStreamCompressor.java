package com.zql.com.zql.hadooplearning.chapter5.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CodecPool;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;


/**
 * 解压工具池，利用本地解压工具，而不是创建java的实现
 * Created by Administrator on 2017/8/4.
 */
public class PooledStreamCompressor {

    public static void main(String[] args) throws ClassNotFoundException {

        String codeClassName = args[0];

        Class<?> codeClass = Class.forName(codeClassName);
        Configuration conf = new Configuration() ;
        CompressionCodec codec = (CompressionCodec)ReflectionUtils.newInstance(codeClass, conf);

        Compressor compressor = null;

        try {
            compressor = CodecPool.getCompressor(codec);
            CompressionOutputStream out = codec.createOutputStream(System.out, compressor);
            IOUtils.copyBytes(System.in,out,4096,false);
            out.finish();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CodecPool.returnCompressor(compressor);
        }
    }
}
