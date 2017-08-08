package com.zql.com.zql.hadooplearning.chapter3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * listing files
 * Created by Administrator on 2017/7/20.
 */
public class ListStatus {

    public static void main(String[] args) throws URISyntaxException, IOException {
        String uri = args[0];
        
        FileSystem fs = FileSystem.get(URI.create(uri), new Configuration());

        Path[] paths = new Path[args.length];
        for(int i=0;i<paths.length;i++){
            paths[i] = new Path(args[i]);
        }

        FileStatus[] fileStatuses = fs.listStatus(paths);
        Path[] paths1 = FileUtil.stat2Paths(fileStatuses);
        for(Path p:paths1){
            System.out.println(p);
        }
    }
}
