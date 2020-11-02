<template>
  <div>
    <!-- 검색창 -->
    <div class="d-flex justify-center">
      <div class="search-field d-flex align-center" :style="{'border': 'solid ' + storeSelectedColor + ' 3px'}">
        <input class="search-panel" type="text" v-model="keyword" placeholder="keyword" :style="{'color' : storeSelectedColor, 'placeholder-color' : storeSelectedColor}"  @keyup.enter="getPicularImages()">
        <v-btn @click="getPicularImages()" class="d-flex align-center" tile large :color="storeSelectedColor" icon>
          <v-icon x-large>mdi-magnify</v-icon>
        </v-btn>
      </div>
    </div>

    <!-- 검색 결과 -->
    <div class="d-flex justify-center" style="height: 900px; width: 100%;">
      <div class="result wrap">
        <ul v-for="colorList in this.picularImages" :key="colorList.index">
          <li v-for="color in colorList" :key="color.color" @click="selectColor(color.color)">
            <v-card class="mx-auto elevation-5">
              <v-img class="white--text" :src="color.img">
                <v-overlay :absolute="true" :opacity="0" class="image-layer" v-bind:style="{'background-color': color.color}"></v-overlay>  
              </v-img>
            </v-card>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import materialColors from '../../assets/colorList'
import axios from 'axios';
import { mapActions, mapGetters } from 'vuex';
const colorStore = 'colorStore';
const themeStore = 'themeStore';

export default {
  name: 'getColor',
  data(){
    return{
      loading: false,
      keyword: '',
      picularResult: {}, 
      picularImages: [], 
      selectedColor: null,
      materialColors: materialColors,
    }
  },
  created() {
    if(this.storeSelectedColor == null) {
      const num = Math.floor(Math.random() * 199) + 1;
      let from = parseInt(num / 10);
      let to = parseInt(num % 10);
      
      // alert(from + " : " + to);
      console.log(this.materialColors[from].variations[to].hex);
      this.AC_SELECTED_COLOR(this.materialColors[from].variations[to].hex)
      this.AC_THEME_LIST(num);
    } else {
      this.materialColors.forEach((color) => {
        color.variations.forEach((ele) => {
          if(ele.hex == this.storeSelectedColor) {
            this.AC_THEME_LIST(ele.id);
            return;
          }
        })
      })
    }
  },
  computed: {
    ...mapGetters(colorStore, {storeSelectedColor : 'GE_SELECTED_COLOR'}),
  },
  methods:{
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
    ...mapActions(themeStore, ['AC_THEME_LIST', 'AC_THEME_LIST_DUMMY']),
    getPicularImages(){
      this.loading = true;
      console.log('keyword', this.keyword);
      axios.get('https://server.picular.co/' + this.keyword)
      .then((res)=> {
        console.log('getColor - res : ', res);
        this.loading = false;
        this.picularResult = res.data
        this.picularImages = [];
        this.picularImages.push(this.picularResult.colors.slice(0, 5));
        this.picularImages.push(this.picularResult.colors.slice(5, 10));
        this.picularImages.push(this.picularResult.colors.slice(10, 15));
        this.picularImages.push(this.picularResult.colors.slice(15, 20));
      })
      .catch((err) => {
        this.loading = false;
        console.log(err);
      })
    },
    selectColor(color){
      this.getColor(color);
      // alert("select "+color+"!!");
      // this.AC_SELECTED_COLOR(color);
    },
    getColor(color){
      var rgb = this.getRGB(color);
      console.log(rgb);
      var select = null;
      var min = 1000000;
      this.materialColors.forEach((ele) => {
        ele.variations.forEach((hex) => {
          var rgb2 = this.getRGB(hex.hex);
          var distance = (rgb2.r - rgb.r) * (rgb2.r - rgb.r) + (rgb2.g - rgb.g) * (rgb2.g - rgb.g) + (rgb2.b - rgb.b) * (rgb2.b - rgb.b);
          if(distance < min) {
            min = distance;
            select = hex;
          }
        })
      })
      console.log('selected', select);
      console.log('selected id', select.id);
      this.AC_THEME_LIST(select.id);
      this.AC_SELECTED_COLOR(select.hex);
    },
    getRGB(color) {
      var hex = color.substring(1, 7);
      var value = hex.match( /[a-f\d]/gi ); 
      if ( value.length == 3 ) hex = value[0] + value[0] + value[1] + value[1] + value[2] + value[2]; 
      value = hex.match( /[a-f\d]{2}/gi ); 
      var r = parseInt( value[0], 16 ); 
      var g = parseInt( value[1], 16 ); 
      var b = parseInt( value[2], 16 ); 
      return {
        r: r,
        g: g,
        b: b
      };
    }
  }
}
</script>

<style scoped>
.search-field{
  display: block;
  height: 60px;
  width: 70%;
  padding-left: 3%;
  margin: 4% 0%;
  background-color: white;
  border: solid 3px;
  border-radius: 8px;
  /* border-color: #a3b396; */
}

.search-field input{
  font-size: 1.5rem;
  font-weight: bold;
}
.search-field input::placeholder {
  color: inherit;
}

.search-field .v-btn{
  margin-right: -20%;
}

.search-panel{
  width: 80%;
  height: 100%;
  outline: none;
}

.result.wrap{
  width: 72%;
  max-height: 70%;
  overflow-y: scroll;
  transition-duration: 300ms;
  display: flex;
}

.result.wrap ul {
  margin-top: 16px;
  width: 100%;
  height: 100%;
  list-style: none;
  padding: 0;
}

.result.wrap ul li {
  display: inline-block;
  width: 100%;
  text-align: center;
}

.result.wrap ul li .v-card {
  position: relative;
  width: 90%;
  background-color: black;
}
.result.wrap ul li .image-layer {
  opacity: 1;
  transition-duration: 300ms;
}

.result.wrap ul li .image-layer:hover {
  opacity: 0.1;
}

</style>