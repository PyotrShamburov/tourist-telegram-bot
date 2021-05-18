package by.free.home.touristtelegrambot.configuration;

import by.free.home.touristtelegrambot.interceptor.AdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/city/**")
                .addPathPatterns("/admin/delete/{adminName}")
                .addPathPatterns("/admin/update/{adminName}")
                .addPathPatterns("/admin/show/{adminName}");
    }
}
