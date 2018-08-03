package com.tositteach.server.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.util.Properties;

/* 创建必要的文件夹*/
public class DocFolderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String configLocation = sce.getServletContext().getInitParameter("uploadConfigLocation");
        Properties props = new Properties();
        try {
            InputStream in = configLocation.startsWith("classpath:")
                    ? DocFolderListener.class.getResourceAsStream("/" + configLocation.substring(10))
                    : new FileInputStream(configLocation);
            props.load(in);
            in.close();
            File file = new File(props.getProperty("doc.real"));
            if (!file.exists()) file.mkdirs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // DO NOTHING
    }
}
