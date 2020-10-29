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
              hr
            </button>

            <button class="menubar__button" @click="commands.undo">
              <v-icon>mdi-undo-variant</v-icon>
            </button>

            <button class="menubar__button" @click="commands.redo">
              <v-icon>mdi-redo-variant</v-icon>
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
          </div>

          <v-btn
            color="blue-grey"
            class="ma-2 white--text"
            @click="loader = 'loading3'"
          >
            Import MD
            <v-icon
              right
              dark
            >
              mdi-file-upload
            </v-icon>
          </v-btn>

        </div>

      </editor-menu-bar>

      <div id="container" class="editor" :style="{'height': this.height}">
        <editor-content class="editor__content" :editor="editor"/>
        <div id="bottomSensor"></div>
      </div>

      <!-- <div class="arrow">
        <div class="pre-arrow"></div>
        <div class="next-arrow"></div>
      </div> -->

    </div>
  </div>
</template>

<script>
  import scrollMonitor from 'scrollmonitor'
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
    TableRow
  } from 'tiptap-extensions'

  export default {
    name: 'Editing',
    components: {
      EditorContent,
      EditorMenuBar
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
            new TableRow()
          ],
          content: ''
        }),
        height: null,
        templates: [],
      }
    },
    mounted() {
      window.addEventListener('resize', this.handleResize)
      this.loadUntilSlideIsFull()
    },
    beforeDestroy() {
      this
        .editor
        .destroy()
      window.removeEventListener('resize', this.handleResize)
    },
    methods: {
      handleResize() {
        this.height = document.querySelector("#container").clientHeight
      },
      loadUntilSlideIsFull: function () {
        document.querySelector("#container").style.height = this.height;

        const containerElement = document.querySelector("#container")
        const containerMonitor = scrollMonitor.createContainer(containerElement)

        const bottomSensor = document.querySelector("#bottomSensor")
        const watcher = containerMonitor.create(bottomSensor)

        watcher.enterViewport(() => {
          console.log('____BOTTOMENTER____')
          // TODO : 새로운 창 만들기
          const node = document.querySelector('#container')
          node.insertAdjacentHTML('afterend', `<div id="container" class="editor" style="height: 400px; background-color: skyblue;">
          <editor-content class="editor__content" :editor="editor"/>
          <div id="bottomSensor"></div>
        </div>`);
        })  
        watcher.exitViewport(() => {
          console.log('____BOTTOMEXIT____')
          // TODO : 다음 창의 내용이 빈 값일 경우 삭제하기
        })
      }
    },
  }
</script>

<style scoped>
  .slide-section {
    background-color: rgba(238, 238, 238, 0.7);
  }

  .slide-section .arrow {
    display: flex;
    margin: 0 3.5%;
    width: 93%;
    justify-content: space-between;
  }

  .slide-section .arrow .pre-arrow {
    width: 0; 
    height: 0;
    border-top: 2rem solid transparent;
    border-bottom: 2rem solid transparent; 
    border-right: 2rem solid blue; 
  }

  .slide-section .arrow .next-arrow {
    width: 0; 
    height: 0; 
    border-top: 2rem solid transparent;
    border-bottom: 2rem solid transparent;
    border-left: 2rem solid blue;
  }

  .slide-section .menu-box {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 10vh;
    width: 85%;
    margin-left: 7.5%;
  }

  .slide-section .menu-box .menubar {
    width: 70%;
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
    /* overflow: scroll; */
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

</style>