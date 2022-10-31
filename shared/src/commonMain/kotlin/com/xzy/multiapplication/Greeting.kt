package com.xzy.multiapplication

class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): String {
        return "Hello, ${platform.name.reversed()}!"+
                "\nThere are only ${daysUntilNewYear()} days left until New Year"
    }
}