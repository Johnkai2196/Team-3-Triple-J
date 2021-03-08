package com.example.testproju2.ui.home;

public class DoubleCounter {

    private double startValue;

    public DoubleCounter() {
        this.startValue = 0.0;
    }
    //Annokset


    public void AnnosMietoYksi() {this.startValue += 0.4;}
    public void AnnosMietoKaksi() {this.startValue += 0.7;}
    public void AnnosMietoKolme() {this.startValue += 1.0;} // yks annos
    public void AnnosMietoNelja() {this.startValue += 1.3;}
    public void AnnosMietoViisi() {this.startValue += 1.5;}
    public void AnnosMietoKuusi() {this.startValue += 2.0;} // kaks annosta
    public void AnnosMietoSeiska() {this.startValue += 2.3;}
    public void AnnosViinaYksi() {this.startValue += 25;} // annoksia litran viinaa
    public void AnnosViinaKaksi() {this.startValue += 18;} // annoksia 0,7l viinaa
    public void AnnosViinaKolme() {this.startValue += 13;} // annoksia 0,5l viinaa
    public void AnnosViinaNelja() {this.startValue += 9;} // annoksia 0,35l viinaa
    public void AnnosLikooriYksi() {this.startValue += 5.7;} // annoksia 0,5l likööriä 17%
    public void AnnosLikooriKaksi() {this.startValue += 8;} // annoksia 0,7l likööriä 17%
    public void AnnosLikooriKolme() {this.startValue += 6.9;} // annoksia 0,5l likööriä 21%
    public void AnnosLikooriNelja() {this.startValue += 9.6;} // annoksia 0,7l likööriä 21%
    public void AnnosLikooriViisi() {this.startValue += 10;} // annoksia 0,5l likööriä 30%
    public void AnnosLikooriKuusi() {this.startValue += 16;} // annoksia 0,7l likööriä 30%
    public void AnnosViskiYksi() {this.startValue += 5;} // annoksia 0,2l viskiä
    public void AnnosViskiKaksi() {this.startValue += 17.5;} // annoksia 0,7l viskiä

    public void reset() {
        startValue = 0.0;
    }

    public double getDouble() {
        return startValue;
    }
}