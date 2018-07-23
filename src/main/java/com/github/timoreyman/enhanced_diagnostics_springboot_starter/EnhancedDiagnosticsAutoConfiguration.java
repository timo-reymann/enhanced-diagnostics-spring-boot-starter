package com.github.timoreyman.enhanced_diagnostics_springboot_starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
public class EnhancedDiagnosticsAutoConfiguration {
    @Bean
    public ReportProcessorBean reportProcessorBean() {
        return report -> log.info("New report", report);
    }
}
