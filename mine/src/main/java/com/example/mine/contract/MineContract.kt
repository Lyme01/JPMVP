package com.example.mine.contract

import com.example.common.base.BaseContract

class MineContract:BaseContract {
    interface MineView:BaseContract.BaseView{

    }

    interface MinePresenter:BaseContract.BasePresenter{
      fun logout()
    }
}