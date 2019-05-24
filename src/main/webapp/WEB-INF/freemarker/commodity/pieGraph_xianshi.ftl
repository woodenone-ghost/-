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
        <#if selected == "男女比例-饼图">
			option = {
			    title: {
			        text: '${commodity.name}-男女比例分布图',
			        left: 'center',
			        top: 20,
			        textStyle: {
			            color: '#ccc'
			        }
			    },
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data:['女性比例','男性比例']
			    },
			    series: [
			        {
			            name:'性别比例',
			            type:'pie',
			            radius: ['50%', '70%'],
			            avoidLabelOverlap: false,
			            label: {
			                normal: {
			                    show: false,
			                    position: 'center'
			                },
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '30',
			                        fontWeight: 'bold'
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            data:[
			                {value:${femaleNumber}, name:'女性比例'},
			                {value:${maleNumber}, name:'男性比例'}
			            ]
			        }
			    ]
			};        
        <#elseif selected == "年龄分布-饼图">
			option = {
			    title: {
			        text: '${commodity.name}-年龄比例分布图',
			        left: 'center',
			        top: 20,
			        textStyle: {
			            color: '#ccc'
			        }
			    },
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data:['50岁以下','50-59岁','60-69岁','70-79岁','80-89岁','90-99岁']
			    },
			    series: [
			        {
			            name:'年龄比例',
			            type:'pie',
			            radius: ['50%', '70%'],
			            avoidLabelOverlap: false,
			            label: {
			                normal: {
			                    show: false,
			                    position: 'center'
			                },
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '30',
			                        fontWeight: 'bold'
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            data:[
			                {value:${age_49}, name:'50岁以下'},
			                {value:${age_50_59}, name:'50-59岁'},
			                {value:${age_60_69}, name:'60-69岁'},
			                {value:${age_70_79}, name:'70-79岁'},
			                {value:${age_80_89}, name:'80-89岁'},
			                {value:${age_90_99}, name:'90-99岁'}
			            ]
			        }
			    ]
			};          
        <#elseif selected == "评价分布-饼图">
			option = {
			    title: {
			        text: '${commodity.name}-评价比例分布图',
			        left: 'center',
			        top: 20,
			        textStyle: {
			            color: '#ccc'
			        }
			    },
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data:['好评比例','中评比例','差评比例']
			    },
			    series: [
			        {
			            name:'评价比例',
			            type:'pie',
			            radius: ['50%', '70%'],
			            avoidLabelOverlap: false,
			            label: {
			                normal: {
			                    show: false,
			                    position: 'center'
			                },
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '30',
			                        fontWeight: 'bold'
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            data:[
			                {value:${goodNumber}, name:'好评比例'},
			                {value:${normalNumber}, name:'中评比例'},
			                {value:${badNumber}, name:'差评比例'}
			            ]
			        }
			    ]
			};          
        </#if>		    

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);        
</script>