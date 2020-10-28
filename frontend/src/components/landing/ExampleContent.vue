<template>
  <div class="background" :style="{'background-color': this.selectedDemoColors[0]}" style="user-select: none;" >
    <h1 :style="{'color': this.selectedDemoColors[1]}">colorPOOL</h1>
    <blockquote>colorPOOL의 빅데이터 풀에서 나의 own 컬러와 배색을 찾고, 나만의 컨텐츠를 만들어보세요</blockquote>
    <h2>1. <samp>colorPOOL</samp>은 캐주얼한 컨텐츠로 소비될 수있는 서비스를 지향합니다.</h2>
    <h3>2. <samp>colorPOOL</samp>은 멋진 배색을 찾고, 컬러 플레이를 즐기는 사용자를 대상으로 합니다.</h3>
    <h3>3. <samp>colorPOOL</samp>은 온라인에서 다시 오프라인으로 이어지는 유저 경험을 제공합니다.</h3>
    <ul>
      <li>메인컬러를 선택하여 배색을 추천받고, 배색을 포함하는 이미지로 매거진을 제작할 수 있습니다.</li>
      <li>편집한 매거진은 인쇄를 통해 실물로 만나 볼 수 있습니다.</li>
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
      // alert("!!")
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
      document.querySelector('.background').style.backgroundColor = this.selectedDemoColors[0];
      document.querySelector('h1').style.color = this.selectedDemoColors[1];
      document.querySelector('blockquote').style.borderColor = this.selectedDemoColors[4];
      document.querySelector('blockquote').style.backgroundColor = this.quoteBackColor;
      document.querySelector('h2').style.color = this.selectedDemoColors[2];
      document.querySelector('samp').style.backgroundColor = this.selectedDemoColors[3];

      this.setBackgroundColor(this.selectedDemoColors);
      console.log('changeColors', this.selectedDemoColors);
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
}

h1, h2, h3{
  margin-bottom: 10px;
}

.background{
  padding: 5%;
  height: 100%;
  width: 100%;
}

blockquote{
  border-radius: 2px;
  padding-left: 8px;
  padding-top: 5px;
  padding-bottom: 5px;
  margin: 10px 1px;
  border-left: 3px solid;
  border-color: rgb(156, 156, 156);
  background-color: rgba(156, 156, 156, 0.1);
}

codeblock{
  padding: 6px 4px;
  border-radius: 3px;
  background-color: rgb(156, 156, 156, 0.5);
}

ul {
  list-style-type : disc;
}

</style>