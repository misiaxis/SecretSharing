package Matma

import kotlin.math.pow


class Funkcja {

        constructor(wspolczynniki: Array<Double>) {
            this.wspolczynniki = wspolczynniki

        }

        public var wspolczynniki: Array<Double>? = null

        public var start=0.0
        public var end=0.0


        fun F(x: Double): Double {
            var f: Double = 0.0

            for (wsp in 0 until wspolczynniki!!.size step 1) {
                f += wspolczynniki!![wsp] * (x.pow(wspolczynniki!!.size - 1 - wsp))
            }

            return f
        }


    }


