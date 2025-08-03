package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 1 - 정적 컨텐츠
    // hello-static.html

    // 2 - thymeleaf 템플릿 엔진 사용 & RequestParam 사용
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // 3 - ResponseBody (String)
    @GetMapping("hello-string")
    @ResponseBody // http에서 Body 부분에 이 데이터를 내가 직접 넣어주겠다! (뷰랑 차이점은 이 데이터가 뷰 없이 그대로 내려간다는 것)
    public String helloString(@RequestParam("name") String name) {
        return "hello, " + name + "!";
    }

    // 4 - ResponseBody (JSON)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    public static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}