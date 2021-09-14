package com.example.mine.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.mine.R
import android.widget.TextView




@SuppressLint("ResourceType")
class CustomDialog(context: Context): Dialog(context, R.layout.custom_dialog) {
    /**
     * 显示的图片
     */
    private var imageIv: ImageView? = null

    /**
     * 显示的标题
     */
    private var titleTv: TextView? = null

    /**
     * 显示的消息
     */
    private var messageTv: TextView? = null

    /**
     * 确认和取消按钮
     */
    private var negtiveBn: Button? = null
    /**
     * 确认和取消按钮
     */
    private  var positiveBn:Button? = null

    /**
     * 按钮之间的分割线
     */
    private var columnLineView: View? = null

    private var message:String?=null
    private var title:String?=null
    private var positive:String?=null
    private var negtive:String?=null
    private var imageResId=-1

    /**
     * 底部是否只有一个按钮
     */
    private var isSingle:Boolean=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog)

        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        refreshView();
        //初始化界面控件的事件
        initEvent();
    }

    private fun initView() {
     negtiveBn =findViewById(R.id.negtive)
     positiveBn=findViewById(R.id.positive)
     titleTv=findViewById(R.id.title)
     messageTv=findViewById(R.id.message)
     imageIv=findViewById(R.id.image)
     columnLineView=findViewById(R.id.column_line)
    }

    private fun refreshView() {
        if (!TextUtils.isEmpty(title)){
            titleTv?.text=title
            titleTv?.visibility=View.VISIBLE
        }else{
            titleTv?.visibility=View.GONE
        }
        if (!TextUtils.isEmpty(message)){
            messageTv?.text=message
        }
        if (!TextUtils.isEmpty(positive)){
            positiveBn?.text=positive
        }else{
            positiveBn?.text="确定"
        }
        if (!TextUtils.isEmpty(negtive)){
            negtiveBn?.text=negtive
        }else{
            negtiveBn?.text="取消"
        }
        if (imageResId!=-1){
            imageIv?.setImageResource(imageResId)
            imageIv?.visibility=View.VISIBLE
        }else{
            imageIv?.visibility=View.GONE
        }
        if (isSingle){
            columnLineView?.visibility=View.GONE
            negtiveBn?.visibility=View.GONE
        }else{
            columnLineView?.visibility=View.VISIBLE
            negtiveBn?.visibility=View.VISIBLE
        }

    }
    private fun initEvent() {
     positiveBn?.setOnClickListener {
         if (onClickBottomListener != null) {
             onClickBottomListener!!.onPositiveClick()
         }
     }
        negtiveBn?.setOnClickListener {
            if (onClickBottomListener!=null){
                onClickBottomListener!!.onNegtiveClick()
            }
        }
    }

    override fun show() {
        super.show()
        refreshView()
    }

    /**
     * 设置确定取消按钮的回调
     */
    var onClickBottomListener: OnClickBottomListener? = null
    fun setOnClickBottomListener(onClickBottomListener: OnClickBottomListener?): CustomDialog? {
        this.onClickBottomListener = onClickBottomListener
        return this
    }
    interface OnClickBottomListener{
        /**
         * 点击确定按钮事件
         */
        fun onPositiveClick()

        /**
         *点击取消按钮事件
         */

        fun onNegtiveClick()
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?): CustomDialog? {
        this.message = message
        return this
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?): CustomDialog? {
        this.title = title
        return this
    }

    fun getPositive(): String? {
        return positive
    }

    fun setPositive(positive: String?): CustomDialog? {
        this.positive = positive
        return this
    }

    fun getNegtive(): String? {
        return negtive
    }

    fun setNegtive(negtive: String?): CustomDialog? {
        this.negtive = negtive
        return this
    }

    fun getImageResId(): Int {
        return imageResId
    }

    fun isSingle(): Boolean {
        return isSingle
    }

    fun setSingle(single: Boolean): CustomDialog? {
        isSingle = single
        return this
    }

    fun setImageResId(imageResId: Int): CustomDialog? {
        this.imageResId = imageResId
        return this
    }



}