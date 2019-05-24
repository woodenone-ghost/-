<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@modalShow1 title=selected>

</@modalShow1>

<script>		
		// 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        <#if selected == "年龄分布-柱状图">
			option = {
			    title: {
			        text: '${commodity.name}-年龄分布-直方图'
			    },
			    legend: {
			        data: ['年龄分布（以2岁为间隔）'],
			        align: 'left'
			    },
			    toolbox: {
			        // y: 'bottom',
			        feature: {
			            dataView: {}
			        }
			    },
			    tooltip: {},
			    xAxis: {
			        data:['50岁以下','50岁','52岁','54岁','56岁','58岁','60岁','62岁','64岁','66岁','68岁','70岁','72岁','74岁','76岁','78岁','80岁','82岁','84岁','86岁','88岁','90岁','92岁','94岁','96岁','98岁'],
			        silent: false,
			        splitLine: {
			            show: false
			        }
			    },
			    yAxis: {
			    },
			    series: [{
			        name: '年龄分布',
			        type: 'bar',
			        data: [<#list ageNumber as a>${a},</#list>],
			        animationDelay: function (idx) {
			            return idx * 50;
			        }
			    }],
			    animationEasing: 'elasticOut',
			    animationDelayUpdate: function (idx) {
			        return idx * 250;
			    }
			};
        <#elseif selected == "上月销量-柱状图">
			option = {
			    title: {
			        text: '${commodity.businessName}-上月销量-直方图'
			    },
			    legend: {
			        data: ['上月销量'],
			        align: 'left'
			    },
			    toolbox: {
			        // y: 'bottom',
			        feature: {
			            dataView: {}
			        }
			    },
			    tooltip: {},
			    xAxis: {
			        data:[<#list lists as a>'${a.time?date}',</#list>],
			        splitLine: {
			            show: false
			        }
			    },
			    yAxis: {
			    },
			    series: [{
			        name: '产品销量',
			        type: 'bar',
			        data: [<#list lists as a>${a.monthSalesVolume},</#list>],
			        animationDelay: function (idx) {
			            return idx * 50;
			        }
			    }],
			    animationEasing: 'elasticOut',
			    animationDelayUpdate: function (idx) {
			        return idx * 250;
			    }
			};			
        <#elseif selected == "所有产品销量-柱状图">
			option = {
			    title: {
			        text: '${commodity.businessName}-所有产品销量-直方图'
			    },
			    legend: {
			        data: ['所有产品销量'],
			        align: 'left'
			    },
			    toolbox: {
			        // y: 'bottom',
			        feature: {
			            dataView: {}
			        }
			    },
			    tooltip: {},
			    xAxis: {
			        data:[<#list commoditys as a>'${a.name}',</#list>],
			        splitLine: {
			            show: false
			        }
			    },
			    yAxis: {
			    },
			    series: [{
			        name: '产品销量',
			        type: 'bar',
			        data: [<#list commoditys as a>${a.salesVolume},</#list>],
			        animationDelay: function (idx) {
			            return idx * 50;
			        }
			    }],
			    animationEasing: 'elasticOut',
			    animationDelayUpdate: function (idx) {
			        return idx * 250;
			    }
			};	
		<#elseif selected == "上月评价-柱状图">	
			option = {
			    title: {
			        text: '${commodity.name}-上月评价-柱状图'
			    },
			    legend: {
			        data: ['好评', '中评','差评'],
			        align: 'left'
			    },
			    toolbox: {
			        // y: 'bottom',
			        feature: {
			            magicType: {
			                type: ['stack', 'tiled']
			            },
			            dataView: {},
			            saveAsImage: {
			                pixelRatio: 2
			            }
			        }
			    },
			    tooltip: {},
			    xAxis: {
			        data:[<#list goodEvaluation as a>'${a.time?date}',</#list>],
			        silent: false,
			        splitLine: {
			            show: false
			        }
			    },
			    yAxis: {
			    },
			    series: [{
			        name: '好评',
			        type: 'bar',
			        data: [<#list goodEvaluation as a>${a.evaluationNumber},</#list>],
			        animationDelay: function (idx) {
			            return idx * 40;
			        }
			    }, {
			        name: '中评',
			        type: 'bar',
			        data: [<#list normalEvaluation as a>${a.evaluationNumber},</#list>],
			        animationDelay: function (idx) {
			            return idx * 50;
			        }
			    },{
			        name: '差评',
			        type: 'bar',
			        data: [<#list badEvaluation as a>${a.evaluationNumber},</#list>],
			        animationDelay: function (idx) {
			            return idx * 60;
			        }			    
			    }],
			    animationEasing: 'elasticOut',
			    animationDelayUpdate: function (idx) {
			        return idx * 5;
			    }
			};
        </#if>		    

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);        
</script>