package fyt.business.core.generator;

import fyt.business.core.manager.rocketmq.RocketMqConsumer;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator
 * on 2018/1/27.
 */
public class CodeGenerator
{
    private static Properties properties;

    private static String CHAR_SEMICOLON = ";";
    private static String CHAR_DOT = ".";
    private static Logger logger = LoggerFactory.getLogger(CodeGenerator.class);


    public static void main(String[] args) throws Exception
    {
        // 加载配置
        loadProperties();

        // 生成实体类，slq mapper文件和接口mapper文件
        createModule();

        // 根据配置文件内容生成对应的文件夹
        createService();
    }

    private static void createService()
    {
        // 相对工程的包路径
        String serviceTarget = properties.getProperty("service.target-package");
        if (StringUtils.isEmpty(serviceTarget))
        {
            logger.warn( "没有获取到配置项：service.target-package");
            throw new RuntimeException("没有配置service.target-package");
        }

        // 工程位于磁盘的路径
        String targetProject = properties.getProperty("target-project");
        if (StringUtils.isEmpty(targetProject) || targetProject == null)
        {
            System.out.println( "没有获取到配置项：target-project");
            throw new RuntimeException("没有配置target-project");
        }

        // 生成文件所在工程目录
        File targetProjectDir = new File(targetProject);
        if (!targetProjectDir.exists())
        {
            targetProjectDir.mkdirs();
            System.out.println( "生成目录："+targetProjectDir);
        }
        else
        {
            System.out.println( "找到target-project："+targetProject);
        }

        // 生成文件所在工程的包路径
        String serviceTargetPackage = serviceTarget.replaceAll("\\.", "/");
        File serviceTargetFile = new File(targetProjectDir, serviceTargetPackage);
        if (!serviceTargetFile.exists())
        {
            serviceTargetFile.mkdirs();
            System.out.println( "生成包路径："+serviceTargetPackage);
        }
        else
        {
            System.out.println( "找到service.target-package："+serviceTarget);
        }

        try
        {
            // 生成接口service文件
            generateFile(serviceTargetFile, false);
        }
        catch (Exception e)
        {
            System.out.println( e);
            throw new RuntimeException(e);
        }

        // 生成实现类包
        String serviceImpFileName = serviceTargetFile.getPath() + "/impl";
        File serviceImplTargetFile = new File(serviceImpFileName);
        if (!serviceImplTargetFile.exists())
        {
            serviceImplTargetFile.mkdirs();
            System.out.println("生成包路径："+serviceImpFileName);
        }
        else
        {
            System.out.println("找到service.target-package："+serviceImpFileName);
        }

        try
        {
            // 生成文件
            generateFile(serviceImplTargetFile, true);
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }

    /**
     * 写出文件内容
     * @param serviceFile
     * @param isImpl
     */
    private static void generateFile(File serviceFile, boolean isImpl)
    {
        // 文件名称
        String fileName = properties.getProperty("service.target-name");
        if (isImpl)
        {
            fileName += "Impl";
        }

        // 文件内容
        String content = createContent(fileName, isImpl);

        // 写出文件
        writeFile(serviceFile, fileName, content);
    }

    private static void writeFile(File serviceFile, String fileName, String content)
    {
        try
        {
            // 文件名称
            StringBuffer sb = new StringBuffer();
            sb.append(serviceFile.getPath()).append(File.separator).append(fileName).append(".java");

            // 如果文件存在并且标记不重写，则直接返回
            File file = new File(sb.toString());
            Boolean override = Boolean.parseBoolean(properties.getProperty("override", "false"));
            if (file.exists() && !override)
            {
                return ;
            }

            // 写出文件
            FileWriter writer = new FileWriter(file);

            try
            {
                writer.write(content);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                writer.close();
            }
        }
        catch (Exception e)
        {
            System.out.println( e);
        }
    }

    /**
     * 拼凑文件内容
     * @param isImpl
     * @return
     */
    private static String createContent(String fileName, boolean isImpl)
    {
        StringBuffer sb = new StringBuffer();
        String parentName = fileName.replace("Impl", ""); // 父类名

        // 包名
        sb.append("package ").append(properties.getProperty("service.target-package"));

        // 引用包
        if (isImpl)
        {
            // 添加实现包
            sb.append(".impl").append(CHAR_SEMICOLON).append("\n\n");

            // 父类引用
            sb.append("import ").append(properties.getProperty("service.target-package")).append(CHAR_DOT).append(parentName).append(CHAR_SEMICOLON).append("\n");
            // 注解引入
            sb.append("import ").append("org.springframework.stereotype.Service").append(CHAR_SEMICOLON).append("\n");
            sb.append("import ").append("org.springframework.beans.factory.annotation.Autowired").append(CHAR_SEMICOLON).append("\n");
            // mapper引入
            sb.append("import ").append(properties.getProperty("mapper.target-package")).append(CHAR_DOT).append(properties.getProperty("mapper.target-name"));
        }
        sb.append(CHAR_SEMICOLON).append("\n\n");

        // 类声明
        if (isImpl)
        {
            sb.append("@Service").append("\n");
            sb.append("public class ");
        }
        else
        {
            sb.append("public interface ");
        }
        sb.append(fileName);

        if (isImpl)
        {
            sb.append(" implements ").append(parentName);
        }

        sb.append("\n").append("{\n");
        if (isImpl)
        {
            // 注入一个mapper
            sb.append("    "); // 添加四个空格
            sb.append("@Autowired").append("\n");

            sb.append("    "); // 添加四个空格
            sb.append("private ").append(properties.getProperty("mapper.target-name")).append(" mapper").append(CHAR_SEMICOLON).append("\n");
        }
        sb.append("}");

        return sb.toString();
    }

    /**
     * 拼凑文件名称
     * @param isImpl
     * @return
     */
    private static String createFileName(boolean isImpl)
    {
        // 功能/模块名称
        String fileName = properties.getProperty("function.name");
        if (StringUtils.isEmpty(fileName))
        {
            System.out.println("没有配置功能名称：function.name");
            return "";
        }
        // 将第一个字符大写
        fileName = fileName.replace(String.valueOf(fileName.charAt(0)), String.valueOf(fileName.charAt(0)).toUpperCase());

        // 文件的后缀名，例如XXXBusiMapper或者XXXInfoMapper等
        String type = getBusiType();

        // 文件的完整名称
        String finalFileName = fileName + type + "Service";

        return finalFileName;
    }

    private static String getBusiType()
    {
        String type = properties.getProperty("common.mapper.generic");
        if (!StringUtils.isEmpty(type))
        {
            if (type.indexOf("Busi") > 0)
            {
                return "Busi";
            }
            else if (type.indexOf("Info") > 0)
            {
                return "Info";
            }
            else
            {
                return "";
            }
        }
        else
        {
            return "";
        }
    }

    /**
     * 是否使用数据库字段名称  false  按照下划线 驼峰   true使用数据库原始字段名
     * <property name="useActualColumnNames" value="false" />
     * 如果数据库表不是下划线风格 需要设置为true
     * 生成好的实体类需要自己添加注解 详情参照CustomerInfoEntity
     * 下划线风格的只需要在配置为false即可
     */
    private static void createModule() throws Exception
    {
        // 配置文件路径
        //String configFilePath = CodeGenerator.class.getClassLoader().getResource("generatorConfig.xml").getFile();
        Resource res = new ClassPathResource("generator/generatorConfig.xml");
        if (res == null)
        {
            throw new RuntimeException("没有读取到配置文件 generatorConfig.xml");
        }

        List<String> warnings = new ArrayList<String>();

        // 配置文件对象
        if (!res.getFile().exists())
        {
            throw new RuntimeException("没有找到配置文件：" + res.getFile());
        }

        // 配置文件解析
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(res.getFile());

        // 配置回调
        DefaultShellCallback callback = new DefaultShellCallback(Boolean.parseBoolean(properties.getProperty("override", "false")));
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

        // 执行生成
        myBatisGenerator.generate(null);
    }

    /**
     * 加载配置
     * @throws IOException
     */
    private static void loadProperties() throws IOException
    {
        if (null == properties)
        {
            properties = new Properties();
            Resource res = new ClassPathResource("generator/application.properties");
            //URL url = CodeGenerator.class.getClassLoader().getResource("/generator/application.properties");
            if (StringUtils.isEmpty(res) || null == res)
            {
                throw new RuntimeException("文件路径不对或配置文件不存在：application.properties");
            }

            InputStream is = new FileInputStream(res.getFile());
            properties.load(is);

            if (properties.size() <= 0)
            {
                throw new RuntimeException("配置文件为空！请添加配置项");
            }
        }
    }

}
