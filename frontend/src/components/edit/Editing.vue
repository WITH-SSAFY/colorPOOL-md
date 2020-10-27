<template>
  <div>
    <v-col class="editing-area">
      <div class="info-section">
        info-section
      </div>
      <div class="slide-section">
        <div class="tool-box">
          <div class="tool-bar">
            <button @click="appendBold"><v-icon>mdi-format-bold</v-icon></button>
            <button @click="appendItalic"><v-icon>mdi-format-italic</v-icon></button>
            <button @click="appendUnderline"><v-icon>mdi-format-underline</v-icon></button>
            <button @click="appendCode"><v-icon>mdi-code-tags</v-icon></button>
            <button @click="appendH1"><v-icon>mdi-format-header-1</v-icon></button>
            <button @click="appendH2"><v-icon>mdi-format-header-2</v-icon></button>
            <button @click="appendH3"><v-icon>mdi-format-header-3</v-icon></button>
            <button @click="appendH4"><v-icon>mdi-format-header-4</v-icon></button>
            <button @click="appendH5"><v-icon>mdi-format-header-5</v-icon></button>
            <button @click="appendH6"><v-icon>mdi-format-header-6</v-icon></button>
            <button @click="appendQuote"><v-icon>mdi-format-quote-open</v-icon></button>
            <button @click="appendUl"><v-icon>mdi-format-list-bulleted</v-icon></button>
            <button @click="appendOl"><v-icon>mdi-format-list-numbered</v-icon></button>
            <button @click="appendEmoji"><v-icon>mdi-emoticon-outline</v-icon></button>
          </div>
          <div class="import-button">
            import md
          </div>
        </div>
        <div class="slide-box">
          <div class="slide-content">
            <!-- 한국어 입력 동기화 이슈로, v-model 대신 v-bind와 v-on 직접 연결 방식 이용 -->
            <textarea ref="md" :value="inputText" @input="updateInput" class="text-input"></textarea>
            <div id="page"></div>
          </div>
        </div>
      </div>
    </v-col>
  </div>
</template>

<script>
// 마크다운 라이브러리 import
const emoji = require('markdown-it-emoji');
const md = require('markdown-it')({
  html: true,
  linkify: true,
  typographer: true,
});
md.use(emoji);

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
    updateInput () {
      var updatedText = event.target.value;
      this.inputText = updatedText;
      document.querySelector("#page").innerHTML = md.render(this.inputText.toString());
    },
    appendBold () {
      // this.inputText = '**'.concat(this.inputText, '**')
      // document.querySelector("#page").innerHTML = md.render(this.inputText.toString());
      this.inputText += '\n** bold example';
      this.inputText += '\n**';
    },
    appendItalic () {
      this.inputText += '\n_ bold example';
      this.inputText += '\n_';
    },
    appendUnderline () {
      this.inputText += '\n~~ bold example';
      this.inputText += '\n~~';
    },
    appendCode () {
      this.inputText += '\n ``` js\n';
      this.inputText += "var foo = function (bar) {\nreturn bar++;\n};\nconsole.log(foo(5));";
      this.inputText += '\n ```';
    },
    appendH1 () {
      this.inputText += '\n# h1 example'
    },
    appendH2 () {
      this.inputText += '\n## h2 example'
    },
    appendH3 () {
      this.inputText += '\n### h3 example'
    },
    appendH4 () {
      this.inputText += '\n#### h4 example'
    },
    appendH5 () {
      this.inputText += '\n##### h5 example'
    },
    appendH6 () {
      this.inputText += '\n###### h6 example'
    },
    appendQuote () {
      this.inputText += '\n > quote example'
    },
    appendUl () {
      this.inputText += '\n- bullet example'
    },
    appendOl () {
      this.inputText += '\n1. number example'
    },
    appendEmoji() {
      let emojis = [':wink:', ':cry:', ':laughing:', ':yum:'];
      this.inputText += '\n' + emojis[Math.floor(Math.random() * emojis.length)];
    }
  },
  watch: {
    inputText() {
      document.querySelector("#page").innerHTML = md.render(this.inputText.toString());
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

.editing-area.col .tool-box .tool-bar button {
  padding: 1% 1%;
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
  padding: 0 10%;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  border: solid 2.5px #999;
  -webkit-box-shadow: 5px 5px 10px -5px rgba(0,0,0,0.75);
  -moz-box-shadow: 5px 5px 10px -5px rgba(0,0,0,0.75);
  box-shadow: 5px 5px 10px -5px rgba(0,0,0,0.75);
}

.editing-area.col .slide-section .slide-content .text-input {
  position: absolute;
  width: 80%;
  bottom: 0;
}

* >>> blockquote {
  border-left: 8px solid #ccc;
  margin: 10px;
  padding: 10px;
}

</style>