package com.ttnd.linksharing


enum Visibility {
    PUBLIC, PRIVATE

    static convert(String s) {
        valueOf(s.toUpperCase())
    }

}

