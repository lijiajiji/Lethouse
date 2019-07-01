
    $(function () {
        $('#dg').datagrid({
            title:">>>>未审核管理",
            url:'/admin/getHouseByNoPass',
            fitColumns:true,
            pagination:true,
            pageList:[5,10,15,20],
            toolbar:"#ToolBar",
            pageSize:5,
            columns:[[
                {field:'ck',checkbox:true},
                {field:'id',title:'编号',width:50,align:"center"},
                {field:'title',title:'标题',width:50,align:"center"},
                {field:'price',title:'价格',width:50,align:"center"},
                {field:'floorage',title:'面积',width:50,align:"center"},
                {field:'dname',title:'区域',width:50,align:"center"},
                {field:'sname',title:'街道',width:50,align:"center"},
                {field:'tname',title:'类型',width:50,align:"center"},
                {field:'ispass',title:'状态',width:50,align:"center",
                    formatter:function (value,row,index) {
                        return"未审核";
                    }},
                {field:'opt',title:'操作',width:50,aligb:"center",
                    formatter:function (value,row,index) {
                    return "<a href='javascript:checkPass("+row.id+")'>审核</a>";
                    }}
            ]]
        });
    });

//实现搜索
    function  searchUsers() {
         //datagrid控件重新加载的方法
        $("#dg").datagrid("load",{//跟查询条件
            "telephone":$("#tel").val(),
            "starAge":$("#startAge").val(),
            "endAge":$("#endAge").val()
        });


        function checkPass(id) {
            //发送异步请求
            $.post("passHouse",{"id":id},function (data) {
                if (data.result>0){
                  $.messager.alert('提示框','恭喜审核通过！')
                    $("#dg").datagrid("reload");
                }else {
                    $.messager.alert('提示框','审核未通过！')
                }

            },"json")

        }
    }


