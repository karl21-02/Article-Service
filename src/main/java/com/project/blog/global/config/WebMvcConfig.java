package com.project.blog.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${custom.fileDirPath}")
    private String fileDirPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**") // list.html에서 /file/로 접근하면, 실제론 아래 경로에 있는 파일을 가져오기
                .addResourceLocations("file:///" + fileDirPath + "/");

        // localhost:8010/file 경로로 접근하면 실제 file://{fileDirPath}/ 경로로 접근해서 파일을 가져온다.!
    }
}
