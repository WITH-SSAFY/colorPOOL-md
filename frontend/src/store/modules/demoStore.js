import exampleThemes from '../../assets/themeEx'
const demoStore = {
  namespaced: true,
  state: {
    // demoThemes: 현재는 더미
    // 나중에 서버에서 인기있는 배색 불러올 수 있도록 바꿀 것
    demoThemes: exampleThemes,
    selectedDemoTheme: exampleThemes[4],
    isFlagChanged: false,
    flagDemoTheme: [true, true, true, true, true],
  },
  getters: {
    GE_DEMO_THEMES: state => state.demoThemes,
    GE_SELECTED_DEMO_THEME: state => state.selectedDemoTheme,
    GE_FLAG_DEMO_THEME: state => state.flagDemoTheme,
    GE_IS_FLAG_CHANGED: state => state.isFlagChanged,
  },
  mutations: {
    MU_DEMO_THEMES: (state, payload) => {
      state.demoThemes = payload;
    },
    MU_SELECTED_DEMO_THEME: (state, payload) => {
      state.selectedDemoTheme = payload;
    },
    MU_FLAG_DEMO_THEME: (state, payload) => {
      state.flagDemoTheme = payload;
      state.isFlagChanged = !state.isFlagChanged;
      console.log('MU_FLAG', state.isFlagChanged)
    }
  },
  actions: {
    AC_DEMO_THEMES: ({commit}, payload) => {
      console.log(commit, payload);
    },
    AC_SELECTED_DEMO_THEME: ({commit}, payload) => {
      commit('MU_SELECTED_DEMO_THEME', payload);
      console.log(payload);
    },
    AC_FLAG_DEMO_THEME: ({commit}, payload) => {
      commit('MU_FLAG_DEMO_THEME', payload);
      console.log(payload);
    }
  }
}
export default demoStore;