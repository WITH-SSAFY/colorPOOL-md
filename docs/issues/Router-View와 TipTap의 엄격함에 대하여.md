## Router-View와 TipTap의 엄격함에 대하여

> **Router-View**란 'SPA의 장점'이다
>
> *by **강세응**(1994 ~ )*

<br>

SPA는 Single Page Application의 약자로, DOM tree에서 일부분만 렌더링하여 사용자에게 **다양한 작업을 마치 한 페이지에서의 작업처럼** 느껴지도록 하는 웹 어플리케이션이다. 우리는 이걸 이용해 사용자에게 **보다 나은 사용성**과 함께 **개발하는 입장에서의 편리함**을 추구했다. 우리가 사용할 것은 Router-View였다. Router-View를 사용하는 방법을 간단하게 설명해보자면

```javascript
{
    path: '/edit',
    name: 'Edit',
    component: Edit,
    // children: [
    //   {
    //     path: 'md',
    //     name: 'Editing',
    //     component: Editing
    //   },
    //   {
    //     path: 'color',
    //     name: 'Coloring',
    //     component: Coloring
    //   }
    // ]
  },
```

children이라는 항목을 두고 Edit이라는 컴포넌트에서는 Editing과 Coloring 컴포넌트를 번갈아 나오게 하면 되는 문제였다. 너무도 간단하지만 TipTap 라이브러리가 굉장히 strict하다는 점 때문에 이를 포기할 수 밖에 없었다. 그 이유는 **컴포넌트가 바뀌면서 작성 중인 마크다운 페이지가 렌더링될 때**, 마크다운의 **색깔 부분을 다 없애버린다는 점** 때문이었다. 그랬기 때문에 위처럼 주석처리를 할 수 밖에 없었고 Edit 컴포넌트의 자식인 Editing 컴포넌트에서 툴 박스와 컬러 박스를 번갈아 나오게 하는 방식으로 변경했다.

물론 SPA를 사용해서 Router-View 방식으로 구현했다면 더 깔끔했겠지만 TipTap의 엄격함을 감안해주었으면 한다.

```html
<span v-if="storePage == 1">
    <button
            class="toolbox-btn" :style="{'background-color': 'black'}"
            :class="{ 'is-active': isActive.customstyle({ level: 'black' }) }"
            @click="commands.customstyle({ level: 'black' })"
    ></button>
    <button
      class="toolbox-btn" :style="{'background-color': 'white'}"
      :class="{ 'is-active': isActive.customstyle({ level: 'white' }) }"
      @click="commands.customstyle({ level: 'white' })"
    ></button>
    <button
      class="toolbox-btn" :style="{'background-color': storeFinalTheme[0]}"
      :class="{ 'is-active': isActive.customstyle({ level: 'color1' }) }"
      @click="commands.customstyle({ level: colors[0] })"
    ></button>
    <button
      class="toolbox-btn" :style="{'background-color': storeFinalTheme[1]}"
      :class="{ 'is-active': isActive.customstyle({ level: 'color2' }) }"
      @click="commands.customstyle({ level: colors[1] })"
    ></button>
    <button
      class="toolbox-btn" :style="{'background-color': storeFinalTheme[2]}"
      :class="{ 'is-active': isActive.customstyle({ level: 'color3' }) }"
      @click="commands.customstyle({ level: colors[2] })"
    ></button>
    <button
      class="toolbox-btn" :style="{'background-color': storeFinalTheme[3]}"
      :class="{ 'is-active': isActive.customstyle({ level: 'color4' }) }"
      @click="commands.customstyle({ level: colors[3] })"
    ></button>
    <button
      class="toolbox-btn" :style="{'background-color': storeFinalTheme[4]}"
      :class="{ 'is-active': isActive.customstyle({ level: 'color5' }) }"
      @click="commands.customstyle({ level: colors[4] })"
    ></button>        
</span>
```



```html
<div class="background-box" v-if="storePage == 1">
    <button style="background-color: black;" @click="changeBackground('black')"></button>
    <button style="background-color: white;" @click="changeBackground('white')"></button>
    <button v-for="(color, index) in colors" :key="index" :style="{'background-color' : color}"
            @click="changeBackground(color)"></button>
</div>
```

어쩔 수 없이 위와 같은 방식으로 storePage가 1일 경우(Coloring인 경우 | Editing일 경우에는 storePage가 0이다) 에는 색깔을 칠하는 툴 박스와 함께 배경을 칠할 수 있는 박스가 보이도록 했고, **css 작업을 통해 마치 페이지가 넘어가는 듯한 효과**를 주면서 해결할 수 있었다.