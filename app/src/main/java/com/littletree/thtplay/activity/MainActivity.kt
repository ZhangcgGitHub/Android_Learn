package com.littletree.thtplay.activity

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.littletree.thtplay.R
import com.littletree.thtplay.fragment.HomeFragment
import com.littletree.thtplay.fragment.ServiceFragment
import com.littletree.thtplay.fragment.SpotsFragment


class MainActivity : FragmentActivity(), View.OnClickListener {

    // 底部菜单Linear layout
    private var ll_home: LinearLayout? = null
    private var ll_spots: LinearLayout? = null
    private var ll_service: LinearLayout? = null

    // 底部菜单ImageView
    private var iv_home: ImageView? = null
    private var iv_spots: ImageView? = null
    private var iv_service: ImageView? = null

    // 底部菜单菜单标题
    private var tv_home: TextView? = null
    private var tv_spots: TextView? = null
    private var tv_service: TextView? = null

    // 顶部标题
    private var tv_top: TextView? = null

    // Fragment
    private var homeFragment: HomeFragment? = null
    private var spotsFragment: SpotsFragment? = null
    private var serviceFragment: ServiceFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化控件
        initView()
        // 初始化底部按钮事件
        initEvent()
        // 初始化并设置当前Fragment
        initFragment(0)

    }

    private fun initFragment(index: Int) {
        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        val fragmentManager = supportFragmentManager
        // 开启事务
        val transaction = fragmentManager.beginTransaction()
        // 隐藏所有Fragment
        hideFragment(transaction)
        when (index) {
            0 -> if (homeFragment == null) {
                homeFragment = HomeFragment()
                transaction.add(R.id.fl_content, homeFragment)
            } else {
                transaction.show(homeFragment)
            }
            1 -> if (spotsFragment == null) {
                spotsFragment = SpotsFragment()
                transaction.add(R.id.fl_content, spotsFragment)
            } else {
                transaction.show(spotsFragment)
            }
            2 -> if (serviceFragment == null) {
                serviceFragment = ServiceFragment()
                transaction.add(R.id.fl_content, serviceFragment)
            } else {
                transaction.show(serviceFragment)
            }
            else -> {
            }
        }

        // 提交事务
        transaction.commit()

    }

    //隐藏Fragment
    private fun hideFragment(transaction: FragmentTransaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment)
        }
        if (spotsFragment != null) {
            transaction.hide(spotsFragment)
        }
        if (serviceFragment != null) {
            transaction.hide(serviceFragment)
        }

    }

    private fun initEvent() {
        // 设置按钮监听
        ll_home!!.setOnClickListener(this)
        ll_spots!!.setOnClickListener(this)
        ll_service!!.setOnClickListener(this)

    }

    private fun initView() {

        // 底部菜单Linearl ayout
        this.ll_home = findViewById(R.id.ll_home) as LinearLayout?
        this.ll_spots = findViewById(R.id.ll_spots) as LinearLayout?
        this.ll_service = findViewById(R.id.ll_service) as LinearLayout?

        // 底部菜单ImageView
        this.iv_home = findViewById(R.id.iv_home) as ImageView?
        this.iv_spots = findViewById(R.id.iv_spots) as ImageView?
        this.iv_service = findViewById(R.id.iv_service) as ImageView?

        // 底部菜单菜单标题
        this.tv_home = findViewById(R.id.tv_home) as TextView?
        this.tv_spots = findViewById(R.id.tv_spots) as TextView?
        this.tv_service = findViewById(R.id.tv_service) as TextView?

        // 顶部标题
        this.tv_top = findViewById(R.id.tv_top) as TextView?

    }

    override fun onClick(v: View) {
        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton()
        // ImageView和TetxView置为绿色，页面随之跳转
        when (v.id) {
            R.id.ll_home -> {
                iv_home!!.setImageResource(R.drawable.ic_home1)
                tv_home!!.setTextColor(0xff439ad7.toInt())
                tv_top!!.setText(R.string.tv_top)
                tv_top!!.setTextColor(0xff439ad7.toInt())
                initFragment(0)
            }
            R.id.ll_spots -> {
                iv_spots!!.setImageResource(R.drawable.ic_spot1)
                tv_spots!!.setTextColor(0xff439ad7.toInt())
                tv_top!!.setText(R.string.tv_spots)
                tv_top!!.setTextColor(0xff439ad7.toInt())
                initFragment(1)
            }
            R.id.ll_service -> {
                iv_service!!.setImageResource(R.drawable.ic_service1)
                tv_service!!.setTextColor(0xff439ad7.toInt())
                tv_top!!.setText(R.string.tv_service)
                tv_top!!.setTextColor(0xff439ad7.toInt())
                initFragment(2)
            }
            else -> {
            }
        }

    }

    private fun restartBotton() {
        // ImageView置为灰色
        iv_home!!.setImageResource(R.drawable.ic_home)
        iv_spots!!.setImageResource(R.drawable.ic_spot)
        iv_service!!.setImageResource(R.drawable.ic_service)

        // TextView置为白色
        tv_home!!.setTextColor(0xff787878.toInt())
        tv_spots!!.setTextColor(0xff787878.toInt())
        tv_service!!.setTextColor(0xff787878.toInt())

    }

}
