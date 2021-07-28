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

}