<template>
  <div>
    <v-col class="editing-area">
      <div class="toolbox" :class="[isToolBoxShow? 'show': '']">
        <button class="toolbox-btn" style="width: 20%; height: 100%; border-radius: 70%;" v-for="(color, index) in colors" :style="{'background-color': color}" :key="index" @click="clickColor(color, index)"></button>
      </div>
      <div class="info-section">
        <div class="progress-bar"><progress-bar v-bind:page="3" /></div>
      </div>
      <section id="container"  class="editor" :class="'item' + index"  v-for="(item, index) in contents" :key="index" @click="pageIndex = index">
        <div class="colorbox">
          <button class="colorbox-btn" style="background-color: white" @click="clickBackground('white', index)"></button>
          <button class="colorbox-btn" v-for="color in colors" :key="color"  :style="{'background-color': color}" @click="clickBackground(color, index)"></button>
        </div>
        <div class="editor__content" :class="'item' + index" v-html="item"></div>
      </section>
    </v-col>
  </div>
</template>

<script>
import ProgressBar from '../../components/header/ProgressBar'
import { mapActions, mapGetters } from 'vuex'
require('../../assets/LiveEditStyle.css')
const contentStore = 'contentStore'
const customStore = 'customStore'

export default {
  name: 'Coloring',
  components: {
    ProgressBar
  },
  computed: {
    ...mapGetters(contentStore, {storeContents: 'GE_CONTENTS'}),
    ...mapGetters(customStore, {storeFinalTheme: 'GE_FINAL_THEME'})
  },
  data() {
    return {
      contents: [],
      colors: [],
      isToolBoxShow: false,
      target: null,
      targetStr: '',
      isTotal: false,
      targetParent: null,
      pageIndex: 0,
    }
  },
  mounted () {
    window.addEventListener('click', this.handleDrag);
    // document.querySelectorAll('h1').forEach((node) => {
    //   node.classList.add('active');
    // })
  },
  created() {
    this.contents = this.storeContents;
    this.colors = this.storeFinalTheme;
    console.log(this.storeContents);
    
  },
  beforeDestroy() {
    window.removeEventListener('click', this.handleDrag);
  },
  methods : {
    ...mapActions(contentStore, ['AC_CONTENTS']),
    handleDrag(e) {
      if(e.target.offsetParent == null) {
        this.target = null;
        this.targetStr = '';
        this.isToolBoxShow = false;
        return;
      }
      if(!e.target.offsetParent.className.includes('editor')) {
        this.target = null;
        this.targetStr = '';
        this.isToolBoxShow = false;
        return;
      }
      
      let str = window.getSelection().toString();
      if(str.length == 0) {
        this.target = null;
        this.targetStr = '';
        this.isToolBoxShow = false;
      } else {
        e.target.focus();
        if(e.target.innerHTML == str && e.target.parentElement.localName == 'blockquote') {
          this.isTotal = true;
          this.targetParent = e.target.parentElement;
        }
                
        let toolbox = document.querySelector('.toolbox');
        toolbox.style.left = (e.pageX - 100) + 'px';
        toolbox.style.top = (e.pageY - 60) + 'px';
        this.target = e.target;
        this.targetStr = str;
        this.isToolBoxShow = true;
      }
    },
    clickColor(color, index) {
      // 만약 텍스트를 전체 선택한 거라면?
      if(this.isTotal){
        this.isTotal = false;
        this.targetParent.style.borderColor = color;
        this.targetParent = null;
      }
      // 단순히 텍스트를 바꾸는 효과
      
      this.target.className = "";
      this.target.classList.add('color' + index);
      
      
      // this.target.innerHTML = this.target.innerHTML.replace(this.targetStr, "<span style='color:" + color + "'>" + this.targetStr + '</span>')
      const payload = {
        page: this.pageIndex,
        content:  '<section>' + document.querySelector('.editor__content.item' + this.pageIndex).innerHTML + '</section>'
      }
      this.AC_CONTENTS(payload);
    },
    clickBackground(color, index) {
      if(color == 'white') console.log(document.querySelector('.editor.item' + index))

      document.querySelector('.editor.item' + index).style.backgroundColor = color;
    }
  }
}
</script>

<style scoped>
  .editing-area.col {
    padding: 0;
  }

  .editing-area.col .info-section {
    margin: 0;
    height: 20vh;
    padding-left: 3rem;
  }

  .editor {
    width: 795px;
    height: 445px;
    margin-left: 20%;
    padding: 40px 80px;
    box-shadow: 8px 8px 23px 0px rgba(0,0,0,0.75);
  }

  section {
    transition-duration: 300ms;
  }

  /* =================== COLOR BOX COLOR =================== */
  .colorbox {
    position: absolute;
    top: -50px;
    right: 0;
    width: 290px;
    height: 40px;
  }

  .colorbox-btn {
    height: 40px;
    width: 40px;
    margin: auto;
    border-radius: 70%;
    border: 2px solid slategray;
  }

  .colorbox-btn:nth-child(n + 2) {
    margin-left: 10px;
  }
  /* ====================================================== */

  /* =================== TOOL BOX COLOR =================== */
  .toolbox {
    position: absolute;
    width: 200px;
    height: 40px;
    opacity: 0;
    visibility: hidden;
    transition-duration: 300ms;
  }

  .toolbox.show {
    opacity: 1;
    z-index: 95;
    visibility: visible;
  }

  .toolbox button {
    border: 2px solid slategray;
  }

  * >>> blockquote {
    border-left: 8px solid #ccc;
    margin: 10px;
    padding: 10px;
  }
  /* ====================================================== */


  .color1 {
    color: red;
  }

  .color2 {
    color: blue;
  }

  .color3 {
    color: green;
  }

  .color4 {
    color: yellow;
  }

  .color5 {
    color: orange;
  }
</style>