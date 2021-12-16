package com.company;
import java.lang.String;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan            = new Scanner(System.in);

        String stringToCheck    = scan.nextLine();

        char[] arrayChars       = stringToCheck.toCharArray();

        int stringLength        = arrayChars.length;

        String brackets         = "";
        String result           = "";

        int lastIndex           = 0;
        boolean stringIsCorrect = true;

        int numberOfRepeat          = -1;
        String strToMultiply        = "";
        String strTemporary         = "";
        String typeOfStr            = "";
        String nextTypeOfStr        = "";
        String previousTypeOfStr    = "";

        for (int i = 0; i < stringLength; i++) {

            typeOfStr       = checkOfType(arrayChars[i]);
            strTemporary    = "";

            if (typeOfStr == "isInt" || typeOfStr == "isChar") {

                StringBuilder temporarySB = new StringBuilder(strTemporary);
                temporarySB.insert(strTemporary.length(), arrayChars[i]);
                strTemporary = temporarySB.toString();

                int y = i;
                y++;

                while (y < stringLength) {

                    nextTypeOfStr = checkOfType(arrayChars[y]);

                    if (typeOfStr == "isInt" && nextTypeOfStr == "isInt") {

                        temporarySB = new StringBuilder(strTemporary);
                        temporarySB.insert(strTemporary.length(), arrayChars[y]);
                        strTemporary = temporarySB.toString();

                    } else if (typeOfStr == "isChar" && nextTypeOfStr == "isChar") {

                        temporarySB = new StringBuilder(strTemporary);
                        temporarySB.insert(strTemporary.length(), arrayChars[y]);
                        strTemporary = temporarySB.toString();

                    } else if (nextTypeOfStr == "wrongType") {

                        stringIsCorrect = false;
                        break;

                    } else if (typeOfStr == "isInt" && nextTypeOfStr == "isChar") {

                        stringIsCorrect = false;
                        break;

                    } else {

                        i = --y;
                        break;
                    }

                    y++;
                }

                if (typeOfStr == "isInt") {

                    numberOfRepeat = Integer.parseInt(strTemporary);

                } else if (typeOfStr == "isChar") {

                    strToMultiply = strTemporary;
                }

            } else if (typeOfStr == "isBracketLeft") {

                if (previousTypeOfStr != "isInt") {

                    stringIsCorrect = false;
                    break;

                } else {

                    brackets += "[";
                }

            } else if (typeOfStr == "isBracketRight") {

                if (previousTypeOfStr != "isChar") {

                    stringIsCorrect = false;
                    break;

                } else {

                    lastIndex = brackets.length() - 1;

                    if (lastIndex < 0) {
                        stringIsCorrect = false;
                        break;
                    } else {
                        brackets = brackets.substring(0, lastIndex);
                    }
                }

            } else {

                stringIsCorrect = false;
            }

            if(strToMultiply.length() > 0) {

                strTemporary = "";

                if(numberOfRepeat < 0)
                    numberOfRepeat = 1;

                for (int y = 0; y < numberOfRepeat; y++) {
                    strTemporary = strTemporary + strToMultiply;
                }

                result          = result + strTemporary;

                numberOfRepeat  = -1;
                strToMultiply   = "";
            }

            previousTypeOfStr = typeOfStr;
        }

        if (stringIsCorrect == true && brackets.length() > 0)
            stringIsCorrect = false;

        if (stringIsCorrect == true)
            System.out.println("result: " + result);
        else
            System.out.println("string is not valid ");
    }

public static String checkOfType(char charToCheck) {

        String typeOfStr = "";

        if (Character.isDigit(charToCheck))
        {
            typeOfStr = "isInt";
        }
        else if ((charToCheck >= 'a') && (charToCheck <= 'z') || (charToCheck >= 'A')&&(charToCheck <= 'Z'))
        {
            typeOfStr = "isChar";
        }
        else if (charToCheck == '[')
        {
            typeOfStr = "isBracketLeft";
        }
        else if (charToCheck == ']')
        {
            typeOfStr = "isBracketRight";
        }
        else
        {
            typeOfStr = "wrongType";
        }

        return typeOfStr;
    }
}