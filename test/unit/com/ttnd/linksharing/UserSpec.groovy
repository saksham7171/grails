package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    @Unroll("#sno")
    def "validating user"() {
        given:
        User user = new User(email: email, username: uname, password: pwd, firstName: fname, lastName: lname,
                admin: admin, active: active, photo: photo)
        when:
        Boolean valid = user.validate()

        then:
        valid == result

        where:
        sno | email          | uname      | pwd        | fname   | lname  | admin | active | photo         | result
        1   | "abc@ttnd.com" | "username" | "password" | "first" | "last" | true  | true   | "photo".bytes | true
        2   | "abc "         | "username" | "password" | "first" | "last" | true  | true   | "photo".bytes | false
        3   | ""             | "username" | "password" | "first" | "last" | true  | true   | "photo".bytes | false
        4   | null           | "username" | "password" | "first" | "last" | true  | true   | "photo".bytes | false
        5   | "abc@ttnd.com" | ""         | "password" | "first" | "last" | true  | true   | "photo".bytes | false
        6   | "abc@ttnd.com" | null       | "password" | "first" | "last" | true  | true   | "photo".bytes | false
        7   | "abc@ttnd.com" | "username" | ""         | "first" | "last" | true  | true   | "photo".bytes | false
        8   | "abc@ttnd.com" | "username" | null       | "first" | "last" | true  | true   | "photo".bytes | false
        9   | "abc@ttnd.com" | "username" | "password" | ""      | "last" | true  | true   | "photo".bytes | false
        10  | "abc@ttnd.com" | "username" | "password" | null    | "last" | true  | true   | "photo".bytes | false
        11  | "abc@ttnd.com" | "username" | "password" | "first" | ""     | true  | true   | "photo".bytes | false
        12  | "abc@ttnd.com" | "username" | "password" | "first" | null   | true  | true   | "photo".bytes | false
        13  | "abc@ttnd.com" | "username" | "password" | "first" | "last" | false | true   | "photo".bytes | true
        14  | "abc@ttnd.com" | "username" | "password" | "first" | "last" | true  | true   | "photo".bytes | true
        15  | "abc@ttnd.com" | "username" | "password" | "first" | "last" | null  | true   | "photo".bytes | true
        16  | "abc@ttnd.com" | "username" | "password" | "first" | "last" | true  | false  | "photo".bytes | true
        17  | "abc@ttnd.com" | "username" | "password" | "first" | "last" | true  | true   | "photo".bytes | true
        18  | "abc@ttnd.com" | "username" | "password" | "first" | "last" | true  | null   | "photo".bytes | true
        19  | "abc@ttnd.com" | "username" | "password" | "first" | "last" | true  | true   | "".bytes      | true
        20  | "abc@ttnd.com" | "username" | "password" | "first" | "last" | true  | true   | null          | true
        21  | "abc@ttnd.com" | "username" | "123"      | "first" | "last" | true  | false  | "photo".bytes | false


    }

    def "validating duplicate email"() {

        given:
        User user = new User(email: "abc@tothenew.com", username: "saksham", password: "pass1234", firstName: "saksham",
                lastName: "sharma", admin: true, active: true, photo: "photo".bytes)
        User user1 = new User(email: "abc@tothenew.com", username: "saksham", password: "pass1234", firstName: "saksham",
                lastName: "sharma", admin: true, active: true, photo: "photo".bytes)

        when:
        user.save(flush: true)
        user1.save(flush: true)

        then:
        !user.errors.allErrors.size()
        user1.errors.allErrors.size()
        user1.errors.getFieldError('email')

    }

}
