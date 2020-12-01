## Git branch 전략

> master - (fix) - release - develop - feature

- master : 완전히 서비스가 배포된 bracnh
- hotfix : 릴리즈된 서비스에서 버그 수정 bracnh
- release : 완전한 서비스가 배포되기전에 임시로 배포된 branch (version)
- develop : 각 기능들이 합쳐져서 개발중인 branch
- feature : 각자의 기능들을 개발중인 bracnh

### 각 브랜치 별 상세 규칙

> 머지리퀘스트시 병합되는 브런치를 잘 확인하자 (default는 develop)

#### master 
모든 테스트과정을 거친 후 서비스 시작을 위해 배포된다. (version)

#### hotfix
상시로 발견된 버그들을 수정해서 develop branch에 merge한다.

#### release
버전 출시 전(master로 병합되기 전)에 시나리오를 짜서 테스트해본다.
> release branch에서 작성한 테스트 시나리오는 제품의 기능을 테스트 하는 것

#### develop
릴리즈하기전에 기능들을 취합하고 정적인 버그들을 잡는다.

#### feature
기능들을 쪼개서 개발한다.

### REF 
[우아한 형제들 git-flow](https://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)
