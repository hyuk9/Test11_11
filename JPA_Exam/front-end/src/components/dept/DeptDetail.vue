<template>
<!-- 5) -->
  <div v-if="currentDept" class="edit-form">
    <h4>Dept</h4>
    <form>
      <div class="form-group">
        <label for="dname">Dname</label>
        <input type="text" class="form-control" id="dname"
          v-model="currentDept.dname"
        />
      </div>
      <div class="mt-3 form-group">
        <label for="loc">Loc</label>
        <input type="text" class="form-control" id="loc"
          v-model="currentDept.loc"
        />
      </div>
    </form>
      
    <button class="mt-3 btn btn-danger"
      @click="deleteDept"
    >Delete
    </button>

    <button type="submit" class="ms-3 mt-3 btn btn-success"
      @click="updateDept"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Dept...</p>
  </div>
</template>

<script>
import DeptDataService from "../../services/DeptDataService";

/* eslint-disable */
export default {
  data() {
    return {
      currentDept: null,
      message: ''
    };
  },
  methods: {
    getDept(dno) {
      DeptDataService.get(dno) // spring 요청
        //  성공/실패 모르겠지만
        //  성공하면 then안에 있는것이 실행
        .then(response => {
          this.currentDept = response.data;
          console.log(response.data);
        })
        //  실패하면 catch안에 있는것이 실행
        .catch(e => {
          console.log(e);
        });
    },

    updateDept() {
      DeptDataService.update(this.currentDept.dno, this.currentDept)
        .then(response => {
          console.log(response.data);
          this.message = 'The Dept was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deleteDept() {
      DeptDataService.delete(this.currentDept.dno)
        .then(response => {
          console.log(response.data);
          this.$router.push("/dept");
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getDept(this.$route.params.dno);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
