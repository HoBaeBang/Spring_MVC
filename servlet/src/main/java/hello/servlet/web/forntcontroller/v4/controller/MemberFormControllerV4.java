package hello.servlet.web.forntcontroller.v4.controller;

import hello.servlet.web.forntcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMAp, Map<String, Object> model) {
        return "new-form";
    }
}
