<template>
  <div>
    <!-- 검색창 -->
    <div class="d-flex justify-center">
      <div class="search-field d-flex align-center">
        <input class="search-panel" type="text" v-model="keyword" placeholder="keyword">
        <v-btn @click="getPicularImages()" class="d-flex align-center" tile large color="#a3b396" icon>
          <v-icon x-large>mdi-magnify</v-icon>
        </v-btn>
      </div>
    </div>

    <!-- 검색 결과 -->
    <div class="d-flex justify-center">
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
import axios from 'axios';
import { mapActions } from 'vuex';
const colorStore = 'colorStore';

export default {
  name: 'getColor',
  data(){
    return{
      loading: false,
      keyword: '',
      picularResult: {}, 
      picularImages: [], 
      selectedColor: null,
    }
  },
  methods:{
    ...mapActions(colorStore, ['AC_SELECTED_COLOR']),
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
      alert("select "+color+"!!");
      this.AC_SELECTED_COLOR(color);
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
  border-color: #a3b396;
}

.search-field input{
  font-size: 1.5rem;
  font-weight: bold;
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
  height: 70%;
  overflow: scroll;
  transition-duration: 300ms;
  display: flex;
}

.result.wrap ul {
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