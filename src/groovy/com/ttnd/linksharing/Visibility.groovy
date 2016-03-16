package com.ttnd.linksharing


enum Visibility {
    PUBLIC('Public'), PRIVATE('Private')

    final String value

    static convert(String s) {
        valueOf(s.toUpperCase())
    }

    Visibility(String value) {
        this.value = value
    }

    String toString() { value }

    String getKey() { name() }

}

