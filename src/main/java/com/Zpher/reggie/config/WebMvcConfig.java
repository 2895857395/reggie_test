package com.Zpher.reggie.config;

import com.Zpher.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.smile.MappingJackson2SmileHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * ClassName: WebMvcConfig
 * Package: com.Zpher.reggie.config
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/6 20:24
 * @Version 1.0
 */
@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 设置静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    /**
     * 扩展MVC消息框架的转换器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        log.info("扩展消息转换器");
        //创建新的消息转换器
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());

        converters.add(0, messageConverter);

    }
}
