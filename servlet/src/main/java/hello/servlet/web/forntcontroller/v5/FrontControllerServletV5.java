package hello.servlet.web.forntcontroller.v5;

import hello.servlet.web.forntcontroller.ModelView;
import hello.servlet.web.forntcontroller.MyView;
import hello.servlet.web.forntcontroller.v3.ControllerV3;
import hello.servlet.web.forntcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.forntcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.forntcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.forntcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();

        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);       //핸들러 정보 조회
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        MyHandlerAdapter adapter = getHandlerAdapter(handler);  //핸들러 어댑터 조회

        ModelView mv = adapter.handle(request, response, handler);  //핸들러 어댑터를 통한 핸들러 처리

        String viewName = mv.getViewName();     //뷰네임
        MyView view = viewResolver(viewName);   //viewResolver를 통한 처리

        view.render(mv.getModel(),request,response);


    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.support(handler)) {
                 return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter을 찾을 수 없습니다. handler = " + handler);
    }


    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
