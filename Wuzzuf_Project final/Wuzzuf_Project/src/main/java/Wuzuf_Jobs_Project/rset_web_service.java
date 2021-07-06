package Wuzuf_Jobs_Project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class rset_web_service {
    @GetMapping("wuzzuf")
    public String getHelloWorld(){
        return "showing data of wuzzuf jobs .csv";
    }
}
