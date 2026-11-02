package com.example.dlcsp.util;

import java.util.UUID;

public final class IdGenerator {

    private IdGenerator() {}

    public static String newUserId() {
        // Produces e.g. USR-1A2B3C4D; avoids the duplicate-ID problem from Step 5
        return "USR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static String newReportId() {
        return "RPT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}