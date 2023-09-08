package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServiceV1", urlPatterns = "/front-controller/v1/*")  //
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //urlPatterns = "/front-controller/v1/*" v1 아래로 호출이 되면 먼저 아래가 출력된다.
        System.out.println("FrontControllerServletV1.service");

        // /front-controller/v1/members 가 들어오면 생성자로 생성된 객체인스턴스 new MemberListControllerV1()가 반환된다.
        String requestURI = request.getRequestURI();

        //다형성에 의해서  new MemberListControllerV1()의 부모가 ControllerV1이기 때문에 아래에서 받을수 있다.
        ControllerV1 controllerV1 = controllerV1Map.get(requestURI);
        if (controllerV1 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controllerV1.process(request, response);
    }
}
