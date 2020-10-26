<template>
  <div class="background" :style="{'background-color': this.demoColor.backColor}">
    <h1 :style="{'color': this.demoColor.headColor}">colorPOOL</h1>
    <blockquote :style="{'border-color': this.demoColor.quoteColor, 'background-color': this.quoteBack}">colorPOOL의 빅데이터 풀에서 나의 own 컬러와 배색을 찾고, 나만의 컨텐츠를 만들어보세요</blockquote>
    <h2 :style="{'color': this.demoColor.subColor}">1. <codeblock :style="{'background-color': this.demoColor.emphaColor}">coloPOOL</codeblock>은 캐주얼한 컨텐츠로 소비될 수있는 서비스를 지향합니다.</h2>
    <h3>2. <codeblock :style="{'background-color': this.demoColor.emphaColor}">coloPOOL</codeblock>은 멋진 배색을 찾고, 컬러 플레이를 즐기는 사용자를 대상으로 합니다.</h3>
    <h3>3. <codeblock :style="{'background-color': this.demoColor.emphaColor}">coloPOOL</codeblock>은 온라인에서 다시 오프라인으로 이어지는 유저 경험을 제공합니다.</h3>
    <ul>
      <li>메인컬러를 선택하여 배색을 추천받고, 배색을 포함하는 이미지로 매거진을 제작할 수 있습니다.</li>
      <li>편집한 매거진은 인쇄를 통해 실물로 만나 볼 수 있습니다.</li>
    </ul>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
const colorStore = 'colorStore'

export default {
  name: 'ExampleContent',
  computed:{
    ...mapGetters(colorStore, {storeDemoColor: 'GE_DEMO_COLOR'}),
  },
  data(){
    return{
      demoColor: null,
      quoteBack: ''
    }
  },
  watch: {
    storeDemoColor(val){
      this.demoColor = val;
      this.appyColor('watch');
    },
  },
  created(){
    this.demoColor = this.storeDemoColor;
    console.log(this.demoColor);
    this.addOpacity(this.demoColor.quoteColor);
    this.applyColor('created');
  },
  beforeUpdate(){
    this.applyColor('beforeupdate');
  },
  updated() {
    this.addOpacity(this.demoColor.quoteColor);
    this.applyColor('updated');
  },
  methods:{
    addOpacity(color){
      let temp = color.replace('rgb(', "rgba(");
      temp = temp.replace(')', ", 0.1)");
      this.quoteBack = temp;
    },

    //tag 검색해서 거기에 style 추가하는 함수
    applyColor(temp){
      // alert('applyColor : '+temp);
      console.log('applyColor', temp)
      if(this.demoColor.backColor!==''){
        const backGround = document.querySelector('.background');
        console.log(backGround);
        backGround.style.backgroundColor=this.demoColor.backColor;
      }
      // else if(this.demoColor.headColor!==''){

      // } else if(this.demoColor.subColor!==''){
        
      // } else if(this.demoColor.emphaColor!==''){
        
      // } else if(this.demoColor.headColor!==''){
        
      // }
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