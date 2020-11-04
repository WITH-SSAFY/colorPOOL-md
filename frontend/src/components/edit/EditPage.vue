<template>
  <div>
    <div class="slide-section">
      <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
        <div class="menu-box">
          <div class="menubar">
            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.bold() }"
              @click="commands.bold">
              <v-icon>mdi-format-bold</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.italic() }"
              @click="commands.italic">
              <v-icon>mdi-format-italic</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.strike() }"
              @click="commands.strike">
              <v-icon>mdi-format-strikethrough</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.underline() }"
              @click="commands.underline">
              <v-icon>mdi-format-underline</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.code() }"
              @click="commands.code">
              <v-icon>mdi-code-tags</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.paragraph() }"
              @click="commands.paragraph">
              <v-icon>mdi-format-paragraph</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.heading({ level: 1 }) }"
              @click="commands.heading({ level: 1 })">
              <v-icon>mdi-format-header-1</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.heading({ level: 2 }) }"
              @click="commands.heading({ level: 2 })">
              <v-icon>mdi-format-header-2</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.heading({ level: 3 }) }"
              @click="commands.heading({ level: 3 })">
              <v-icon>mdi-format-header-3</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.bullet_list() }"
              @click="commands.bullet_list">
              <v-icon>mdi-format-list-bulleted</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.ordered_list() }"
              @click="commands.ordered_list">
              <v-icon>mdi-format-list-numbered</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.blockquote() }"
              @click="commands.blockquote">
              <v-icon>mdi-format-quote-open</v-icon>
            </button>

            <button
              class="menubar__button"
              :class="{ 'is-active': isActive.code_block() }"
              @click="commands.code_block">
              <v-icon>mdi-code-not-equal-variant</v-icon>
            </button>

            <button class="menubar__button" @click="commands.horizontal_rule">
              <p style="font-size: 1.2rem; font-weight: bold; color: #666;">hr</p>
            </button>

            <button class="menubar__button" @click="commands.undo">
              <v-icon>mdi-undo-variant</v-icon>
            </button>

            <button class="menubar__button" @click="commands.redo">
              <v-icon>mdi-redo-variant</v-icon>
            </button>

            <button class="menubar__button" @click="showImagePrompt(commands.image)">
              <v-icon>mdi-image-outline</v-icon>
            </button>

            <button
              class="menubar__button"
              @click="commands.createTable({rowsCount: 3, colsCount: 3, withHeaderRow: false })">
              <v-icon>mdi-table-large</v-icon>
            </button>

            <br>

            <span v-if="isActive.table()">

              <button class="menubar__button" @click="commands.deleteTable">
                <v-icon>mdi-table-large-remove</v-icon>
              </button>

              <button class="menubar__button" @click="commands.addColumnBefore">
                <v-icon>mdi-table-column-plus-before</v-icon>
              </button>

              <button class="menubar__button" @click="commands.addColumnAfter">
                <v-icon>mdi-table-column-plus-after</v-icon>
              </button>

              <button class="menubar__button" @click="commands.deleteColumn">
                  <v-icon>mdi-table-column-remove</v-icon>
              </button>

              <button class="menubar__button" @click="commands.addRowBefore">
                  <v-icon>mdi-table-row-plus-before</v-icon>
              </button>

              <button class="menubar__button" @click="commands.addRowAfter">
                  <v-icon>mdi-table-row-plus-after</v-icon>
              </button>

              <button class="menubar__button" @click="commands.deleteRow">
                  <v-icon>mdi-table-row-remove</v-icon>
              </button>

              <button class="menubar__button" @click="commands.toggleCellMerge">
                  <v-icon>mdi-table-merge-cells</v-icon>
              </button>
            </span>

            <span v-if="isImage">
              <button class="menubar__button" @click="imageSize = 0">
                <v-icon>mdi-size-xs</v-icon>
              </button>
              <button class="menubar__button" @click="imageSize = 1">
                <v-icon>mdi-size-s</v-icon>
              </button>
              <button class="menubar__button" @click="imageSize = 2">
                <v-icon>mdi-size-m</v-icon>
              </button>
              <button class="menubar__button" @click="imageSize = 3">
                <v-icon>mdi-size-l</v-icon>
              </button>
              <button class="menubar__button" @click="imageSize = 4">
                <v-icon>mdi-size-xl</v-icon>
              </button>
            </span>
          </div>

          <v-btn
            color="blue-grey"
            class="btnImport ma-2 white--text"
            @click="clickBtn()"
          >
            Import MD
            <v-icon
              right
              dark
            >
              mdi-file-upload
            </v-icon>
          </v-btn>
          <input type="file" class="importMd" :class="'item' + page" accept="" @change="changeVal" style="visibility: hidden">

        </div>
      </editor-menu-bar>

      <section id="container" class="editor" :class="'item' + page" :style="{'height': this.height}">
        <editor-content v-focus class="editor__content" :class="'item' + page" :editor="editor"/>
        <div class="bottomSensor" :class="'item' + page"></div>
      </section>

      <div class="arrow">
        <div class="pre-arrow"></div>
        <div class="next-arrow"></div>
      </div>

    </div>
    <!-- 컬러 툴박스 (임시) -->
    <!-- <div class="toolbox" :class="[isToolBoxShow? 'show': '']">
      <button style="background-color : red" @click="red">red</button>
      <button style="background-color : blue" @click="blue">blue</button>
      <button style="background-color : green" @click="green">green</button>
    </div> -->
  </div>
