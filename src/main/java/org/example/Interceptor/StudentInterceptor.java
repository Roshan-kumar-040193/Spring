//package org.example.Interceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.example.DTO.StudentRequest;
//import org.jspecify.annotations.NonNull;
//import org.jspecify.annotations.Nullable;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.time.LocalDateTime;
//
//@Component
//public class StudentInterceptor implements HandlerInterceptor {
//    static LocalDateTime time;
//
//    StudentRequest stuRequest;
//    public StudentInterceptor(StudentRequest request){
//        this.stuRequest=request;
//    }
//
//    @Override
//    public boolean preHandle(@NonNull HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
////        HandlerMethod handlerMethod = (HandlerMethod) handler;
////        System.out.println(handlerMethod.getBeanType().getName()+" method :  "+handlerMethod.getMethod().getName());
//        System.out.println(response.getStatus());
//        System.out.println("hi inside Interceptor "+stuRequest.getName());
//        time=LocalDateTime.now();
//        return true;
//    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        System.out.println(response.getStatus());
//        System.out.println("bye inside Interceptor " +((HandlerMethod) handler).getShortLogMessage());
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        System.out.println(response.getStatus());
//        System.out.println("why inside Interceptor "+java.time.Duration.between(time, LocalDateTime.now()).toMillis());
//    }
//}
