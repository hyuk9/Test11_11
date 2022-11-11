<template>
<!-- 3) -->
<!-- 검색어 -->
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by dname"
          v-model="dname"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchDname"
          >
            Search
          </button>
        </div>
      </div>
    </div>

<!-- 전체 목록 -->
    <div class="col-md-6">
      <h4>Dept List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(data, index) in dept"
          :key="index"
          @click="setActiveDept(data, index)"
        >
          {{ data.dname }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllDept">
        Remove All
      </button>
    </div>

    <!-- 상세 목록 -->
    <div class="col-md-6">
      <div v-if="currentDept">
        <h4>Dept</h4>
        <div>
          <label><strong>Dname:</strong></label> {{ currentDept.dname }}
        </div>
        <div>
          <label><strong>Loc:</strong></label> {{ currentDept.loc }}
        </div>

        <router-link :to="'/dept/' + currentDept.dno"><span class="badge bg-success">Edit</span></router-link>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Dept...</p>
      </div>
    </div>
    
  </div>
</template>

<script>
import DeptDataService from "../../services/DeptDataService";

export default {
  data() {
    return {
      dept: [],
      currentDept: null,
      currentIndex: -1,
      dname: ""
    };
  },
  methods: {
    retrieveDept() {
      DeptDataService.getAll()
        .then(response => {
          this.dept = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveDept();
      this.currentDept = null;
      this.currentIndex = -1;
    },

    setActiveDept(dept, index) {
      this.currentDept = dept;
      this.currentIndex = index;
    },

    removeAllDept() {
      DeptDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchDname() {
      DeptDataService.findByDname(this.dname)
        .then(response => {
          this.dept = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveDept();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
