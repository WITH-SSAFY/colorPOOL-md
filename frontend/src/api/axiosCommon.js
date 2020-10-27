import axios from "axios";

export default axios.create({
  baseURL: "http://k3a501.p.ssafy.io/api/",

  headers: {
    "Content-type": "application/json",
  },
});