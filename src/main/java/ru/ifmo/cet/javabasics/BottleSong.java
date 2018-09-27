package ru.ifmo.cet.javabasics;

/**
 * Нужно реализовать констурктор и метод, возвращающий слова песни про бутылки на стене.
 * <p>
 * Слова следующие:
 * <p>
 * 99 bottles of beer on the wall, 99 bottles of beer
 * Take one down, pass it around, 98 bottles of beer
 * 98 bottles of beer on the wall, 98 bottles of beer
 * Take one down, pass it around, 97 bottles of beer
 * 97 bottles of beer on the wall, 97 bottles of beer
 * Take one down, pass it around, 96 bottles of beer
 * 96 bottles of beer on the wall, 96 bottles of beer
 * Take one down, pass it around, 95 bottles of beer
 * 95 bottles of beer on the wall, 95 bottles of beer
 * ...
 * <p>
 * 3 bottles of beer on the wall, 3 bottles of beer
 * Take one down, pass it around, 2 bottles of beer
 * 2 bottles of beer on the wall, 2 bottles of beer
 * Take one down, pass it around, 1 bottles of beer
 * 1 bottle of beer on the wall, 1 bottle of beer
 * Take one down, pass it around, no more bottles of beer on the wall
 * No more bottles of beer on the wall, no more bottles of beer
 * Go to the store and buy some more, 99 bottles of beer on the wall
 * <p>
 * Дело усложняется тем, что текст песни переменный:
 * За раз может быть взято несколько бутылок.
 * Значение передается в качестве параметра конструктора
 * Нужно ограничить возможность взятия бутылок натуральным число не более 99 бутылок за раз.
 */
public class BottleSong {

    private final int bottles;

    public BottleSong(int bottleTakenAtOnce) {
        if(bottleTakenAtOnce > 99 || bottleTakenAtOnce < 1) throw new IllegalArgumentException();
        this.bottles = bottleTakenAtOnce;
    }

    private String numberToString(int num){
        String[] upToNineteenArray = new String[20];
        upToNineteenArray[0] = "";
        upToNineteenArray[1] = "one";
        upToNineteenArray[2] = "two";
        upToNineteenArray[3] = "three";
        upToNineteenArray[4] = "four";
        upToNineteenArray[5] = "five";
        upToNineteenArray[6] = "six";
        upToNineteenArray[7] = "seven";
        upToNineteenArray[8] = "eight";
        upToNineteenArray[9] = "nine";
        upToNineteenArray[10] = "ten";
        upToNineteenArray[11] = "eleven";
        upToNineteenArray[12] = "twelve";
        upToNineteenArray[13] = "thirteen";
        upToNineteenArray[14] = "fourteen";
        upToNineteenArray[15] = "fifteen";
        upToNineteenArray[16] = "sixteen";
        upToNineteenArray[17] = "seventeen";
        upToNineteenArray[18] = "eighteen";
        upToNineteenArray[19] = "nineteen";

        String[] tensArray = new String[8];
        tensArray[0] = "twenty";
        tensArray[1] = "thirty";
        tensArray[2] = "forty";
        tensArray[3] = "fifty";
        tensArray[4] = "sixty";
        tensArray[5] = "seventy";
        tensArray[6] = "eighty";
        tensArray[7] = "ninety";

        String result = "";
        int tens = num / 10;
        int ones = num % 10;

        if(num < 20){
            result = upToNineteenArray[num];
        }
        else if(num >= 20 && ones == 0){
            result = tensArray[tens - 2];
        }
        else{
            result = tensArray[tens - 2] + " " + upToNineteenArray[ones];
        }

        return result;
    }

    public String getBottleSongLyrics() {
        int curBottles = 99;
        String song = "";
        while(curBottles > 0){
            if(curBottles == 1){
                song += curBottles + " bottle of beer on the wall, "
                        + curBottles + " bottle of beer.\n" + "Take ";
            }
            else {
                song += curBottles + " bottles of beer on the wall, "
                        + curBottles + " bottles of beer.\n" + "Take ";
            }
            if(curBottles >= bottles) {
                curBottles -= bottles;
                song += numberToString(bottles);
            }
            else {
                song += numberToString(curBottles);
                curBottles = 0;
            }
            song += " down and pass around, ";
            if(curBottles == 0){
                song += "no more bottles";
            }
            else if(curBottles == 1){
                song += curBottles + " bottle";
            }
            else{
                song += curBottles + " bottles";
            }
            song += " of beer on the wall.\n";
        }
        song += "No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n";
        return song;
    }
}
