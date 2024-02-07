package com.java.diogo.challenge.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@NoArgsConstructor
public class NumberUtils {
    public static String random() {
        return String.valueOf(ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L));
    }
}
