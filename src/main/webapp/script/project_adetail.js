
let app = new Vue({
    el: "#app",
    data: {
        //data
        project:{doc:{},eng:{}},
        user:{},
        id:'',

        dis:false
    },
    //call on page loaded
    created() {
        this.id = sessionStorage.getItem('param_pro_adetail_proId');
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
                if (!x.doc) x.doc = {};
                if (!x.eng) x.eng = {};
                this.project = x;
            });
        },


        check(i) {
            if (confirm('确认'+(i==1?'':'不')+'通过该项目吗？')) {
                this.dis=true;
                request200('POST', '/in/pro/check', {pi:this.id,s:i}, x => {
                    if(x){
                        alert(i==1?'项目已通过！':'项目已废止！');
                        window.location = "/project_access.html";
                    } else{
                        alert("操作失败！");
                        this.dis = false;
                    }
                });
            }
        }
    }
});