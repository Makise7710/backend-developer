package me.makise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args){
        List<Integer> nums = List.of(1,2,3,4,5,6,7,8,9,10);
        List<Integer> result = new ArrayList<>();
        for (Integer n : nums) {
            if(n % 2 == 0){
                result.add(n*2);
            }
        }

    }
}