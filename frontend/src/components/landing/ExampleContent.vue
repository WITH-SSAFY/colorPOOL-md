<template>
  <div class="background" :style="{'background-color': this.selectedDemoColors[0]}">
    <h1 :style="{'color': this.selectedDemoColors[1]}">colorPOOL.md</h1>
    <!-- <hr> -->
    <blockquote>
      colorPOOL.md에서
      <blockquote>원하는 색에 대한 <samp>배색</samp>을 찾고, <samp>마크다운</samp>에 <strong>컬러를 입히세요</strong></blockquote>
    </blockquote>
    <h2>1. 전문가가 선택한 배색 데이터를 추천합니다</h2>
    <hr>
    <ul>
      <li><em>팀 컬러</em> 에 대한 배색 정보를 찾아보세요</li>
      <li>발표의 <em>키워드</em> 에 대한 배색 정보를 찾아보세요</li>
    </ul>
    <h2>2. 마크다운 라이브 에디팅이 가능합니다</h2>
    <hr>
    <ul>
      <li>프리뷰 없이, 마크다운 문법을 <strong>실시간으로 렌더</strong>하세요</li>
      <li><del>이미지 첨부는 물론,</del> 크기를 조절하여 시각적 효과를 극대화하세요</li>
      <li>외부 마크다운 파일을 <strong>불러오기</strong> 하여 컬러를 입히세요</li>
      <li>코드를 자료에 첨부하고 싶다면?</li>
      <ul>
        <li class="light"><code> //  Atom 테마의 하이라이팅이 적용된 코드 블록을 활용해보세요</code></li>
      </ul>
      <li>마크다운 문법이 익숙하지 않다면?</li>
      <ul>
        <li class="light">마크다운 기능을 그대로 담은 <samp>툴 박스</samp>를 이용해 쉽게 사용해보세요</li>
      </ul>
    </ul>
    <h2>3. 슬라이드 단위로 자료의 영역을 나눕니다</h2>
    <hr>
    <ul>
      <li>발표 자료를 아래의 방법으로 추출하세요</li>
      <ol><strong>1. pdf</strong> :  문서 형식을 <samp>그대로 보존</samp>하세요</ol>
      <ol><strong>2. viewer</strong> :  <samp>gif의 동작</samp>까지 보여주세요</ol>
      <ol><strong>3. text copy</strong> :  외부 에디터에서도 <samp>호환되는 컨텐츠</samp>를 확인하세요</ol>
    </ul>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
// const colorStore = 'colorStore'
const demoStore = 'demoStore'

