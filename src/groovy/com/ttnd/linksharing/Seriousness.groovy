package com.ttnd.linksharing

enum Seriousness {
    SERIOUS, VERY_SERIOUS, CASUAL

    static convert(String s) {
        return valueOf(s.toUpperCase())
    }
}