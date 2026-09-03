package me.makise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    public String test() {
        return "안념?'http://localhost:8080/hi' 에 대한 응덥입니다.";
    }


    @PostMapping("/test")
    public String postTest() {
        return "안념 /test Post 요청에 대한 응답입니다.";
    }
    @PutMapping("/test")
    public String putTest() {
        return "안념 /test Post 요청에 대한 응답입니다.";
    }
    @PatchMapping("/test")
    public String patchTest() {
        return "안념 /test Post 요청에 대한 응답입니다.";
    }
    @GetMapping("/test")
    public String getTest() {
        return "안념 /test Post 요청에 대한 응답입니다.";
    }
    @DeleteMapping("/test")
    public String deleteTest() {
        return "안념 /test Post 요청에 대한 응답입니다.";
    }
}