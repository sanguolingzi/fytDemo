package fyt.business.core.listener;

import fyt.business.core.netty.NettyServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ResourceListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("=====================contextInitialized======================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try{
            System.out.println("=====================contextDestroyed======================");
            NettyServer nettyServer = SpringContextUtil.getBean("nettyServer");
            nettyServer.close();
        }catch (Exception e){
            //e.printStackTrace();
        }

    }
}
