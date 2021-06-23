/*


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class MysqlGenerator {

    public static void main(String[] args) {

        AutoGenerator mpg = new AutoGenerator();

        // 设置模板引擎,默认为velocity
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        //全局配置
        GlobalConfig gc = new GlobalConfig();

        //得到当前项目的路径
        String oPath = System.getProperty("user.dir");

        //生成文件输出根目录
        gc.setOutputDir(oPath + "/src/main/java");

        //生成完成后不弹出文件框
        gc.setOpen(false);

        //文件覆盖
        gc.setFileOverride(true);

        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);

        // XML 二级缓存
        gc.setEnableCache(false);

        // XML ResultMap
        gc.setBaseResultMap(true);

        // XML columList
        gc.setBaseColumnList(false);

        // 作者
        gc.setAuthor("zjz");

        // 实体属性 Swagger2 注解 是否自动生成接口文档
        gc.setSwagger2(false);


        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setEntityName("%s");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        //设置数据库类型
        dsc.setDbType(DbType.MYSQL);

        dsc.setDriverName("com.mysql.cj.jdbc.Driver");

        //用户名
        dsc.setUsername("root");

        //密码
        dsc.setPassword("123456");

        //指定数据库
        dsc.setUrl("jdbc:mysql://39.108.126.83:3310/garbage_sorting?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        // 表名生成策略 根据表名生成类
        strategy.setNaming(NamingStrategy.underline_to_camel);

        // 需要生成的表名
        strategy.setInclude(new String[]{"t_article", "t_article_label", "t_label", "t_paper", "t_paper_topic", "t_ranking", "t_topic", "t_type", "t_user"});
*/
/*strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);*//*





        strategy.setSuperMapperClass(null);

        //设置实体类串行版本UID是否启用(用于Object IO 的序列化和反序列)
        strategy.setEntitySerialVersionUID(false);

        // 设置实体类表字段注释是否启用
        strategy.setEntityTableFieldAnnotationEnable(false);

        // lombok的配置,默认是false
        //strategy.setEntityLombokModel(false);

        //去除表前缀
        strategy.setTablePrefix("t_");
        //去除字段前缀
        strategy.setFieldPrefix("");


        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包路径
        pc.setParent("com.zjz.code");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml("mapper.xml");
        mpg.setPackageInfo(pc);
        // 执行生成
        mpg.execute();
    }
}
*/
