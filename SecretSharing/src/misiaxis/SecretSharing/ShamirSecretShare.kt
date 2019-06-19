package misiaxis.SecretSharing

import Matma.Funkcja
import kotlin.random.Random
import kotlin.random.nextInt

class ShamirSecretShare {
    private var x:Double
    private var fx:Double

    public fun X():Double{return x}
    public fun FX():Double{return fx}

    public constructor(X:Double,Fx:Double){
        x=X
        fx=Fx
    }
}

public fun SplitSecretToShamirShares(Secret:Double,AllShares:Int,MinimumSharesNeeedToCalculateSecret:Long):List<ShamirSecretShare>{

    val rnd = Random(System.currentTimeMillis())
    var shamirshares = mutableListOf<ShamirSecretShare>()

    do {
        val ai = mutableListOf<Double>() //wspolczynniki funkcji
        for (a in 0 until MinimumSharesNeeedToCalculateSecret) { //stopien wielomianu odpowiada na pytanie "ile minimum udzialow bedzie potrzebne do odtworzenia sekretu"
            var b = rnd.nextInt(-100, 100)
            while (b == 0) b = rnd.nextInt(-100, 100)
            ai.add(b.toDouble())
        }
        ai.add(Secret)//ostatni wspolczynnik to wyraz wolny wielomianu

        val xi = mutableListOf<Double>()
        for (it in 0..AllShares) {
            var x = (rnd.nextInt(1, 100)).toDouble() //losowanie x czyl argumentow
            if (!xi.contains(x)) xi.add(x)
            else {
                while (xi.contains(x)) {
                    x = (rnd.nextInt(1, 100)).toDouble()
                }
                xi.add(x)
            }
        }

        val func = Funkcja(ai.toTypedArray())


        for (it in 0..AllShares) {
            shamirshares.add(ShamirSecretShare(xi[it], func.F(xi[it])))
        }
    }while (CalculateSecretFromShamirShares(shamirshares.toList())!=Secret)



    return shamirshares.toList()
}

public fun CalculateSecretFromShamirShares(ShamirShares:List<ShamirSecretShare>):Double{
    var secret:Double=0.0

    var singleq:Double

    for(ShamirShare in ShamirShares){
        singleq=1.0
        for(ShamirSHARE in ShamirShares){

            if(ShamirSHARE.X()!=ShamirShare.X()) {
                singleq*=((0.0 - ShamirSHARE.X()) * Math.pow((ShamirShare.X() - ShamirSHARE.X()),-1.0))%15485863
            }
        }

        secret+=ShamirShare.FX()*singleq
    }

    secret= Math.round(secret).toDouble()


    return secret
}

