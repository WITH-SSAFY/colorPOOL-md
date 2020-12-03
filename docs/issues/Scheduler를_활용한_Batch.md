# Scheduler를 활용한 Batch



### 매일 특정 시각에 PDF DB와 AWS S3에 저장한 PDF 파일 삭제(현재는 동작x)

- Application.java

  ```java
  @SpringBootApplication
  @EnableScheduling
  public class ColorpoolApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(ColorpoolApplication.class, args);
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
  
      @Scheduled(cron = "0 0 18 * * *") //매일 오후 6시에 진행
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
          s3api.delete(allPdfNameList,subPath);
          pdfJpaRepository.deleteAll();
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date date = new Date();
          String time = simpleDateFormat.format(date);
          logger.info(time+" - all pdfFiles deleted");
      }
  }
  ```

  