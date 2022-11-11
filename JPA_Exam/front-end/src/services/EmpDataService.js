// 2)
import http from "../http-common";

class EmpDataService {
  getAll() {
    return http.get("/emp");
  }

  get(eno) {
    return http.get(`/emp/${eno}`);
  }

  create(data) {
    return http.post("/emp", data);
  }

  update(eno, data) {
    return http.put(`/emp/${eno}`, data);
  }

  delete(eno) {
    return http.delete(`/emp/deletion/${eno}`);
  }

  deleteAll() {
    return http.delete(`/emp`);
  }

  findByEname(ename) {
    return http.get(`/emp?ename=${ename}`);
  }
}

export default new EmpDataService();
