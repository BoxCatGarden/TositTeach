
let app = new Vue({
    el: "#app",
    data: {
        //data
        task:{},
        id:'',
        user:{}
        //paging
        /*       currPage: 1,
               totalPage: 0,
               pageSize: 4,*/

        //action
        /*currDoc:0*/
    },
    //call on page loaded
    created() {
        this.id = sessionStorage.getItem('param_task_detail_docId');
        sessionStorage.removeItem('param_task_detail_docId');
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        this.update();
    },
    //other functions
    methods: {
        //paging
        update() {
            request200('GET', '/in/task/get', {ti:this.id}, x => {
                this.task = x;
            });
        },
        save(){
            var disp = document.getElementById("schedule").value;
            request200('POST', '/in/task/mod', {tasId:this.id}, x => {
                if(x==1){
                    alert("修改成功！");
                }
                else{
                    alert("修改失败！");
                }
                this.update();
            });
        }

    }
});