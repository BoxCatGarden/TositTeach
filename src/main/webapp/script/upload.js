layui.use(['layer', 'upload'], function () {
    var layer = layui.layer;
    layui.upload.render({
        elem: '#selectFilesDiv',
        url: '/in/engdoc/upload/',
        method: 'POST',
        accept: 'file',
        auto: false,
        bindAction: '#cfmUploadBtn',
        multiple: true,
        number: 50,
        drag: true,
        // choose(obj) {
        //     console.log(obj.pushFile());
        // },
        before: function () {
            layer.load();
        },
        done(res, i, u) {
            console.log(res);
            console.log(i);
        },
        allDone: function () {
            layer.closeAll('loading');
        }
    });
});

let paging = new Vue({
    //el: "#paging",
    data: {
        currPage: 1,
        totalPage: 0,
        pageSize: 10,
        total: 0,
        docs: []
    },
    created() {
        this.update();
    },
    methods: {
        update() {
            request200('GET', '/in/engdoc/ttl', null, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage;
                request200('GET', '/in/engdoc', {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize},
                    x => this.docs = x);
            });
        },
        prev() {
            if (this.currPage > 1) {
                --this.currPage;
                this.update();
            }
        },
        next() {
            if (this.currPage < this.totalPage) {
                ++this.currPage;
                this.update();
            }
        }
    }
});

layui.use('table', function () {
    var table = layui.table;
    table.render({
        elem: '#doctable',
        width:600,
        height: 'full-200',
        url: '/in/engdoc/layui',
        method: 'GET',
        where: {pn:null},
        contentType:'text/html',
        request:{
            pageName:'pg',
            limitName:'nm'
        },
        response: {
            statusCode: 0
        },
        page: true,
        limit: 20,
        limits: [10, 20, 30, 40, 50, 60],
        loading: true,
        text: {
            none: '还没有文件哦'
        },
        cols: [[ //表头
            {field: 'docId', title: '编号', width: '20%', sort: true, fixed: 'left'}
            , {
                field: 'url', title: '文件', width: '76%',
                templet(d) {
                    var n = d.url.substring(37);
                    return `<a href="${d.url}" target="_blank" title="${n}">${n}</a>`;
                }
            }
        ]]
    });
});