//package com.project.task.util;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class SumAllEvenNumber {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
//
//        scanner.close();
//        String[] numbersArray = input.split("\\s+");
//
//        List<Integer> numbers = new ArrayList<>();
//
//        for (String number : numbersArray) {
//            numbers.add(Integer.parseInt(number));
//        }
//
//        int sum = sumOfEvenNumbers(numbers);
//
//        System.out.println("Sum of even numbers: " + sum);
//    }
//
//    public static int sumOfEvenNumbers(List<Integer> numbers) {
//        int totalSum = 0;
//        for (int num : numbers) {
//            // Check if the number is even
//            if (num % 2 == 0) {
//                // Add the even number to the total sum
//                totalSum += num;
//            }
//        }
//        // Return the sum of even numbers
//        return totalSum;
//    }
//}
//
//
