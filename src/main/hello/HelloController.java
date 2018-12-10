package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        getAllRequestMappingInfo();
        return "Hello For Spring Boot1";
    }

    @Autowired
    ApplicationContext applicationContext;
//    @RequestMapping("/getallurl")
    public void getAllRequestMappingInfo() {
        AbstractHandlerMethodMapping<RequestMappingInfo> objHandlerMethodMapping = (AbstractHandlerMethodMapping<RequestMappingInfo>) applicationContext.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> mapRet = objHandlerMethodMapping.getHandlerMethods();
        Iterator<Map.Entry<RequestMappingInfo, HandlerMethod>> iterator = mapRet.entrySet().iterator();
        ArrayList<ArrayList<String>> alldata = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<RequestMappingInfo, HandlerMethod> entry = iterator.next();
            ArrayList<String> arrayList = new ArrayList<>();
            System.out.println(entry.getKey().getPatternsCondition());
            arrayList.add(entry.getKey().getPatternsCondition().toString());
//            System.out.println(entry.getKey().getMethodsCondition());
            if (entry.getKey().getMethodsCondition().isEmpty()) {
                System.out.println("GET");
                arrayList.add("GET");
            }
            else {
                System.out.println(entry.getKey().getMethodsCondition());
                arrayList.add(entry.getKey().getMethodsCondition().toString());
            }
            alldata.add(arrayList);
//            System.out.println(entry.getValue().getMethod());
        }


//        for (Iterator<RequestMappingInfo> iterator = mapRet.keySet().iterator(); iterator.hasNext(); ) {
//            RequestMappingInfo info = iterator.next();
//            System.out.print(info.getPatternsCondition());
//        }
//        return mapRet;
    }
}
