package com.zql.chapter1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

/**
 * Created by Administrator on 2017/7/19.
 */
public class FileCopyWithProgress {

    public static void main(String[] args) throws IOException {
        String localSrc = args[0];
        String uri = args[1];

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri),conf);
        FSDataOutputStream out = fs.create(new Path(uri), new Progressable() {
            @Override
            public void progress() {
                System.out.println('.');
            }
        });
        IOUtils.copyBytes(in,out,4096,false);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }
}
