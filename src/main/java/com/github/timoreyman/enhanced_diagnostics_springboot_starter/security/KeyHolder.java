package com.github.timoreyman.enhanced_diagnostics_springboot_starter.security;

import com.github.timo_reymann.spring_boot_enhanced_diagnostics_starter.util.CryptoUtility;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

@Component
@Slf4j
public class KeyHolder {
    @Getter
    private KeyPair keyPair;

    @Scheduled(fixedRate = 1_000 * 60 * 60 * 12)
    public void renew() throws NoSuchAlgorithmException {
        log.debug("Renew key pair for reporting");
        keyPair = CryptoUtility.generateKey(4096);
    }

}
