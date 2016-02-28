package com.ttnd.linksharing


class UnitController {

    def index() {
        List<User> user=User.list()
        render view:'index',model: [user:user]
    }
//        log.fatal("fatal ******************************")
//        log.error("erorr ******************************")
//        log.warn("warn *******************************")
//        log.info("info *****************************")
//        log.debug("debug***************************")
//        log.trace("trace***************************")
//    }

    def save(User user){
        if(user?.hasErrors())
            render view: 'index'
        else
        render "saved"
    }


}
