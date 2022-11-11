<template>
<!-- 5) -->
  <div v-if="currentEmp" class="edit-form">
    <h4>Emp</h4>
    <form>
      <div class="form-group">
        <label for="ename">Ename</label>
        <input type="text" class="form-control" id="ename"
          v-model="currentEmp.ename"
        />
      </div>

      <div class="mt-3 form-group">
        <label for="job">job</label>
        <input type="text" class="form-control" id="job"
          v-model="currentEmp.job"
        />
      </div>      
      <div class="mt-3 form-group">
        <label for="manager">manager</label>
        <input type="text" class="form-control" id="manager"
          v-model="currentEmp.manager"
        />
      </div>      
      <div class="mt-3 form-group">
        <label for="hiredate">hiredate</label>
        <input type="text" class="form-control" id="hiredate"
          v-model="currentEmp.hiredate"
        />
      </div>      
      <div class="mt-3 form-group">
        <label for="salary">salary</label>
        <input type="text" class="form-control" id="salary"
          v-model="currentEmp.salary"
        />
      </div>      
      <div class="mt-3 form-group">
        <label for="commission">commission</label>
        <input type="text" class="form-control" id="commission"
          v-model="currentEmp.commission"
        />
      </div>      
      <div class="mt-3 form-group">
        <label for="dno">dno</label>
        <input type="text" class="form-control" id="dno"
          v-model="currentEmp.dno"
        />
      </div>
    </form>
      
    <button class="mt-3 btn btn-danger"
      @click="deleteEmp"
    >Delete
    </button>

    <button type="submit" class="ms-3 mt-3 btn btn-success"
      @click="updateEmp"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Emp...</p>
  </div>
</template>

<script>
import EmpDataService from "../../services/EmpDataService";

/* eslint-disable */
export default {
  data() {
    return {
      currentEmp: null,
      message: ''
    };
  },
  methods: {
    getEmp(eno) {
      EmpDataService.get(eno) // spring 요청
        //  성공/실패 모르겠지만
        //  성공하면 then안에 있는것이 실행
        .then(response => {
          this.currentEmp = response.data;
          console.log(response.data);
        })
        //  실패하면 catch안에 있는것이 실행
        .catch(e => {
          console.log(e);
        });
    },

    updateEmp() {
      EmpDataService.update(this.currentEmp.eno, this.currentEmp)
        .then(response => {
          console.log(response.data);
          this.message = 'The Emp was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deleteEmp() {
      EmpDataService.delete(this.currentEmp.eno)
        .then(response => {
          console.log(response.data);
          this.$router.push("/emp");
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getEmp(this.$route.params.eno);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
