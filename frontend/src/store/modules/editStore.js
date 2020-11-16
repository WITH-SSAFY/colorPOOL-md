const editStore = {
  namespaced: true,
  state: {
    page: 0
  },
  getters: {
    GE_PAGE: state => state.page
  },
  mutations: {
    MU_PAGE: (state, payload) => {
      state.page = payload;
    }
  },
  actions: {
    AC_PAGE: ({commit}, payload) => {
      commit('MU_PAGE', payload);
    },
  }
}

export default editStore;