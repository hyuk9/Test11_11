<template>
<!-- 3) -->
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by ename"
          v-model="ename"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchEname"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Emp List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(data, index) in emp"
          :key="index"
          @click="setActiveEmp(data, index)"
        >
          {{ data.ename }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllEmp">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentEmp">
        <h4>Emp</h4>
        <div>
          <label><strong>Ename:</strong></label> {{ currentEmp.ename }}
        </div>

        <div>
          <label><strong>Job:</strong></label> {{ currentEmp.job }}
        </div>        
        <div>
          <label><strong>Manager:</strong></label> {{ currentEmp.manager }}
        </div>        
        <div>
          <label><strong>Hiredate:</strong></label> {{ currentEmp.hiredate }}
        </div>        
        <div>
          <label><strong>Salary:</strong></label> {{ currentEmp.salary }}
        </div>        
        <div>
          <label><strong>Commission:</strong></label> {{ currentEmp.commission }}
        </div>        
        <div>
          <label><strong>eno:</strong></label> {{ currentEmp.eno }}
        </div>

        <router-link :to="'/emp/' + currentEmp.eno"><span class="badge bg-success">Edit</span></router-link>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Emp...</p>
      </div>
    </div>
  </div>
</template>

<script>
import EmpDataService from "../../services/EmpDataService";

export default {
  data() {
    return {
      emp: [],
      currentEmp: null,
      currentIndex: -1,
      ename: ""
    };
  },
  methods: {
    retrieveEmp() {
      EmpDataService.getAll()
        .then(response => {
          this.emp = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveEmp();
      this.currentEmp = null;
      this.currentIndex = -1;
    },

    setActiveEmp(emp, index) {
      this.currentEmp = emp;
      this.currentIndex = index;
    },

    removeAllEmp() {
      EmpDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchEname() {
      EmpDataService.findByEname(this.ename)
        .then(response => {
          this.emp = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveEmp();
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
