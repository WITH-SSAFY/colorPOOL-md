const colorStore = {
  namespaced: true,
  state: {
    selectedColor: null,
    selectedTheme: null,
    demoColor: [],
    demoFlag: [true, true, true, true, true],
    selectedDemo: [],
  },
  getters:{
    GE_SELECTED_COLOR: state => state.selectedColor,
    GE_SELECTED_THEME: state => state.selectedTheme,
    GE_DEMO_COLOR: state => state.demoColor,
    GE_DEMO_FLAG: state => state.demoFlag,
    GE_SELECTED_DEMO: state => state.selectedDemo,
  },
  mutations: {
    MU_SELECTED_COLOR: (state, payload) => {
      state.selectedColor = payload.selectedColor
    },
    MU_SELECTED_THEME: (state, payload) => {
      // console.log('MU_SELCTED_THEME', payload)
      state.selectedTheme = payload;
    },
    MU_DEMO_COLOR: (state, payload) => {
      // console.log('MU_DEMO_COLOR', payload)
      state.demoColor = payload;
    },
    MU_DEMO_FLAG: (state, payload) => {
    //  console.log('MU_DEMO_FALG', payload)
     state.demoFlag = payload;
    },
    MU_SELECTED_DEMO: (state, payload) => {
      // console.log('MU_SELECTED_DEMO',payload)
      state.selectedDemo = payload;
    } 
  },
  actions:{
    AC_SELECTED_COLOR: ({commit}, payload) =>  {
      commit('MU_SELECTED_COLOR', payload)
    },
    AC_SELECTED_THEME: ({commit}, payload) =>  {
      commit('MU_SELECTED_THEME', payload)
    },
    AC_DEMO_COLOR: ({commit, dispatch}, payload) => {
      commit('MU_DEMO_COLOR', payload.theme)
      dispatch('AC_SELECTED_DEMO', payload);
    },
    AC_DEMO_FLAG: ({commit, dispatch}, payload) => {
      commit('MU_DEMO_FLAG', payload.flag);
      dispatch('AC_SELECTED_DEMO', payload);
    },
    AC_SELECTED_DEMO: ({commit}, payload) => {
      commit
      const temp = ['', '', '', '', ''];
      for(var i = 0; i < 5; i++){
        if(payload.flag[i]){
          temp[i] = payload.theme[i];
        }
      }
      commit('MU_SELECTED_DEMO', temp);
    }
  }
}
export default colorStore;