



	 /** 异步分页 */
	 /** 全局变量 */
        var index = 1;
        var totalPage = 0;
        
        /** 分页窗体事件 */
        $(function () {
            /**取从Controller中传到JSP页面的总条数信息 给全局变量totalPage 赋值
             * 下为JSP页面中显示当前页和总页数
             */
            totalPage=$("#totalPage").val();
               //首页
               $('#firstpage').click(function () {
                   if (index != 1) {
                       index = 1;
                       paging();/** 点击分页异步刷新函数 */
                   } 
                   return false;
               });
               //尾页
               $('#lastpage').click(function () {
                   if (index != totalPage) {
                       index = totalPage;
                       paging();
                   }
                   return false;
               });
               //上一页
               $("#beforepage").click(function () {
                   if (index != 1) {
                       index = index - 1;
                       paging();
                   }
                   return false;
               });

               //下一页
               $("#nextpage").click(function () {
                   if (index < totalPage) {
                       index = index + 1;
                       paging();
                   }
                   return false;
               });

           });
		
        /** 点击分页 */
        function paging(){
            ajaxBase($("input[id=pagingUrl]").val(),"pageIndex="+index,"paging");
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
                   alert("ajax:error");
               }
          });
        };
        
        /** 根据method方法信号判断进行什么操作 
         * @param result:服务端响应的数据
         * @param method:方法信号，作用判断进行什么操作
         */
       function success(result,method){
           if(method=="paging"){
        	   pagingSuccess(result);
           }
       };
		
       /** 分页成功后操作 */
       function pagingSuccess(result){
     	  var rst=eval("("+result+")");
     	  tableBody(rst);
       };
       
       /** 页面中tbody里的内容 */
       function tableBody(rst){
	        var tbody="";
	        for(var i=0;i<rst.length;i++){
		        tbody=tbody+"<tr>"
		        		+"<td>"+rst[i].lab1+"</td>"
		        		+"<td>"+rst[i].lab2+"</td>"
		        		+"<td>"+rst[i].lab3+"</td>"
		        		+"<td>";
		        			if(rst[i].state==1){
		        				tbody=tbody+"已充值";
		        			}else if(rst[i].state==2){
		        				tbody=tbody+"充值成功但是要等待48小时";
		        			}else{
		        				tbody=tbody+"未充值";
		        			}
		        tbody=tbody+"</td><td>"
					        if(rst[i].rechargeTime==null){
			    				tbody=tbody+"未充值没有充值日期";
			    			}else{
			    				tbody=tbody+rst[i].rechargeTime;
			    			}
		        tbody=tbody+"</td></tr>";
	        }
	        $("#ver-minimalist #mainTbody").children().remove();
	        $("#ver-minimalist #mainTbody").append(tbody);
	        $("#index").val(index);
       };
