import SERVER from '../../api/restApi'
import axios from '../../api/axiosCommon'
import exampleThemes from '../../assets/themeEx'

const themeStore = {
  namespaced: true,
  state: {
    themeList: [],
  },
  getters: {
    GE_THEME_LIST: state => state.themeList
  },
  mutations: {
    MU_THEME_LIST: (state, payload) => {
      console.log('MU_THEME_LIST', payload);
      state.themeList = payload
    }
  },
  actions: {
    AC_THEME_LIST: ({commit}, payload) => {
      //선택한 컬러로 배색 정보 불러오기
      const color = payload+1;
      axios.get(SERVER.ROUTES.getThemes + color)
      .then((res) => {
        console.log("getThemes - res : ", res)
        const themes = res.data.data;
        commit('MU_THEME_LIST', themes);
      })
      .catch((err) => {
        console.log(err.response);
      })
    },
    AC_THEME_LIST_DUMMY: ({commit}) => {
      commit('MU_THEME_LIST', exampleThemes)
    }
  }
}
export default themeStore