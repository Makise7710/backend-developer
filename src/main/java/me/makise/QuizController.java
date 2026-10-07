package me.makise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController



    public class QuizController {
    @GetMapping
    public ResponseEntity<String> quiz(@RequestParam("code") int code){
        switch(code){
            case 1:
                return ResponseEntity.created(null).body("Created");
            case 2:
                return ResponseEntity.badRequest().body("Bad Resquest!");
            default:
                return ResponseEntity.ok().body("ok");


        }
    }
    @PostMapping("/quiz")
    public  ResponseEntity<String> quiz2(@RequestBody int code){
        switch (code){
            case 1:
                return ResponseEntity.status(403).body("Forbiden");
            default:
                return ResponseEntity.ok().body("ok");

        }
    }

}

record Code(int value){}