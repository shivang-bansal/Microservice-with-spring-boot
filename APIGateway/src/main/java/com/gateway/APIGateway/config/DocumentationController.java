package com.gateway.APIGateway.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@EnableAutoConfiguration
public class DocumentationController implements SwaggerResourcesProvider {

    @Override
    public List get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("user-service", "/user-service/v2/api-docs", "1.0"));
        resources.add(swaggerResource("conversion-service", "/conversion-service/v2/api-docs", "1.0"));
        resources.add(swaggerResource("exchange-service", "/exchange-service/v2/api-docs", "1.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}