# 민감 정보 처리

### Special thanks to 준호

> DB 비밀번호와 같은 민감 정보는 git에 노출되면 안됩니다
>
> *by coach **K.H.Kim** (199?~)*

<br>

### 도입 계기

application.yml 파일을 통해 스프링부트가 어플리케이션을 구동시 다양한 환경 설정을 할 수 있다.<br> 이를 통해 DB, AWS와 같은 외부 리소스에 접근도 가능하다. 당연하게도 접근하려면 application.yml에 해당 리소스의 민감 정보(ex) 비밀번호)를 명시해 주어야한다. <br>

하지만 비밀번호를 쓰고 git에 push하는 순간 외부에서 commit 기록을 통해 외부 리소스의 모든 정보를 알게 되어 악용(ex)DB를 턴다거나...)할 수 있다. <br>

colorPOOL과 LOCKER에서 준호(a.k.a 룬로) 형이 이를 처리한 방식을 clone하였다. (He is coding genius....)

<br>

### 처리 방법

- application.yml

  ```yaml
  spring:
    profiles:
      active: dev #application-dev.yml과 연결
    messages:
      basename: i18n/exception
      encoding: UTF-8
    servlet:
      multipart:
        max-file-size: 10MB
        max-request-size: 10MB
  
  springdoc:
    version : 1.0.1
    api-docs:
      path: /api-docs
    default-consumes-media-type: application/json
    default-produces-media-type: application/json
    swagger-ui:
      operations-sorter: method
      tags-sorter: alpha
      disable-swagger-default-url: true
      display-query-params-without-oauth2: true
      path: /docs/swagger-ui.html
    paths-to-match:
      - /api/**
  ```

- application-dev.yml(드러나면 안되는 민감 정보 처리)

  ```yaml
  server:
    port: #포트번호
  
  cloud:
    aws:
      credentials:
        accessKey: #키
        secretKey: #키
      s3:
        bucket: #버킷 이름
      region:
        static: #지역
      stack:
        auto: false
  
  spring:
    profiles: dev
    datasource:
      url: #url 입력
      username: #user이름
      password: #비밀번호
      driver-class-name: com.mysql.cj.jdbc.Driver
    jpa:
      hibernate:
        ddl-auto: updat
  ```

  

### 추가

또한 이 방식은 application-dev.yml(배포 서버 환경)뿐만 아니라 application-local.yml(로컬 작업 환경)로 나눠서 application.yml에서 원하는 profile을 active 하면서 작업할 수 있다.