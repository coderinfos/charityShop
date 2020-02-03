$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'admin/donate/soldList',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '捐赠人id', name: 'userId', index: 'user_id', width: 80 },
			{ label: '捐赠至分店id', name: 'shopId', index: 'shop_id', width: 80 },
			{ label: '名称', name: 'donateName', index: 'donate_name', width: 80 },
			{ label: '1物品,2书籍', name: 'donateType', index: 'donate_type', width: 80 },
			{ label: '图片', name: 'donateImage', index: 'donate_image', width: 80 },
			{ label: '价格', name: 'donatePrice', index: 'donate_price', width: 80 },
			{ label: '提交时间', name: 'donateSubmitTime', index: 'donate_submit_time', width: 80 },
			{ label: '登记时间', name: 'donateRegisterTime', index: 'donate_register_time', width: 80 },
			{ label: '售出时间', name: 'donateSaleTime', index: 'donate_sale_time', width: 80 },
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
		donate: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.donate = {};
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
                var url = vm.donate.id == null ? "admin/donate/save" : "admin/donate/update";
                vm.donate.operator = localStorage.getItem("admin");
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.donate),
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
                        url: baseURL + "admin/donate/delete",
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
			$.get(baseURL + "admin/donate/info/"+id, function(r){
                vm.donate = r.donate;
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