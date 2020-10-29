<template>
    <div>
        <v-col class="editing-area">
            <div class="info-section">
                info-section
            </div>
            <div class="slide-section">
                <div class="editor">
                    <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
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
                    </editor-menu-bar>
                    <editor-content class="editor__content" :editor="editor"/>
                </div>
            </div>
        </v-col>
    </div>
</template>

<script>
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
        name: 'EditingKang',
        components: {
            EditorContent,
            EditorMenuBar
        },
        // beforeCreate() {
        //   var res = prompt("두유노 비밀번호?");
        //   if(res !== 'beaverbae123') {
        //     alert('아 나가요 ㅡㅡ');
        //     this.$router.push({name: 'Landing'});
        //   } else {
        //     alert('흠.. 사용하도록..')
        //   }
        // },
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
                })
            }
        },
        beforeDestroy() {
            this
                .editor
                .destroy()
        },
        methods: {},
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

    
</style>