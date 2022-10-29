package com.xzy.multiapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform