package com.github.timoreyman.enhanced_diagnostics_springboot_starter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.timo_reymann.spring_boot_enhanced_diagnostics_starter.dto.EncryptedReport;
import com.github.timoreyman.enhanced_diagnostics_springboot_starter.security.KeyHolder;
import com.github.timoreyman.enhanced_diagnostics_springboot_starter.transfer.PublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/${enhanced-diagnostics.route-prefix:/report}")
public class ReportRestController {
    private final ReportProcessorBean reportProcessorBean;
    private final ObjectMapper objectMapper;
    private final KeyHolder keyHolder;

    @Autowired
    public ReportRestController(ReportProcessorBean reportProcessorBean, ObjectMapper objectMapper, KeyHolder keyHolder) {
        this.reportProcessorBean = reportProcessorBean;
        this.objectMapper = objectMapper;
        this.keyHolder = keyHolder;
    }

    @PostMapping
    public void sendReport(@RequestBody List<String> encryptedChunks) {
        EncryptedReport encryptedReport = new EncryptedReport(encryptedChunks);
        reportProcessorBean.process(encryptedReport.decrypt(keyHolder.getKeyPair().getPrivate(), objectMapper));
    }

    @GetMapping("/publicKey")
    public PublicKey getPublicKey() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-----BEGIN PRIVATE KEY-----\n");
        byte[] encoded = keyHolder.getKeyPair().getPublic().getEncoded();
        stringBuilder.append(new String(Base64.getEncoder().encode(encoded)));
        stringBuilder.append("\n-----END PRIVATE KEY-----\n");
        return new PublicKey(stringBuilder.toString());
    }
}
