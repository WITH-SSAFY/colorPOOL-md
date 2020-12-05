## V-Carousel과 발표 자료의 상관성에 대하여

> 슬라이드 단위 발표 자료는 '페이지를 넘기는 맛'이다
>
> *by **Beaver Bae*** (1995 ~ )

<br>

배팀장께서는 발표 자료는 발표를 하면서 페이지를 넘기는 맛이라고 하셨다. 발표 자료를 넘기는 방식은 매우 많다. 마우스 클릭을 통해 넘길 수도 있고, 키보드의 **방향키 버튼 아래나 오른쪽**을 눌러서 **다음 페이지**를, **방향키 위나 왼쪽**을 눌러서 **이전 페이지**를 볼 수 있다. 특히 키보드를 이용하는 방식을 많이들 사용하는데 우리는 이런 **UX를 중시**했고 전용 뷰어에서도 이런 감성을 느낄 수 있도록 했다.

```javascript
this.handler = window.addEventListener('keydown', (e) => {
    if(e.code == 'ArrowLeft' || e.code == 'ArrowUp') {
        document.querySelector('.v-window__prev button').click();
    }
    if(e.code == 'ArrowRight' || e.code == 'ArrowDown') {
        document.querySelector('.v-window__next button').click();
    }
})
```

뷰어에서의 핸들러다. keydown은 **키보드의 어떤 버튼이든 눌리기만 한다면 이벤트를 발생**시킨다. 이벤트로 눌린 키의 코드가 왼쪽이거나 위일 경우에는 V-Carousel의 왼쪽 버튼을 누르는 것과 같은 효과를, 오른쪽이거나 아래일 경우에는 V-Carousel의 오른쪽 버튼을 누르는 것과 같은 효과를 노렸다. V-Carousel은 공통 프로젝트부터 특화 프로젝트, 자율 프로젝트까지 꾸준히 써오고 있다. V-Carousel이 유용한 점은 **단순하고 직관적으로 Carousel을 표현**할 수 있고 **delimeter를 숨기거나 크기를 마음대로 조정**할 수 있다는 점에 있다. 이번 자율 프로젝트에서는 V-Carousel을 사용하여 발표자료가 전체 화면인 점을 이용할 수 있었다.


