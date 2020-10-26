const colorStore = {
  namespaced: true,
  state: {
    selectedColor: null,
    selectedTheme: null,
    demoColor: {
      backColor: '',
      headColor: '',
      subColor: '',
      emphaColor: '',
      quoteColor: ''
    },
    demoColorFlag:{
      backFlag: '',
      headFlag: '',
      subFlag: '',
      emphaFlag: '',
      quoteFlag: ''
    }
  },
  getters:{
    GE_SELECTED_COLOR: state => state.selectedColor,
    GE_SELECTED_THEME: state => state.selectedTheme,
    GE_DEMO_COLOR: state => state.demoColor,
    GE_DEMO_FLAG: state => state.demoFlag,
  },
  mutations: {
    MU_SELECTED_COLOR: (state, payload) => {
      state.selectedColor = payload.selectedColor
    },
    MU_SELECTED_THEME: (state, payload) => {
      console.log('MU_SELCTED_THEME', payload)
      state.selectedTheme = payload.selectedTheme
   },
   MU_DEMO_COLOR: (state, payload) => {
      // console.log('MU_DEMO_COLOR', payload)
     state.demoColor = payload
   },
   MU_DEMO_FLAG: (state, payload) => {
     state.demoFlag = payload
   }
  },
  actions:{
    AC_SELECTED_COLOR: ({commit}, payload) =>  {
      commit('MU_SELECTED_COLOR', payload)
    },
    AC_SELECTED_THEME: ({commit}, payload) =>  {
      commit('MU_SELECTED_THEME', payload)
    },
    AC_DEMO_COLOR: ({commit}, payload) => {
      commit('MU_DEMO_COLOR', payload)
    },
    AC_DEMO_FLAG: ({commit}, payload) => {
      commit('MU_DEMO_FLAG', payload)
    }
  }
}
export default colorStore;