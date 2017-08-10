package com.zql.hadooplearning.chapter3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.apache.hadoop.mapreduce.Cluster;

import java.io.IOException;


/**
 * Created by Administrator on 2017/7/20.
 */
public class FileStatusDemo {

    public static void main(String[] args) throws IOException {
        String uri = "/dir/file";
        Configuration conf = new Configuration();
        MiniDFSCluster miniDFSCluster = new MiniDFSCluster.Builder(conf).build();
        DistributedFileSystem fs = miniDFSCluster.getFileSystem();
        FSDataOutputStream out = fs.create(new Path(uri));
        out.write("content".getBytes("UTF-8"));
        out.close();

        FileStatus fileStatus = fs.getFileStatus(new Path(uri));
        System.out.println(fileStatus.getPath().toUri().getPath());
        System.out.println(fileStatus.isDirectory());
        System.out.println(fileStatus.getLen());
        System.out.println(fileStatus.getReplication());
        System.out.println(fileStatus.getModificationTime());
        System.out.println(fileStatus.getBlockSize());
        System.out.println(fileStatus.getOwner());
        System.out.println(fileStatus.getGroup());
        System.out.println(fileStatus.getPermission());
        System.out.println("------------------------------------------");

        FileStatus dirStatus = fs.getFileStatus(new Path("/dir"));
        System.out.println(dirStatus.getPath().toUri().getPath());
        System.out.println(dirStatus.isDirectory());
        System.out.println(dirStatus.getLen());
        System.out.println(dirStatus.getReplication());
        System.out.println(dirStatus.getModificationTime());
        System.out.println(dirStatus.getBlockSize());
        System.out.println(dirStatus.getOwner());
        System.out.println(dirStatus.getGroup());
        System.out.println(dirStatus.getPermission());


        //判断path是否存在，直接用fs.exists
        fs.exists(new Path("/dir"));
    }
}
