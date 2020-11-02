const customStore = {
  namespaced: true,
  state: {
    customTheme: null,
    finalTheme: null,
  },
  getters: {
    GE_CUSTOM_THEME: state => state.customTheme,
    GE_FINAL_THEME: state => state.finalTheme,
  },
  mutations: {
    MU_CUSTOM_THEME: (state, payload) => {
      state.customTheme = payload;
    },
    MU_FINAL_THEME: (state, payload) => {
      state.finalTheme = payload;
      console.log('MU_FINAL_THEME', state.finalTheme)
    },
  },
  actions: {
    AC_CUSTOM_THEME: ({commit}, payload) => {
      commit('MU_CUSTOM_THEME', payload);
    },
    AC_FINAL_THEME: ({commit}, payload) => {
      commit('MU_FINAL_THEME', payload);
    }
  }
}

export default customStore;