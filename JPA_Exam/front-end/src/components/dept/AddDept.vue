<template>
<!-- 4) -->
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="dname">Dname</label>
        <input
          type="text"
          class="form-control"
          id="dname"
          required
          v-model="dept.dname"
          name="dname"
        />
      </div>

      <div class="form-group">
        <label for="loc">Loc</label>
        <input
          class="form-control"
          id="loc"
          required
          v-model="dept.loc"
          name="loc"
        />
      </div>

      <button @click="saveDept" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newDept">Add</button>
    </div>
  </div>
</template>

<script>
import DeptDataService from "../../services/DeptDataService";

export default {
  data() {
    return {
      dept: {
        dno: null,
        dname: "",
        loc: ""
      },
      submitted: false
    };
  },
  methods: {
    saveDept() {
      let data = {
        dname: this.dept.dname,
        loc: this.dept.loc
      };

      DeptDataService.create(data)
        .then(response => {
          this.dept.dno = response.data.dno;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newDept() {
      this.submitted = false;
      this.dept = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>