export default {
  name: 'ExampleContent',
  computed:{
    ...mapGetters(demoStore, {storeSelectedDemoTheme: 'GE_SELECTED_DEMO_THEME', storeFlagDemoTheme: 'GE_FLAG_DEMO_THEME', storeIsFlagChanged: 'GE_IS_FLAG_CHANGED'}),
  },
  data(){
    return{
      selectedDemoTheme: null,
      flagDemoTheme: [],
      demoColors: [], // themeSidebar에서 선택한 theme
      selectedDemoColors: [], // 선택한 theme에서 적용할 색들만 저장 
      quoteBackColor: '',
    }
  },
  watch: {
    storeSelectedDemoTheme (val) {
      this.selectedDemoTheme = val;
      this.changeColors();
    },
    storeIsFlagChanged() {
      console.log(this.storeFlagDemoTheme);
      this.flagDemoTheme = this.storeFlagDemoTheme;
      this.changeColors();
    },
  },
  mounted() {
    this.changeColors();
  },
 
  methods:{
    changeColors() {
      this.selectedDemoTheme = this.storeSelectedDemoTheme;
      this.flagDemoTheme = this.storeFlagDemoTheme;
      const color = this.selectedDemoTheme;
      let colorList = [];
      colorList[0] = 'rgb(' + color.red1 + ', ' + color.green1 + ', ' + color.blue1 + ')';
      colorList[1] = 'rgb(' + color.red2 + ', ' + color.green2 + ', ' + color.blue2 + ')';
      colorList[2] = 'rgb(' + color.red3 + ', ' + color.green3 + ', ' + color.blue3 + ')';
      colorList[3] = 'rgb(' + color.red4 + ', ' + color.green4 + ', ' + color.blue4 + ')';
      colorList[4] = 'rgb(' + color.red5 + ', ' + color.green5 + ', ' + color.blue5 + ')';
      this.flagDemoTheme.forEach ((flag, index) => {
        if(flag) this.selectedDemoColors[index] = colorList[index];
        else this.selectedDemoColors[index] = '';
      })
      this.addOpacity(this.selectedDemoColors[4]);
      /* 배경색 처리 */
      if (this.selectedDemoColors[0] == '') {
        document.querySelector('.indicator').style.backgroundColor = 'white'
        document.querySelector('.example-md').style.backgroundColor = 'white'
      } else {
        document.querySelector('.indicator').style.backgroundColor = this.selectedDemoColors[0]
        document.querySelector('.example-md').style.backgroundColor = this.selectedDemoColors[0]
      }
      /* background */
      document.querySelectorAll('.background h1').forEach(node => node.style.color = this.selectedDemoColors[1])
      
      document.querySelectorAll('.background strong').forEach(node => node.style.color = this.selectedDemoColors[1])
      document.querySelectorAll('.background hr').forEach(node => node.style.borderBottomColor = this.selectedDemoColors[2])
      document.querySelectorAll('.background h2').forEach(node => node.style.color = this.selectedDemoColors[2])
      document.querySelectorAll('.background em').forEach(node => node.style.color = this.selectedDemoColors[2])
      document.querySelectorAll('.background code').forEach(node => node.style.color = this.selectedDemoColors[2])
      document.querySelectorAll('.background del').forEach(node => node.style.color = this.selectedDemoColors[2])
      document.querySelectorAll('.background samp').forEach(node => node.style.backgroundColor = this.selectedDemoColors[3])
      document.querySelectorAll('.background blockquote').forEach(node => node.style.borderColor = this.selectedDemoColors[4])
      document.querySelectorAll('.background blockquote').forEach(node => node.style.backgroundColor = this.quoteBackColor)
      document.querySelectorAll('.background code').forEach(node => node.style.backgroundColor = this.selectedDemoColors[4])
      /* page-content */
      document.querySelectorAll('.page-content hr').forEach(node => node.style.borderBottomColor = this.selectedDemoColors[1])
      document.querySelectorAll('.page-content blockquote').forEach(node => node.style.borderColor = this.selectedDemoColors[4])
      document.querySelectorAll('.page-content blockquote').forEach(node => node.style.backgroundColor = this.quoteBackColor)
      document.querySelectorAll('.page-content samp').forEach(node => node.style.backgroundColor = this.selectedDemoColors[3])

      this.setBackgroundColor(this.selectedDemoColors);
      // console.log('changeColors', this.selectedDemoColors);
    },
    addOpacity(color){
      if(color !== ''){
        let temp = color.replace('rgb(', "rgba(");
        temp = temp.replace(')', ", 0.2)");
        this.quoteBackColor = temp;
      } else {
        this.quoteBackColor = '';
      }
    },
    setBackgroundColor(color) {
      if(this.flagDemoTheme[0] != true) {
        document.querySelector('.background').style.color = "black";
        return;
      }
      let rgb = color[0].replace('rgb(', "");
      rgb = rgb.replace(')', "");
      rgb = rgb.split(', ');
      var num = Number(rgb[0]) + Number(rgb[1]) + Number(rgb[2]);
      if(num < 250) {
        document.querySelector('.background').style.color = "white";
      } else {
        document.querySelector('.background').style.color = "black";
      }
    }
  }
}
</script>

<style scoped>
* {
  transition-duration: 400ms;
  transition-property: background-color;
}

h1, h2, h3 {
  margin: 0;
  margin-top: 20px;
}

hr {
  margin: 0;
  margin-top: 2px;
  margin-bottom: 5px;
  height: 0px;
  color: transparent;
  border-color: transparent;
}

.background {
  padding: 0 5%;
  padding-bottom: 5%;
  height: 100%;
  width: 100%;
  user-select: none;
}

blockquote {
  border-radius: 1px;
  padding: 0;
  padding-left: 10px;
  margin: 5px 0;
  border-left: 6px solid;
  border-color: rgb(156, 156, 156);
  background-color: rgba(156, 156, 156, 0.1);
  color: #888;
}

code {
  padding: 6px 6px;
  border-radius: 3px;
  background-color: rgb(156, 156, 156, 0.1);
}

samp {
  padding: 0 5px;
  background-color: rgba(156, 156, 156, 0.5);
  border: 0.5px solid rgba(156, 156, 156, 0.1);
  border-radius: 4px;
}

ul {
  list-style-type: disc;
  margin: 0;
}

li.light {
  list-style-type: circle;
}

ol {
  margin: 0;
  padding: 0;
}
</style>