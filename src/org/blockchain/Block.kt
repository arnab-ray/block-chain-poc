package org.blockchain

import java.util.*

class Block(private val data: String, var previousHash: String) {

    var hash: String
    private var timeStamp: Long = Date().time
    private var nonce = 0

    init {
        this.hash = calculateHash()
    }

    fun calculateHash() = StringUtil.applySha256(previousHash + timeStamp.toString() + nonce.toString() + data)

    fun mineBlock(difficulty: Int) {
        //Create a string with difficulty * "0"
        val target = String(CharArray(difficulty)).replace('\u0000', '0')
        while (hash.substring(0, difficulty) != target) {
            nonce++
            hash = calculateHash()
        }
        println("Block Mined!!! : $hash")
    }
}
