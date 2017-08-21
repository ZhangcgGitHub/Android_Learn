package com.littletree.thtplay.util

/**
 * Created by frank.zhang on 2017/8/18.
 */
class ApiException : RuntimeException {
    private var ErrCode: Int = 0

    constructor(msg: String) : super(msg) {
        this.ErrCode = -1
    }

    constructor(errCode: Int, msg: String) : super(msg) {
        this.ErrCode = errCode
    }

    constructor(format: String, vararg args: Any) : super(String.format(format, *args)) {
        this.ErrCode = -1
    }
}