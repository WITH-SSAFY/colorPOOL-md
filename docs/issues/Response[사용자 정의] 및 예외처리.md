# Response(사용자 정의) 및 예외처리

### Special thanks to 준호

> 모든 경우에 대해 응답해주고 싶었다.....
>
> by **Beaver Bae** (1995~)

<br>

### 도입 배경

test용 API에서는 성공적인 결과만 리턴 되게 해놨었다. 하지만 실패 할 경우 클라이언트 단에서는 어떤 원인으로 실패하는 지를 모르고, 서버 단에서는 무진장 긴 에러 코드가 나왔다. <br>
그래서 응답 코드에 따라 여러 응답 처리를 해줄 수 있는 ResponseEntity를 사용하려 했으나, 이전 플젝인 colorPOOL에서 준호형이 구현한 사용자 정의 Response가 좀 더 compact해서 clone 했다. <br>

마찬가지로 예외 처리도 colorPOOL의 코드를 clone 하였다.

<br>

### 작업 내용

#### Response(사용자 정의)

- Response에 담는 내용

  - isSuccess: 성공여부
  - code
  - msg : 내용
  - data : 반환 data(단일 or 리스트)

- Response의 Model을 3가지로 분류

  - CommonResult.java (응답 성공, 실패 여부만 리턴)

    ```java
    @Getter
    @Setter
    public class CommonResult {
        @Schema(defaultValue = "응답 성공 여부 : true/false")
        private boolean isSuccess;
    
        @Schema(defaultValue = "응답 코드 번호 : >=0 정상, <0 비정상")
        private int code;
    
        @Schema(defaultValue = "응답 메시지")
        private String msg;
    }
    ```

  - SingleResult (단일건 리턴)

    ```
    @Getter
    @Setter
    public class SingleResult<T> extends CommonResult {
        private T data;
    }
    ```

  - ListResult (다중건 리턴)

    ```java
    @Getter
    @Setter
    public class ListResult<T> extends CommonResult {
        private List<T> data;
    }
    ```

- ResponseService구현

  - 단일 건, 다중 건, 성공, 실패(예외 발생) 처리

  - ResponseService.java

    ```java
    package com.cerberos.colorpool.service;
    
    import com.cerberos.colorpool.model.response.CommonResult;
    import com.cerberos.colorpool.model.response.ListResult;
    import com.cerberos.colorpool.model.response.SingleResult;
    import org.springframework.stereotype.Service;
    
    import java.util.List;
    
    @Service
    public class ResponseService {
    
        //enum으로 api 요청 결과에 대한 code, msg 정의
        public enum CommonResponse {
            SUCCESS(0,"성공하였습니다."),
            FAIL(-1, "실패하였습니다.");
    
            int code;
            String msg;
    
            CommonResponse(int code, String msg){
                this.code = code;
                this.msg = msg;
            }
    
            public int getCode(){return code;}
    
            public String getMsg(){return msg;}
    
        }
    
        //단일건 결과 처리
        public <T> SingleResult<T> getSingleResult(T data){
            SingleResult<T> result = new SingleResult<>();
            result.setData(data);
            setSuccessResult(result);
            return result;
        }
    
        //다중건 결과 처리
        public <T> ListResult<T> getListResult(List<T> list){
            ListResult<T> result = new ListResult<>();
            result.setData(list);
            setSuccessResult(result);
            return result;
        }
    
        //성공 결과만 처리
        public CommonResult getSuccessResult(){
            CommonResult result = new CommonResult();
            setSuccessResult(result);
            return result;
        }
    
        //실패 결과 처리
        public CommonResult getFailResult(int code, String msg){
            CommonResult result = new CommonResult();
            result.setSuccess(false);
            result.setCode(code);
            result.setMsg(msg);
            return result;
        }
    
        //결과 모델에 api 요청 성공 데이터 세팅
        private void setSuccessResult(CommonResult result){
            result.setSuccess(true);
            result.setCode(CommonResponse.SUCCESS.getCode());
            result.setMsg(CommonResponse.SUCCESS.getMsg());
        }
    }
    ```

<br>

#### 예외처리

- ExceptionHandler, MessageSource 활용

- exception.yml (가능한 예외에 대한 code와 message 내용)

  ```yaml
  unKnown:
    code: "-9999"
    msg: "알수 없는 오류가 발생하였습니다."
  themeNotFoundException:
    code: "-1000"
    msg: "해당 컬러의 배색 조합을 추천할 수 없습니다."
  pdfNotCreateException:
    code: "-2000"
    msg: "pdf 생성 중 오류가 발생하였습니다."
  imageNotUploadException:
    code: "-3000"
    msg: "image 업로드 생성 중 오류가 발생하였습니다."
  contentsNotFoundException:
    code: "-2001"
    msg: "해당 contents가 존재하지 않습니다."
  ```

