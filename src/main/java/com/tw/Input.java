package com.tw;

import java.util.Scanner;

public class Input {
    private Scanner scanner = new Scanner(System.in);

    public String readInput(){
        String str = scanner.next();
        return str;
    }
}
