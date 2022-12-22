package ru.geelbrains.spring.winter.market.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/wm/*");
    }

    //http://localhost:8189/winter/wm/students.wsdl
    @Bean("products")
    public DefaultWsdl11Definition groupWsdDefinition(XsdSchema productsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ProductsPort");
        wsdl11Definition.setLocationUri("/wm");
        wsdl11Definition.setTargetNamespace("http://www.winter-market.com/spring/products");
        wsdl11Definition.setSchema(productsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema productsSchema() {return new SimpleXsdSchema(new ClassPathResource("products.xsd"));
    }
}
