package com.zjz.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author zjz
 * @Date 2021/1/1 12:38
 */
@SuppressWarnings("ALL")
@Configuration
// 这个注解应该是针对swagger3.0以下的版本，3.0不用写
/*@EnableSwagger2*/
/*@ConditionalOnProperty(name = "swagger.enable",  havingValue = "true")*/
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            // 为当前包路径, swagger3 好像不用配置了
            // .apis(RequestHandlerSelectors.basePackage("com.zjz.code.controller"))
            .paths(PathSelectors.any())
            .build()
            .globalRequestParameters(globalRequestParameters())
            /*.securitySchemes(security())*/;
    }

    private List<SecurityScheme> security() {
        ApiKey apiKey = new ApiKey("Token", "Token", "header");
        return Collections.singletonList(apiKey);
    }

    private List<RequestParameter> globalRequestParameters() {
        RequestParameterBuilder parameterBuilder = new RequestParameterBuilder()
            //每次请求加载header
            .in(ParameterType.HEADER)
            //头标签
            .name("token")
            .required(true)
            .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
        return Collections.singletonList(parameterBuilder.build());
    }

    @Bean(value = "adminApi")
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .paths(PathSelectors.ant("/admin/**"))
            .build()
            .globalRequestParameters(globalRequestParameters())
            .groupName("需要token验证");
    }

    @Bean(value = "publicApi")
    public Docket publicApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .paths(PathSelectors.ant("/default/**"))
            .build().groupName("无需token验证");
    }

    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            // 页面标题
            .title("garbage-sorting(垃圾分类宣传平台) API")
            // 创建人信息
            .contact(new Contact("zjz",  "https://github.com/Annnnnnovice",  "823940249@qq.com"))
            // 版本号
            .version("1.0")
            // 描述
            .description("API 描述")
            .build();
    }
}
