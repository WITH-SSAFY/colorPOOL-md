import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import colorStore from '@/store/modules/colorStore'
import landingStore from '@/store/modules/landingStore'

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    colorStore,
    landingStore
  }
})
