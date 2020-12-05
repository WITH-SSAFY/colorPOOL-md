## TipTap-Extension과 그 확장성에 대하여

> TipTap**은 'Compact'한 라이브러리다
>
> *by **Beaver Bae*** (1995 ~ )

<br>

TipTap에 대해 간략하게 얘기해보겠다. TipTap은 마크다운 라이브러리이다. 우리가 필요로 했던 **마크다운 실시간 반영**이 가능한 라이브러리로, 유용하게 사용할 부분이 많아서 프로젝트에 적용하게 되었다. 그렇다면 TipTap에서 우리가 간과했던 점은 무엇이었을까? 그것은 바로 굉장히 **strict**하다는 것이다.

우리는 Colorful한 발표 자료 작성을 추구한다. 텍스트에 색을 입히고, 배경에도 색을 입히면서 내가 작성한 발표 자료가 **알록달록하면서도 구조적**이길 바라는 것이다. 그렇지만 TipTap은 색이 칠해진 발표 자료를 가져오면 무조건 **정규식을 통해 색깔을 모두 지워**버렸다. 프로젝트 초기에 시험삼아 텍스트에 색깔을 입히는 방식을 도입했을 때보다 더 TipTap은 까다로웠고, TipTap 라이브러리를 거치거나 TipTap-extension을 통해야 색깔을 입힐 수 있었다.

#### 구글 할아버지는 알고 계신대~ 어떻게 extension 작성하는지~ 어떻게 해야 오류없는지~

기존에 텍스트에 색깔을 입히는 방식은 버그가 많았다. 기껏 색깔을 칠해놓은 텍스트가 **색깔이 초기화**되기도 하고, **색깔이 하나로 통일**되어 버리기도 했다. 그래서 구글 할아버지에게 물어보았다. 구글 할아버지의 소개로 만난 스택 오버플로우 아저씨가 알려준 바에 의하면, TipTap 라이브러리 사용자들이 '색깔을 칠할 수 있게 해달라~!!' 라는 무수한 확장 요청이 이어졌지만, '고려해보겠다'라는 TipTap 개발자의 말을 끝으로 개발된 것은 없다고 했다. 색깔을 칠하지 못 하게 된 우울한 사용자들의 댓글 사이로 '색깔? 그거 **라이브러리를 Custom 하면 되지**~' 라는 댓글과 함께 링크가 있었다. 링크에서는 어떻게 하면 색깔을 칠할 수 있을지에 대한 간단한 설명이 있었다.

```javascript
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

```

거기서 가져온 코드에 내가 덧붙여 보았다. 사용자가 색깔을 누르면 black, white, color1, color2, color3, color4, color5라는 요청 중 하나가 오게 된다. color1이라면 **로컬 스토리지에서 color1에 해당되는 색깔**을 가져오게 되고 **드래그된 텍스트를 색깔과 함께 span으로 묶어서 다시 DOM tree에** 보내게 된다. 여기서 내가 팀원들한테 말을 했는지 안했는지 모르는 부분이 있는데, 사실 배색 조합을 선택해서 커스텀하고 다음 단계로 넘어가는 순간 **로컬 스토리지에 그 배색이 저장**되게 된다. 하하하하. 이거 아니면 방법이 없었다라고 하면 용서 받을 수 있을까. 