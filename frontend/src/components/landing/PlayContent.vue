<template>
  <div class="container">
    <div data-mode="dark" class="page-container">
      <div class="page-content">
        <section>
          <h1>
            <div mode="out-in" class="content-line">
              <div class="content-word color-primary">
                <span class="color color-3">W</span>
                <span class="color color-4">e</span>
                <span class="color color-5">'</span>
                <span class="color color-1">r</span>
                <span class="color color-2">e</span>
              </div>
              <div class="content-word color-primary">
                <span class="color color-2">c</span>
                <span class="color color-3">o</span>
                <span class="color color-4">l</span>
                <span class="color color-5">o</span>
                <span class="color color-1">r</span>
                <span class="color color-2">P</span>
                <span class="color color-3">O</span>
                <span class="color color-4">O</span>
                <span class="color color-5">L</span>
                <span class="color color-1">.</span>
                <span class="color color-2">m</span>
                <span class="color color-3">d</span>
              </div>
              <div class="content-word color-primary">
                <span class="color color-4">!</span>
              </div>
            </div>
            <hr style="0.5px solid">
            <blockquote>
              <div mode="out-in" class="content-line">
                <div class="content-word color-secondary">
                  <span class="color color-2">C</span>
                  <span class="color color-3">r</span>
                  <span class="color color-4">e</span>
                  <span class="color color-5">a</span>
                  <span class="color color-1">t</span>
                  <span class="color color-2">e</span>
                </div>
                <div class="content-word color-secondary">
                  <span class="color color-2">y</span>
                  <span class="color color-3">o</span>
                  <span class="color color-4">u</span>
                  <span class="color color-5">r</span>
                </div>
                <div class="content-word color-secondary">
                  <samp>
                    <span class="color color-1">M</span>
                    <span class="color color-2">a</span>
                    <span class="color color-3">r</span>
                    <span class="color color-4">k</span>
                    <span class="color color-5">d</span>
                    <span class="color color-1">o</span>
                    <span class="color color-2">w</span>
                    <span class="color color-3">n</span>
                    <!-- <span class="color color-4">-</span> -->
                    <span class="color color-1">S</span>
                    <span class="color color-2">l</span>
                    <span class="color color-3">i</span>
                    <span class="color color-4">d</span>
                    <span class="color color-5">e</span>
                  </samp>
                </div>
                <div class="content-word color-secondary">
                  <span class="color color-2">w</span>
                  <span class="color color-3">i</span>
                  <span class="color color-4">t</span>
                  <span class="color color-5">h</span>
                </div>
                <div class="content-word color-secondary">
                  <!-- <span class="color color-1">y</span> -->
                  <span class="color color-2">o</span>
                  <span class="color color-3">u</span>
                  <span class="color color-4">r</span>
                </div>
                <div class="content-word color-secondary">
                  <!-- <v-icon size="1em" class="color color-1">mdi-arrow-right</v-icon> -->
                  <em>
                    <span class="color color-5">p</span>
                    <span class="color color-1">a</span>
                    <span class="color color-2">l</span>
                    <span class="color color-3">e</span>
                    <span class="color color-4">t</span>
                    <span class="color color-5">t</span>
                    <span class="color color-1">e</span>
                  </em>
                </div>
              </div>
            </blockquote>
          </h1>
        </section>
      </div>
    </div>

  </div>

</template>

<script>

export default {
  name: 'PlayContent',
  computed: {

  },
  data() {
    return {

    }
  }
}
</script>

