package com.ex.business.users.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String handleError() {
        // You can handle error scenarios here
        return "error"; // Return the name of your error template
    }
}
