/*
 * Copyright (C) 2016-2020 IMassBank Corporation
 *
 */
package com.example.day1;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    static HashSet<String> set	= new HashSet<String>();
    static PrintWriter pw	= null;
    static Pattern			p	= Pattern.compile("\".+dd_sdk_apk[^%]+pin\":\"([^%]*%25.+)\"");

    public static void main(String[] args) {

        ThreadPoolExecutor es = new ThreadPoolExecutor(4, 8, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(100000),
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            String log = null;
            pw = new PrintWriter(new File("E:/lh.txt"));
            InputStreamReader fis = new InputStreamReader(new FileInputStream("E:/chinese_user6.txt"));
            BufferedReader br = new BufferedReader(fis);

            while ((log = br.readLine()) != null) {
                es.execute(new Task(log));
            }
            es.shutdown();
            es.awaitTermination(5, TimeUnit.MINUTES);

            fis.close();
            br.close();
            pw.close();
            System.out.println("--------------------------finish!-------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decode(String codedName) {
        String lastName = null;
        try {
            for (int idx = 0;; idx++) {
                lastName = codedName;
                codedName = URLDecoder.decode(codedName, "UTF-8");
                if (codedName.equals(lastName)) {
                    return codedName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedName;
    }

    private static class Task implements Runnable {
        private String	log	= null;

        public Task(String value) {
            this.log = value;
        }

        @Override
        public void run() {
            try {
                Matcher matcher = p.matcher(log);
                if (matcher.find()) {
                    String codedName = matcher.group(1);
                    if (codedName.length() < 200) {
                        String trueName = decode(codedName);
                        if (!set.contains(trueName)) {
                            set.add(trueName);
                            pw.println(trueName);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

