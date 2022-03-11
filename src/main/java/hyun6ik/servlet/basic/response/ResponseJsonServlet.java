package hyun6ik.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyun6ik.servlet.basic.HelloData;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
@RequiredArgsConstructor
public class ResponseJsonServlet extends HttpServlet {

    private final ObjectMapper objectMapper;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        final HelloData helloData = HelloData.of("hyun6ik", 20);

        //{"username":"hyun6ik", "age":20}
        // objectMapper.writeValueAsString() : 객체를 JSON 문자로 변경하기
        final String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
