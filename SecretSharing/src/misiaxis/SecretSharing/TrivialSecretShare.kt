package misiaxis.SecretSharing

import kotlin.random.Random
import kotlin.random.nextLong

private val sharelimit:Long=10000

public class TrivialSecretShare {
    private var value : Long

    public fun Value():Long{
        return value
    }

    public constructor(SecretShare:Long){
        value=SecretShare
    }


}

public fun SplitSecretToTrivialShares(Secret:Long,Shares:Int):List<TrivialSecretShare>{
    var trivialshares = mutableListOf<TrivialSecretShare>()

    val rnd = Random(System.currentTimeMillis())

    for (share in 0 until Shares-1){
        trivialshares.add(TrivialSecretShare(rnd.nextLong(sharelimit)))
    }

    var trivialsharessum:Long=0
    for(trivialsecret in trivialshares){
        trivialsharessum+=trivialsecret.Value()
    }
    trivialshares.add(TrivialSecretShare(Secret-trivialsharessum%sharelimit))

    return trivialshares.toList()
}

public fun CalculateSecretFromTrivialShares(trivialshares:List<TrivialSecretShare>):Long{
    var secret:Long=0
    for(trivialshare in trivialshares){
        secret+=trivialshare.Value()
    }
    return secret% sharelimit
}