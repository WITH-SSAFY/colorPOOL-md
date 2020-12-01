## Git commit message convention

### 라벨
- feat : 새로운 기능
- fix : 버그 수정
- docs : 문서 수정
- style : 스타일 변경(코드변경x)
- refactor : 리펙토링
- test : 테스트 코드 추가
- chore : setting 변경

### 양식

#### 그라운드 양식
- 커밋 메세지 제목은 오직 영어로 입력한다.
- 라벨을 제외한 커밋 메세지 제목의 첫글자는 대문자로 시작한다.
- 본문 내용은 한글과 영어를 섞어서 사용 가능하다.
- 명령조를 이행할때 동사는 아래와같은 키워드를 활용한다.

> chore : **`Create`** application.yml for Spring setting <br>
> docs : **`Update`** Reademe.md <br>
> docs : **`Remove`** Readme.md <br>

#### 기본 양식
```
Label : 변경사항 30자 이내로 요약 (명령조, 마침표 금지)

제목과 본문사이 띄어쓰기 필수
본문 내용은 어떻게 보다 "왜, 무엇을"에 초점을 맞춰서 적을 것

줄 바꿈 후 이슈 트래커를 위해서 아래에 다음과같은 커밋 내용을 첨가
Resolves: 자리 이슈 번호
See also: 같이 보면 좋은 이슈 (optional)
```

#### 간단한 양식
chore와 같이 간단한 내용들은 아래 규칙을 따른다.
```
chore : 변경사항 30자 이내로 요약 (명령조, 마침표 금지)
```

<br>