import axios from '../../api/axiosCommon'
import SERVER from '../../api/restApi'

const pdfStore = {
  namespaced: true,
  state: {
    contents: '',
    pdfUrl: '',
    linkUrl: '',
    isSent: false,
  },
  getters: {
    GE_CONTENTS: state => state.contents,
    GE_PDF_URL: state => state.pdfUrl,
    GE_LINK_URL: state => state.linkUrl,
    GE_IS_SENT: state => state.isSent,
  },
  mutations: {
    MU_CONTENTS: (state, payload) => {
      state.contents = payload;
      if(payload == null) state.isSent = false;
    },
    MU_PDF_URL: (state, payload) => {
      state.pdfUrl = payload;
      state.isSent = true;
    },
    MU_LINK_URL: (state, payload) => {
      state.linkUrl = payload;
    }
  },
  actions: {
    AC_CONTENTS: ({commit}, payload) => {
      commit('MU_CONTENTS', payload);
    },
    AC_PDF_URL: ({commit}, payload) => {
      const data = payload;
      axios.post(SERVER.ROUTES.setPdfUrl, data)
      .then((res) => {
        commit('MU_PDF_URL', res.data.data.path);
        commit('MU_LINK_URL', res.data.data.id);
        console.log(res);
      })
      .catch(err => {
        console.log(err);
      })
      // commit('MU_PDF_URL', payload);
    }
  }
}

export default pdfStore;