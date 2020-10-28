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
        methods: {}
    }
</script>

<style>
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

    /* 
============== added CSS by Seeung Kang ==============
  - USED FOR MARKDWON LIVE EDIT
  - DESIGN REQUIRES CHANGE
======================================================
 */

    * {
        margin: 0;
        padding: 0;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        -webkit-text-size-adjust: 100%;
        -moz-text-size-adjust: 100%;
        text-size-adjust: 100%;
        -webkit-tap-highlight-color: rgba(0,0,0,0);
        -webkit-touch-callout: none;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-rendering: optimizeLegibility;
    }

    :focus {
        outline: none;
    }

    :after,
    :before {
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }

    html {
        font-family: Inter;
        font-size: 18px;
        color: #000;
        line-height: 1.5;
    }

    body {
        margin: 0;
    }

    a {
        color: inherit;
    }

    blockquote,
    h1,
    h2,
    h3,
    ol,
    p,
    pre,
    hr,
    ul {
        margin: 1rem 0;
    }

    blockquote:first-child,
    h1:first-child,
    h2:first-child,
    h3:first-child,
    ol:first-child,
    p:first-child,
    pre:first-child,
    ul:first-child {
        margin-top: 0;
    }

    blockquote:last-child,
    h1:last-child,
    h2:last-child,
    h3:last-child,
    ol:last-child,
    p:last-child,
    pre:last-child,
    ul:last-child {
        margin-bottom: 0;
    }

    h1,
    h2,
    h3 {
        line-height: 1.3;
    }

    .button {
        font-weight: 700;
        display: -webkit-inline-box;
        display: inline-flex;
        background: transparent;
        border: 0;
        color: #000;
        padding: 0.2rem 0.5rem;
        margin-right: 0.2rem;
        border-radius: 3px;
        cursor: pointer;
        background-color: rgba(0,0,0,.1);
    }

    .button:hover {
        background-color: rgba(0,0,0,.15);
    }

    .message {
        background-color: rgba(0,0,0,.05);
        color: rgba(0,0,0,.7);
        padding: 1rem;
        border-radius: 6px;
        margin-bottom: 1.5rem;
        font-style: italic;
    }

    .editor {
        position: relative;
        max-width: 50rem;
        margin: 0 auto 5rem;
    }

    .editor__content {
        overflow-wrap: break-word;
        word-wrap: break-word;
        word-break: break-word;
    }

    .editor__content * {
        caret-color: currentColor;
    }

    .editor__content pre {
        padding: 0.7rem 1rem;
        border-radius: 5px;
        background: #000;
        color: #fff;
        font-size: 0.8rem;
        overflow-x: auto;
    }

    .editor__content pre code {
        display: block;
    }

    .editor__content p code {
        padding: 0.2rem 0.4rem;
        border-radius: 5px;
        font-size: 0.8rem;
        font-weight: 700;
        background: rgba(0,0,0,.1);
        color: rgba(0,0,0,.8);
    }

    .editor__content ol,
    .editor__content ul {
        padding-left: 1rem;
    }

    .editor__content li > ol,
    .editor__content li > p,
    .editor__content li > ul {
        margin: 0;
    }

    .editor__content a {
        color: inherit;
    }

    .editor__content blockquote {
        border-left: 3px solid rgba(0,0,0,.1);
        color: rgba(0,0,0,.8);
        padding-left: 0.8rem;
        font-style: italic;
    }

    .editor__content blockquote p {
        margin: 0;
    }

    .editor__content img {
        max-width: 100%;
        border-radius: 3px;
    }

    .editor__content table {
        border-collapse: collapse;
        table-layout: fixed;
        width: 100%;
        margin: 0;
        overflow: hidden;
    }

    .editor__content table td,
    .editor__content table th {
        min-width: 1em;
        border: 2px solid #ddd;
        padding: 3px 5px;
        vertical-align: top;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        position: relative;
    }

    .editor__content table td > *,
    .editor__content table th > * {
        margin-bottom: 0;
    }

    .editor__content table th {
        font-weight: 700;
        text-align: left;
    }

    .editor__content table .selectedCell:after {
        z-index: 2;
        position: absolute;
        content: "";
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        background: rgba(200,200,255,.4);
        pointer-events: none;
    }

    .editor__content table .column-resize-handle {
        position: absolute;
        right: -2px;
        top: 0;
        bottom: 0;
        width: 4px;
        z-index: 20;
        background-color: #adf;
        pointer-events: none;
    }

    .editor__content .tableWrapper {
        margin: 1em 0;
        overflow-x: auto;
    }

    .editor__content .resize-cursor {
        cursor: ew-resize;
        cursor: col-resize;
    }

    .menubar {
        margin-bottom: 1rem;
        -webkit-transition: visibility 0.2s 0.4s,opacity 0.2s 0.4s;
        transition: visibility 0.2s 0.4s,opacity 0.2s 0.4s;
    }

    .menubar.is-hidden {
        visibility: hidden;
        opacity: 0;
    }

    .menubar.is-focused {
        visibility: visible;
        opacity: 1;
        -webkit-transition: visibility 0.2s,opacity 0.2s;
        transition: visibility 0.2s,opacity 0.2s;
    }

    .menubar__button {
        font-weight: 700;
        display: -webkit-inline-box;
        display: inline-flex;
        background: transparent;
        border: 0;
        color: #000;
        padding: 0.2rem 0.5rem;
        margin-right: 0.2rem;
        border-radius: 3px;
        cursor: pointer;
    }

    .menubar__button:hover {
        background-color: rgba(0,0,0,.05);
    }

    .menubar__button.is-active {
        background-color: rgba(0,0,0,.1);
    }

    .menubar span.menubar__button {
        font-size: 13.3333px;
    }

    .menububble {
        position: absolute;
        display: -webkit-box;
        display: flex;
        z-index: 20;
        background: #000;
        border-radius: 5px;
        padding: 0.3rem;
        margin-bottom: 0.5rem;
        -webkit-transform: translateX(-50%);
        transform: translateX(-50%);
        visibility: hidden;
        opacity: 0;
        -webkit-transition: opacity 0.2s,visibility 0.2s;
        transition: opacity 0.2s,visibility 0.2s;
    }

    .menububble.is-active {
        opacity: 1;
        visibility: visible;
    }

    .menububble__button {
        display: -webkit-inline-box;
        display: inline-flex;
        background: transparent;
        border: 0;
        color: #fff;
        padding: 0.2rem 0.5rem;
        margin-right: 0.2rem;
        border-radius: 3px;
        cursor: pointer;
    }

    .menububble__button:last-child {
        margin-right: 0;
    }

    .menububble__button:hover {
        background-color: hsla(0,0%,100%,.1);
    }

    .menububble__button.is-active {
        background-color: hsla(0,0%,100%,.2);
    }

    .menububble__form {
        display: -webkit-box;
        display: flex;
        -webkit-box-align: center;
        align-items: center;
    }

    .menububble__input {
        font: inherit;
        border: none;
        background: transparent;
        color: #fff;
    }

    .ProseMirror {
        position: relative;
    }

    .ProseMirror {
        word-wrap: break-word;
        white-space: pre-wrap;
        -webkit-font-variant-ligatures: none;
        font-variant-ligatures: none;
    }

    .ProseMirror pre {
        white-space: pre-wrap;
    }

    .ProseMirror-gapcursor {
        display: none;
        pointer-events: none;
        position: absolute;
    }

    .ProseMirror-gapcursor:after {
        content: "";
        display: block;
        position: absolute;
        top: -2px;
        width: 20px;
        border-top: 1px solid black;
        animation: ProseMirror-cursor-blink 1.1s steps(2, start) infinite;
    }

    @keyframes ProseMirror-cursor-blink {
        to {
            visibility: hidden;
        }
    }

    .ProseMirror-hideselection *::selection {
        background: transparent;
    }

    .ProseMirror-hideselection *::-moz-selection {
        background: transparent;
    }

    .ProseMirror-hideselection * {
        caret-color: transparent;
    }

    .ProseMirror-focused .ProseMirror-gapcursor {
        display: block;
    }
</style>