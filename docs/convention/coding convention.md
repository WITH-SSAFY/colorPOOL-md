## 코딩 컨벤션
## JAVA

[Naver Hackday Java 코딩 컨벤션](https://naver.github.io/hackday-conventions-java/#space-around-comment)을 따른다.

[Checkstyle](https://github.com/naver/hackday-conventions-java/blob/master/rule-config/naver-checkstyle-rules.xml)을 통해서 빌드시 코딩 컨벤션 pass 여부를 결정한다. 

> 아래 내용은 별도로 코딩 컨벤션을 지킨다.

- [한국어 발음대로의 표기 금지](https://naver.github.io/hackday-conventions-java/#avoid-korean-pronounce)
- [클래스 이름에 명사 사용](https://naver.github.io/hackday-conventions-java/#class-noun)
- [인터페이스 이름에 명사/형용사 사용](https://naver.github.io/hackday-conventions-java/#interface-noun-adj)
- [메서드 이름은 동사/전치사로 시작](https://naver.github.io/hackday-conventions-java/#method-verb-preposition)
- [테스트 클래스는 'Test’로 끝남](https://naver.github.io/hackday-conventions-java/#test-class-suffix)
- [대괄호 뒤에 공백 삽입](https://naver.github.io/hackday-conventions-java/#space-after-bracket)
- [주석문 기호 전후의 공백 삽입](https://naver.github.io/hackday-conventions-java/#space-around-comment)

우리는 IntelliJ IDEA를 이용하는데, [intellij-community](https://github.com/JetBrains/intellij-community/tree/master/plugins/editorconfig)를 보면 editconfig를 제공하기 때문에 별도의 [editconfig](https://editorconfig.org/)를 설정할 필요가 없다.

\+ optional을 적극 활용하자

<br>

<br>

## Vue

### export default 내 요소의 순서

```
- name:
- components:
- computed:
- props:
- data()
- watch:
- lifecycle
  - beforeCreated
  - created
  - beforeMount
  - mounted
  - beforeUpdate
  - updated
  - beforeDestroy
  - destroyed
- methods:
```

