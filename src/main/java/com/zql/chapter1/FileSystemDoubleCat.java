package com.zql.chapter1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Administrator on 2017/7/19.
 */
public class FileSystemDoubleCat {


    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), conf);
        FSDataInputStream in = null;

        try {
            in = fileSystem.open(new Path(uri));
            IOUtils.copyBytes(in,System.out,4096,false);
            in.seek(10);
            IOUtils.copyBytes(in,System.out,4096,false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
        }


    }
}
