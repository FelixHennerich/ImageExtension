package de.hennerich.imageextension

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform