<template>
  <el-tree
      :data="categoryList"
      :props="defaultProps"
      node-key="catId"
      @node-click="treeNodeClick"
    >
  </el-tree>
</template>

<script>
export default {
  data() {
    return {
      categoryList: [],
      defaultProps: {
        children: "categoryEntityChildrenList",
        label: "name",
      },
    }
  },
  created() {
    this.getMenu();
  },
  methods: {
    getMenu() {
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get",
      }).then((data) => {
        this.categoryList = data.data.data;
        console.log(this.categoryList);
      });
    },
    treeNodeClick(data, node, components) {
      console.log("当前节点被点击", data, node, components);
      this.$emit("tree-node-click", data, node, components);
    }
  }
}
</script>

<style>

</style>