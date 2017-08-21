package com.littletree.thtplay.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.littletree.thtplay.R
import com.littletree.thtplay.adapter.GuideViewPagerAdapter
import com.littletree.thtplay.util.SharedPreferencesUtil


class WelcomeGuideActivity : Activity(), View.OnClickListener {

    private var vp: ViewPager? = null
    private var adapter: GuideViewPagerAdapter? = null
    private var views: MutableList<View>? = null
    private var startBtn: Button? = null

    // 引导页图片资源
    private val pics = intArrayOf(R.layout.guide_view1, R.layout.guide_view2, R.layout.guide_view3)

    // 底部小点图片
    private var dots: Array<ImageView?> = arrayOfNulls(pics.size)

    // 记录当前选中位置
    private var currentIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_guide)

        views = ArrayList<View>()

        // 初始化引导页视图列表
        for (i in pics.indices) {
            val view = LayoutInflater.from(this).inflate(pics[i], null)

            if (i == pics.size - 1) {
                startBtn = view.findViewById(R.id.btn_enter) as Button
                startBtn!!.setTag("enter")
                startBtn!!.setOnClickListener(this)
            }

            views!!.add(view)

        }

        vp = findViewById(R.id.vp_guide) as ViewPager
        adapter = GuideViewPagerAdapter(views!!)
        vp!!.adapter = adapter
        vp!!.addOnPageChangeListener(PageChangeListener())

        initDots()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        // 如果切换到后台，就设置下次不进入功能引导页
        SharedPreferencesUtil.putBoolean(this@WelcomeGuideActivity, SharedPreferencesUtil.FIRST_OPEN, false)
        finish()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initDots() {
        val ll = findViewById(R.id.ll) as LinearLayout
        // 循环取得小点图片
        for (i in pics.indices) {
            // 得到一个LinearLayout下面的每一个子元素
            dots[i] = ll.getChildAt(i) as ImageView
            dots[i]!!.setEnabled(false)// 都设为灰色
            dots[i]!!.setOnClickListener(this)
            dots[i]!!.setTag(i)// 设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0
        dots[currentIndex]!!.setEnabled(true) // 设置为白色，即选中状态

    }

    /**
     * 设置当前view

     * @param position
     */
    private fun setCurView(position: Int) {
        if (position < 0 || position >= pics.size) {
            return
        }
        vp!!.currentItem = position
    }

    /**
     * 设置当前指示点

     * @param position
     */
    private fun setCurDot(position: Int) {
        if (position < 0 || position > pics.size || currentIndex == position) {
            return
        }
        dots[position]!!.setEnabled(true)
        dots[currentIndex]!!.setEnabled(false)
        currentIndex = position
    }

    override fun onClick(v: View) {
        if (v.tag == "enter") {
            enterMainActivity()
            return
        }

        val position = v.tag as Int
        setCurView(position)
        setCurDot(position)
    }


    private fun enterMainActivity() {
        val intent = Intent(this@WelcomeGuideActivity,
                WelcomeActivity::class.java)
        startActivity(intent)
        SharedPreferencesUtil.putBoolean(this@WelcomeGuideActivity, SharedPreferencesUtil.FIRST_OPEN, false)
        finish()
    }

    private inner class PageChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(position: Int) {

        }

        override fun onPageScrolled(position: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageSelected(position: Int) {
            // 设置底部小点选中状态
            setCurDot(position)
        }

    }

}
