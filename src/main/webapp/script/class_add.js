
let app = new Vue({
    el: "#app",
    data: {
        //data
        id:'',
        user:{},
        engs:[],
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
            request200('GET', '/in/eng', {st:0,nm:0}, x => {
                this.eng = x;
            });
        },
        add(){
            var name = document.getElementById("cname").value;
            var room = document.getElementById("croom").value;
            var eng = document.getElementById("teacher");
            var index = eng.selectedIndex;
            var engid = eng.options[index].value;
            request200('GET', '/in/cla/add', {cn:name,rm:room,ui:engid}, x => {
                if(x==1){
                    alert("添加小组成功！");
                }
                else{
                    alert("添加小组失败！");
                }
            });
        }
    }
});