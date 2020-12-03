# Scheduler를 활용한 Batch

> ##### 주기적으로 해야되는 작업이 있다면 Batch를 사용해 보세요
>
> *by Consultant **K.M.Park** (1976~)*



#### 매일 특정 시각에 PDF DB의 내용과 AWS S3에 저장한 PDF 파일 삭제

회원이 없기에 초기 기획 당시 작성한 발표 자료의 추출을 일회성으로 하기로 했다. 그렇기에 PDF DB와 AWS S3에 저장한 PDF 파일은 계속 쌓아 두면 낭비였다. 컨설턴트님이 Batch를 구축해서 매일 특정 시각에 자동으로 PDF DB의 내용과 S3에 저장된 내용을 삭제해 보는 것을 추천하셨다.



#### 구현 내용

- Application.java (Scheduler를 사용한다고 명시)

  ```java
  @SpringBootApplication
  @EnableScheduling //Scheduler 사용
  public class ColorpoolApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(ColorpoolApplication.class, args);
      }
  
  }
  ```

- SchedulerService.java (실제 Scheduler 구현)

  ```java
  @Service
  @RequiredArgsConstructor
  public class ScheduleService {
      private final PdfJpaRepository pdfJpaRepository;
      private final S3api s3api;
      private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
      @Scheduled(cron = "0 0 18 * * *") //매일 오후 6시에 스케줄러 실행
      public void clearPdfBucket(){
          List<Pdf> allPdfList = pdfJpaRepository.findAll();
  
          if(allPdfList.isEmpty()) return;
  
          String keyPrefix = "pdf/";
          String subPath = "/pdf";
  
          List<String> allPdfNameList = new ArrayList<>();
          for(Pdf pdf : allPdfList){
              String[] splitedPath = pdf.getPath().split("[/]");
              String pdfName = splitedPath[splitedPath.length-1];
              allPdfNameList.add(keyPrefix+pdfName);
          }
          
          s3api.delete(allPdfNameList,subPath);//S3에 저장된 pdf 모두 삭제
          pdfJpaRepository.deleteAll();//pdf DB의 내용 모두 삭제
      }
  }
  ```

  