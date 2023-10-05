package com.ex.business.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class FileController {
    @GetMapping("/upload")
    public String uploadPage(){
        return "upload";
    }
}
