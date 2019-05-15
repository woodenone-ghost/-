<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@modalShow title=selected>

</@modalShow>

<script>		
		// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '${commodity.name}'
            },
		    legend: {
		    	<#if selected == "销量-时间-折线图">
		    		data:['销量']
		    	<#elseif selected == "好评数-时间-折线图">
		    		data:['销量','好评数']
		    	<#elseif selected == "差评数-时间-折线图">
		    		data:['销量','差评数']
		    	</#if>
		    },
		    tooltip: {trigger: 'axis'},
	        toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    xAxis: {
		        type: 'category',
		        data: [<#list list1 as a>'${a.time?date}',</#list>]
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [
		    {
		    	type: 'line',
		    	name:'销量',
		        data: [<#list list1 as a>${a.salesVolume},</#list>]		        
		    }
		    <#if selected == "好评数-时间-折线图">
			    ,{
			    	type: 'line',
			    	name:'好评数',
			        data: [<#list list1 as a>${a.evaluationNumber},</#list>]		        
			    }		    
		    <#elseif selected == "差评数-时间-折线图">
			    ,{
			    	type: 'line',
			    	name:'差评数',
			        data: [<#list list1 as a>${a.evaluationNumber},</#list>]		        
			    }			    
		    </#if>		    
		    ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);        
</script>