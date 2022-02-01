package hello.servlet.web.forntcontroller.v3;

import hello.servlet.web.forntcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
