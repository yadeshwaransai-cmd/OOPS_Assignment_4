package com.socketcalculator;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CalculatorClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to Server.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter expressions like: 10 + 5");
            System.out.println("Type 'exit' to quit.");

            while (true) {
                System.out.print("Enter expression: ");
                String expression = scanner.nextLine();
                output.println(expression);

                if (expression.equalsIgnoreCase("exit")) {
                    break;
                }

                String response = input.readLine();
                System.out.println("Server: " + response);
            }

            scanner.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