</template>

<script>
  import scrollMonitor from 'scrollmonitor'
  import markdownIt from 'markdown-it'
  import {mapActions} from 'vuex'
  require('../../assets/LiveEditStyle.css')
  import {Editor, EditorContent, EditorMenuBar} from 'tiptap'
  import {
    Blockquote,
    CodeBlock,
    HardBreak,
    Heading,
    HorizontalRule,
    OrderedList,
    BulletList,
    ListItem,
    TodoItem,
    TodoList,
    Bold,
    Code,
    Italic,
    Link,
    Strike,
    Underline,
    History,
    Table,
    TableHeader,
    TableCell,
    TableRow,
    Image
  } from 'tiptap-extensions'
  
  const md = new markdownIt();
  const contentStore = 'contentStore';

  export default {
    name: 'EditPage',
    components: {
      EditorContent,
      EditorMenuBar
    },
    props: {
      page: {
        default: void 0
      }
    },
    data() {
      return {
        editor: new Editor({
          extensions: [
            new Blockquote(),
            new BulletList(),
            new CodeBlock(),
            new HardBreak(),
            new Heading({
                levels: [1, 2, 3]
            }),
            new HorizontalRule(),
            new ListItem(),
            new OrderedList(),
            new TodoItem(),
            new TodoList(),
            new Link(),
            new Bold(),
            new Code(),
            new Italic(),
            new Strike(),
            new Underline(),
            new History(),
            new Table(),
            new TableHeader(),
            new TableCell(),
            new TableRow(),
            new Image(),
          ],
          content: ''
        }),
        height: null,
        isNewPage: false,
        // isToolBoxShow: false,
        // isBlock: false,
        // target: null,
        // targetStr: '',
        isImage: false,
        imageSize: 0,
        img: null,
        isContentStored: false,
        handler: null,
        // isNewPageCreated: true,
      }
    },
    mounted() {
      window.addEventListener('resize', this.handleResize)
      // window.addEventListener('keydown', this.handleContent);
      this.handler = setInterval(() => {
        this.handleContent();
      }, 3000);

      this.loadUntilSlideIsFull();

      // 툴박스 mouseup 이벤트 리스너
      // document.addEventListener('mouseup', (e) => {
      //   let str = window.getSelection().toString();
      //   if(e.target.localName == 'button') {
      //     this.isToolBoxShow = false;
      //     return;
      //   }
      //   if(str.length == 0) {
      //     this.target = null;
      //     this.targetStr = '';
      //     this.isToolBoxShow = false;
      //   } else {
      //     this.target = e.target;
      //     this.targetStr = str;
      //     this.isBlock = true;
      //     let toolbox = document.querySelector('.toolbox');
      //     toolbox.style.left = (e.screenX) + 'px';
      //     toolbox.style.top = (e.screenY - 50) + 'px';
      //     this.isToolBoxShow = true;
      //   }
      // })

      // 이미지 이벤트 리스너
      document.addEventListener('click', (e) => {
        if(e.target.className.includes('mdi-size-') && this.img != null) {
          // 이미지에 대한 크기 변경 0 -> 1 -> 2 -> 3 -> 4
          console.log(this.imageSize, this.img);
          // let width = this.img.target.clientWidth;
          // let height = this.img.target.clientHeight;
          if(this.imageSize == 0) {
            this.img.target.style.width = '20%'
            this.img.target.style.height = '20%'
            // this.img.target.style.width = parseInt(width / 2);
            // this.img.target.style.height = parseInt(height / 2);
          } else if(this.imageSize == 1) {
            this.img.target.style.width = '40%'
            this.img.target.style.height = '40%'
            // this.img.target.style.width = parseInt(width / 1.5);
            // this.img.target.style.height = parseInt(height / 1.5);
          } else if(this.imageSize == 2) {
            this.img.target.style.width = '50%'
            this.img.target.style.height = '50%'
            // this.img.target.style.width = parseInt(width / 1.2);
            // this.img.target.style.height = parseInt(height / 1.2);
          } else if(this.imageSize == 3) {
            this.img.target.style.width = '80%'
            this.img.target.style.height = '80%'
            // this.img.target.style.width = width;
            // this.img.target.style.height = height;
          } else if(this.imageSize == 4) {
            this.img.target.style.width = '100%'
            this.img.target.style.height = '100%'
            // this.img.target.style.width = parseInt(width / 0.9);
            // this.img.target.style.height = parseInt(height / 0.9);
          }
          this.isImage = false;
          this.img = null;
          return;
        } 
        if(e.target.localName != 'img') {
          this.isImage = false;
          return;
        }
        this.isImage = true;
        this.img = e;
      })
    },
    beforeDestroy() {
      this
        .editor
        .destroy()
      window.removeEventListener('resize', this.handleResize)
      clearInterval(this.handler);
    },
    methods: {
      ...mapActions(contentStore, ['AC_CONTENTS']),
      handleResize() {
        this.height = document.querySelector("#container").clientHeight
      },
      handleContent () {
        // if(this.isContentStored) return;
        // this.isContentStored = true;
        const payload = {
          page: this.page,
          content: document.querySelector('.editor__content.item' + this.page +' .ProseMirror').innerHTML
        }
        this.AC_CONTENTS(payload);
        // setTimeout(() => {this.isContentStored = false}, 3000);
      },
      loadUntilSlideIsFull: function () {
        if(this.isNewPage) return;
        document.querySelector("#container").style.height = this.height;

        const containerElement = document.querySelector(".editor.item" +this.page)
        const containerMonitor = scrollMonitor.createContainer(containerElement)
        
        const bottomSensor = document.querySelector(".bottomSensor.item" + this.page)
        const watcher = containerMonitor.create(bottomSensor)

        watcher.enterViewport(() => {
          console.log('____BOTTOMENTER____' + this.page)
          // this.isNewPageCreated = true;
          if(!this.isNewPage) this.$emit('enterNewPage')
          this.isNewPage = true;
        })
        watcher.exitViewport(() => {
          console.log('____BOTTOMEXIT____' + this.page)
          // if(this.isNewPageCreated) return;
          // else this.isNewPageCreated = true;
          if(this.isNewPage) this.$emit('exitNewPage')
          this.isNewPage = false;
        })
      },
      red() {
        if(this.isBlock) {
          console.log(this.target);
          console.log(this.target.innerHTML);
          console.log(this.targetStr)
          this.target.innerHTML = this.target.innerHTML.replace(this.targetStr, '<a style="color: red">' + this.targetStr + '</a>')
        }
        this.isBlock = false;
        this.isToolBoxShow = false;
      },
      blue() {
        if(this.isBlock) {
          this.target.innerHTML = this.target.innerHTML.replace(this.targetStr, '<a style="color: blue">' + this.targetStr + '</a>')
        }
        this.isBlock = false;
        this.isToolBoxShow = false;
      },
      green() {
        if(this.isBlock) {
          this.target.innerHTML = this.target.innerHTML.replace(this.targetStr, '<a style="color: green">' + this.targetStr + '</a>')
        }
        this.isBlock = false;
        this.isToolBoxShow = false;
      },
      async changeVal(val) {
        let file = val.target.files[0]
        var reader = new FileReader();
        
      
        reader.readAsText(file, /* optional */ "utf-8");

        const result = await new Promise((resolve) => {
          reader.onload = function(event) {
            console.log(event);
            resolve(md.render(reader.result))
          }
        })
        this.editor.content = result;
        // console.log(document.querySelector('.editor__content.item' + this.page + ' .ProseMirror').innerHTML)
        document.querySelector('.editor__content.item' + this.page + ' .ProseMirror').innerHTML = result;
        console.log(document.querySelector('.editor__content.item' + this.page + ' .ProseMirror').innerHTML)
      },
      clickBtn() {
        document.querySelector('.importMd.item' + this.page).click();
        this.loader = 'loading3'
      },
      showImagePrompt(command) {
        const src = prompt('Enter the url of your image here')
        if (src !== null) {
          command({ src })
        }
      }
    },
    directives: {
      focus: {
        inserted (el) {
          setTimeout(() => {
            el.firstChild.focus();
            el.scrollIntoView({behavior: "smooth"});
          }, 10);
        }
      }
    },
    computed: {
    },
    watch: {
    }
    // watch: {
    //   isNewPage (val) {
    //     if (val) {
    //       setTimeout(() => {
    //         this.$refs.input.focus();
    //       }, 10);
    //     }
    //   }
    // }
  }
