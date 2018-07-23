package com.github.timoreyman.enhanced_diagnostics_springboot_starter.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties("enhanced-diagnostics")
@EnableConfigurationProperties
@Data
public class EnhancedDiagnosticsConfigurationProperties {
    /**
     * Prefix for endpoints
     */
    private String routePrefix = "/report";
}
