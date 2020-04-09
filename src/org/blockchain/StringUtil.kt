package org.blockchain

import java.security.MessageDigest


object StringUtil {
    fun applySha256(input: String) : String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            //Applies sha256 to our input,
            val hash = digest.digest(input.toByteArray())
            // This will contain hash as hexadecimal
            val hexString = StringBuffer()
            for (i in hash.indices) {
                val hex = Integer.toHexString(0xff and hash[i].toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            hexString.toString()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
