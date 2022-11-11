// 1)
import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8000/api",
  headers: {
    // "Content-type" : 소문자 3.x 에서 에러 발생
    "Content-Type": "application/json"
  }
});
