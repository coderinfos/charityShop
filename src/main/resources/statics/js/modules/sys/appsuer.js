$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/user/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户名', name: 'userName', index: 'user_name', width: 80 },
			{ label: '用户密码', name: 'passWord', index: 'pass_word', width: 80 },
			{ label: '头像', name: 'face', index: 'face', width: 80 },
			{ label: '昵称', name: 'nickName', index: 'nick_name', width: 80 },
			{ label: '真实姓名', name: 'realName', index: 'real_name', width: 80 },
			{ label: '性别', name: 'sex', index: 'sex', width: 80 ,formatter: function(value, options, row){
                return value === 1 ?
                    '<span >男</span>' :
                    '<span >女</span>';
            }},
            { label: '年龄', name: 'age', index: 'age', width: 50},
            { label: '电话号', name: 'mobilePhone', index: 'mobile_phone', width: 80 },
            { label: '签名', name: 'signatureLine', index: 'signature_line', width: 80 },
            { label: '微信id', name: 'wechatId', index: 'wechat_id', width: 80 },
            { label: '注册时间', name: 'regTime', index: 'reg_time', width: 50},
            { label: '上一次登录时间', name: 'preLoginTime', index: 'pre_login_time', width: 50},
            { label: '上一次登录地址ip', name: 'preLoginIp', index: 'pre_login_ip', width: 50},
            { label: '最后一次登录时间', name: 'lastLoginTime', index: 'last_login_time', width: 50},
            { label: '最后一次登录地址ip', name: 'lastLoginIp', index: 'last_login_ip', width: 50},
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            mobilePhoneOpen: null
        },
		showList: true,
		title: null,
		user: {}

	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.user = {};

		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)

		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.user.id == null ? "app/user/save" : "app/user/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.user),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "app/user/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "app/user/info/"+id, function(r){
                vm.user = r.user;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'mobilePhoneOpen': vm.q.mobilePhoneOpen},
                page:page
            }).trigger("reloadGrid");
		}
	}
});