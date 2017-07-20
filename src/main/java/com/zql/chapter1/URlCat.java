package com.zql.chapter1;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2017/7/19.
 */
public class URlCat {

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args)  {
        InputStream in = null;
        try {
            in = new URL(args[0]).openStream();
            IOUtils.copyBytes(in,System.out,4096,false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
