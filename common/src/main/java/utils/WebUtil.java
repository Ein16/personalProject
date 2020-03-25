package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebUtil {
    /**
     * 判断是否是Ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }


    /**
     * 以JSON格式输出
     * @param response
     */
    public static void responseOutWithJson(HttpServletResponse response,Object responseObject) throws IOException {
        //将实体对象转换为JSON Object转换
        String json = JacksonUtil.BeanToJson(responseObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


}
