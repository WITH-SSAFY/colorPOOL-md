<template>
  <div>
    <div class="progress">
      <div class="circle" :class="[page == 1 ? 'active' : '', page > 1 ? 'done': '']">
        <span class="label">1</span><br>
        <span class="title">{{landText}}</span>
      </div>
      <span class="bar"></span>
      <div class="circle" :class="[page == 2 ? 'active' : '', page > 2 ? 'done': '']">
        <span class="label">2</span><br>
        <span class="title" style="margin-left: -50px;">Create Markdown</span>
      </div>
      <span class="bar"></span>
      <div class="circle" :class="[page == 3 ? 'active' : '', page > 3 ? 'done': '']">
        <span class="label">3</span><br>
        <span class="title" style="margin-left: -15px;">Coloring</span>
      </div>
      <span class="bar"></span>
      <div class="circle" :class="[page == 4 ? 'active' : '', page > 4 ? 'done': '']">
        <span class="label">4</span><br>
        <span class="title" style="margin-left: -5px;">Final</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
const landingStore = 'landingStore'
export default {
  props: {
    page: {
      default: void 0
    }
  },
  computed: {
    ...mapGetters(landingStore, {storeIsPick: 'GE_IS_PICK', storeIsGet: 'GE_IS_GET'})
  },
  data() {
    return {
      // page: 0,
      landText : ''
    }
  },
  created() {
    console.log(this.page);
    if(this.page == 1) {
      if(this.storeIsPick) { 
        this.landText = 'Pick Your Color'
      }
      if(this.storeIsGet) {
        this.landText = 'Get Your Color'
      }
    } else {
      this.landText = 'Select Your Color'
    }
  },
  watch : {
    storeIsPick(val) {
      if(val) this.landText = 'Pick Your Color'
    },
    storeIsGet(val) {
      if(val) this.landText = 'Get Your Color'
    }
  }
}
</script>

<style scoped>
*, *:after, *:before {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Open Sans";
  white-space: nowrap;
}


/* Form Progress */
.progress {
  position : absolute;
  top : 20px;
  width: 50%;
  /* width: 1000px; */
  /* margin: 20px auto; */
  text-align: center;
  word-wrap: normal;
}
.progress .circle,
.progress .bar {
  display: inline-block;
  background: #fff;
  width: 40px; height: 40px;
  border-radius: 40px;
  border: 1px solid #d5d5da;
}
.progress .bar {
  position: relative;
  width: 150px;
  height: 6px;
  top: -33px;
  margin-left: 0px;
  margin-right: 0px;
  border-left: none;
  border-right: none;
  border-radius: 0;
}
.progress .circle .label {
  display: inline-block;
  width: 32px;
  height: 32px;
  line-height: 32px;
  border-radius: 32px;
  margin-top: 3px;
  color: #b5b5ba;
  font-size: 17px;
}

.progress .circle:nth-child(2) .title {
  margin-left: -100px;
}

.progress .circle:nth-child(1) .title {
  margin-left: -50px;
}
.progress .circle .title {
  color: #b5b5ba;
  font-size: 10px;
  line-height: 30px;
  /* margin-left: -15px; */
}


/* Done / Active */
.progress .bar.done,
.progress .circle.done {
  background: #eee;
}
.progress .bar.active {
  background: linear-gradient(to right, #EEE 40%, #FFF 60%);
}
.progress .circle.done .label {
  color: #FFF;
  background: #8bc435;
  box-shadow: inset 0 0 2px rgba(0,0,0,.2);
}
.progress .circle.done .title {
  color: #444;
}
.progress .circle.active .label {
  color: #FFF;
  background: #0c95be;
  box-shadow: inset 0 0 2px rgba(0,0,0,.2);
}
.progress .circle.active .title {
  color: #0c95be;
}
</style>