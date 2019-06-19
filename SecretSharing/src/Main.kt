
import misiaxis.SecretSharing.CalculateSecretFromShamirShares
import misiaxis.SecretSharing.ShamirSecretShare
import java.util.*



    public fun main(args:Array<String>) {
        println("Let's test our pirates")

        val trivialshares = misiaxis.SecretSharing.SplitSecretToTrivialShares(2019,5)
        val result01 = misiaxis.SecretSharing.CalculateSecretFromTrivialShares(trivialshares)

        val shamirshares = misiaxis.SecretSharing.SplitSecretToShamirShares(2019.0,10,2)

        val testshamir = mutableListOf<ShamirSecretShare>()
        testshamir.add(ShamirSecretShare(5.0,2.0))
        testshamir.add(ShamirSecretShare(4.0,1.0))
        testshamir.add(ShamirSecretShare(3.0,2.0))


        val result02 = CalculateSecretFromShamirShares(testshamir) //should be 17
        val result03 = CalculateSecretFromShamirShares(shamirshares) //should be 2019

        val fewshamirshares = mutableListOf<ShamirSecretShare>(shamirshares[3],shamirshares[1],shamirshares[2])

        val result04 = CalculateSecretFromShamirShares(fewshamirshares);


        val MyPatentedWayOfDebugging = true
    }