- MessageConfiguration.java (ExceptionHandler의 특정 exception에 맞는 exception.yml의 내용을 담아줌)

  ```java
  @Configuration
  public class MessageConfiguration implements WebMvcConfigurer {
      @Bean // 세션에 지역설정. default는 KOREAN = 'ko'
      public LocaleResolver localeResolver() {
          SessionLocaleResolver slr = new SessionLocaleResolver();
          slr.setDefaultLocale(Locale.KOREAN);
          return slr;
      }
  
      @Bean // 지역설정을 변경하는 인터셉터. 요청시 파라미터에 lang 정보를 지정하면 언어가 변경됨.
      public LocaleChangeInterceptor localeChangeInterceptor() {
          LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
          lci.setParamName("lang");
          return lci;
      }
  
      @Override // 인터셉터를 시스템 레지스트리에 등록
      public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(localeChangeInterceptor());
      }
  
      @Bean // yml 파일을 참조하는 MessageSource 선언
      public MessageSource messageSource(
              @Value("${spring.messages.basename}") String basename,
              @Value("${spring.messages.encoding}") String encoding
      ) {
          YamlMessageSource ms = new YamlMessageSource();
          ms.setBasename(basename);
          ms.setDefaultEncoding(encoding);
          ms.setAlwaysUseMessageFormat(true);
          ms.setUseCodeAsDefaultMessage(true);
          ms.setFallbackToSystemLocale(true);
          return ms;
      }
  
      // locale 정보에 따라 다른 yml 파일을 읽도록 처리
      private static class YamlMessageSource extends ResourceBundleMessageSource {
          @Override
          protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
              return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
          }
      }
  }
  ```

- ExceptionAdvice.java (각 예외에 대한 실질적인 처리 수행)

  ```java
  @RequiredArgsConstructor
  @RestControllerAdvice
  public class ExceptionAdvice {
      private final ResponseService responseService;
      private final MessageSource messageSource;
  
      @ExceptionHandler(Exception.class)
      @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
      protected CommonResult defaultException(HttpServletRequest request, Exception e){
          return responseService.getFailResult(Integer.parseInt(getMessage("unknown.code")),getMessage("unknown.msg"));
      }
  
      @ExceptionHandler(CThemeNotFoundException.class)
      @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
      protected CommonResult themeNotFoundException(HttpServletRequest request, CThemeNotFoundException e){
          return responseService.getFailResult(Integer.parseInt(getMessage("themeNotFoundException.code")),getMessage("themeNotFoundException.msg"));
      }
  
      @ExceptionHandler(CPdfNotCreateException.class)
      @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
      protected CommonResult pdfNotCreateException(HttpServletRequest request, CPdfNotCreateException e){
          return responseService.getFailResult(Integer.parseInt(getMessage("pdfNotCreateException.code")),getMessage("pdfNotCreateException.msg"));
      }
  
      @ExceptionHandler(CImageNotUploadException.class)
      @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
      protected CommonResult imageNotUploadException(HttpServletRequest request, CImageNotUploadException e){
          return responseService.getFailResult(Integer.parseInt(getMessage("imageNotUploadException.code")),getMessage("imageNotUploadException.msg"));
      }
  
      @ExceptionHandler(CContentsNotFoundException.class)
      @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
      protected CommonResult contentsNotFoundException(HttpServletRequest request, CContentsNotFoundException e){
          return responseService.getFailResult(Integer.parseInt(getMessage("contentsNotFoundException.code")),getMessage("contentsNotFoundException.msg"));
      }
  
  
      // code정보에 해당하는 메시지를 조회
      private String getMessage(String code) {
          return getMessage(code, null);
      }
  
      // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회
      private String getMessage(String code, Object[] args) {
          return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
      }
  }
  ```

- exception

  - CContentsNotFoundException.java (pdf 내용 조회 실패)

    ```java
    public class CContentsNotFoundException extends RuntimeException {
        public CContentsNotFoundException(){super();}
    }
    ```

  - CImageNotUploadException.java (이미지 업로드 실패)

    ```java
    public class CImageNotUploadException extends RuntimeException{
        public CImageNotUploadException(){ super();}
    }
    ```

  - CPdfNotCreateException.java (pdf 업로드 실패)

    ```java
    public class CPdfNotCreateException extends RuntimeException {
        public CPdfNotCreateException(){super();}
    }
    ```

  - CThemeNotFoundException.java  (배색 조합 추천 불가)

    ```java
    public class CThemeNotFoundException extends RuntimeException{
        public CThemeNotFoundException() {
            super();
        }
    }
    ```

    