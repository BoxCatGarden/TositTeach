layui.use(['layer', 'upload'], function () {
    var layer = layui.layer;
    layui.upload.render({
        elem:'#selectFilesDiv',
        url:'/in/engdoc/upload/',
        method:'POST',
        accept:'file',
        auto:false,
        bindAction:'#cfmUploadBtn',
        multiple:true,
        number: 50,
        drag:true,
        before: function () {
            layer.load();
        },
        allDone: function () {
            layer.closeAll('loading');
        }
    });
});