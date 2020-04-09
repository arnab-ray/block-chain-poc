package org.blockchain

class NoobChain {

    private val blockChain: MutableList<Block> = mutableListOf()
    private val difficulty = 5;

    private fun isChainValid() : Boolean {
        var currentBlock: Block
        var previousBlock: Block

        val hashTarget = String(CharArray(difficulty)).replace('\u0000', '0')

        //loop through blockchain to check hashes:
        //loop through blockchain to check hashes:
        for (i in 1 until blockChain.size) {
            currentBlock = blockChain[i]
            previousBlock = blockChain[i - 1]
            //compare registered hash and calculated hash:
            if (currentBlock.hash != currentBlock.calculateHash()) {
                println("Current Hashes not equal")
                return false
            }
            //compare previous hash and registered previous hash
            if (previousBlock.hash != currentBlock.previousHash) {
                println("Previous Hashes not equal")
                return false
            }
            //check if hash is solved
            if(currentBlock.hash.substring( 0, difficulty) != hashTarget) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true
    }

    fun mine() {
        val genesisBlock = Block("Hi im the first block", "0")
        println("Hash for block 1 : " + genesisBlock.hash)
        blockChain.add(genesisBlock)
        blockChain[0].mineBlock(difficulty)

        val secondBlock = Block("Yo im the second block", genesisBlock.hash)
        println("Hash for block 2 : " + secondBlock.hash)
        blockChain.add(secondBlock)
        blockChain[1].mineBlock(difficulty)

        val thirdBlock = Block("Hey im the third block", secondBlock.hash)
        println("Hash for block 3 : " + thirdBlock.hash)
        blockChain.add(thirdBlock)
        blockChain[2].mineBlock(difficulty)

        println("\nBlock-chain is Valid: " + isChainValid())
    }

}

fun main() {
    NoobChain().mine()
}
