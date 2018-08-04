let app = new Vue({
    el:"#app",
    data:{
        user:{}
    },
    create(){
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        this.update();
    },
    method:{

        update(){

        },

    }
});
function uploadfile(){
    var filename = document.getElementById("dname").value;
    var disp = document.getElementById("ddisp").value;
    var file1 = document.getElementById("dfile").files[0];
    var form = new FormData();
    form.append("dn",filename);
    form.append("dp",disp)
    form.append("file",file1);
    $.ajax({
        url:'/in/studoc/add',
        type:"POST",
        data:form,
        dataType:"json",
        success:function(returndata){
            if(returndata==1){
                alert("添加文件成功！");
            }
            else{
                alert("添加文件失败！");
            }
        }
    });
}