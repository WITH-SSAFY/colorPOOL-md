import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import colorStore from '@/store/modules/colorStore'
import themeStore from '@/store/modules/themeStore'
import landingStore from '@/store/modules/landingStore'
import demoStore_prev from '@/store/modules/demoStore_prev'
import demoStore from '@/store/modules/demoStore'
import colorStore2 from '@/store/modules/colorStore2'

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    colorStore,
    themeStore,
    landingStore,
    demoStore_prev,
    demoStore,
    colorStore2
  }
})
