package controller;

import java.util.Scanner;

public class Valid {
    public static boolean isValidDamage(int damage){
        if(damage >= 0 && damage <= 100){
            return true;
        }
        return false;
    }
    public static int checkOption(int min_v,int max_v){
        int value = 0;
        while (true){
            try{
                value =Integer.valueOf(new Scanner(System.in).nextLine());
                if(value < min_v || value > max_v){
                    throw new NumberFormatException("Must enter option is valid. ");
                }
                break;
            }catch (NumberFormatException e){
                System.out.println(e);
            }
        }

        return value;
    }
    public static boolean checkAnswer(String text){
        if(text.trim().equalsIgnoreCase("Y")){
            return true;
        }else{
            return false;
        }
    }
}
