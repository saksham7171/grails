package com.ttnd.linksharing.util

import org.apache.commons.lang.RandomStringUtils

class PasswordGenerator {
    static String getRandomPassword() {
        Integer length = 6
        String randomString = RandomStringUtils.randomAlphanumeric(length)
        return randomString
    }
}