</script>

<style scoped>
  .slide-section .arrow {
    display: flex;
    margin: 0 3.5%;
    width: 93%;
    justify-content: space-between;
  }

  .slide-section .arrow .pre-arrow {
    width: 0; 
    height: 0;
    position: fixed;
    top: 50%;
    border-top: 2rem solid transparent;
    border-bottom: 2rem solid transparent; 
    border-right: 2rem solid #424242;
    cursor: pointer;
  }

  .slide-section .arrow .next-arrow {
    width: 0; 
    height: 0;
    position: fixed;
    top: 50%;
    right: 3.5%;
    border-top: 2rem solid transparent;
    border-bottom: 2rem solid transparent;
    border-left: 2rem solid #424242;
    cursor: pointer;
  }

  .slide-section .menu-box {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 10vh;
    width: 100%;
    margin-left: 7.5%;
  }

  .slide-section .menu-box .menubar {
    width: 100%;
  }

  .slide-section .menu-box .menubar button {
    margin: 0 0.5%;
  }

  .slide-section .editor {
    position: relative;
    margin-left: 7.5%;
    width: 85%;
    overflow-y: scroll;
  }
  
  .slide-section .editor:before {
    content: "";
    display: block;
    padding-top: 56.25%;
  }

  .slide-section .editor__content {
    position: absolute;
    background-color: #fff;
    padding: 5% 10%;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    border: solid 2.5px #999;
    -webkit-box-shadow: 5px 5px 10px -5px rgba(0,0,0,0.75);
    -moz-box-shadow: 5px 5px 10px -5px rgba(0,0,0,0.75);
    box-shadow: 5px 5px 10px -5px rgba(0,0,0,0.75);
  }

  .v-btn {
    font-size: 1.1rem;
    font-weight: bold;
  }

  /* =================== TOOL BOX COLOR =================== */
  .toolbox {
    position: absolute;
    width: 200px;
    height: 50px;
    /* visibility: hidden; */
    opacity: 0;
    transition-duration: 300ms;
  }

  .toolbox.show {
    /* visibility: visible; */
    opacity: 1;
    transition-duration: 300ms;
  }

  * >>> blockquote {
    border-left: 8px solid #ccc;
    margin: 10px;
    padding: 10px;
  }

  .btnImport {
    
  }
  /* ====================================================== */

</style>