// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fi.ixonos.builder.web;

import fi.ixonos.builder.domain.Meego;
import fi.ixonos.builder.domain.NewsTemplate;
import fi.ixonos.builder.domain.Platform;
import fi.ixonos.builder.domain.Projects;
import fi.ixonos.builder.domain.Symbian;
import fi.ixonos.builder.domain.Templates;
import java.lang.String;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(new MeegoConverter());
        registry.addConverter(new NewsTemplateConverter());
        registry.addConverter(new PlatformConverter());
        registry.addConverter(new ProjectsConverter());
        registry.addConverter(new SymbianConverter());
        registry.addConverter(new TemplatesConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
    static class fi.ixonos.builder.web.ApplicationConversionServiceFactoryBean.MeegoConverter implements Converter<Meego, String> {
        public String convert(Meego meego) {
            return new StringBuilder().append(meego.getHome_page()).append(" ").append(meego.getShort_description()).append(" ").append(meego.getLong_description()).append(" ").append(meego.getDisplay_name()).toString();
        }
        
    }
    
    static class fi.ixonos.builder.web.ApplicationConversionServiceFactoryBean.NewsTemplateConverter implements Converter<NewsTemplate, String> {
        public String convert(NewsTemplate newsTemplate) {
            return new StringBuilder().append(newsTemplate.getCategory_url()).toString();
        }
        
    }
    
    static class fi.ixonos.builder.web.ApplicationConversionServiceFactoryBean.PlatformConverter implements Converter<Platform, String> {
        public String convert(Platform platform) {
            return new StringBuilder().append(platform.getPlatform_name()).toString();
        }
        
    }
    
    static class fi.ixonos.builder.web.ApplicationConversionServiceFactoryBean.ProjectsConverter implements Converter<Projects, String> {
        public String convert(Projects projects) {
            return new StringBuilder().append(projects.getName()).append(" ").append(projects.getEmail()).toString();
        }
        
    }
    
    static class fi.ixonos.builder.web.ApplicationConversionServiceFactoryBean.SymbianConverter implements Converter<Symbian, String> {
        public String convert(Symbian symbian) {
            return new StringBuilder().append(symbian.getFilename()).append(" ").append(symbian.getUrl()).toString();
        }
        
    }
    
    static class fi.ixonos.builder.web.ApplicationConversionServiceFactoryBean.TemplatesConverter implements Converter<Templates, String> {
        public String convert(Templates templates) {
            return new StringBuilder().append(templates.getName()).toString();
        }
        
    }
    
}
