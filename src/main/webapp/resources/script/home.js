

$(document).ready(function() {
	loadDatePiker();
});

function loadDatePiker() {
	var options = {};
	var dt = new Date();
	options.startDate = new Date(dt.getTime() - (86400000 * 7));
	options.endDate = new Date(dt.getTime() + (86400000 * 7));
//	options.timePicker = true;
//	options.timePicker24Hour = true;
	$("#dateRange").daterangepicker(options);
	$('#dateRange').on('apply.daterangepicker', function(ev, picker) {
		loadData(picker.startDate.format('YYYY-MM-DD'), picker.endDate.format('YYYY-MM-DD')); // Thh:mm:ss
	});
}

function loadData(start, end) {
	var postData = {
		"start" : start + "T00:00:000",
		"end" : end + "T23:59:999"
	};
	$.ajax({
		method : "POST",
		url : "/api/processview",
		dataType: "json",
		contentType: "application/json",
		data: JSON.stringify(postData),
	}).done(function(data) {
		var chartData = {"x":[ "No of vehicle information", "No of unique vehicles", "No of suspended vehicles"], y:[data.total, data.unique, data.suspended]};
		$("#searchDetailsContainer").show();
		loadChart(chartData);
		loadTable(data);
	});
}

function loadTable(data){
	$("#vehicleTotal").html(data.total);
	$("#vehicleUnique").html(data.unique);
	$("#vehicleSuspended").html(data.suspended);
}

let viewChart, chartGraphicsComponent;
function loadChart(chartData) {
	Math.easeOutBounce = function (pos) {
	    if ((pos) < (1 / 2.75)) {
	        return (7.5625 * pos * pos);
	    }
	    if (pos < (2 / 2.75)) {
	        return (7.5625 * (pos -= (1.5 / 2.75)) * pos + 0.75);
	    }
	    if (pos < (2.5 / 2.75)) {
	        return (7.5625 * (pos -= (2.25 / 2.75)) * pos + 0.9375);
	    }
	    return (7.5625 * (pos -= (2.625 / 2.75)) * pos + 0.984375);
	};
	
	viewChart = Highcharts.chart('chart', {
		chart : {
			type : 'column',
	        options3d: {
	            enabled: true,
	            alpha: 0,
	            beta: -10,
	            depth: 70,
	            viewDistance: 25
	        }
		},
		title : {
			text : ''
		},
		xAxis : {
			categories: chartData.x,
			tickInterval : 1
		},
		yAxis : {
			allowDecimals : false,
			title : {
				text : 'Units'
			}
		},
		series : [ {
			name : 'Biz data stats',
			data : chartData.y,
			animation: {
	            duration: 2000,
	            // Uses Math.easeOutBounce
	            easing: 'easeOutBounce'
	        }
		} ],
		tooltip : {
			formatter : function() {
				return '<b>' + this.series.name + '</b><br/>' + this.point.y
						+ ' ' + this.x;
			}
		},
	    exporting: {
	        enabled: true
	    }
	});
	chartGraphicsComponent = {betaMin: -20, betaMax: 20, betaCur: -10, betaDirectionInc: true, betaIncVal:0.5, timeout: 50,};
}

let chartRotate = false, graphicsTimeout;
function toggleChartGraphics(){
	if(graphicsTimeout){
		clearInterval(graphicsTimeout);
	}
	if(chartRotate == true){
		chartRotate = false;
	}
	else {
		chartRotate = true;
		graphicsTimeout = setInterval(function(){
			if(viewChart && viewChart.options){
				if(chartGraphicsComponent.betaCur <= chartGraphicsComponent.betaMin){
					chartGraphicsComponent.betaDirectionInc = true;
				}
				else if(chartGraphicsComponent.betaCur >= chartGraphicsComponent.betaMax){
					chartGraphicsComponent.betaDirectionInc = false;
				}
				if(chartGraphicsComponent.betaDirectionInc){
					chartGraphicsComponent.betaCur = chartGraphicsComponent.betaCur + chartGraphicsComponent.betaIncVal;
				}
				else {
					chartGraphicsComponent.betaCur = chartGraphicsComponent.betaCur - chartGraphicsComponent.betaIncVal;
				}
				viewChart.options.chart.options3d["beta"] = parseFloat(chartGraphicsComponent.betaCur);
				viewChart.redraw(false);
			}
			else {
				clearInterval(graphicsTimeout);
				chartRotate = false;
			}
		}, chartGraphicsComponent.timeout);
	}
}