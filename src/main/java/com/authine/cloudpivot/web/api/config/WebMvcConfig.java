package com.authine.cloudpivot.web.api.config;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author longhai
 */
@Configuration
@SuppressWarnings("unused")
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${cloudpivot.webmvc.corsmappings:true}")
	private boolean corsMappings;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	if(corsMappings) {
		    registry.addMapping("/**")
				    .allowedOrigins("*")
				    .allowedHeaders("*")
				    .allowedMethods("*")
				    .maxAge(3600);
	    }
    }

    @Bean
    public ReferenceConfig referenceConfig(@Value("${dubbo.registry.check}") boolean check, @Value("${dubbo.provider.token}") String token) {
        ReferenceConfig<Object> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setCheck(check);
        RpcContext.getContext().setAttachment("token", token);
        return referenceConfig;
    }

}
