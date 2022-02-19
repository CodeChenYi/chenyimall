<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-switch
        v-model="treeSwitch"
        active-text="开启拖拽"
        inactive-text="关闭拖拽"
        style="margin-right: 10px"
      >
      </el-switch>
      <el-button v-if="treeSwitch" style="margin-right: 10px"
        >提交拖拽</el-button
      >
      <el-button type="danger" @click="tree">批量删除</el-button>
    </div>
    <el-tree
      :data="categoryList"
      :props="defaultProps"
      show-checkbox
      :draggable="treeSwitch"
      :expand-on-click-node="false"
      node-key="catId"
      @node-click="handleNodeClick"
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">
            添加
          </el-button>
          <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => update(data)">
            修改
          </el-button>
          <el-button v-if="node.childNodes.length == 0" type="text" size="mini" @click="() => remove(node, data)">
            删除
          </el-button>
        </span>
      </span></el-tree
    >
  </div>
</template>

<script>
export default {
  data() {
    return {
      treeSwitch: false,
      category: {},
      categoryList: [],
      defaultProps: {
        children: "categoryEntityChildrenList",
        label: "name",
      },
    };
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
  },
};
</script>