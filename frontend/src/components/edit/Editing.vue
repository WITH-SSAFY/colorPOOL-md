<template>
  <div>
    <div class="slide-section">
      
      <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
        
        <div class="menu-box">
          <div class="menubar">
            <span v-if="storePage == 0">
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

            <button class="menubar__button" @click="clickPhotoUpload(commands.image)">
              <v-icon>mdi-image</v-icon>
            </button>

            <button
              class="menubar__button"
              @click="commands.createTable({rowsCount: 3, colsCount: 3, withHeaderRow: false })">
              <v-icon>mdi-table-large</v-icon>
            </button>

            <input type="file" accept="image/gif, image/jpeg, image/png" class="photo_upload" :class="'item' + page" style="visibility: hidden;">
            </span>

            <span v-if="storePage == 1">
              <br>
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
            

            <span v-if="storePage == 0 && isActive.table()">
              <br>
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

            <span v-if="storePage == 0 && isImage">
              <br>
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
      <div style="width: 100%; display: flex;">
        <section id="container" class="editor" :class="'item' + page" :style="{'height': this.height}">
          <editor-content v-focus class="editor__content" :class="'item' + page" :editor="editor"/>
          <div class="bottomSensor" :class="'item' + page"></div>
        </section>
        <div class="background-box" v-if="storePage == 1">
          <button style="background-color: black;" @click="changeBackground('black')"></button>
          <button style="background-color: white;" @click="changeBackground('white')"></button>
          <button v-for="(color, index) in colors" :key="index" :style="{'background-color' : color}" @click="changeBackground(color)"></button>
        </div>
      </div>
      <div class="arrow" v-if="page == 0">
        <div class="pre-arrow" v-if="storePage == 1" @click="goEdit" ></div>
        <div class="next-arrow" v-if="storePage == 0" @click="goColor"></div>
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
  import hljs from 'highlight.js/lib/core'
  import 'highlight.js/styles/github.css'
  import {mapActions, mapGetters} from 'vuex'
  require('../../assets/LiveEditStyle.css')
  import {Editor, EditorContent, EditorMenuBar} from 'tiptap'
  import CustomStyle from "../../assets/CustomStyle";
  import axios from '../../api/axiosCommon'

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
    Image,
    // 코드 하이라이팅
    CodeBlockHighlight
  } from 'tiptap-extensions'
  
  import javascript from 'highlight.js/lib/languages/javascript'
  import css from 'highlight.js/lib/languages/css'

  const md = new markdownIt();
  const contentStore = 'contentStore';
  const customStore = 'customStore'
  const boxStore = 'boxStore';
  const editStore = 'editStore';

  export default {
    name: 'EditPage',
    components: {
      EditorContent,
      EditorMenuBar
    },
    props: {
      page: {
        default: void 0
      },
      content_parent: {
        default: void 0
      }
    },
    data() {
      return {
        editor: new Editor({
          extensions: [
            new CustomStyle(),
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
            // 코드 하이라이팅
            new CodeBlockHighlight({
              languages: {
                javascript,
                css,
              },
            }),
          ],
          content: this.content_parent
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
        /* 색깔 지정 변수들 */
        colors: [],
        isToolBoxShow: false,
        target: null,
        command: null,
        // targetStr: '',
        // isTotal: false,
        // targetParent: null,


        // target: null,
        // targetStr: '',
        // isTotal: false,
        // targetParent: null,
      }
    },
    computed: {
      ...mapGetters(customStore, {storeFinalTheme: 'GE_FINAL_THEME'}),
      ...mapGetters(editStore, {storePage: 'GE_PAGE'})
    },
    created() {
      this.colors = this.storeFinalTheme;
      hljs.initHighlightingOnLoad()
    },
    mounted() {
      // console.log(document.querySelector('.toolbox.item' + this.page));
      // this.editor.setContent(this.content_parent)
      document.querySelector('.photo_upload.item' + this.page).addEventListener('change', (e) => {
        console.log(e);
        this.readUploadImage(e);
      })

      window.addEventListener('resize', this.handleResize)
      this.handler = setInterval(() => {
        this.handleContent();
      }, 3000);


      this.loadUntilSlideIsFull();

      // 컬러 이벤트 리스너
      // document.addEventListener('click', this.handleColor);

      // 이미지 이벤트 리스너
      
      document.addEventListener('click', (e) => {
        /*
        if(window.getSelection().toString().length == 0) {
          // this.isToolBoxShow = false;
          // document.querySelector('.toolbox.item' + this.page).classList.remove('show')
          this.target = null;
        }
        else {

          // let toolbox = document.querySelector('.toolbox');
          // toolbox.style.left = (e.pageX - 100) + 'px';
          // toolbox.style.top = (e.pageY - 60) + 'px';


          // this.isToolBoxShow = true;
          // document.querySelector('.toolbox.item' + this.page).classList.add('show')
          if(e.target.localName != 'button') {
            this.target = e;
          } else {
            // this.isToolBoxShow = false;
            // document.querySelector('.toolbox.item' + this.page).classList.remove('show')
            console.log(this.target);
            if(this.target.target.className == 'span') {
              let name = this.target.target.className;
              if(name == 'color1') this.target.target.style.color = this.colors[0];
              else if(name == 'color2') this.target.target.style.color = this.colors[1];
              else if(name == 'color3') this.target.target.style.color = this.colors[2];
              else if(name == 'color4') this.target.target.style.color = this.colors[3];
              else if(name == 'color5') this.target.target.style.color = this.colors[4];
              this.target.target.className = '';
            } else {
              this.target.target.children.forEach((child) => {
              if(child.className != '') {
                if(child.className == 'color1') child.style.color = this.colors[0];
                else if(child.className == 'color2') child.style.color = this.colors[1];
                else if(child.className == 'color3') child.style.color = this.colors[2];
                else if(child.className == 'color4') child.style.color = this.colors[3];
                else if(child.className == 'color5') child.style.color = this.colors[4];
                child.className = "";
              }
              
            })
            }
            
            
            this.target = null;
          }
          
        } 
        */
        if(e.target.className.includes('mdi-size-') && this.img != null) {
          // 이미지에 대한 크기 변경 0 -> 1 -> 2 -> 3 -> 4
          if(this.imageSize == 0) {
            this.img.target.style.width = '20%'
            this.img.target.style.height = '20%'
          } else if(this.imageSize == 1) {
            this.img.target.style.width = '40%'
            this.img.target.style.height = '40%'
          } else if(this.imageSize == 2) {
            this.img.target.style.width = '50%'
            this.img.target.style.height = '50%'
          } else if(this.imageSize == 3) {
            this.img.target.style.width = '80%'
            this.img.target.style.height = '80%'
          } else if(this.imageSize == 4) {
            this.img.target.style.width = '100%'
            this.img.target.style.height = '100%'
          }
          this.isImage = false;
          this.img = null;
          return;
        } 
        if(e.target.localName == 'img') {
          this.isImage = true;
          this.img = e;
        } else {
          this.isImage = false;
        }
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
      ...mapActions(boxStore, ['AC_IS_BOX', 'AC_TARGET']),
      ...mapActions(editStore, ['AC_PAGE']),
      handleResize() {
        this.height = document.querySelector("#container").clientHeight
      },
      handleContent () {
        // if(this.isContentStored) return;
        // this.isContentStored = true;
        // console.log(document.querySelector('.editor__content.item' + this.page).innerHTML);
        // console.log(document.querySelector('.editor__content.item' + this.page).style.backgroundColor);
        let background = document.querySelector('.editor__content.item' + this.page).style.backgroundColor;
        if(background == '') background = 'white'
        const payload = {
          page: this.page,
          content: "<div style='background-color : " + background + "'>" + document.querySelector('.editor__content.item' + this.page +' .ProseMirror').innerHTML + '</div>'
        }
        this.AC_CONTENTS(payload);
        // setTimeout(() => {this.isContentStored = false}, 3000);
      },
      // handleColor(e) {
      //   if(!e.target.parentElement.className.includes('ProseMirror')) {
      //     this.target = null;
      //     this.targetStr = '';
      //     this.AC_IS_BOX(false);
      //     return;
      //   }
      //   // 두 가지 경우 ( 드래그해서 window.getSelection 하는 경우 )
      //   // 나머지 경우 ( ToolBox에서 색을 선택한 경우 => Editing에서 색깔을 바꿔줌 -> storeColor를 가져옴 -> 색깔 지정)
      //   // if(e.target.offsetParent == null || !e.target.offsetParent.className.includes('editor')) {
      //   //   // 클릭한 곳이 부모가 없거나 || editor 내부가 아닌 곳을 선택한 경우?
      //   //   this.target = null;
      //   //   this.targetStr = '';
      //   //   this.AC_IS_BOX(false);
      //   //   return;
      //   // }
      //   // 드래그해서 블록 처리된 스트링
      //   let str = window.getSelection().toString();
      //   if(str.length == 0) {
      //     this.target = null;
      //     this.targetStr = '';
      //     this.AC_IS_BOX(false);
      //   } else {
      //     e.target.focus();
      //     if(e.target.innerHTML == str && e.target.parentElement.localName == 'blockquote') {
      //       this.isTotal = true;
      //       this.targetParent = e.target.parentElement;
      //     }
      //     let toolbox = document.querySelector('.toolbox');
      //     toolbox.style.left = (e.pageX - 100) + 'px';
      //     toolbox.style.top = (e.pageY - 60) + 'px';
      //     this.target = e.target;
      //     this.targetStr = str;
      //     this.AC_IS_BOX(true);
      //   }
      // },
      // handleColor(e) {
      //    if(e.target.offsetParent == null) {
      //     this.target = null;
      //     this.targetStr = '';
      //     this.isToolBoxShow = false;
      //     return;
      //   }
      //   if(!e.target.offsetParent.className.includes('editor')) {
      //     this.target = null;
      //     this.targetStr = '';
      //     this.isToolBoxShow = false;
      //     return;
      //   }
        
      //   let str = window.getSelection().toString();
      //   if(str.length == 0) {
      //     this.target = null;
      //     this.targetStr = '';
      //     this.isToolBoxShow = false;
      //   } else {
      //     e.target.focus();
      //     if(e.target.innerHTML == str && e.target.parentElement.localName == 'blockquote') {
      //       this.isTotal = true;
      //       this.targetParent = e.target.parentElement;
      //     }
                  
      //     let toolbox = document.querySelector('.toolbox');
      //     toolbox.style.left = (e.pageX - 100) + 'px';
      //     toolbox.style.top = (e.pageY - 60) + 'px';
      //     this.target = e.target;
      //     this.targetStr = str;
      //     this.isToolBoxShow = true;
      //   }
      // },
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
        
        document.querySelector('.editor__content.item' + this.page + ' .ProseMirror').innerHTML = result;
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
      },
      changeBackground(color) {
        document.querySelector('.editor__content.item' + this.page).style.backgroundColor = color;
      },
      goEdit() {
        this.AC_PAGE(0);
      },
      goColor() {
        this.AC_PAGE(1);
      },
      clickPhotoUpload (command) {
        // console.log(document.querySelector('.photo_upload.item' + this.page))
        document.querySelector('.photo_upload.item' + this.page).click();
        this.command = command;
      },
      readUploadImage( inputObject ) {
        console.log(inputObject)
        var formData = new FormData();
        formData.append('imageMultipartFile', inputObject.target.files[0])
        axios.post('/image', formData, { headers: {accpet: 'application/json'}})
        .then(res => {
          console.log(res)
          let src = res.data.data
          this.command({ src })
        })
        .catch(err => console.log(err))
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
    watch: {
      storePage() {
        document.querySelector('.menubar').classList.add('active')
        setTimeout(() => {
          document.querySelector('.menubar').classList.remove('active')
        }, 250);
      }
    }
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

  .menubar {
    transition-duration: 250ms;
    transform: translateX(0em);
    opacity: 1;
  }

  .menubar.active {
    transition-duration: 0s;
    transform: translateX(5em);
    opacity: 0;
  }


  .slide-section .menu-box .menubar button {
    margin: 0 0.5%;
  }

  .slide-section section {
    overflow: hidden;
    min-width: 800px;
  }

  .slide-section .editor {
    position: relative;
    margin-left: 7.5%;
    width: 85%;
    /* 강세응이 추가함 */
    float: left;
    margin-right: 0;
    /* 강세응이 추가함 */
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

  .toolbox-btn {
    width: 30px;
    height: 30px;
    /* width: 20%; 
    height: 100%;  */
    border-radius: 70%;
    border: 2px solid slategray;
  }

  * >>> blockquote {
    border-left: 8px solid #ccc;
    margin: 10px;
    padding: 10px;
  }


  .btnImport {
    
  }
  
  /* ====================================================== */

  /* =================== BACKGROUND BOX =================== */

  .background-box {
    /* position: absolute; */
    /* margin-top: 100px; */
    float: right;
    margin-left: -2px;
    /* left: 85%; */
    z-index: 95;
    width: 80px;
    height: 280px;
  }

  .background-box button {
    width: 100%;
    height: 40px;
    border: 3px solid slategray;
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
    border-left: none;
  }

  /* ====================================================== */

</style>

<style lang="scss">
  @import '~highlight.js/styles/github.css';
  // @import '~highlight.js/styles/atom-one-dark.css';
  pre {
    &::before {
      content: attr(data-language);
      text-transform: uppercase;
      display: block;
      text-align: right;
      font-weight: bold;
      font-size: 0.6rem;
    }
    code {
      .hljs-comment,
      .hljs-quote {
        color: #999999;
      }
      .hljs-variable,
      .hljs-template-variable,
      .hljs-attribute,
      .hljs-tag,
      .hljs-name,
      .hljs-regexp,
      .hljs-link,
      .hljs-name,
      .hljs-selector-id,
      .hljs-selector-class {
        color: #f2777a;
      }
      .hljs-number,
      .hljs-meta,
      .hljs-built_in,
      .hljs-builtin-name,
      .hljs-literal,
      .hljs-type,
      .hljs-params {
        color: #f99157;
      }
      .hljs-string,
      .hljs-symbol,
      .hljs-bullet {
        color: #99cc99;
      }
      .hljs-title,
      .hljs-section {
        color: #ffcc66;
      }
      .hljs-keyword,
      .hljs-selector-tag {
        color: #6699cc;
      }
      .hljs-emphasis {
        font-style: italic;
      }
      .hljs-strong {
        font-weight: 700;
      }
    }
  }
</style>