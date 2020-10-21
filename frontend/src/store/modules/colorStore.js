const colorStore = {
  namespaced: true,
  state: {
    selectedColor: null,
    selectedTheme: null
  },
  getters:{
    GE_SELECTED_COLOR: state => state.selectedColor,
    GE_SELECTED_THEME: state => state.selectedTheme,
  },
  mutations: {
    MU_SELECTED_COLOR: (state, payload) => {
      state.selectedColor = payload.selectedColor
    },
    MU_SELECTED_THEME: (state, payload) => {
      // console.log('MU_SELCTED_THEME', payload)
      state.selectedTheme = payload.selectedTheme
   },
  },
  actions:{
    AC_SELECTED_COLOR: ({commit}, payload) =>  {
      commit('MU_SELECTED_COLOR', payload)
    },
    AC_SELECTED_THEME: ({commit}, payload) =>  {
      commit('MU_SELECTED_THEME', payload)
    },
  }
}
export default colorStore;