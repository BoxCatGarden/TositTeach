
let app = new Vue({
    el: "#app",
    data: {
        //data
        user:{},
        doc:{},
        id:'',
        //paging
        /*       currPage: 1,
               totalPage: 0,
               pageSize: 4,*/

        //action
        /*currDoc:0*/
    },
    //call on page loaded
    created() {
        this.id = sessionStorage.getItem('param_doc_detail_docId');
        sessionStorage.removeItem('param_doc_detail_docId');
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        this.update();
    },
    //other functions
    methods: {
        update(){
            request200('GET', '/in/studoc/get', {di:this.id}, x => {
                this.docs = x;
            });
        },
        score(){
            var ss = document.getElementById("schedule").value;
            request200('POST', '/in/studoc/score', {di:this.id,sc:ss}, x => {
                if(x==1){
                    alert("评分成功！");
                }
                else{
                    alert("评分失败！");
                }
            });
        },


    }
});
function reup(){
    var form = new FormData();
    var file1 = document.getElementById("fileid").files[0];
    form.append('di',app.id);
    form.append('file',file1);
    $.ajax({
        url:"/in/studoc/mod",
        type:"POST",
        data:form,
        dataType:"json",
        success:function (returndata) {
            if(returndata){
                alert("修改文件成功！");
            }
            else{
                alert("修改文件失败！");
            }
        }
    });
}