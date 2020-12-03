# itext라이브러리 css처리

- PDF추출을 위해 외부 라이브러리로 itext를 사용

- 하지만 Itext가 기본 css를 적용하지 않기 때문에 css파일을 별도로 두어 이를 적용

- [w3Schools - Default CSS Values for HTML Elements](https://www.w3schools.com/cssref/css_default_values.asp) 로 기본 css를 구성하고, 클라이언트 단에서 마크다운 작성시 적용한 css를 추가  

- pdf.css

  ```css
  blockquote,
  h1,
  h2,
  h3,
  ol,
  p,
  pre,
  hr,
  ul {
    /*margin: 1rem 0;*/
    margin-bottom: 60px;
    font-size: 16px;
  }
  
  blockquote:first-child,
  h1:first-child,
  h2:first-child,
  h3:first-child,
  ol:first-child,
  p:first-child,
  pre:first-child,
  ul:first-child {
    margin-top: 0;
  }
  
  blockquote:last-child,
  h1:last-child,
  h2:last-child,
  h3:last-child,
  ol:last-child,
  p:last-child,
  pre:last-child,
  ul:last-child {
    margin-bottom: 0;
  }
  
  h1,
  h2,
  h3 {
    line-height: 1.3;
  }
  
  /* default css */
  h1 {
    display: block;
    font-size: 2em;
    margin-top: 0.67em;
    margin-bottom: 0.67em;
    margin-left: 0;
    margin-right: 0;
    font-weight: bold;
  }
  
  
  h2 {
    display: block;
    font-size: 1.5em;
    margin-top: 0.83em;
    margin-bottom: 0.83em;
    margin-left: 0;
    margin-right: 0;
    font-weight: bold;
  }
  
  h3 {
    display: block;
    font-size: 1.17em;
    margin-top: 1em;
    margin-bottom: 1em;
    margin-left: 0;
    margin-right: 0;
    font-weight: bold;
  }
  
  h4 {
    display: block;
    margin-top: 1.33em;
    margin-bottom: 1.33em;
    margin-left: 0;
    margin-right: 0;
    font-weight: bold;
  }
  
  h5 {
    display: block;
    font-size: .83em;
    margin-top: 1.67em;
    margin-bottom: 1.67em;
    margin-left: 0;
    margin-right: 0;
    font-weight: bold;
  }
  
  h6 {
    display: block;
    font-size: .67em;
    margin-top: 2.33em;
    margin-bottom: 2.33em;
    margin-left: 0;
    margin-right: 0;
    font-weight: bold;
  }
  
  
  blockquote {
    display: block;
    margin-top: 1em;
    margin-bottom: 1em;
    margin-left: 40px;
    margin-right: 40px;
  }
  
  p {
    display: block;
    margin-top: 1em;
    margin-bottom: 1em;
    margin-left: 0;
    margin-right: 0;
  }
  
  pre {
    display: block;
    font-family: monospace;
    white-space: pre;
    margin: 1em 0;
  }
  
  ol{
    display: block;
    list-style-type: decimal;
    margin-top: 1em;
    margin-bottom: 1em;
    margin-left: 0;
    margin-right: 0;
    padding-left: 40px;
  }
  
  ul{
    display: block;
    list-style-type: disc;
    margin-top: 1em;
    margin-bottom: 1 em;
    margin-left: 0;
    margin-right: 0;
  
  }
  
  ul ul {
    padding-left: 40px;
  }
  
  div {
    height: 100%;
    padding: 40px 80px 40px 80px;
    overflow: hidden;
  }
  
  body {
    height: 445px;
  }
  ```

  