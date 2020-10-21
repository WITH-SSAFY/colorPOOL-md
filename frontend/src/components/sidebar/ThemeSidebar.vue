<template>
  <div class="theme-list-wrap d-flex justify-center">
    <div class="theme-wrap d-flex align-center justify-center" :style="{'background-color' : '#55423d'}">
      <div class="theme color1" :style="{'background-color' : '#281c1b'}"></div>
      <div class="theme color2" :style="{'background-color' : '#fff3ec'}"></div>
      <div class="theme color3" :style="{'background-color' : '#9656a1'}"></div>
      <div class="theme color4" :style="{'background-color' : '#ffc1ad'}"></div>
      <div class="theme color5" :style="{'background-color' : '#e78fb3'}"></div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

.theme-list-wrap{
  height: 100%;
  border-right: 3px solid #707070;
}

.theme-wrap{
  margin-top: 25px;
  width: 70%;
  height: 95px;
  border: 3px solid #707070;
}

.theme {
  width: 65px;
  height: 65px;
  border-radius: 75px;
  border: 2.5px solid #707070;
  float: left;
}

.theme.color1{
  z-index: 50;
}

.theme.color2{
  margin-left: -30px;
  z-index: 51;
}

.theme.color3{
  margin-left: -30px;
  z-index: 52;
}

.theme.color4{
  margin-left: -30px;
  z-index: 53;
}

.theme.color5{
  margin-left: -30px;
  z-index: 54;
}

</style>