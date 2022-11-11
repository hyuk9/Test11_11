<template>
<!-- 5) -->
  <div v-if="currentFaq" class="edit-form">
    <h4>Faq</h4>
    <form>
      <div class="form-group">
        <label for="title">Dname</label>
        <input type="text" class="form-control" id="title"
          v-model="currentFaq.title"
        />
      </div>
      <div class="mt-3 form-group">
        <label for="content">Content</label>
        <input type="text" class="form-control" id="content"
          v-model="currentFaq.content"
        />
      </div>
    </form>
      
    <button class="mt-3 btn btn-danger"
      @click="deleteFaq"
    >Delete
    </button>

    <button type="submit" class="ms-3 mt-3 btn btn-success"
      @click="updateFaq"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Faq...</p>
  </div>
</template>

<script>
import FaqDataService from "../../services/FaqDataService";

/* eslint-disable */
export default {
  data() {
    return {
      currentFaq: null,
      message: ''
    };
  },
  methods: {
    getFaq(no) {
      FaqDataService.get(no) // spring 요청
        //  성공/실패 모르겠지만
        //  성공하면 then안에 있는것이 실행
        .then(response => {
          this.currentFaq = response.data;
          console.log(response.data);
        })
        //  실패하면 catch안에 있는것이 실행
        .catch(e => {
          console.log(e);
        });
    },

    updateFaq() {
      FaqDataService.update(this.currentFaq.no, this.currentFaq)
        .then(response => {
          console.log(response.data);
          this.message = 'The Faq was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deleteFaq() {
      FaqDataService.delete(this.currentFaq.no)
        .then(response => {
          console.log(response.data);
          this.$router.push("/faq");
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getFaq(this.$route.params.no);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
