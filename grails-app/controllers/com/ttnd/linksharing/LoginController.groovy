package com.ttnd.linksharing

class LoginController {

    def index() {
        if(session.user)
            forward(controller: 'User',action: 'index')
        else
            render("failure")
    }

    def logout(){
        session.invalidate()
        foward(action: index)

    }

    def loginHandler(String username,String password){
    }
}
