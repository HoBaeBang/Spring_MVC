package hello.servlet.web.forntcontroller.v3;

import hello.servlet.web.forntcontroller.ModelView;
import hello.servlet.web.forntcontroller.MyView;
import hello.servlet.web.forntcontroller.v2.ControllerV2;
import hello.servlet.web.forntcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.forntcontroller.v2.controller.MemberListContorllerV2;
import hello.servlet.web.forntcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.forntcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.forntcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.forntcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV3",urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {

        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();

        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request,response);
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
