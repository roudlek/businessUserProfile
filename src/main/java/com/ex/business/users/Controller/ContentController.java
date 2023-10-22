package com.ex.business.users.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

//        public static void main(String[] args) {
//            int[] numbers = {5,3,5,3,3,3,3,3,3,3};
//            System.out.println(getMajorityNumber(numbers));
//            //System.out.println("no majority number found");
//        }
        public static int getMajorityNumber(int[] listOfNumbers){
            int count = 1;
            for(int indexL1 = 0 ; indexL1 < listOfNumbers.length - 1; indexL1 ++){
                count = 1;
                for(int indexL2 = indexL1 + 1 ; indexL2 < listOfNumbers.length - 1; indexL2 ++){
                    if(listOfNumbers[indexL1] == listOfNumbers[indexL2]){
                        count++;
                        System.out.println("+1");
                        if(count > listOfNumbers.length/2){
                            return count;
                        }
                    }
                }
            }
            return 0;
        }

}
