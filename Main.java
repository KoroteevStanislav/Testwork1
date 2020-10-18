package com.company;


import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] roman = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
        Scanner scanner = new Scanner(System.in);
        String[] blocks = scanner.nextLine().split(" ");
        char oper2 = blocks[1].charAt(0);
        int num1 = 0, num2 = 0;
        boolean flag = false;
        for (int i = 0; i < roman.length; i++) {
            if (roman[i].equals(blocks[0]) || roman[i].equals(blocks[1])) {
                flag = true;
            }
        }
        try{
            if (flag) {
                num1 = romanToArabic(blocks[0]);
                num2 = romanToArabic(blocks[2]);
                if ((num1 > 10 || num1 < 1) || (num2 > 10 || num2 < 1)) {
                    throw new IllegalArgumentException();
                }
                System.out.println(arabToRoman(calc(num1, num2, oper2)));
            }
            else {
                num1 = Integer.parseInt(blocks[0]);
                num2 = Integer.parseInt(blocks[2]);
                if ((num1 > 10 || num1 < 1) || (num2 > 10 || num2 < 1)) {
                    throw new IllegalArgumentException();}
                System.out.println(calc(num1, num2, oper2));
            }
            if ((num1 > 10 || num1 < 0) || (num2 > 10 || num2 < 0)) {
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException e) {
            if ((num1 > 10 || num1 < 1) || (num2 > 10 || num2 < 1)) {
                throw new IllegalArgumentException();
            }
        }
    }
    private static String arabToRoman(int num) {
        String m[] = {"", "M", "MM", "MMM"};
        String c[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String x[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String i[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String thousands = m[num / 1000];
        String hundereds = c[(num % 1000) / 100];
        String tens = x[(num % 100) / 10];
        String ones = i[num % 10];
        return thousands + hundereds + tens + ones;
    }

    private static int value(char r) {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }
    private static int romanToArabic(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            int s1 = value(str.charAt(i));
            if (i + 1 < str.length()) {
                int s2 = value(str.charAt(i + 1));
                if (s1 >= s2) {
                    res = res + s1;
                } else {
                    res = res + s2 - s1;
                    i++;
                }
            } else {
                res = res + s1;
                i++;
            }
        }
        return res;
    }

    private static int calc(int num1, int num2, char oper2) {
        int result;
        switch (oper2) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/': result = num1 / num2; break;
            default:
                result = calc(num1, num2, oper2);
        }
        return result;
    }
}