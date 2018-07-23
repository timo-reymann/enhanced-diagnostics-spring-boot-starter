package com.github.timoreyman.enhanced_diagnostics_springboot_starter;


import com.github.timo_reymann.spring_boot_enhanced_diagnostics_starter.dto.Report;

/**
 * Process ingoing reports
 */
public interface ReportProcessorBean {
    void process(Report report);
}
