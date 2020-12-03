# Nginx reverse proxy

> 숨겨야되는 것은 다 숨겨야 한다
>
> *by **Beaver Bae** (1995~)*

<br>

#### reverse proxy를 통한 URL 변경

보안상 클라이언트 단에서 서버의 API를 호출할 떄 서버의 port번호는 보이면 안된다. <br>

마찬가지로 AWS S3의 실제 URL을 보여주면 악용 당할 우려가 있다고 판단하여 실제 URL을 감췄다 

<br>

#### 구현 내용

![image-20201203154240299](./assets/image/ReverseProxy.png)

- 클라이언트 단에서 서버의 port번호를 입력하지 않고도 서버 접근 가능
- image와 pdf가 저장된  AWS S3의 실제 URL이 아닌 location에 입력한 URL이 리턴된다.