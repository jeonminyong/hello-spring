package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

        @GetMapping("hello")
        public String hello(Model model){
            model.addAttribute("data","hello!!");
            //key 는 data 고 값은 hello
            return "hello";
            //return 의 이름이 resources 밑에 templates 에 hello.html 로 넘겨라
        }


        @GetMapping("hello-mvc")
        public String hellomvc(@RequestParam(value = "name",required = false) String name, Model model){
            //required 디폴트가 true 꼭 넘어가야하고 false면 꼭 안넘어 가도 괜찮다.
            //@RequestParam 파라메터를 넘기기 위해 사용한다.
            model.addAttribute("name",name);
            return "hello-template";
        }


        @GetMapping("hello-string")
        @ResponseBody //HTTP 응답 바디에 바로 아래의 데이터를 넣어주겠다는 의미이다.
        public String helloString(@RequestParam("name") String name){
            return "hello "+name;  //hello spring
        }
        //이건 그냥 문자열이 화면 그대로 나오게 된다.


        @GetMapping("hello-api")
        @ResponseBody
        public Hello helloApi(@RequestParam("name") String name){
            Hello hello = new Hello();
            hello.setName(name);
            return hello;
        }
        // 기본은 json으로 반환


        static class Hello{
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }





}
