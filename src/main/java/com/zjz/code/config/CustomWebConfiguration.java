package com.zjz.code.config;

import com.zjz.code.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Author zjz
 * @Date 2021/1/18 16:28
 */

@Configuration
public class CustomWebConfiguration implements WebMvcConfigurer {

    /**
     * 注入配置文件中写好的图片保存路径
     */
    @Value("${user.filepath}")
    private String filePath;

    /**
     * 文件访问路径
     */
    @Value("${user.virtualpath}")
    private String virtualPath;

    /**
     * 自定义资源映射
     * 访问图片示例：http://localhost:3000/api/images/图片名称.jpg
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(virtualPath + "**")
            .addResourceLocations("file:"+ filePath);
    }

    /**
     * 注册自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new MyInterceptor());
        //添加拦截的路径
        registration.addPathPatterns("/admin/**");
        //添加不拦截路径
        /*registration.excludePathPatterns();*/
    }
}
