const contentStore = {
  namespaced: true,
  state: {
    contents: []
  },
  getters: {
    GE_CONTENTS: state => state.contents,
  },
  mutations: {
    MU_CONTENTS: (state, payload) => {
      state.contents[payload.page] = payload.content;
      console.log(state.contents);
    }
  },
  actions: {
    AC_CONTENTS: ({commit}, payload) => {
      commit('MU_CONTENTS', payload);
    }
  }
}

export default contentStore;