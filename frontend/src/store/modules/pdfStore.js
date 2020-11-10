const pdfStore = {
  namespaced: true,
  state: {
    contents: '',
    pdfUrl: '',
  },
  getters: {
    GE_CONTENTS: state => state.contents,
    GE_PDF_URL: state => state.pdfUrl,
  },
  mutations: {
    MU_CONTENTS: (state, payload) => {
      state.contents = payload;
      console.log(state.contents);
    },
    MU_PDF_URL: (state, payload) => {
      state.pdfUrl = payload;
    }
  },
  actions: {
    AC_CONTENTS: ({commit}, payload) => {
      commit('MU_CONTENTS', payload);
    },
    AC_PDF_URL: ({commit}, payload) => {
      commit('MU_PDF_URL', payload);
    }
  }
}

export default pdfStore;