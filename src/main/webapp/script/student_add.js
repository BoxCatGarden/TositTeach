let app = new Vue({
    el:"#app",
    data:{
        user:{}
    },
    create(){
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
    }
});

function add1(){
    var input_name = document.getElementById("sname").value;
    var input_sex = document.getElementById("sex").index;
    var input_school = document.getElementById("school").value;
    var input_id = document.getElementById("xue").value;
    var input_grade = document.getElementById("grade").index;
    if(input_grade==0){
        input_grade = "大一";
    }
    else if(input_grade==1){
        input_grade = "大二";
    }
    else if(input_grade==2){
        input_grade = "大三";
    }
    else{
        input_grade = "大四";
    }
    $.ajax({
        url:"/in/stu/add",
        type:"POST",
        data:{
            school:input_school,
            id:input_id,
            name:input_name,
            sex:input_sex,
            grade:input_grade
        },
        dataType:"json"
        success:function (returndata) {
            if(returndata!=null){
                alert("添加成功！");
            }
            else{
                alert("添加失败！");
            }
        }
    });
}
function pi() {
    var file1 = document.getElementById("fileid").files[0];
    var form  = new FormData();
    form.append("file",file1);
    $.ajax({
        url:'/import',
        type:"POST",
        data:form,
        dataType:"json",
        success:function(returndata){
            if(returndata==1){
                alert("导入成功！");
            }
            else{
                alert("导入失败！");
            }
        }
    });
}