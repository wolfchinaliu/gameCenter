

		//-------------------------------------------------函数区域

		function check() {
			var excel_file = $("#excel_file").val();
			if (excel_file == "" || excel_file.length == 0) {
				alert("请选择文件路径！");
				return false;
			} else {
				return true;
			}
		}
	
		$(document).ready(function() {
			var msg = "";
			if ($("#importMsg").text() != null) {
				msg = $("#importMsg").text();
			}
			if (msg != "") {
				alert(msg);
			}
		});
	
		function fluxCheck() {
			var value = $("input[name=flux]").val()
			
			/**/
			var reg = /^\d+$/;
			if ($.trim(value) != "") {//去前后空格判断
				var re = value.match(reg);
				if (re == null) {
					alert("请输入数字！")
				} else {
					return true;
				}
			} else {
				alert("请输入流量值！");
			}
			
			return false;
		}
        
        /** 窗体事件 */
        $(function () {
        	
        });
        
       //-------------------------------------------------ajax区域
		
        /** 点击添加 */
        function insertClient(){
        	if(fluxCheck()){
        		alert($("input[id=insertClientUrl]").val());
        		ajaxBase($("input[id=insertClientUrl]").val(),$("#fluxForm").serialize(),"insertClient");
        	}else{
        		return false;
        	}
            
        }
        
        /** ajax函数工具类
         * @param url:地址
         * @param data:请求参数
         * @param method:方法信号，作用判断进行什么操作
         */
        function ajaxBase(url,data,method){
          $.ajax({
              type:'post',
               url:''+url+'',
               cache:false,
               data:data,
               dataType:'text',
               success:function(result){
                   /** 调用请求成功后的操作函数 */
                   success(result,method);
               },
               error:function(){
                   alert("ajax-error：系统异常！");
               }
          });
        };
        
        /** 根据method方法信号判断进行什么操作 
         * @param result:服务端响应的数据
         * @param method:方法信号，作用判断进行什么操作
         */
       function success(result,method){
           if(method=="insertClient"){
        	   insertClientSuccess(result);
           }
       };
		
       /** 添加成功后操作 */
       function insertClientSuccess(result){
    	   alert(result);
       };
       
       /** 页面中tbody里的内容 */
       function tableBody(result){
    	   
       };
