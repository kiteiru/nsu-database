<template>
  <div>
    <h1>{{ this.name }}</h1>

    <div class="createForm">
      <el-form :model="form">
        <el-form-item label="Type">
          <el-input v-model="form.type" />
        </el-form-item>

        <el-form-item label="Number">
          <el-input v-model="form.num" />
        </el-form-item>

        <el-form-item label="Object name">
          <el-select-v2
              class="selectDropdown"
              v-model="form.object.id"
              :options="this.allObjectOptions"
              placeholder="Select Object"
          />
        </el-form-item>

        <el-form-item label="Work Type name">
          <el-select-v2
              class="selectDropdown"
              v-model="form.workType.id"
              :options="this.allWorkTypeOptions"
              placeholder="Select Work Type"
          />
        </el-form-item>

        <el-form-item>
          <el-button class="submit" type="success" round @click="createEntity">Create</el-button>

        </el-form-item>
      </el-form>
    </div>

    <el-table :data="tableData.rows" style="width: 100%">
      <el-table-column v-for="column in this.tableData.columns"
                       v-bind:key="column.field" :prop="column.field" :label="column.headerName"/>
      <el-table-column label="Delete Record">
        <template #default="scope">
          <el-button type="danger" round  v-on:click="deleteEntity(scope.$index)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {getTableRecords} from "@/getTableRecords";
import {ElMessage, ElMessageBox} from "element-plus";
import axios from "axios";
import {reactive, ref} from "vue";
import {objectIdParamOptions, workTypeIdParamOptions} from "@/data/parameters";

export default {
  name: "EquipmentTable",
  props: {
    link: String,
    name: String
  },
  data() {
    return {
      tableData: {},
      form: reactive({
        object: {id: ''},
        workType: {id: ''},
        num: null,
        type: ''
      }),
      params: {objectIdParam: ''},
    }
  },
  mounted() {
    this.getTable()
  },
  methods: {
    getTable() {
      getTableRecords(this.link, null).then((ret) => { this.tableData = ret})
    },
    async deleteEntity(idx) {
      let id = this.tableData.rows[idx].id
      if (await ElMessageBox.confirm("Do you really want to delete this record?",
          'Warning',
          {
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
            type: 'warning',
          })) {
        try {
          await axios.delete(this.link + "/" + id)
          this.tableData.rows.splice(idx, 1)
          ElMessage({
            type: 'success',
            message: 'Successfully deleted',
          })
        } catch (e) {
          console.log(e);
          ElMessage({
            type: 'info',
            message: 'Unsuccessfully deleted',
          });
        }
      }
    },
    async createEntity() {
      try {
        await axios.post(this.link, this.form)
        this.getTable()
      } catch (e) {
        console.log(e);
      }
    }
  },
  setup() {
    const allObjectOptions = ref(objectIdParamOptions)
    const allWorkTypeOptions = ref(workTypeIdParamOptions)
    return {allObjectOptions, allWorkTypeOptions}
  }
}
</script>

<style scoped>
.createForm {
  width: 520px;
  margin-left: 35%;
  border: 2px solid #22c0c2;
  border-radius: 20px;
  padding: 30px 30px 15px;
  margin-bottom: 30px;
}
</style>