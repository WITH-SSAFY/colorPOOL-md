# 민감 정보 처리

### Special thanks to 준호

> DB 비밀번호나 AWS 키와 같은 민감 정보는 git에 노출되면 안됩니다
>
> *by coach **K.H.Kim** (199?~)*

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

- application-dev.yml

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

  

