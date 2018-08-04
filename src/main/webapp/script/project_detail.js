
let app = new Vue({
    el: "#app",
    data: {
        //data
        project:{eng:{},doc:{}},

        user:{},
        id:''
    },
    //call on page loaded
    created() {
        this.id = sessionStorage.getItem('param_pro_detail_proId');
        sessionStorage.removeItem('param_pro_detail_proId');
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        request200('GET', '/in/pro/get', {pi:this.id}, x => {
            this.project = x;
            if (!x.doc) x.doc = {url:""};

        });
    },
    //other functions
    methods: {
        //action
        modify(){
            sessionStorage.setItem("param_pro_modify_proId",this.id);
            sessionStorage.setItem("param_pro_modify_docId",this.project.doc.docId);
        }
    }
});