# itext라이브러리 css처리

> 라이브러리는 썸이다. 제대로 알아가는 과정이 필요하기 때문이다.
>
> *by **Beaver Bae** (1995~)*

<br>

### 문제 상황
- PDF추출을 위해 외부 라이브러리로 itext를 사용
- 하지만 Itext가 기본 css를 적용하지 않았다.

<br>

#### 해결 방법
- [w3Schools - Default CSS Values for HTML Elements](https://www.w3schools.com/cssref/css_default_values.asp) 로 기본 css를 구성하고, 클라이언트 단에서 마크다운 작성시 적용한 css를 추가  
- [pdf.css](./backend/src/main/resources/static/pdf.css)

  