<style scoped>
  .container {
    padding: 0;
    margin: 0;
  }

  main {
    display: block
  }

  h1 {
    font-size: 2em;
    margin: .67em 0
  }

  hr {
    -webkit-box-sizing: content-box;
    box-sizing: content-box;
    height: 0;
    margin-top: 0;
    margin-bottom: .67em;
    overflow: visible;
    color: transparent;
    border-color: transparent;
  }

  pre {
    font-family: monospace,monospace;
    font-size: 1em
  }

  a {
    background-color: transparent
  }

  abbr[title] {
    border-bottom: none;
    text-decoration: underline;
    -webkit-text-decoration: underline dotted;
    text-decoration: underline dotted
  }

  b,strong {
    font-weight: bolder
  }

  code,kbd {
    font-family: monospace,monospace;
    font-size: 1em
  }

  samp {
    font-family: monospace,monospace;
    font-size: 1em;
    border: 0.5px solid rgba(156, 156, 156, .1);
    border-radius: calc(1.0rem + .5vw);
  }

  blockquote {
    margin: 0;
    padding: 0;
    border: 0;
    outline: 0;
    font-size: 90%;
    vertical-align: baseline;
    border-left: calc(1.0rem + .5vw) solid rgba(156, 156, 156, .1);
    background-color: transparent !important;
    padding-left: .5em;
  }

  blockquote:before, blockquote:after {
    content:'';
    content:none;
  }

  small {
    font-size: 80%
  }

  sub,sup {
    font-size: 75%;
    line-height: 0;
    position: relative;
    vertical-align: baseline;
    padding: 0 20em;
  }

  sub {
    bottom: -.25em
  }

  sup {
    top: -.5em
  }

  img {
    border-style: none
  }

  button,input,optgroup,select,textarea {
    font-family: inherit;
    font-size: 100%;
    line-height: 1.15;
    margin: 0
  }

  button,input {
    overflow: visible
  }

  button,select {
      text-transform: none
  }[type=button],[type=reset],[type=submit],button {
      -webkit-appearance: button
  }[type=button]::-moz-focus-inner,[type=reset]::-moz-focus-inner,[type=submit]::-moz-focus-inner,button::-moz-focus-inner {
      border-style: none;
      padding: 0
  }[type=button]:-moz-focusring,[type=reset]:-moz-focusring,[type=submit]:-moz-focusring,button:-moz-focusring {
      outline: 1px dotted ButtonText
  }

  fieldset {
    padding: .35em .75em .625em
  }

  legend {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    color: inherit;
    display: table;
    max-width: 100%;
    padding: 0;
    white-space: normal
  }

  progress {
    vertical-align: baseline
  }

  textarea {
      overflow: auto
  }[type=checkbox],[type=radio] {
      -webkit-box-sizing: border-box;
      box-sizing: border-box;
      padding: 0
  }[type=number]::-webkit-inner-spin-button,[type=number]::-webkit-outer-spin-button {
      height: auto
  }[type=search] {
      -webkit-appearance: textfield;
      outline-offset: -2px
  }[type=search]::-webkit-search-decoration {
      -webkit-appearance: none
  }

  ::-webkit-file-upload-button {
    -webkit-appearance: button;
    font: inherit
  }

  details {
    display: block
  }

  summary {
      display: list-item
  }[hidden],template {
      display: none
  }

  * { 
    cursor: default;
    -webkit-user-select: default;
    -moz-user-select: default;
    -ms-user-select: default;
    user-select: default;
    -webkit-box-sizing: border-box;
    box-sizing: border-box
  }

  html {
    font-size: 62.5%
  }

  .container {
    max-width: 100%;
    min-height: 100%;
    font-family: gilroy-bold,-apple-system,system-ui,BlinkMacSystemFont,Segoe UI,Roboto;
    font-size: calc(1.4rem + .5vw)
  }

  .container,html {
    height: 100%;
    overflow: hidden
  }

  .page-container {
    width: 100%;
    height: 100%
  }

  .page-content {
    height: 100%;
    padding: 2rem;
    user-select: none;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: nowrap;
    flex-wrap: nowrap;
    position: relative;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    -ms-flex-line-pack: justify;
    align-content: space-between;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    -webkit-transition: all .4s cubic-bezier(.17,.67,.16,1.03);
    transition: all .4s cubic-bezier(.17,.67,.16,1.03)
  }

  @media (min-width:768px) {
    .page-content {
      padding-bottom: 6rem;
      padding-left: 6rem;
    }
  }[data-mode=dark] .page-content {
      color: #fff;
      background: #232a46
  }[data-mode=light] .page-content {
      color: #232a46;
      background: #fff
  }

  .page-content header {
    width: 100%;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: nowrap;
    flex-wrap: nowrap;
    -webkit-box-orient: horizontal;
    -webkit-box-direction: normal;
    -ms-flex-direction: row;
    flex-direction: row;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between
  }

  .page-content footer,.page-content section {
    width: 100%
  }

  .component-logo {
      width: 3rem;
      height: auto
  }[data-mode=dark] .component-logo .st0 {
      fill: #fff
  }[data-mode=light] .component-logo .st0 {
      fill: #232a46
  }[data-mode=dark] .component-logo .st1,[data-mode=light] .component-logo .st1 {
      fill: #a2a2a2
  }

  @media (min-width:768px) {
    .component-logo {
      width: 4rem
    }
  }

  .component-mode {
    border: 0;
    outline: 0;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    background: transparent
  }

  .component-mode span {
      width: 1.6rem;
      height: 1.6rem;
      border-radius: .8rem;
      -webkit-transition: -webkit-transform .4s ease-in-out;
      transition: -webkit-transform .4s ease-in-out;
      transition: transform .4s ease-in-out;
      transition: transform .4s ease-in-out,-webkit-transform .4s ease-in-out
  }[data-mode=dark] .component-mode span:first-child {
      z-index: 100;
      background: #fff;
      border: 1px solid #fff;
      -webkit-transform: translateX(0);
      transform: translateX(0)
  }[data-mode=light] .component-mode span:first-child {
      z-index: 100;
      background: #fff;
      border: 1px solid #232a46;
      -webkit-transform: translateX(.8rem);
      transform: translateX(.8rem)
  }[data-mode=dark] .component-mode span:last-child {
      z-index: 0;
      background: #232a46;
      border: 1px solid #fff;
      -webkit-transform: translateX(-.8rem);
      transform: translateX(-.8rem)
  }[data-mode=light] .component-mode span:last-child {
      z-index: 0;
      background: #232a46;
      border: 1px solid #232a46;
      -webkit-transform: translateX(-1.6rem);
      transform: translateX(-1.6rem)
  }

  .page-social {
    width: 100%;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    -webkit-box-orient: horizontal;
    -webkit-box-direction: normal;
    -ms-flex-direction: row;
    flex-direction: row;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    -webkit-transition: opacity .2s ease;
    transition: opacity .2s ease;
    -webkit-transition-delay: .8s;
    transition-delay: .8s
  }

  .page-social.hide {
    opacity: 0;
    -webkit-transition: 0s;
    transition: 0s;
    -webkit-transition-delay: 0s;
    transition-delay: 0s
  }

  @media (min-width:768px) {
    .page-social {
      -webkit-box-flex: 1;
      -ms-flex: 1;
      flex: 1;
      -ms-flex-wrap: nowrap;
      flex-wrap: nowrap
    }
  }

  .page-social a {
      text-decoration: none
  }[data-mode=dark] .page-social a {
      color: #fff
  }[data-mode=light] .page-social a {
      color: #232a46
  }

  section {
    width: 100%;
    -webkit-box-flex: 1;
    -ms-flex-positive: 1;
    flex-grow: 1;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: horizontal;
    -webkit-box-direction: normal;
    -ms-flex-direction: row;
    flex-direction: row;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center
  }

  @media (min-width:768px) {
    section {
      width: 100%
    }
  }

  section h1 {
    width: 100%;
    font-size: 9vw
  }

  @media (min-width:768px) {
    section h1 {
      font-size: 5vw;
      width: 55%
    }
  }

  @media (min-width:1366px) {
    section h1 {
      font-size: 6.7rem;
      width: 67rem
    }
  }

  section h1 .content-line {
    width: 100%;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    -webkit-box-orient: horizontal;
    -webkit-box-direction: normal;
    -ms-flex-direction: row;
    flex-direction: row;
    -webkit-box-pack: start;
    -ms-flex-pack: start;
    justify-content: flex-start
  }

  section h1 .content-line .content-word {
    margin-right: 1.6vw
  }

  @media (min-width:768px) {
    section h1 .content-line .content-word {
        margin-right: .8vw
    }
  }

  section h1 .content-line .content-word a {
      text-decoration: none
  }[data-mode=dark] section h1 .content-line .content-word a {
      color: #fff
  }[data-mode=light] section h1 .content-line .content-word a {
      color: #232a46
  }

  footer {
    width: 100%;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    -webkit-box-pack: end;
    -ms-flex-pack: end;
    justify-content: flex-end;
    -webkit-box-orient: horizontal;
    -webkit-box-direction: reverse;
    -ms-flex-direction: row-reverse;
    flex-direction: row-reverse
  }

  @media (min-width:768px) {
      footer {
          -ms-flex-wrap: nowrap;
          flex-wrap: nowrap;
          -webkit-box-orient: horizontal;
          -webkit-box-direction: normal;
          -ms-flex-direction: row;
          flex-direction: row;
          -webkit-box-pack: justify;
          -ms-flex-pack: justify;
          justify-content: space-between
      }
  }[data-mode=dark] .color-primary .color {
      color: #fff
  }[data-mode=light] .color-primary .color {
      color: #232a46
  }[data-mode=dark] .color-secondary .color {
      color: #a2a2a2
  }[data-mode=light] .color-secondary .color {
      color: #6f6f6f
  }

  .color {
    -webkit-transition: color 3s;
    transition: color 3s;
    -webkit-transition-delay: 1s;
    transition-delay: 1s;
    display: inline-block
  }

  .color.color-space {
    margin-right: 10px
  }

  .color.color-1:hover {
      -webkit-transition: color 0s;
      transition: color 0s
  }[data-mode=dark] .color.color-1:hover {
      color: #ffaa79
  }[data-mode=light] .color.color-1:hover {
      color: #ff6913
  }

  .color.color-2:hover {
      -webkit-transition: color 0s;
      transition: color 0s
  }[data-mode=dark] .color.color-2:hover {
      color: #b4fafa
  }[data-mode=light] .color.color-2:hover {
      color: #54f4f4
  }

  .color.color-3:hover {
      -webkit-transition: color 0s;
      transition: color 0s
  }[data-mode=dark] .color.color-3:hover {
      color: #ff787d
  }[data-mode=light] .color.color-3:hover {
      color: #ff121b
  }

  .color.color-4:hover {
      -webkit-transition: color 0s;
      transition: color 0s
  }[data-mode=dark] .color.color-4:hover {
      color: #efb1ed
  }[data-mode=light] .color.color-4:hover {
      color: #de5cd9
  }

  .color.color-5:hover {
      -webkit-transition: color 0s;
      transition: color 0s
  }[data-mode=dark] .color.color-5:hover {
      color: #ffe484
  }[data-mode=light] .color.color-5:hover {
      color: #ffce1e
  }

  .page-clipboard {
    position: absolute;
    right: 2rem;
    bottom: 2rem;
    left: 2rem;
    z-index: 800
  }

  @media (min-width:768px) {
    .page-clipboard {
      -webkit-box-flex: 1;
      -ms-flex: 1;
      flex: 1;
      left: 0;
      right: 0;
      bottom: 0;
      text-align: right;
      position: relative
    }
  }

  .transition-fade-enter-active,.transition-fade-leave-active {
    -webkit-transition: all .6s cubic-bezier(.17,.67,.16,1.03);
    transition: all .6s cubic-bezier(.17,.67,.16,1.03)
  }

  .transition-fade-enter,.transition-fade-leave-to {
    opacity: 0
  }

  .transition-fade-up-enter-active,.transition-fade-up-leave-active {
    -webkit-transition: all .6s cubic-bezier(.17,.67,.16,1.03);
    transition: all .6s cubic-bezier(.17,.67,.16,1.03)
  }

  .transition-fade-up-enter,.transition-fade-up-leave-to {
    opacity: 0;
    -webkit-transform: translateY(3rem);
    transform: translateY(3rem)
  }

  .transition-fade-down-enter-active,.transition-fade-down-leave-active {
    -webkit-transition: all .6s cubic-bezier(.17,.67,.16,1.03);
    transition: all .6s cubic-bezier(.17,.67,.16,1.03)
  }

  .transition-fade-down-enter,.transition-fade-down-leave-to {
    opacity: 0;
    -webkit-transform: translateY(-3rem);
    transform: translateY(-3rem)
  }

  .transition-fade-up-1-enter-active,.transition-fade-up-1-leave-active {
    -webkit-transition: all .6s cubic-bezier(.64,.57,.67,1.53);
    transition: all .6s cubic-bezier(.64,.57,.67,1.53);
    -webkit-transition-delay: 200;
    transition-delay: 200
  }

  .transition-fade-up-1-enter,.transition-fade-up-1-leave-to {
    opacity: 0;
    -webkit-transform: translateY(3rem);
    transform: translateY(3rem)
  }

  .transition-fade-up-2-enter-active,.transition-fade-up-2-leave-active {
    -webkit-transition: all .6s cubic-bezier(.64,.57,.67,1.53);
    transition: all .6s cubic-bezier(.64,.57,.67,1.53);
    -webkit-transition-delay: 400;
    transition-delay: 400
  }

  .transition-fade-up-2-enter,.transition-fade-up-2-leave-to {
    opacity: 0;
    -webkit-transform: translateY(3rem);
    transform: translateY(3rem)
  }

  .transition-fade-up-3-enter-active,.transition-fade-up-3-leave-active {
    -webkit-transition: all .6s cubic-bezier(.64,.57,.67,1.53);
    transition: all .6s cubic-bezier(.64,.57,.67,1.53);
    -webkit-transition-delay: 600;
    transition-delay: 600
  }

  .transition-fade-up-3-enter,.transition-fade-up-3-leave-to {
    opacity: 0;
    -webkit-transform: translateY(3rem);
    transform: translateY(3rem)
  }

  .transition-fade-up-4-enter-active,.transition-fade-up-4-leave-active {
    -webkit-transition: all .6s cubic-bezier(.64,.57,.67,1.53);
    transition: all .6s cubic-bezier(.64,.57,.67,1.53);
    -webkit-transition-delay: 800;
    transition-delay: 800
  }

  .transition-fade-up-4-enter,.transition-fade-up-4-leave-to {
    opacity: 0;
    -webkit-transform: translateY(3rem);
    transform: translateY(3rem)
  }

  .transition-fade-up-5-enter-active,.transition-fade-up-5-leave-active {
    -webkit-transition: all .6s cubic-bezier(.64,.57,.67,1.53);
    transition: all .6s cubic-bezier(.64,.57,.67,1.53);
    -webkit-transition-delay: 1000;
    transition-delay: 1000
  }

  .transition-fade-up-5-enter,.transition-fade-up-5-leave-to {
    opacity: 0;
    -webkit-transform: translateY(3rem);
    transform: translateY(3rem)
  }

  .next-arrow {
    position: absolute;
    color: white;
    right: 4rem;
  }

</style>