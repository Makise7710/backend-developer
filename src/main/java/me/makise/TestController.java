package me.makise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestController {
    @GetMapping("/test")
    public String test () {
        return "안념?'http://localhost:8080/hi' 에 대한 응덥입니다.";
    }
        @GetMapping("/hi")
        public String hi (){
            return "안념?'http://localhost:8080/hi' 에 대한 응덥입니다.";
    }
}
