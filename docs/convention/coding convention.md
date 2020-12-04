## Coding Convention
> 일관성 있는 코드 작성을 위해 다음의 규칙을 지키고 있습니다

<br>

<br>

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

### Vuex 사용 규칙

- state의 변경과 관련된 로직은 컴포넌트 내에 있지 않을 것
- state의 값을 변경할 때는 actions, mutations를 commit하거나 dispatch하여 사용할 것
- state의 값을 가져올 때에는 getters를 사용할 것
- actions 메소드 명은 `AC_`로, mutations의 메소드 명은 `MU_`로, getters 메소드 명은 `GE_`로 시작할 것
- getters로 데이터 가져올 때에는 변수명 앞에 `store`를 붙일 것
- 컴포넌트에서 state의 getter, actions, mutations를 가져올 때는 `mapGetters`, `mapActions`, `mapMutations`를 사용할 것

<br>

#### 적용 예시

> main.js

```javascript
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
```

<br>

> store/index.js

```javascript
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import colorStore from '@/store/modules/colorStore'

export default new Vuex.Store({
  modules: {
    colorStore,
  }
})
```

<br>

> store/modues/colorStore.js

```javascript
const colorStore = {
  namespaced: true,
  state: {
    selectedColor: null,
  },
  getters:{
    GE_SELECTED_COLOR: state => state.selectedColor
  },
  mutations: {
    MU_SELECTED_COLOR: (state, payload) => {
      state.selectedColor = payload
    }
  },
  actions:{
    AC_SELECTED_COLOR: ({commit}, payload) =>  {
      commit('MU_SELECTED_COLOR', payload)
    }
  }
}
export default colorStore
```

<br>

> Vue파일(component 내에서 사용하기)

```vue
<script> 
import { mapGetters, mapActions } from 'vuex'
const colorStore = 'colorStore' // namespaced: true

export default {
  name: 'RecommandTheme',
  computed: {
    // getters로 값을 가져옴
    ...mapGetters(colorStore, {storeSelectedColor: 'GE_SELECTED_COLOR'})
  },
  created(){
    // 가져온 값을 현재 컴포넌트의 data에 저장함
    this.selectedColor = this.storeSelectedColor;
  },
  data () {
    return {
      selectedColor: '',
    }
  },
  watch: {
    // 값이 변경되는 것을 감지함
    storeSelectedColor(val){
      this.selectedColor = val
    }
  },
  methods : {
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
    selectColor(color){
      const payload = { selectedColor: color};
      this.AC_SELECTED_COLOR(payload);
    },
  }
}
</script>
```

<br>

<br>

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

<br>

<br>