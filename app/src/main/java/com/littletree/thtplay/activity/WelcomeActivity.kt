package com.littletree.thtplay.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import butterknife.ButterKnife
import com.littletree.thtplay.R
import com.littletree.thtplay.util.SharedPreferencesUtil
import com.littletree.thtplay.util.find
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import java.util.*
import java.util.concurrent.TimeUnit

class WelcomeActivity : Activity() {

    val mIVEntry by lazy { find<ImageView>(R.id.iv_entry) }

    private val ANIM_TIME = 2000

    private val SCALE_END = 1.15f

    private val images = intArrayOf(R.drawable.welcomimg1, R.drawable.welcomimg2, R.drawable.welcomimg3, R.drawable.welcomimg4, R.drawable.welcomimg5, R.drawable.welcomimg6, R.drawable.welcomimg7, R.drawable.welcomimg8, R.drawable.welcomimg9, R.drawable.welcomimg10, R.drawable.welcomimg11, R.drawable.welcomimg12)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 判断是否是第一次开启应用
        val isFirstOpen = SharedPreferencesUtil.getBoolean(this, SharedPreferencesUtil.FIRST_OPEN, true)
        // 如果是第一次启动，则先进入功能引导页
        if (isFirstOpen) {
            val intent = Intent(this, WelcomeGuideActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.activity_welcome)
        ButterKnife.bind(this)
        startMainActivity()
    }

    private fun startMainActivity() {
        val random = Random(SystemClock.elapsedRealtime())//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
        mIVEntry.setImageResource(images[random.nextInt(images.size)])

        rx.Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Action1<Long> {

                    override fun call(aLong: Long?) {
                        startAnim()
                    }
                })
    }

    private fun startAnim() {

        val animatorX = ObjectAnimator.ofFloat(mIVEntry, "scaleX", 1f, SCALE_END)
        val animatorY = ObjectAnimator.ofFloat(mIVEntry, "scaleY", 1f, SCALE_END)

        val set = AnimatorSet()
        set.setDuration(ANIM_TIME.toLong()).play(animatorX).with(animatorY)
        set.start()

        set.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator) {

                startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
                this@WelcomeActivity.finish()
            }
        })
    }

    /**
     * 屏蔽物理返回按钮
     * @param keyCode
     * *
     * @param event
     * *
     * @return
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
