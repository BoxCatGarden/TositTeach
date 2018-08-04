
let app = new Vue({
    el: "#app",
    data: {
        //data
        project:{eng:{},doc:{}},
        id:'',
        did:'',
        user:{},
    },
    //call on page loaded
    created() {
        this.id = sessionStorage.getItem('param_pro_modify_proId');
        sessionStorage.removeItem('param_pro_modify_proId');
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
        modify(){
            var input_name = document.getElementById("pname").value;
            var input_ed = document.getElementById("ed-ptime").value;
            var input_disp = document.getElementById("pdescription").value;
            var input_file = document.getElementById("file").files[0];
            var pid = this.id;
            sessionStorage.removeItem('param_pro_modify_proId');
            var did = sessionStorage.getItem('param_pro_modify_docId');
            sessionStorage.removeItem('param_pro_modify_docId');
            var res1=0;
            var res2=0;
            if(input_name!=""&&input_disp!=""&&input_ed!=""){
                var input_data = {
                    'pi':pid,
                    'pn':input_name,
                    'edt':input_ed,
                    'dp':input_disp
                };
                request200('POST','/in/pro/mod',input_data,x=>{
                    alert(x?'项目修改成功':"项目修改失败");
                });
                if(input_file != null){
                    var form = new FormData();
                    form.append('file',input_file);
                    form.append('di',did);
                    request200("POST",'/in/engdoc/reupload',form,x=>{
                       console.log(x);
                    });
                }
            }
            else{
                alert("请完成输入！");
            }
            this.$refs.altBoxClose.click();
        },
        back() {
            sessionStorage.setItem('param_pro_detail_proId', this.id);
            window.location = '/project_detail.html';
        }
    }
});