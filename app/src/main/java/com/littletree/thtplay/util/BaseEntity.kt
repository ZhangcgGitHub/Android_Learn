package com.littletree.thtplay.util

import com.google.gson.Gson

/**
 * Created by frank.zhang on 2017/8/18.
 */
open class BaseEntity {
    @Comment("返回的错误码, 0: 成功, 非0: 错误")
    var ret: Int

    @Comment("ret=0时, 返回OK, 非0时, 返回错误描述信息")
    var errmsg: String?

    @Comment("ret 非0时, 附加的错误信息, Json 格式")
    var errors: String?

    init {
        ret = 0
        errmsg = "OK"
        errors = null
    }

    fun toJsonStr(): String {
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return toJsonStr()
    }

    open fun SampleData() {
        ret = 0
        errmsg = "OK"
        errors = null
    }

    fun OnError(ex: Throwable) {
        this.ret = -1
        this.errmsg = ex.message
    }

}