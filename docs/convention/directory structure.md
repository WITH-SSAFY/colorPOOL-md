# Directory

> 일관성 & 확장성 있는 코드 작성을 위해 디렉토리 구조를 관리하고 있습니다

<br>

<br>

## FrontEnd

### Vue의 프로젝트 디렉토리 구조

- 도메인 영역에 따라 디렉토리를 분리하는 것을 원칙으로 합니다

- 라우터 패스를 드러내는 컴포넌트는 views로, store는 모듈화 하여 관리합니다

<br>

```
src
├── api
├── assets
├── components
│   ├── edit
│   │   ├── Coloring.vue    // 텍스트에 색깔을 입히는 컴포넌트
│   │   ├── Editing.vue     // 마크다운 텍스트를 작성하는 EditPage 컴포넌트들을 관리하는 컴포넌트
│   │   └── EditPage.vue    // 마크다운 텍스트를 작성하는 컴포넌트
│   ├── header
│   │   └── progressBar.vue // 화면 상단에 사용자에게 현재 진행 상황을 알 수 있도록 해주는 컴포넌트
│   ├── landing
│   │   ├── Example.vue        // 랜딩페이지에서 사용자에게 서비스에 대한 간접적인 경험을 도와주는 컴포넌트
│   │   └── ExampleContent.vue // 랜딩페이지에 적혀 있는 마크다운 예시 컴포넌트
│   ├── select-palette
│   │   ├── GetColor.vue       // 검색어를 기반으로 색을 받아오고, 배색을 추천받는 컴포넌트
│   │   ├── PaletteCustom.vue  // 사용자가 최종 선택한 배색 조합을 보여주고 색을 커스텀하는 컴포넌트
│   │   ├── PaletteStack.vue   // 사용자가 위시리스트에 추가한 배색이 페이지 우측에 쌓이도록 하는 컴포넌트
│   │   └── PickColor.vue      // 사용자가 직접 200가지 색 중 선택해서, 배색을 추천받는 컴포넌트
│   └── sidebar
│   │   ├── DemoThemeSidebar.vue       // 랜딩페이지에서 미리 지정해둔 배색 조합 컴포넌트
│   │   ├── SelectThemeSidebar.vue     // 사용자가 검색해서 받아온 색 또는 직접 고른 색의 배색 조합 컴포넌트
│   │   ├── SlideListSidebar.vue       // 사용자가 작성하는 마크다운 발표 자료의 페이지별 미리 보기 컴포넌트
│   │   └── FinalSlideListSidebar.vue  // 작성을 완료한 발표 자료의 페이지별 미리 보기 컴포넌트
├── router
├── store
│   └── modules
│   │   ├── colorStore.js    // 사용자가 선택한 색을 저장해두는 store
│   │   ├── contentStore.js  // 작성 중인 발표 자료를 수시로 저장하는 store
│   │   ├── customStore.js   // 최종 선택된 배색 조합을 저장해두는 store
│   │   ├── demoStore.js     // 랜딩 페이지의 미리 지정된 배색 조합 store
│   │   ├── landingStore.js  // 사용자가 GetColor, PickColor 컴포넌트 중 어느 것을 보는지 저장하는 
│   │   ├── pdfStore.js      // 발표 자료를 서버에 업로드 요청하고 받은 결과 값을 저장하는 store
│   │   └── themeStore.js    // 사용자가 선택한 색으로 배색 조합을 받아와 저장하는 store
│   └── index.js
└── views
│   ├── Edit.vue            // 발표 자료 작성 및 컬러링 페이지
│   ├── Landing.vue         // 서비스 랜딩 페이지
│   ├── Result.vue          // 서비스 결과 페이지
│   ├── SelectPalettes.vue  // 배색 조합을 받아오고 선택할 수 있는 페이지
│   └── Viewer.vue          // 서버에 전송된 발표 자료를 온라인으로 볼 수 있는 뷰어 페이지
└── App.vue
└── main.js
```

<br>

### Vuex의 store 모듈화

> store/index.js

```javascript
import Vue from 'vue'
import Vuex from 'vuex'
import createdPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

import colorStore from '@/store/modules/colorStore'
import themeStore from '@/store/modules/themeStore'
import landingStore from '@/store/modules/landingStore'
import demoStore from '@/store/modules/demoStore'
import customStore from '@/store/modules/customStore'
import contentStore from '@/store/modules/contentStore'
import boxStore from '@/store/modules/boxStore'
import editStore from '@/store/modules/editStore'
import pdfStore from '@/store/modules/pdfStore'

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    colorStore,
    themeStore,
    landingStore,
    demoStore,
    customStore,
    contentStore,
    boxStore,
    editStore,
    pdfStore,
  },
  plugins: [
    createdPersistedState({
      paths: ['landingStore', 'colorStore', 'customStore', 'contentStore', 'pdfStore']
    })
  ]
})

```



