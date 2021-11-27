<template>
  <div id="app">
    <img src="./assets/logo.png">
    <div>

      <el-button @click="setVisible(true)">获取坐标</el-button>
    </div>
    <tencent-map :visible="visible" @setVisible="setVisible" :selected-values="selectedValue" @selected-change="selectedChange"/>
  </div>
</template>

<script>


import TencentMap from "@/components/Map/TencentMap";
export default {
  name: 'app',
  components: {
    TencentMap

  },
  data(){
    return{
        visible:false,
        selectedValue:{

        }
    }
  },
  methods:{
    setVisible(visible){
      this.visible=visible
    },
    selectedChange(value){
      console.log(value)
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
