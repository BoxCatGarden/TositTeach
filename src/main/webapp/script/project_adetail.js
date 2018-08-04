
let app = new Vue({
    el: "#app",
    data: {
        //data
        project:{},
        user:{},
        id:'',
    },
    //call on page loaded
    created() {
        this.id = sessionStorage.getItem('param_pro_adetail_proId');
        sessionStorage.removeItem('param_pro_adetail_proId');
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        this.update();
    },
    //other functions
    methods: {
        //paging
        update() {
            request200('GET', '/in/pro/get', {pi:this.id}, x => {
                this.project = x;
            });
        },

        //action
        tong() {
            request200('POST', '/in/pro/check', {pi:this.id,s:1}, x => {
                if(x==1){
                    alert("操作成功！");
                }
                else{
                    alert("操作失败！");
                }

                window.location = "/project_access.html";
            });


        },
        mei() {

            request200('POST', '/in/pro/check',  {pi:this.id,s:2}, x => {
                if(x==1){
                    alert("操作成功！");
                }
                else{
                    alert("操作失败！");
                }
                window.location = "/project_access.html";
            });

        },
    }
});