# LoggerFactory를 활용한 log설정

> 스케줄러까지 구현 했는데 log가 없네요?
>
> *by Consultant **K.M.Park** (1976~)*



### LoggerFactory를 활용한 log설정

스케줄러를 구현했는데 컨트님께서 log가 없다는 것을 아쉬워 하셨다. 생각해 보니 맞는 말씀이었다. <br>주기적으로 어떤 작업이 실행 되었는지 확인하기 위해서는 log를 작성하는 것이 바람직하다. <br>구글 신의 도움을 받아 LoggerFactory를 통해 log를 간단하게 작성해 볼 수 있다는 것을 알게 되었다.



### 구현 내용

- [참고링크](https://goddaehee.tistory.com/206)

- 좀 더 편리한 디버깅을 위해 log파일을 아래 처럼 3가지 파일로 나눴다.
  - colorPOOLmd_stdout_log 파일 : 서버를 언제 시작했는지
  - colorPOOLmd_pdf_log 파일 : pdf 생성 성공 또는 실패 시, Scheduler를 통해 pdf 삭제 시
  - colorPOOLmd_image_log 파일 : S3에 image파일 저장 성공 또는 실패 시 

- logback-spring.xml → STDOUT_FILE(서버 실행), PDF_FILE(pdf), IMAGE_FILE(image) 로 나눠서 log 파일 저장

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <configuration scan="true" scanPeriod="60 seconds">
      <springProfile name="dev">
          <property resource="application-dev.yml"/>
      </springProfile>
  
      <property name="LOG_PATH" value="./logs"/>
      <property name="STDOUT_LOG_FILE_NAME" value="colorPOOLmd_stdout_log"/>
      <property name="PDF_LOG_FILE_NAME" value="colorPOOLmd_pdf_log"/>
      <property name="IMAGE_LOG_FILE_NAME" value="colorPOOLmd_image_log"/>
      <property name="LOG_PATTERN" value="%-5level %d{yy-MM-dd HH:mm:ss}[%thread] [%logger{0}:%line] - %msg%n"/>
  
  <!-- 먼저 console로 되는지 테스트를 해봤습니다.>
  <!-- CONSOLE -->
  <!--    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">-->
  <!--        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">-->
  <!--            <pattern>${LOG_PATTERN}</pattern>-->
  <!--        </encoder>-->
  <!--    </appender>-->
      <appender name="STDOUT_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
          <file>${LOG_PATH}/${STDOUT_LOG_FILE_NAME}.log</file>
          <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
              <pattern>${LOG_PATTERN}</pattern>
          </encoder>
  
          <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
              <fileNamePattern>${LOG_PATH}/${STDOUT_LOG_FILE_NAME}.%d{yyyy-MM-dd}_%i.log</fileNamePattern>
              <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                  <maxFileSize>10MB</maxFileSize>
              </timeBasedFileNamingAndTriggeringPolicy>
              <maxHistory>30</maxHistory>
          </rollingPolicy>
      </appender>
  
      <appender name="PDF_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
          <file>${LOG_PATH}/${PDF_LOG_FILE_NAME}.log</file>
          <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
              <pattern>${LOG_PATTERN}</pattern>
          </encoder>
  
          <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
              <fileNamePattern>${LOG_PATH}/${PDF_LOG_FILE_NAME}.%d{yyyy-MM-dd}_%i.log</fileNamePattern>
              <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                  <maxFileSize>10MB</maxFileSize>
              </timeBasedFileNamingAndTriggeringPolicy>
              <maxHistory>30</maxHistory>
          </rollingPolicy>
      </appender>
  
      <appender name="IMAGE_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
          <file>${LOG_PATH}/${IMAGE_LOG_FILE_NAME}.log</file>
          <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
              <pattern>${LOG_PATTERN}</pattern>
          </encoder>
  
          <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
              <fileNamePattern>${LOG_PATH}/${IMAGE_LOG_FILE_NAME}.%d{yyyy-MM-dd}_%i.log</fileNamePattern>
              <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                  <maxFileSize>10MB</maxFileSize>
              </timeBasedFileNamingAndTriggeringPolicy>
              <maxHistory>30</maxHistory>
          </rollingPolicy>
      </appender>
  
      <logger name="com.cerberos.colorpool.service.pdf">
          <appender-ref ref="PDF_FILE"/>
      </logger>
      <logger name="com.cerberos.colorpool.service.image">
          <appender-ref ref="IMAGE_FILE"/>
      </logger>
  
      <root level="INFO">
          <appender-ref ref="STDOUT_FILE"/>
      </root>
  </configuration>
  ```

- PdfService.java

  ```java
  @Service
  @RequiredArgsConstructor
  public class ImageService {
      private final ImageJpaRepository imageJpaRepository;
      private final S3api s3api;
      private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
      public String uploadImage(MultipartFile imageMultipartFile){
          String extension = getExtension(imageMultipartFile.getContentType());
          String newImageName = getNewImageName(extension);
          String newImagePath = imageFolder+newImageName;
  
          try{
              s3api.upload(imageMultipartFile,"/image",newImageName);
              Image image = Image.builder()
                      .path(newImagePath)
                      .build();
              imageJpaRepository.save(image);
              ///////    log    ////////
  			logger.info("a new image(path : "+image.getPath()+") is added successfully");
  		    //////////////////////////
  		}catch (Exception e){
              logger.info("failed to add a new image");
              throw new CImageNotUploadException();
          }
          return newImagePath;
      }
  }
  ```

  

- ImageService.java

  ```java
  @Service
  @RequiredArgsConstructor
  public class ImageService {
      private final ImageJpaRepository imageJpaRepository;
      private final S3api s3api;
      private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
      public String uploadImage(MultipartFile imageMultipartFile){
          String extension = getExtension(imageMultipartFile.getContentType());
          String newImageName = getNewImageName(extension);
          String newImagePath = imageFolder+newImageName;
  
          try{
              s3api.upload(imageMultipartFile,"/image",newImageName);
              Image image = Image.builder()
                      .path(newImagePath)
                      .build();
              imageJpaRepository.save(image);
             	///////    log    ////////
  				logger.info("a new image(path : "+image.getPath()+") is added successfully");
  		    //////////////////////////
  		}catch (Exception e){
              logger.info("failed to add a new image");
              throw new CImageNotUploadException();
          }
          return newImagePath;
      }
  }
  ```

- SchedulerService.java

  ```java
  @Service
  @RequiredArgsConstructor
  public class ScheduleService {
      private final PdfJpaRepository pdfJpaRepository;
      private final S3api s3api;
      private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
      @Scheduled(cron = "0 0 18 * * *")
      public void clearPdfBucket(){
  		///// business logic /////       
  
          /////    log     /////
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date date = new Date();
          String time = simpleDateFormat.format(date);
          logger.info(time+" - all pdfFiles deleted");
          //////////////////////
      }
  }
  ```

  

