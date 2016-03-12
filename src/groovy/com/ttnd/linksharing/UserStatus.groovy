package com.ttnd.linksharing

enum UserStatus {
    All_USER('All User'), Active('Active'), Inactive('Inactive')

    final String value

    UserStatus(String value) {
        this.value = value
    }

    String toString() { value }

    String getKey() { name() }
}
