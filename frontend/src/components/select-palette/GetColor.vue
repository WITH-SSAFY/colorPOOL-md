<template>
  <div>
    <v-col>
      <!-- 검색창 -->
      <v-row rows="3" class="d-flex justify-center">
        <div class="search-field d-flex align-center">
          <input class="search-panel" type="text" v-model="keyword" placeholder="keyword">
          <v-btn @click="getPicularImages()" class="d-flex align-center" tile large color="#a3b396" icon>
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </div>
      </v-row>

      <!-- 검색 결과 -->
      <v-row rows="9" class="d-flex justify-center">
        <div class="result wrap">
          <ul v-for="colorList in this.picularImages" :key="colorList.index">
            <li v-for="color in colorList" :key="color.color">
              <v-card class="mx-auto elevation-5">
                <v-img class="white--text align-end" :src="color.img">
                  <v-overlay :absolute="true" :opacity="0" class="image-layer" v-bind:style="{'background-color': color.color}"></v-overlay>  
                </v-img>
              </v-card>
            </li>
          </ul>
        </div>
      </v-row>
    </v-col>
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
    ...mapActions(colorStore, ['AC_SELECTED_COLORs']),
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
    }
  }
}
</script>

<style scoped>
.search-field{
  display: block;
  height: 50px;
  width: 70%;
  padding-left: 3%;
  margin: 5% 0%;
  background-color: white;
  border: solid 3px;
  border-radius: 8px;
  border-color: #a3b396;
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
  width: 90%;
  height: 100%;
  float: right;
  transition-duration: 300ms;
  display: flex;
  align-items: center;
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