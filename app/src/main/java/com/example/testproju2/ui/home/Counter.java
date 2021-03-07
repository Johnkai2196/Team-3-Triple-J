package com.example.testproju2.ui.home;

public class Counter {

        private int startValue;

        public Counter() {
            this.startValue = 0;
        }
        //yksi annos on noin 13g alkoholia
        public void increaseKpl() {this.startValue += 1;}

        public void increaseByZeroPointHalf() {this.startValue += 6;} // 0.4 annosta
        public void increaseByZeroPointSeven() {this.startValue += 10;} // 0.7 annosta
        public void increaseByOne() {this.startValue += 13; } // 1 annos
        public void increaseByOnePointThree() {this.startValue += 16;} // 1.3 annos
        public void increaseByOneAndHalf() {
            this.startValue += 19;
        } // 1.5 annos
        public void increaseByTwo() {this.startValue += 25;} // 2 annos
        public void increaseByTwoPointThree() {this.startValue += 28;} // 2.3 annos

        //viinat
        public void increaseViinaLitra() {this.startValue += 325;} // 1l
        public void increaseViinaMelkeinLitra() {this.startValue += 234;} //0,7l
        public void increaseViinaPuoliLitraa() {this.startValue += 169;} //0,5l
        public void increaseViinaMelkeinPuoliLitraa() {this.startValue += 117;} // 0,35l

        //likoori
        public void increaseLikoori1PuoliLitraa() {this.startValue += 75;} // 0,5
        public void increaseLikoori1YliPuoliLitraa() {this.startValue += 105;} // 0,7
        public void increaseLikoori2PuoliLitraa() {this.startValue += 90;} // 0,5
        public void increaseLikoori2YliPuoliLitraa() {this.startValue += 125;} // 0,7
        public void increaseLikoori3PuoliLitraa() {this.startValue += 150;} // 0,5
        public void increaseLikoori3YliPuoliLitraa() {this.startValue += 210;} // 0,7
        //Viski
        public void increaseViski1() {this.startValue += 65;} // 0,2l
        public void increaseViski2() {this.startValue += 227.5;} // 0,7l

        //Kalorit
        //Olut
        public void increaseKaloritOlut1() {this.startValue += 132;} // 4,6% & 0,33
        public void increaseKaloritOlut2() {this.startValue += 200;} // 4,6% & 0,5
        public void increaseKaloritOlut3() {this.startValue += 165;} // 5,5% & 0,33
        public void increaseKaloritOlut4() {this.startValue += 250;} // 5,5% & 0,5
        //Siideri
        public void increaseKaloritSiideri1() {this.startValue += 132;} // 4,6% & 0,275
        public void increaseKaloritSiideri2() {this.startValue += 158;} // 4,6% & 0,33
        public void increaseKaloritSiideri3() {this.startValue += 240;} //  4,6% & 0,5
        //Lonkero
        public void increaseKaloritLonkero1() {this.startValue += 158;} // 4,6% & 0,33
        public void increaseKaloritLonkero2() {this.startValue += 240;} // 4,6% & 0,5
        public void increaseKaloritLonkero3() {this.startValue += 189;} // 5,5% & 0,33
        public void increaseKaloritLonkero4() {this.startValue += 287;} // 5,5% & 0,5
        //Viina
        public void increaseKaloritViina1() {this.startValue += 44;} // 0,04l
        public void increaseKaloritViina2() {this.startValue += 770;} // 0,35l
        public void increaseKaloritViina3() {this.startValue += 1100;} // 0,5l
        public void increaseKaloritViina4() {this.startValue += 2200;} // 1l
        //Likööri
        public void increaseKaloritLikoori2() {this.startValue += 1175;} // 0,5l
        public void increaseKaloritLikoori3() {this.startValue += 1645;} // 0,7l
        //Viini
        public void increaseKaloritViini1() {this.startValue += 84;} // 0,12l
        public void increaseKaloritViini2() {this.startValue += 525;} // 0,75l
        //Viski
        public void increaseKaloritViski1() {this.startValue += 440;} // 0,2l
        public void increaseKaloritViski2() {this.startValue += 1540;} // 0,5l


        //resetoi promillelaskurin
        public void reset() {
            startValue = 0;
        }

        //hakee ja palauttaa sen hetkisen arvon
        public int getValue() {
            return startValue;
        }
        public double getDouble() {
            return startValue;
        }
    }


