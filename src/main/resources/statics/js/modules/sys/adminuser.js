$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/adminuser/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '用户名', name: 'userName', index: 'user_name', width: 80 }, 			
			{ label: '密码', name: 'passWord', index: 'pass_word', width: 80 }, 			
			{ label: '级别', name: 'level', index: 'level', width: 80 }, 			
			{ label: '微信id', name: 'wechatId', index: 'wechat_id', width: 80 }, 			
			{ label: '用户id 默认0', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '默认为0为false,1为true', name: 'bossModel', index: 'boss_model', width: 80 }, 			
			{ label: '上一次登录时间', name: 'preLoginTime', index: 'pre_login_time', width: 80 }, 			
			{ label: '上一次登录IP', name: 'preLoginIp', index: 'pre_login_ip', width: 80 }, 			
			{ label: '最后登录时间', name: 'lastLoginTime', index: 'last_login_time', width: 80 }, 			
			{ label: '最后一次登录IP', name: 'lastLoginIp', index: 'last_login_ip', width: 80 }, 			
			{ label: '验证码', name: 'verificationCode', index: 'verification_code', width: 80 }, 			
			{ label: '备注', name: 'memo', index: 'memo', width: 80 }			
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
		showList: true,
		title: null,
		adminUser: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.adminUser = {};
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
                var url = vm.adminUser.id == null ? "app/adminuser/save" : "app/adminuser/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.adminUser),
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
                        url: baseURL + "app/adminuser/delete",
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
			$.get(baseURL + "app/adminuser/info/"+id, function(r){
                vm.adminUser = r.adminUser;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});