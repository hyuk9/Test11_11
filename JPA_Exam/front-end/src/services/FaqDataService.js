// 2)
import http from "../http-common";

// springboot 로 연결할 axios 함수들
// 전송 경로 : springboot url을 코딩
class FaqDataService {
  getAll() {
    return http.get("/faq");
  }

  get(no) {
    return http.get(`/faq/${no}`);
  }

  create(data) {
    return http.post("/faq", data);
  }

  update(no, data) {
    return http.put(`/faq/${no}`, data);
  }

  delete(no) {
    return http.delete(`/faq/deletion/${no}`);
  }

  deleteAll() {
    return http.delete(`/faq/all`);
  }

  findByDname(title) {
    return http.get(`/faq?title=${title}`);
  }
}

export default new FaqDataService();
