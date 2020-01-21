$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/boss/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '捐赠人id', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '报名分店id', name: 'shopId', index: 'shop_id', width: 80 }, 			
			{ label: '值班日期', name: 'dutyDate', index: 'duty_date', width: 80 }, 			
			{ label: '1早班2晚班', name: 'dutyType', index: 'duty_type', width: 80 }, 			
			{ label: '0为false,1为true审核通过(默认自动', name: 'dutyStatus', index: 'duty_status', width: 80 }, 			
			{ label: '提交时间', name: 'dutySubmitTime', index: 'duty_submit_time', width: 80 },
			{ label: '操作员', name: 'operator', index: 'operator', width: 80 },
			{ label: '操作时间', name: 'operationTime', index: 'operation_time', width: 80 },
			{ label: '操作IP', name: 'operatorIp', index: 'operator_ip', width: 80 },
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
        q:{
            userId: null
        },
		showList: true,
		title: null,
		boss: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.boss = {};
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
                var url = vm.boss.id == null ? "app/boss/save" : "app/boss/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.boss),
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
                        url: baseURL + "app/boss/delete",
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
			$.get(baseURL + "app/boss/info/"+id, function(r){
                vm.boss = r.boss;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'userId': vm.q.userId},
                page:page
            }).trigger("reloadGrid");
		}
	}
});