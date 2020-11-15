const boxStore = {
  namespaced: true,
  state: {
    isBox: false,
    target: null,
  },
  getters: {
    GE_IS_BOX: state => state.isBox,
    GE_TARGET: state => state.target,
  },
  mutations: {
    MU_IS_BOX: (state, payload) => {
      state.isBox = payload;
    },
    MU_TARGET: (state, payload) => {
      state.target = payload;
    }
  },
  actions: {
    AC_IS_BOX: ({commit}, payload) => {
      commit('MU_IS_BOX', payload);
    },
    AC_TARGET: ({commit}, payload) => {
      commit('MU_TARGET', payload);
    }
  }
}
export default boxStore;