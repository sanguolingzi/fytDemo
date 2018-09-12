package fyt.business.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import fyt.business.core.annotation.JSON;
import fyt.business.core.annotation.ResultBeanResponseBody;
import fyt.business.core.json.CustomerJsonSerializer;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.*;
import java.util.Arrays;

/**
 * 自定义一个ReturnValueHandler 用于返回只需要返回的字段 减少接口返回的无关内容以及 私密内容
 * 在需要使用该handler的controller上加注解 @ResultBeanResponseBody 即可被拦截
 */
public class BusinessReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        System.out.println("----------------");
        if(methodParameter.hasMethodAnnotation(ResultBeanResponseBody.class))
            return true;

        return false;
    }

    @Override
    public void handleReturnValue(
            Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest
    ) throws Exception {

        // 设置这个就是最终的处理类了，处理完不再去找下一个类进行处理
        mavContainer.setRequestHandled(true);

        // 获得注解并执行filter方法 最后返回
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Annotation[] annos = returnType.getMethodAnnotations();
        if(returnType.hasMethodAnnotation(JSON.class)){
            CustomerJsonSerializer jsonSerializer = new CustomerJsonSerializer();
            Arrays.asList(annos).forEach(a -> {
                if (a instanceof JSON) {
                    JSON json = (JSON) a;
                    jsonSerializer.filter(json.type(), json.include(), json.filter());
                }
            });


            String json = jsonSerializer.toJson(returnValue);
            response.getWriter().write(json);
        }else{

            ObjectMapper mapper = new ObjectMapper();
            String json =  mapper.writeValueAsString(returnValue);
            response.getWriter().write(json);
            //delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        }

        //response.getWriter().write(json);
    }
}
