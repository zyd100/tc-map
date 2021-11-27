import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import './plugins/element.js'
import apiUtil from "./api/api-util";
Vue.config.productionTip = false
Vue.prototype.$apiUtil=apiUtil

new Vue({
  render: h => h(App),
}).$mount('#app')
