package com.ttnd.linksharing

enum Seriousness {
    SERIOUS('Serious'), VERY_SERIOUS('Very Serious'), CASUAL('Casual')

    final String value

    static convert(String s) {
        return valueOf(s.toUpperCase())
    }


    Seriousness(String value) {
        this.value = value
    }

    String toString() { value }

    String getKey() { name() }
}