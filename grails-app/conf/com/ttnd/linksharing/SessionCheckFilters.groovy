package com.ttnd.linksharing

class SessionCheckFilters {

    def filters = {
        all(controller: 'login', invert:true) {
            before = {
                if(!session.user)
                    redirect(controller: 'Login',action: 'index')
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}