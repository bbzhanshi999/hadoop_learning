package com.zql.com.zql.hadooplearning.chapter3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by Administrator on 2017/7/19.
 */
public class FileSystemCat {


    public static void main(String[] args) throws IOException {

        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        InputStream in = null;
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in,System.out,4096,false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
