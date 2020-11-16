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
      console.log(payload);
      localStorage.setItem('color1', payload[0]);
      localStorage.setItem('color2', payload[1]);
      localStorage.setItem('color3', payload[2]);
      localStorage.setItem('color4', payload[3]);
      localStorage.setItem('color5', payload[4]);
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