package com.littletree.thtplay.adapter

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup


/**
 * Created by frank.zhang on 2017/8/14.
 */
class GuideViewPagerAdapter(var views: List<View>) : PagerAdapter() {

    override fun getCount(): Int {
        if (views.isNotEmpty()) {
            return views.size
        }
        return 0
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(views[position])
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        (container as ViewPager).addView(views[position], 0)
        return views[position]
    }

}