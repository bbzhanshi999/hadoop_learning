package com.zql.hadooplearning.chapter3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

/**
 * Created by Administrator on 2017/7/31.
 */
public class CoherencyModelTest {

    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path p = new Path("p");
        OutputStream out = fs.create(p);
        out.write("content".getBytes("UTF-8"));
        out.flush();
        System.out.println(fs.getFileStatus(p).getLen());
    }
}
