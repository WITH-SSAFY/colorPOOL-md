<template>
  <div>
    <v-col class="editing-area">
      <div class="info-section">
        info-section
      </div>
      <component v-for="(template, index) in templates" v-bind:page="index" @enterNewPage="createPage()" @exitNewPage="deletePage()" :key="index" :is="template"></component>
      <!-- <div class="arrow">
        <div class="pre-arrow"></div>
        <div class="next-arrow"></div>
      </div> -->
    </v-col>
  </div>
</template>

<script>
  import EditPage from './EditPage'
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
    created() {
      this.templates.push(EditPage)
    },
    beforeDestroy() {
      this
        .editor
        .destroy()
    },
    methods: {
      createPage() {
        this.templates.push(EditPage)
      },
      deletePage() {
        this.templates.pop(EditPage)
      }
    },
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
    background-color: rgba(238, 238, 238, 0.7);
  }

  .editing-area.col .slide-section .arrow {
    display: flex;
    margin: 0 3.5%;
    width: 93%;
    justify-content: space-between;
  }

  .editing-area.col .slide-section .arrow .pre-arrow {
    width: 0; 
    height: 0;
    border-top: 2rem solid transparent;
    border-bottom: 2rem solid transparent; 
    border-right: 2rem solid blue; 
  }

  .editing-area.col .slide-section .arrow .next-arrow {
    width: 0; 
    height: 0; 
    border-top: 2rem solid transparent;
    border-bottom: 2rem solid transparent;
    border-left: 2rem solid blue;
  }

  .editing-area.col .slide-section .menu-box {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 10vh;
    width: 85%;
    margin-left: 7.5%;
  }

  .editing-area.col .slide-section .menu-box .menubar {
    width: 70%;
  }

  .editing-area.col .slide-section .menu-box .menubar button {
    margin: 0 0.5%;
  }

  .editing-area.col .slide-section .editor {
    position: relative;
    margin-left: 7.5%;
    width: 85%;
    overflow-y: scroll;
  }
  
  .editing-area.col .slide-section .editor:before {
    content: "";
    display: block;
    padding-top: 56.25%;
  }

  .editing-area.col .slide-section .editor__content {
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

</style>