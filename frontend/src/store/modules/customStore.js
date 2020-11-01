const customStore = {
  namespaced: true,
  state: {
    customTheme: null,
  },
  getters: {
    GE_CUSTOM_THEME: state => state.customTheme,
  },
  mutations: {
    MU_CUSTOM_THEME: (state, payload) => {
      state.customTheme = payload;
    }
  },
  actions: {
    AC_CUSTOM_THEME: ({commit}, payload) => {
      commit('MU_CUSTOM_THEME', payload);
    }
  }
}

export default customStore;