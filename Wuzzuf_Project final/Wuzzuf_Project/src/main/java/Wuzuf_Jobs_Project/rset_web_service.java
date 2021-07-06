package Wuzuf_Jobs_Project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class rset_web_service {
    @GetMapping("hello2020")
    public String getHelloWorld(){
        return "Hello monica!";
    }
}
