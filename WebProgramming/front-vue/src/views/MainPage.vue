<template>
  <Preview></Preview>
  <hr>
  <DotForm></DotForm>
</template>

<script>
import Preview from "@/components/Preview";
import DotForm from "@/components/DotForm";
export default {
  components: {DotForm, Preview},
  created() {
    this.onCreate();
  },
  methods: {
    onCreate() {
      if (localStorage.getItem("signIs")!="true"){
        this.$router.push("/");
        return;
      }
    }
  }
}
</script>