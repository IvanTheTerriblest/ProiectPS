package com.projectPS.testing;
import java.time.LocalDate;

public class Cezar {

    LocalDate currentdate = LocalDate.now();
    int currentDay = currentdate.getDayOfMonth();




    public String cryptPassoword(PassType type,String password){
        String newPassword="";
        char character;
        int ascii;
        for (int i=0; i<password.length();i++){
            character = password.charAt(i);
            if(type.equals(PassType.WEAK)) {
                ascii = (int) character + 1;
                if(ascii > 122){
                    ascii-=26;
                }
            } else if (type.equals(PassType.COMPLEX)) {
                ascii = (int)character +currentDay;
                if(ascii > 122){
                    ascii-=26;
                }
            }
            else {
                ascii = (int) character + 4;
                if(ascii > 122){
                    ascii-=26;
                }
            }

            character = (char)ascii;
            newPassword +=character;
        }
        return newPassword;
    }




}
