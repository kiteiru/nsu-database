<template>
  <div>
    <h1>{{ this.name }}</h1>
    <el-select-v2
        class="selectDropdown"
        v-model="this.params.managementIdParam"
        :options="this.allManagementOptions"
        placeholder="Select Management"
    />
    <el-button class="submit" type="success" round @click="this.updateData()">Submit</el-button>
    <el-table :data="tableData.rows" style="width: 100%">
      <el-table-column v-for="column in this.tableData.columns"
                       v-bind:key="column.field" :prop="column.field" :label="column.headerName"/>

    </el-table>
  </div>
</template>

<script>
import {getTableRecords} from "@/getTableRecords";
import {managementIdParamOptions} from "@/data/parameters";
import {ref} from "vue";

export default {
  name: "EquipmentListQuery",
  props: {
    link: String,
    name: String
  },
  data() {
    return {params: {managementIdParam: null}, tableData: {}}
  },
  mounted() {
    this.updateData()
  },
  methods: {
    updateData() {
      getTableRecords(this.link, this.params).then((ret) => { this.tableData = ret})
    }
  },
  setup() {
    const allManagementOptions = ref(managementIdParamOptions)
    return {allManagementOptions}
  }
}
</script>

<style scoped>
.submit {
  margin-left: 20px;
  margin-bottom: 30px;
}

.selectDropdown {
  margin-bottom: 30px;
}

h1 {
  position: center;
  font-size: 50px;
  font-family: "Akshar";
}
</style>