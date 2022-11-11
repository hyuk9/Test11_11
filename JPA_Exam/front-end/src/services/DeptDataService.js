// 2)
import http from "../http-common";

// springboot 로 연결할 axios 함수들
// 전송 경로 : springboot url을 코딩
class DeptDataService {
  getAll() {
    return http.get("/dept");
  }

  get(dno) {
    return http.get(`/dept/${dno}`);
  }

  create(data) {
    return http.post("/dept", data);
  }

  update(dno, data) {
    return http.put(`/dept/${dno}`, data);
  }

  delete(dno) {
    return http.delete(`/dept/deletion/${dno}`);
  }

  deleteAll() {
    return http.delete(`/dept/all`);
  }

  findByDname(dname) {
    return http.get(`/dept?dname=${dname}`);
    // return http.get(`/dept/${dname}`);
  }
}

export default new DeptDataService();
