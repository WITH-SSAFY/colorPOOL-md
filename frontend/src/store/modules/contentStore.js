const contentStore = {
  namespaced: true,
  state: {
    contents: [],
    change: false,
    changeAll: false,
  },
  getters: {
    GE_CONTENTS: state => state.contents,
    GE_CHANGE: state => state.change,
    GE_CHANGE_ALL: state => state.changeAll,
  },
  mutations: {
    MU_CONTENTS: (state, payload) => {
      state.contents[payload.page] = payload.content;
      state.change = !state.change;
      // console.log(state.contents)
    },
    MU_CONTENTS_NULL: (state) => {
      state.contents = [];
      state.change = !state.change;
    },
    MU_CONTENTS_CHANGE: (state, payload) => {
      state.contents = payload;
      state.changeAll = !state.changeAll;
      // console.log(state.contents);
    }
  },
  actions: {
    AC_CONTENTS: ({commit}, payload) => {
      commit('MU_CONTENTS', payload);
    },
    AC_CONTENTS_NULL: ({commit}) => {
      commit('MU_CONTENTS_NULL')
    },
    AC_CONTENTS_CHANGE: ({commit}, payload) => {
      commit('MU_CONTENTS_CHANGE', payload);
    }
  }
}

export default contentStore;