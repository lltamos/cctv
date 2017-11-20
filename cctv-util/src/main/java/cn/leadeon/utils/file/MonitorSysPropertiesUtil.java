package cn.leadeon.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 轮询监控配置文件是否更新，动态获取最新内容
 * 
 * @author LiJunJun
 * @date 2017.08.08
 */
public class MonitorSysPropertiesUtil
{
    private static Properties SysLocalPropObject = null;
    
    // 配置文件绝对路径
    private static String defaultPropFilePath = null;
    
    // 配置文件名称
    private static String propertiesName = null;
    
    // 文件更新标识
    protected long lastModifiedData = -1;
    
    private static MonitorSysPropertiesUtil instance;
    
    public static MonitorSysPropertiesUtil getInstance(String fileName)
    {
        if (propertiesName == null)
        {
            propertiesName = fileName;
        }
        
        if (instance == null)
        {
            syncInit();
        }
        
        return instance;
    }
    
    /**
     * 同步块，避免多线程调用，生成多个实例
     */
    private static synchronized void syncInit()
    {
        if (instance == null)
        {
            instance = new MonitorSysPropertiesUtil();
        }
    }
    
    /**
     * 私有构造器启动一个线程实时监控配置文件
     */
    private MonitorSysPropertiesUtil()
    {
        SysLocalPropObject = new Properties();
        
        ClassLoader classLoader = MonitorSysPropertiesUtil.class.getClassLoader();
        
        defaultPropFilePath = classLoader.getResource(propertiesName).getFile();
        
        File propertyFile = new File(defaultPropFilePath);
        
        if (propertyFile.exists())
        {
            reloadFile(propertyFile);
        }
        
        // 循环监控配置文件的变化，一旦发现文件发生变化了则重读配置文件
        Thread thread = new Thread(new monitorRunnable(defaultPropFilePath));
        
        thread.start();
    }
    
    /**
     * 监控线程内部类
     * 
     * @author LiJunJun
     */
    class monitorRunnable implements Runnable
    {
        
        private String filePath;
        
        final MonitorSysPropertiesUtil self = MonitorSysPropertiesUtil.this;
        
        public monitorRunnable(String filePath)
        {
            this.filePath = filePath;
        }
        
        @Override
        public void run()
        {
            while (true)
            {
                // 间隔10分钟
                try
                {
                    Thread.sleep(1000 * 60 * 10);
                    // Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    System.out.println("定时巡检配置文件线程休眠异常" + Thread.currentThread().getName());
                    e.printStackTrace();
                }
                
                try
                {
                    File propertyFile = new File(filePath);
                    
                    if (self.lastModifiedData != propertyFile.lastModified())
                    {
                        self.reloadFile(propertyFile);
                        self.lastModifiedData = propertyFile.lastModified();
                    }
                }
                catch (Exception e)
                {
                    System.out.println("校验配置文件是否改变异常,MonitorSysPropertiesUtil -- monitorRunnable");
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    /**
     * 重新加载文件
     * 
     * @author LiJunJun 2017.08.08
     * @param propertyFile 配置文件路径
     */
    private void reloadFile(File propertyFile)
    {
        FileInputStream inputStreamLocal = null;
        try
        {
            inputStreamLocal = new FileInputStream(propertyFile);
            
            SysLocalPropObject.load(inputStreamLocal);
        }
        catch (Exception e)
        {
            if (e instanceof FileNotFoundException)
            {
                SysLocalPropObject = null;
            }
            else
            {
                System.out.println("校验配置文件是否改变异常,MonitorSysPropertiesUtil -- reloadFile failed");
            }
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (inputStreamLocal != null)
                {
                    inputStreamLocal.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("关闭IO流异常,MonitorSysPropertiesUtil -- reloadFile failed");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 对外提供获取配置文件对象的方法
     */
    public static Properties getPropertiesObject()
    {
        return SysLocalPropObject;
    }
}
