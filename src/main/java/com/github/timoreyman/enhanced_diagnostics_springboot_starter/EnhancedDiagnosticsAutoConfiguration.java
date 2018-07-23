package com.github.timoreyman.enhanced_diagnostics_springboot_starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan
public class EnhancedDiagnosticsAutoConfiguration {
    @Bean
    public ReportProcessorBean reportProcessorBean() {
        return report -> log.info("New report", report);
    }
}
