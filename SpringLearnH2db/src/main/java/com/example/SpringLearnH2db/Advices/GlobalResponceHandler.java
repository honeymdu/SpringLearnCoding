// package com.example.SpringLearnH2db.Advices;

// import org.springframework.core.MethodParameter;
// import org.springframework.http.MediaType;
// import org.springframework.http.converter.HttpMessageConverter;
// import org.springframework.http.server.ServerHttpRequest;
// import org.springframework.http.server.ServerHttpResponse;
// import org.springframework.lang.Nullable;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// @RestControllerAdvice
// public class GlobalResponceHandler implements ResponseBodyAdvice<Object> {

//     @SuppressWarnings("null")
//     @Override
//     public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return true;
//     }

//     @SuppressWarnings("null")
//     @Override
//     @Nullable
//     public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType,
//             Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
//             ServerHttpResponse response) {
        
//             if(body instanceof ApiResponce<?>)
//             return body;

//            return new ApiResponce<>(body);
//     }

// }
