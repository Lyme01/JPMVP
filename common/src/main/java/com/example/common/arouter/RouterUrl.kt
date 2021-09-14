package com.example.common.arouter

class RouterUrl {

    companion object{
        private const val ACTIVITY ="Activity"
    }

   object APP{
       private const val model="/App/"
       const val Tab = model + "tab" + ACTIVITY
   }
    object Web {
        private const val model = "/web/"
        const val H5 = model + "web" + ACTIVITY //vm版H5 web

        const val BridgeWebView = model + "BridgeWebView" + ACTIVITY //vm版H5 web
    }

    object Login{
        private const val model="/login/"
        const val  Login = model +"login"+ ACTIVITY
        const val  Register = model +"register"+ ACTIVITY
    }
    object Home{
        private const val model="/home/"
        const val Search= model+"search"+ ACTIVITY
        const val Detail= model+"detail"+ ACTIVITY
    }
    object Mine{
        private const val model="/mine/"
        const val Setting=model+"setting"+ ACTIVITY
        const val Collection=model+"collection"+ ACTIVITY
    }

}