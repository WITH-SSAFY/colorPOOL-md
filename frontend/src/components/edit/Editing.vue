<template>
  <div>
    <v-col class="editing-area">
      <div class="info-section">
        <div class="progress-bar"><progress-bar v-bind:page="2" /></div>
      </div>
      <component v-for="(template, index) in templates" v-bind:page="index" @enterNewPage="createPage()" @exitNewPage="deletePage()" :key="index" :is="template"></component>
    </v-col>
  </div>
</template>

<script>
  import EditPage from './EditPage'
  import ProgressBar from '../../components/header/ProgressBar'
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
      EditorMenuBar,
      ProgressBar
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
  }
</style>