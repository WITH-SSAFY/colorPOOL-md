import { Mark } from "tiptap";
import { updateMark } from "tiptap-commands";

export default class CustomHeader extends Mark {
  get name() {
    return "customstyle";
  }

  get defaultOptions() {
    return {
      levels: ["h1", "color2", "color3", "color4", "color5"]
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
        return ["span", { class: `${mark.attrs.level}` }, 0];
      }
    };
  }

  commands({ type }) {
    return attrs => updateMark(type, attrs);
  }
}
