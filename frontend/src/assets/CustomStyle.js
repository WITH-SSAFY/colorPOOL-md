import { Mark } from "tiptap";
import { updateMark } from "tiptap-commands";
import { mapGetters } from "vuex";
const customStore = 'customStore'

export default class CustomStyle extends Mark {
  get colors() {
    mapGetters(customStore, {colors: ['GE_FINAL_THEME']});
    return this.colors;
  }

  get name() {
    return "customstyle";
  }

  get defaultOptions() {
    return {
      levels: ['black', 'white', localStorage.getItem('color1'), localStorage.getItem('color2'), localStorage.getItem('color3'), localStorage.getItem('color4'), localStorage.getItem('color5')]
    };
  }

  get schema() {
    return {
      attrs: {
        level: {
          default: "body-green"
        }
      },
      parseDOM: [
        {
          tag: "span.custom-style",
          getAttrs(dom) {
            console.log(dom.classList[1]);
            return { level: dom.classList[1] };
          }
        }
      ],
      toDOM: mark => {
        return ["span", { style: `color: ${mark.attrs.level}` }, 0];
        // return ["span", { class: `${mark.attrs.level}` }, 0];
      }
    };
  }

  commands({ type }) {
    return attrs => updateMark(type, attrs);
  }
}
