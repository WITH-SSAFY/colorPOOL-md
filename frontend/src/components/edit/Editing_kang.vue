<template>
  <div>
    <v-col class="editing-area">
      <div class="info-section">
        info-section
      </div>
      <div class="slide-section">
        <div class="tool-box">
          <div class="tool-bar">
            tool bar
          </div>
          <div class="import-button">
            import md
          </div>
        </div>
        <div class="slide-box">
          <!-- TODO : 슬라이드를 배경과 구분할 수 있는 UI 넣기 -->
          <div class="slide-content">
            <!-- 한국어 입력 동기화 이슈로, v-model 대신 v-bind와 v-on 직접 연결 방식 이용 -->
            <!-- <textarea ref="md" :value="inputText" @input="updateInput" style="width: 100%;"></textarea> -->
            <div contenteditable id="page" @input="inputted2"></div>
            <!-- <textarea id="page" :value="inputText" @input="inputted"></textarea> -->
          </div>
        </div>
      </div>
    </v-col>
  </div>
</template>

<script>
// 마크다운 라이브러리 import
import md from '../../assets/markdown/markdown.js'

export default {
  name: 'Editing',
  components: {
    
  },
  data() {
    return {
      inputText: '',
    }
  },
  methods: {
    updateInput: function(event) {
      var updatedText = event.target.value;
      this.inputText = updatedText
      document.querySelector("#page").innerHTML = md.render(this.inputText.toString());
    },
    inputted2(e) {
      console.log(e);
      let str = document.querySelector('#page').innerHTML;
      console.log(str);
      if(e.inputType == 'insertParagraph')
        document.querySelector('#page').innerHTML = md.render(document.querySelector('#page').innerHTML);
      if(e.inputType == 'insertText' && e.data == null) {
        document.querySelector('#page').innerHTML = md.render(document.querySelector('#page').innerHTML);
      }
      // console.log(e)
      // console.log(document.querySelector('#page').innerHTML)
      // if(e.data == '-') {
      //   document.querySelector('#page').innerHTML = md.render(document.querySelector('#page').innerHTML)
      // }
      // console.log(this.inputText);
      // if(e.inputType == 'insertParagraph' || ((e.inputType == 'insertText') && (e.data == null) )) {
      //   document.querySelector('#page').innerHTML = md.render(this.inputText.toString())
      // }
    },
    inputted (e) {
      console.log(this.inputText)
      console.log(e.inputType);
      if(e.inputType == 'insertParagraph') {
        this.inputText += '\n';
        document.querySelector('#page').innerHTML = md.render(this.inputText.toString());
        console.log(document.querySelector('#page').innerHTML)
      } else if (e.inputType == 'deleteContentBackward') {
        this.inputText = this.inputText.slice(0, -1);
      } else {
        if(e.data == null) {
          this.inputText += '\n'
          // this.inputText = this.inputText.slice(0, -4);
          document.querySelector('#page').innerHTML = md.render(this.inputText.toString());
        } else {
          this.inputText += e.data;
        }
      }

    }
  },
  created() {
    // test
    // var str = "# h1 Heading \n## h2 Heading Heading \n### h3 Heading \n#### h4 Heading \n##### h5 Heading \n###### h6 Heading \n---"
  },
  watch: {
    inputText(val) {
      console.log(val);
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
  padding: 2%;
  height: 20vh;
  background-color: cadetblue;
}

.editing-area.col .slide-section {
  background-color: aqua;
}

.editing-area.col .tool-box {
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: blue;
  height: 10vh;
  width: 85%;
  margin-left: 7.5%;
}

.editing-area.col .tool-box .tool-bar {
  display: inline-block;
  background-color: lightgrey;
  height: 4vh;
  width: 50%;
}

.editing-area.col .tool-box .import-button {
  display: inline-block;
  background-color: lightgreen;
  height: 4vh;
  width: 10%;
}

/* 
슬라이드 창 비율 고정 [16:9]
padding-top = 세로 / 가로 * 100
*/
.editing-area.col .slide-section .slide-box {
  position: relative;
  margin-left: 7.5%;
  width: 85%;
}
 
.editing-area.col .slide-section .slide-box:before {
  content: "";
  display: block;
  padding-top: 56.25%;
}
 
.editing-area.col .slide-section .slide-content {
  position: absolute;
  background-color: whitesmoke;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}





</style>