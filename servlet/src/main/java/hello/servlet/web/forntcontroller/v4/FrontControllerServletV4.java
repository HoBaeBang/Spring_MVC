package hello.servlet.web.forntcontroller.v4;

import hello.servlet.web.forntcontroller.ModelView;
import hello.servlet.web.forntcontroller.MyView;
import hello.servlet.web.forntcontroller.v3.ControllerV3;
import hello.servlet.web.forntcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.forntcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.forntcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.forntcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.forntcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.forntcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV4",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {

        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        Map<String, String> paramMap = createParamMap(request);     //파라미터를 담는 맵선언
        Map<String, Object> model = new HashMap<>();                //모델을 추가
        String viewName = controller.process(paramMap, model);


        MyView view = viewResolver(viewName);
        view.render(model,request,response);
    }


    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }


    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        /**
         * 클래스 따라가면서 이해하면 편함
         * 리퀘스트 -> 파라미터 네임스가 이너미네이터로 반환하는것을 -> 이터레이터로 바꾸고 -> 계속 액션을돌림 ->저장장         */

        return paramMap;
    }
}
