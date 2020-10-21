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
          <div class="slide-content">16:9 비율</div> 
        </div>
      </div>

    </v-col>
  </div>
</template>

<script>
export default {
  name: 'Editing',
  components: {
    
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